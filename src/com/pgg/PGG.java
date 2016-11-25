package com.pgg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.DoubleStream;
import java.lang.Math;
import java.io.BufferedWriter;
public class PGG {

    private static final double EPS = 0.00;

    private Sample[] population;
    private int population_size;
    private int group_size;
    private double factor;
    private int times;
    private double beta;
    private BufferedWriter writer;

    /**
     * Main method
     **/
    public static void main(String[] args){
        int population_size = Integer.valueOf(args[0]);
        int group_size = Integer.valueOf(args[1]);
        double factor = Double.valueOf(args[2]);
        int times = Integer.valueOf(args[3]);
        double beta = Double.valueOf(args[4]);
        int iterations = Integer.valueOf(args[5]);
        PGG myPGG = new PGG(population_size, group_size, factor, times, beta);
        myPGG.createPopulation();

        for(int i = 0; i < iterations; i++){
            myPGG.trainFitness();
            myPGG.writePopulationStatsToFile();
        }
        try {
            myPGG.writer.close();
        }catch (Exception e){
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }
    }

    //constructor
    private PGG(int population_size, int group_size, double factor, int times, double beta){
        this.population_size = population_size;
        this.group_size = group_size;
        this.factor = factor;
        this.times = times;
        this.beta = beta;
        try {
            this.writer = new BufferedWriter(new FileWriter("stats_F-" + factor + "_B-" + beta + ".txt"));
        }catch (Exception e){
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }
    }

    private void createPopulation(){
        Random rnd = new Random();
        population = new Sample[population_size];
        for (int i = 0; i< population_size; i++) {
            population[i] = new Sample(Math.round(rnd.nextDouble() * 10) / 10.0) ;
            // population[i] = new Sample(1);
        }
    }

    private void trainFitness(){
        int playerA = new Random().nextInt(population_size);

        int playerB;
        while ((playerB = new Random().nextInt(population_size)) == playerA);

        double fitnessA = getFitness(playerA);
        double fitnessB = getFitness(playerB);

        updateOffer(playerA, playerB, fitnessA, fitnessB);
    }

    private double getFitness(int index){
        double[] profits = new double[times];
        for(int i= 0; i<times; i++){
            profits[i] = playGame(index);
        }
        return DoubleStream.of(profits).sum() / times;

    }

    private void writePopulationStatsToFile(){
        try{
            double offer_sum = 0;
            for (Sample subject : population) {
                offer_sum += subject.getOffer();
            }

            writer.write(Double.toString(offer_sum/population_size));
            writer.newLine();

        } catch (Exception e) {
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }
    }

    private double playGame(int index){
        Sample[] group = pickGroup(index);
        Sample player = population[index];
        return getProfit(group) - player.getOffer() ;
    }

    private Sample[] pickGroup(int player){
        Sample[] group = new Sample[group_size];
        int i = 0;
        while(i < group_size - 1){
            int index = new Random().nextInt(population_size);
            if (index != player){
                group[i] = population[index];
                i++;
            }
        }
        group[group_size-1] = population[player];
        return group;
    }

    private double getProfit(Sample[] group){
        double offer_sum = 0;
        for (Sample sample : group) {
            offer_sum += sample.getOffer();
        }
        return (offer_sum * factor) / group_size;
    }

    private void updateOffer(int player, int neighbour, double player_fitness, double neighbour_fitness){

        double diff = player_fitness - neighbour_fitness;
        if(diff < 0){
            if(new Random().nextDouble() <= getProbability(player_fitness, neighbour_fitness)) {
                population[player].setOffer(population[neighbour].getOffer());
            }
        }
    }

    private double getProbability(double player_fitness, double neighbour_fitness){
        double diff = neighbour_fitness - player_fitness;
        return 1 / (1 + Math.exp( - beta * diff)) + EPS;
    }
}


