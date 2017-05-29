/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.extra;

import com.ga.individuals.TourIndividual;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author CSLAB313-1740
 */
public class TourUI extends JFrame {

    public TourUI(int width, int height, double scale, TourIndividual gene) {
        super("Tour, Fitness: " + gene.getFitness());
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new Tour(gene, scale));
        setVisible(true);
    }

    private class Tour extends JPanel {

        private final TourIndividual gene;
        private final double scale;

        public Tour(TourIndividual gene, double scale) {
            this.gene = gene;
            this.scale = scale;
        }

        private void drawTour(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.scale(scale, scale);

            for (int i = 0; i < gene.getSequence().length - 1; i++) {
                g2d.drawLine((int) gene.getCity(gene.getSequence(i)).getX(),
                        (int) gene.getCity(gene.getSequence(i)).getY(),
                        (int) gene.getCity(gene.getSequence(i + 1)).getX(),
                        (int) gene.getCity(gene.getSequence(i + 1)).getY());
            }
            g2d.drawLine((int) gene.getCity(gene.getSequence(gene.getSequence().length - 1)).getX(),
                    (int) gene.getCity(gene.getSequence(gene.getSequence().length - 1)).getY(),
                    (int) gene.getCity(gene.getSequence(0)).getX(),
                    (int) gene.getCity(gene.getSequence(0)).getY());
            g2d.setColor(Color.RED);
            int size = 2;
            g2d.fillRect((int) gene.getCity(gene.getSequence(0)).getX() - size,
                    (int) gene.getCity(gene.getSequence(0)).getY() - size,
                    size * 2,
                    size * 2);
            g2d.setColor(Color.BLACK);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawTour(g);
        }
    }
}
