/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.extra;

/**
 *
 * @author CSLAB313-1740
 */
public class City {

    private final double x, y;

    public City(double max) {
        this.x = Math.random() * max;
        this.y = Math.random() * max;
    }

    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(City city) {
        double realX = this.x - city.getX();
        double realY = this.y - city.getY();
        return Math.sqrt(realX * realX + realY * realY);
    }

    @Override
    public String toString() {
        return "<x: " + x + "| y: " + y + ">";
    }
}
