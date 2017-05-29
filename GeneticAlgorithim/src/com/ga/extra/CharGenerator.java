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
public class CharGenerator {
    public static char generate(int start, int end, boolean changeStart, char changeTo) {
        int c = (start + (int)(Math.random() * (end - start)));
        if(c == start && changeStart)
            return changeTo;
        return (char) c;
    }
}
