/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.solutions;

/**
 *
 * @author CSLAB313-1740
 */
public class BinarySolution implements Solution {

    private final String solution;

    public BinarySolution(String str) {
        solution = str;
    }

    @Override
    public String getSolution() {
        return solution;
    }
    
    @Override
    public int getMaxFitness() {
        return solution.length();
    }

}
