/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.individuals;

import com.ga.extra.City;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author CSLAB313-1740
 */
public class TourIndividual extends Individual {

    protected final List<City> cities;
    protected Integer[] sequence;
    public final static double MUTATION_RATE = .01;

    public TourIndividual(List<City> cities) {
        super(null);
        this.cities = new ArrayList<>();
        this.cities.addAll(cities);
        sequence = new Integer[this.cities.size()];
        int random;
        for (int i = 0; i < sequence.length; i++) {
            random = randomize(sequence.length);
            if (Arrays.asList(sequence).contains(random)) {
                i--;
            } else {
                sequence[i] = random;
            }
        }
    }

    public final int randomize(int length) {
        return (int) (Math.random() * length);
    }

    public double getDistance() {
        if (cities.isEmpty()) {
            return 0;
        }
        double distance = 0;
        for (int i = 0; i < sequence.length - 1; i++) {
            distance += cities.get(sequence[i]).distanceTo(cities.get(sequence[i + 1]));
        }
        distance += cities.get(sequence[sequence.length - 1]).distanceTo(cities.get(sequence[0]));
        return distance;
    }

    @Override
    public void determineFitness() {
        if (getDistance() == 0)
            fitness = 0;
        else
            fitness = (1 / getDistance()) * 100;
    }

    @Override
    public Individual createNew() {
        return new TourIndividual(cities);
    }

    @Override
    public void mutate() {
        if (Math.random() < MUTATION_RATE) {
            int tempIndex = (int) (Math.random() * sequence.length);
            int tempIndex2 = 0;
            do {
                tempIndex2 = (int) (Math.random() * sequence.length);
            } while (tempIndex == tempIndex2);
            int temp = sequence[tempIndex];
            sequence[tempIndex] = sequence[tempIndex2];
            sequence[tempIndex2] = temp;
        }
    }

    @Override
    public void crossOver(Individual independent) {
        System.arraycopy(((TourIndividual) independent).sequence, 0, sequence, 0, sequence.length);
    }

    public Integer[] getSequence() {
        return sequence;
    }

    public Integer getSequence(int index) {
        return sequence[index];
    }

    public City getCity(int index) {
        return cities.get(index);
    }

    @Override
    public String toString() {
        String route = "";
        for (Integer value : sequence) {
            route += "\n" + cities.get(value).toString() + " to";
        }
        route = route.substring(0, route.length() - 2);
        route += "back to \n" + cities.get(sequence[0]).toString();
        return super.toString() + "\nSequence: " + Arrays.toString(sequence) + "\nGo From: " + route;
    }
}
