package com.pgg.v2.simulator.games.pgg;

import com.pgg.v2.simulator.games.Game;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.Random;
import java.util.stream.DoubleStream;
import com.pgg.v2.simulator.Parameters;

/**
 * Created by Carina on 30/05/2017.
 */
public class PGG implements Game {

    public Subject[] population;
    public double avg;
    public double std_variance;

    public PGG(double avg, double std_variance){
        this.avg = avg;
        this.std_variance = std_variance;
    }

    public double getOfferAverage(){
        double offer_sum = 0;
        for (Subject subject : population) {
            offer_sum += subject.getOffer();
        }

        return offer_sum/ Parameters.POPULATION_SIZE;
    }

    public void createPopulation(){

        Random rnd = new Random();
        population = new Subject[Parameters.POPULATION_SIZE];
        for (int i = 0; i< Parameters.POPULATION_SIZE; i++) {
            //double offer = (rnd.nextGaussian() / 6) + 0.25;
            double offer = (rnd.nextGaussian() * (std_variance/3)) + avg;
            if(offer < 0) offer = 0;
            if(offer > 1) offer = 1;

            // double offer = rnd.nextDouble();
            population[i] = new Subject(offer);

        }
    }

    public double getFitness(int index){
        double[] profits = new double[Parameters.N_GAMES];
        for(int i = 0; i< Parameters.N_GAMES; i++){
            profits[i] = playGame(index);
        }
        return DoubleStream.of(profits).sum() / Parameters.N_GAMES;

    }


    //private methods
    public double playGame(int subject_index){
        Subject[] group = pickGroup(subject_index);
        Subject subject = population[subject_index];
        return getProfit(group) + 1 - subject.getOffer() ;
    }

    protected Subject[] pickGroup(int subject_index){
        Subject[] group = new Subject[Parameters.GROUP_SIZE];
        int i = 0;
        while(i < Parameters.GROUP_SIZE - 1){
            int index = new Random().nextInt(Parameters.POPULATION_SIZE);
            if (index != subject_index){
                group[i] = population[index];
                i++;
            }
        }
        group[Parameters.GROUP_SIZE-1] = population[subject_index];
        return group;
    }

    protected double getProfit(Subject[] group){
        double offer_sum = 0;
        for (Subject subject : group) {
            offer_sum += subject.getOffer();
        }
        return (offer_sum * Parameters.FACTOR) / Parameters.GROUP_SIZE;
    }

}
