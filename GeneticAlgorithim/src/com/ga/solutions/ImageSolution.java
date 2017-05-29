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
public class ImageSolution implements Solution {

    private final int[][] solution;

    public ImageSolution(int[][] black_white_pixels) {
        solution = black_white_pixels;
    }

    @Override
    public int[][] getSolution() {
        return solution;
    }
    
    @Override
    public int getMaxFitness() {
        return solution.length * solution[0].length;
    }

}
