/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.solutions;

/**
 *
 * @author Jonathan Elue
 */
public class PhraseSolution implements Solution{

    private final char[] solution;
    
    public PhraseSolution(String solution) {
        this.solution = solution.toCharArray();
    }
    
    @Override
    public char[] getSolution() {
        return solution;
    }

    @Override
    public int getMaxFitness() {
        return solution.length;
    }
    
}
