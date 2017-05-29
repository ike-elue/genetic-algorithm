/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.main;

import com.ga.individuals.Individual;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author CSLAB313-1740
 */
public class Population {
    public final int length;
    private final Individual[] genes;
    private final ExecutorService pool;
    private final List<Future> futures;
    private boolean solutionFound;
    private Individual fittest;
    
    public Population(int popCount, Individual geneType, int threadCount) {
        pool = Executors.newFixedThreadPool(threadCount);
        futures = new ArrayList<>();
        genes = new Individual[popCount];
        length = popCount;
        solutionFound = false;
        fittest = null;
        for(int i = 0; i < length; i++) {
            setGene(i, geneType.createNew());
        }
    }
    
    public Individual execute(int maxGenerations, int everyNth, int selectionSplit, boolean printBestIndividual) {
        if(solutionFound)
            return fittest;
        
        int pointer;
        int counter = 0;
        int split = length/selectionSplit;
        Individual independent;
        
        if(genes[0].getSolution() != null)
            System.out.println("Max Fitness: " + genes[0].getSolution().getMaxFitness());
        else
            System.out.println("Max Fitness: null");
        System.out.println("---------------------------------------------------------------------");
        while(counter <= maxGenerations) {
            independent = null;
            pointer = 0;
            
            update();
            
            // Selection
            for(int i = 0; i < split; i++) {
                if(independent == null)
                    independent = getFittest(true);
                else
                    getFittest(true).setGenerationOfFittest(counter);
            }
            
            if(independent != null)
                independent.setGenerationOfFittest(counter);
            else
                break;
            
            // Crossover
            while(pointer < length) {
                if(genes[pointer].isFitEnough())
                    pointer++;
                else
                    crossOver(genes[pointer++], independent);
            }
            
            // Mutate
            for(int i = 0; i < length; i++)
                if(!genes[i].isFitEnough())
                    genes[i].mutate();
            
            solutionFound = independent.hasSolution();
            if(solutionFound)
                break;
            
            if(counter % everyNth == 0) {
                if(printBestIndividual)
                    System.out.println("Best Individual:\n" + independent + "\n");
                else
                    System.out.println("Generation: " + counter + ", Fitness: " + independent.getFitness());
            }
                
            
            
            counter++;
       }
       System.out.println("---------------------------------------------------------------------");
       if(solutionFound)
          System.out.println("Solution has been found");
       
       fittest = getFittest(false);
       
       System.out.println("Best Individual:\n" + fittest);
       
       if(!solutionFound) {
            if(fittest.getSolution() != null)
                System.out.println("Max Fitness: " + fittest.getSolution().getMaxFitness());
            else
                System.out.println("Max Fitness: null");
       }
       
       return fittest;
    }
    
    public void update() {
        futures.clear();
        for(Individual gene : genes) {
            Future f = pool.submit(gene);
            futures.add(f);
        }
        futures.stream().forEach((f) -> {
            try {
                f.get();
            } catch(InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
    
    public void crossOver(Individual dependent, Individual independent) {
        if(dependent == null || independent == null)
            return;
        dependent.crossOver(independent);
    }
  
    public Individual getFittest() {
        return fittest;
    }
    
    public final Individual getFittest(boolean setFitestToFitEnoguh) {
        Individual fittestGene = null;
        for(int i = 0; i < length; i++) {
            if(!genes[i].isFitEnough() || !setFitestToFitEnoguh) {
                fittestGene = genes[i]; 
                break;
            }
        }
        if(fittestGene == null)
            return null;
        for(Individual compare : genes) {
            if(fittestGene.getFitness() < compare.getFitness() && (!compare.isFitEnough() || !setFitestToFitEnoguh))
                fittestGene = compare;
        }
        fittestGene.setFitEnough(setFitestToFitEnoguh);
        return fittestGene;
    }
    
    public final void setGene(int index, Individual gene) {
        genes[index] = gene;
    }
    
    public void dispose() {
        try {
            System.out.println("\nAttempting to shutdown executor...");
            pool.shutdown();
            pool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Tasks interrupted");
            e.printStackTrace();
        } finally {
            if (!pool.isTerminated()) {
                System.err.println("Cancel non-finished tasks");
            }
            pool.shutdownNow();
            System.out.println("Executor shutdown finished!");
        }
    }
}
