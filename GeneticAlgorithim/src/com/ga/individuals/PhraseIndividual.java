/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.individuals;

import com.ga.extra.CharGenerator;
import com.ga.solutions.PhraseSolution;

/**
 *
 * @author Jonathan Elue
 */
public class PhraseIndividual extends Individual{

    public static final double MUTATION_RATE = .01;
    protected char[] phrase;
    private static final int START_CHAR = 31, END_CHAR = 127;
    
    public PhraseIndividual(PhraseSolution s) {
        super(s);
        phrase = new char[s.getMaxFitness()];
        for(int i = 0; i < phrase.length; i++)
            phrase[i] = CharGenerator.generate(START_CHAR, END_CHAR, true, '\n');
    }

    private int compare(char[] c1, char[] c2) {
        int counter = 0;
        for(int i = 0; i < c1.length; i++)
            if(c1[i] == c2[i])
                counter++;
        return counter;
    }
    
    @Override
    public void determineFitness() {
        fitness = compare((char[]) solution.getSolution(), phrase);
    }

    @Override
    public Individual createNew() {
        return new PhraseIndividual((PhraseSolution)solution);
    }

    @Override
    public void mutate() {
        for (int i = 0; i < phrase.length; i++)
            if (Math.random() < MUTATION_RATE)
                phrase[i] = CharGenerator.generate(START_CHAR, END_CHAR, true, '\n');
    }

    @Override
    public void crossOver(Individual independent) {
        System.arraycopy(((PhraseIndividual) independent).phrase, 0, phrase, 0, phrase.length);
    }
    
    private String convertToString(char[] chars) {
        String str = "";
        for(char c : chars)
            str += c;
        return str;
    }
    
    @Override
    public String toString() {  
        return super.toString() + "\nPhrase: " + convertToString(phrase);
    }
    
}
