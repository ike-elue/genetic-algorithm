/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.individuals;

import com.ga.solutions.ImageSolution;

/**
 *
 * @author CSLAB313-1740
 */
public class ImageIndividual extends Individual {

    protected int[][] pixels;
    public static final double MUTATION_RATE = .01;

    public ImageIndividual(ImageSolution s) {
        super(s);
        pixels = new int[s.getSolution().length][s.getSolution()[0].length];
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                pixels[row][col] = (int) (Math.random() + .5);
            }
        }
    }

    private int compare(int[][] i1, int[][] i2) {
        int counter = 0;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                if (i1[row][col] == i2[row][col]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public void determineFitness() {
        fitness = compare((int[][]) solution.getSolution(), pixels);
    }

    @Override
    public Individual createNew() {
        return new ImageIndividual((ImageSolution) solution);
    }

    @Override
    public void mutate() {
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                if (Math.random() < MUTATION_RATE) {
                    if (pixels[row][col] == 0) {
                        pixels[row][col] = 1;
                    } else {
                        pixels[row][col] = 0;
                    }
                }
            }
        }
    }

    @Override
    public void crossOver(Individual independent) {
        for (int row = 0; row < pixels.length; row++) {
            System.arraycopy(((ImageIndividual) independent).pixels[row], 0, pixels[row], 0, pixels[row].length);
        }
    }

    public int[][] getPixels() {
        return pixels;
    }
}
