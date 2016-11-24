package com.pgg;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.*;
import java.util.stream.DoubleStream;
import java.lang.Math;

public class PGG {

    private static final double EPS = 0.05;

    private Sample[] population;
    private int population_size;
    private int group_size;
    private double factor;
    private int times;
    private double beta;

    /**
     * Main method
     **/
    public static void main(String[] args){
        int population_size = Integer.valueOf(args[0]);
        int group_size = Integer.valueOf(args[1]);
        double factor = Double.valueOf(args[2]);
        int times = Integer.valueOf(args[3]);
        double beta = Double.valueOf(args[4]);
        PGG myPGG = new PGG(population_size, group_size, factor, times, beta);
        myPGG.createPopulation();
        myPGG.iterations();
    }

    //constructor
    private PGG(int population_size, int group_size, double factor, int times, double beta){
        this.population_size = population_size;
        this.group_size = group_size;
        this.factor = factor;
        this.times = times;
        this.beta = beta;
    }

    private void createPopulation(){
        Random rnd = new Random();
        population = new Sample[population_size];
        for (int i = 0; i< population_size; i++) {
            population[i] = new Sample(Math.round(rnd.nextDouble() * 10) / 10.0) ;
        }
    }

    private void iterations(){
        int index = new Random().nextInt(population_size);
        Sample player = population[index];
        double[] profits = new double[times];
        for(int i= 0; i<times; i++){
            profits[i] = playGame(index);
        }
        System.out.println(Arrays.toString(profits));
        player.setFitness(DoubleStream.of(profits).sum() / times);
        System.out.println(" getFitness ---- " + player.getFitness());
        System.out.println("playerOffer ---- " + player.getOffer());


        updateOffer(index);


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

    private void updateOffer(int player_index){
        int neighbour_index;
        while ((neighbour_index = new Random().nextInt(population_size)) == player_index);
        Sample player = population[player_index];
        Sample neighbour = population[neighbour_index];

        double player_fitness = player.getFitness();
        double neighbour_fitness = neighbour.getFitness();
        double diff = player_fitness - neighbour_fitness;
        if(diff < 0){
            if(new Random().nextDouble() <= getProbability(player_fitness, neighbour_fitness))
                player.setOffer(neighbour.getOffer());
        }
        System.out.println(" neigh Offer ---- " + neighbour.getOffer());

        System.out.println(" neighbour_fitness ---- " + neighbour_fitness);
        System.out.println(" playerOffer ---- " + player.getOffer());
    }

    private double getProbability(double player_fitness, double neighbour_fitness){
        double diff = player_fitness - neighbour_fitness;
        return 1 / (1 + Math.exp( - beta * diff)) + EPS;
    }
}


