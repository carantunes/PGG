package com.pgg;

import java.util.Random;
import java.util.stream.DoubleStream;


class PGG extends Game {

    public PGG(int population_size, int group_size, double factor, int n_games){
        super(population_size, group_size, factor, n_games);
    }



    protected void createPopulation(){
        Random rnd = new Random();
        population = new Subject[population_size];
        for (int i = 0; i< population_size; i++) {
            population[i] = new Subject(Math.round(rnd.nextDouble() * 10) / 10.0) ;
        }
    }

    protected double getFitness(int index){
        double[] profits = new double[n_games];
        for(int i = 0; i< n_games; i++){
            profits[i] = playGame(index);
        }
        return DoubleStream.of(profits).sum() / n_games;

    }


    //private methods
    protected double playGame(int subject_index){
        Subject[] group = pickGroup(subject_index);
        Subject subject = population[subject_index];
        return getProfit(group) - subject.getOffer() ;
    }

    protected Subject[] pickGroup(int subject_index){
        Subject[] group = new Subject[group_size];
        int i = 0;
        while(i < group_size - 1){
            int index = new Random().nextInt(population_size);
            if (index != subject_index){
                group[i] = population[index];
                i++;
            }
        }
        group[group_size-1] = population[subject_index];
        return group;
    }

    protected double getProfit(Subject[] group){
        double offer_sum = 0;
        for (Subject subject : group) {
            offer_sum += subject.getOffer();
        }
        return (offer_sum * factor) / group_size;
    }
}
