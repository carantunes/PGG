package com.pgg.v2.simulator.games.population;

import com.pgg.v2.simulator.Parameters;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Carina on 25/06/2017.
 */
public class RandomPopulation implements Population {

    public Subject[] population;
    public double avg;
    public double std_variance;

    public ArrayList<Integer> neighbors;


    public RandomPopulation(double avg, double std_variance) {
        this.avg = avg;
        this.std_variance = std_variance;

        //create population
        Random rnd = new Random();
        neighbors =  new ArrayList<Integer>();
        population = new Subject[Parameters.POPULATION_SIZE];

        for (int i = 0; i < Parameters.POPULATION_SIZE; i++) {
            //double offer = (rnd.nextGaussian() / 6) + 0.25;
            double offer = (rnd.nextGaussian() * (std_variance / 3)) + avg;
            if (offer < 0) offer = 0;
            if (offer > 1) offer = 1;

            // double offer = rnd.nextDouble();
            population[i] = new Subject(offer);

            neighbors.add(i);
        }
    }

    @Override
    public double getOfferAverage(){
        double offer_sum = 0;
        for (Subject subject : population) {
            offer_sum += subject.getOffer();
        }

        return offer_sum/ Parameters.POPULATION_SIZE;
    }

    @Override
    public Subject getSubject(int index){
        return population[index];
    }

    @Override
    public Subject[] getSubjectNeighborsIndex(int subject_index) {
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
}