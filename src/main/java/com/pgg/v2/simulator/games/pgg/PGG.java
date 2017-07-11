package com.pgg.v2.simulator.games.pgg;

import com.pgg.v2.simulator.games.Game;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.DoubleStream;
import com.pgg.v2.simulator.Parameters;

/**
 * Created by Carina on 30/05/2017.
 */
public class PGG implements Game {

    public Population population;

    public PGG(Population population){
        this.population = population;
    }

    public double getOfferAverage(){
        return population.getOfferAverage();
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
        Subject subject = population.getSubject(subject_index);
        return getProfit(group) + 1 - subject.getOffer() ;
    }

    protected Subject[] pickGroup(int subject_index){
        return  population.getSubjectNeighborsIndex(subject_index);
    }

    protected double getProfit(Subject[] group){
        double offer_sum = 0;
        for (Subject subject : group) {
            offer_sum += subject.getOffer();
        }
        return (offer_sum * Parameters.FACTOR) / Parameters.GROUP_SIZE;
    }

}
