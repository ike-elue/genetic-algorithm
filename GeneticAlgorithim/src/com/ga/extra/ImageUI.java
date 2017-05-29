/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.extra;

import com.ga.individuals.ImageIndividual;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author CSLAB313-1740
 */
public class ImageUI extends JFrame {

    private final JPanel panel;

    public ImageUI(int width, int height, int scale, Image img, ImageIndividual gene) {
        super("Edged Image, Fitness: " + gene.getFitness());
        setSize(width * scale, height * scale);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.scale(scale, scale);
                g2d.drawImage(img.getBufferedImage(), 0, 0, img.getWidth(), img.getHeight(), null);
            }
        };
        add(panel);
        setVisible(true);
    }
}
