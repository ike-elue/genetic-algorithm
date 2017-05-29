/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ga.main;

import com.ga.individuals.PhraseIndividual;
import com.ga.solutions.PhraseSolution;
import java.io.IOException;

/**
 *
 * @author CSLAB313-1740
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int maxGenerations = 10000;
        int everyNth = 100;
        int threadCount = 100;
        int popSize = 1000;
        int selectionSplit = 100;
        boolean printBestIndividual = true;

//        String solution = "11110001010101001001001000100001000100100000001010100000100101111111000101010100100100100010000100010010000000101010000010010111";
//        Population pop = new Population(popSize, new BinaryIndividual(new BinarySolution(solution)), threadCount);
//        pop.execute(maxGenerations, everyNth, selectionSplit, printBestIndividual);
        
//        ArrayList<City> cities = new ArrayList<>();
//        cities.add(new City(20,20));
//        cities.add(new City(20,40));
//        cities.add(new City(60,20));
//        cities.add(new City(100,40));
//        cities.add(new City(160,20));
//        cities.add(new City(200,40));
//        cities.add(new City(180,60));
//        cities.add(new City(120,80));
//        Population pop = new Population(popSize, new TourIndividual(cities), threadCount);
//        int width = 640;
//        int height = 480;
//        int scale = 2;
//        Individual individual = pop.execute(maxGenerations, everyNth, selectionSplit, printBestIndividual);
//        TourUI ui = new TourUI(width, height, scale, (TourIndividual)individual);
        
//        BufferedImage image = ImageIO.read(new File("res/bird4.jpg"));
//        Image img = new Image(image);
//        int edgeDist = 10;
//        int scale = 5;
//        Population pop = new Population(popSize, new ImageIndividual(new ImageSolution(img.getEdges(edgeDist))), threadCount);
//        Individual individual = pop.execute(maxGenerations, everyNth, selectionSplit, printBestIndividual);
//        ImageUI ui = new ImageUI(img.getWidth(), img.getHeight(), scale, img, (ImageIndividual) individual);
//        Image img2 = new Image(((ImageIndividual) individual).getPixels());
//        ImageUI ui2 = new ImageUI(img2.getWidth(), img2.getHeight(), scale, img2, (ImageIndividual) individual);
//        Image img3 = new Image(img.getEdges(edgeDist));
//        ImageUI ui3 = new ImageUI(img3.getWidth(), img3.getHeight(), scale, img3, (ImageIndividual) individual);
//        image.flush();

        String str = "Mutability is the appropriate motto of humanity; for what are men\n" +
"but creatures of a day; monarchs, but transient shadows of earthly\n" +
"greatness; empires, but passing events? Time, with more than eagle\n" +
"swiftness, hurls all things into the great bosom of Eternity. Futurity\n" +
"is dark and impenetrable, but the present is with us, and still more\n" +
"the past, teeming with vast records of human life, of rising and\n" +
"falling empires, bloody tales of extinguished armies and extirpated\n" +
"races of mankind, detailing the effects of the wild ambition of kings,\n" +
"emperors, sultans, themselves but atoms, yet involving the whole mass\n" +
"in their career.\n";
        Population pop = new Population(popSize, new PhraseIndividual(new PhraseSolution(str)), threadCount);
        pop.execute(maxGenerations, everyNth, selectionSplit, printBestIndividual);

        pop.dispose();
    }
}
