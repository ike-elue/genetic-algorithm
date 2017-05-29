/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.extra;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author CSLAB313-1740
 */
public class Image {

    private final BufferedImage image;

    public Image(BufferedImage image) {
        this.image = image;
    }

    public Image(int[][] pixels) {
        image = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < pixels.length - 1; row++) {
            for (int col = 0; col < pixels[0].length - 1; col++) {
                if (pixels[row][col] == 0) {
                    image.setRGB(row, col, Color.BLACK.getRGB());
                } else {
                    image.setRGB(row, col, Color.WHITE.getRGB());
                }
            }
        }

    }

    public int[][] getEdges(double edgeDist) {
        int[][] pixels = new int[image.getWidth()][image.getHeight()];
        int[][] pixels2 = new int[image.getWidth()][image.getHeight()];

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                pixels[row][col] = image.getRGB(row, col);
            }
        }

        int leftPixel;
        int rightPixel;
        int bottomPixel;

        Color leftColor;
        Color rightColor;
        Color bottomColor;

        for (int row = 0; row < pixels.length - 1; row++) {
            for (int col = 0; col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                bottomPixel = pixels[row + 1][col];

                leftColor = new Color(leftPixel);
                rightColor = new Color(rightPixel);
                bottomColor = new Color(bottomPixel);

                if (colorDistance(leftColor, rightColor)
                        > edgeDist || colorDistance(leftColor, bottomColor)
                        > edgeDist) {
                    pixels2[row][col] = 0;
                } else {
                    pixels2[row][col] = 1;
                }
            }
        }
        return pixels2;
    }

    public double colorDistance(Color color1, Color color2) {
        double redDistance = color1.getRed() - color2.getRed();
        double greenDistance = color1.getGreen() - color2.getGreen();
        double blueDistance = color1.getBlue() - color2.getBlue();
        double distance = Math.sqrt(redDistance * redDistance
                + greenDistance * greenDistance
                + blueDistance * blueDistance);
        return distance;
    }

    public BufferedImage getBufferedImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
