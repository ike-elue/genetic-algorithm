/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.individuals;

import com.ga.solutions.BinarySolution;
import java.util.Arrays;

/**
 *
 * @author CSLAB313-1740
 */
public class BinaryIndividual extends Individual {

    protected byte[] bytes;
    public final static double MUTATION_RATE = .01;

    public BinaryIndividual(BinarySolution s) {
        super(s);
        bytes = new byte[s.getMaxFitness()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Math.round(Math.random());
        }
    }

    public double compare(String sol, byte[] bytes) {
        int counter = 0;
        for (int i = 0; i < sol.length(); i++) {
            if (Byte.parseByte(sol.substring(i, i + 1)) == bytes[i]) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void determineFitness() {
        fitness = compare((String) solution.getSolution(), bytes);
    }

    @Override
    public Individual createNew() {
        return new BinaryIndividual((BinarySolution)solution);
    }

    @Override
    public void mutate() {
        for (int i = 0; i < bytes.length; i++) {
            if (Math.random() < MUTATION_RATE) {
                if (bytes[i] == 0) {
                    bytes[i] = 1;
                } else {
                    bytes[i] = 0;
                }
            }
        }
    }

    @Override
    public void crossOver(Individual independent) {
        System.arraycopy(((BinaryIndividual) independent).bytes, 0, bytes, 0, bytes.length);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + Arrays.toString(bytes) + " = Individual's Bytes\n" + Arrays.toString(((String) solution.getSolution()).split("")) + " = Solution";
    }
}
