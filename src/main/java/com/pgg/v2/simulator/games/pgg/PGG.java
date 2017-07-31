package com.pgg.v2.simulator.games.pgg;

import com.pgg.v2.simulator.games.Game;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.subject.Subject;

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


    public double getFitness(int index)
    {
        return population.getFitness(index, this);
    }


    //private methods
    public double playGame(Subject subject, Subject[] neighbours_group){
        return getProfit(neighbours_group) + 1 - subject.getOffer() ;
    }

    protected double getProfit(Subject[] group){
        double offer_sum = 0;
        for (Subject subject : group) {
            offer_sum += subject.getOffer();
        }
        return (offer_sum * Parameters.FACTOR) / Parameters.GROUP_SIZE;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }
}
