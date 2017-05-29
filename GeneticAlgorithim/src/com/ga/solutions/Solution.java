/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.solutions;

/**
 *
 * @author CSLAB313-1740
 * @param <V> represents the solution to the problem
 */
public interface Solution<V> {

    public V getSolution();
    public int getMaxFitness();
}
