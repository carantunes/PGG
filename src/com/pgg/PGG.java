package com.pgg;

import java.util.*;

public class PGG {

    private double[] population;
    private Random rnd = new Random();

    //constructor
    public PGG(){

    }


    private void createPopulation(int n){
        population = new double[n];

        for (int i = 0; i<n; i++) {
            population[i] = Math.round(rnd.nextDouble() * 10) / 10.0 ;
        }
    }
    
    
    /**
    * Main method
    **/
    public static void main(String[] args){
        PGG myPGG = new PGG();

        myPGG.createPopulation(5);
    }
}


