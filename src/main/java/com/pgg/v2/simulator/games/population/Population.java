package com.pgg.v2.simulator.games.population;

import com.pgg.v2.simulator.Parameters;
import com.pgg.v2.simulator.games.Game;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Carina on 25/06/2017.
 */
public abstract class Population {
    public Subject[] population;
    public double avg;
    public double std_variance;


    abstract public double getOfferAverage();
    abstract public Subject getSubject(int index);
    abstract public Subject[] getSubjectNeighbors(int index);
    public abstract double getFitness(int index, Game game);

    public void resetOffers(double avg, double std_variance){
        this.avg = avg;
        this.std_variance = std_variance;
        Random rnd = new Random();

        for (int i = 0 ; i < Parameters.POPULATION_SIZE; i++) {
            double offer = (rnd.nextGaussian() * (std_variance / 3)) + avg;
            if (offer < 0) offer = 0;
            if (offer > 1) offer = 1;

            population[i].setOffer(offer);
        }
    }

    public void initOffers(double avg, double std_variance){
        resetOffers(avg, std_variance);
    }
}
