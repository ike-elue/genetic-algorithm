/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.individuals;

import com.ga.solutions.Solution;

/**
 *
 * @author CSLAB313-1740
 */
public abstract class Individual implements Runnable {

    protected double fitness;
    protected Solution solution;
    private boolean fitEnough;
    private int generationOfFittest;

    public Individual(Solution s) {
        fitness = 0;
        solution = s;
        fitEnough = false;
        generationOfFittest = -1;
    }

    public abstract void determineFitness();

    public abstract Individual createNew();

    public abstract void mutate();

    public abstract void crossOver(Individual independent);

    public boolean hasSolution() {
        if(solution == null)
            return false;
        return fitness == solution.getMaxFitness();
    }

    @Override
    public void run() {
        fitEnough = false;
        generationOfFittest = -1;
        determineFitness();
    }

    public boolean isFitEnough() {
        return fitEnough;
    }

    public double getFitness() {
        return fitness;
    }

    public int getGeneration() {
        return generationOfFittest;
    }

    public Solution getSolution() {
        return solution;
    }
    
    public void setFitEnough(boolean fitEnough) {
        this.fitEnough = fitEnough;
    }

    public void setGenerationOfFittest(int generation) {
        generationOfFittest = generation;
    }

    @Override
    public String toString() {
        return "Fitness: " + fitness + "\nGeneration: " + generationOfFittest;
    }
}
