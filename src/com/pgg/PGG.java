package com.pgg;

import java.util.*;
import com.pgg.Sample;

public class PGG {

    private Sample[] population;
    private Random rnd = new Random();
    private int population_size;

    /**
     * Main method
     **/
    public static void main(String[] args){
        PGG myPGG = new PGG(args[0]);
        myPGG.createPopulation();
    }

    //constructor
    private PGG(String arg){
        population_size = Integer.valueOf(arg);
    }

    private void createPopulation(){
        population = new Sample[population_size];
        for (int i = 0; i< population_size; i++) {
            population[i] = new Sample(Math.round(rnd.nextDouble() * 10) / 10.0) ;
            System.out.println(population[i].getOffer());
        }
    }
}


