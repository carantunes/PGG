package com.pgg.v2.simulator.games.population;

import com.pgg.v2.simulator.Parameters;
import com.pgg.v2.simulator.games.Game;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.DoubleStream;

/**
 * Created by Carina on 25/06/2017.
 * Uniform and complete
 */
public class UniformPopulation extends Population
{
    public ArrayList<Integer> neighbors;
    public UniformPopulation() {

        //create population
        Random rnd = new Random();
        neighbors =  new ArrayList<Integer>();
        population = new Subject[Parameters.POPULATION_SIZE];

        for (int i = 0; i < Parameters.POPULATION_SIZE; i++) {
            // double offer = rnd.nextDouble();
            population[i] = new Subject(i);
            neighbors.add(i);
        }
    }

    @Override
    public double getFitness(int index, Game game) {
        double[] profits = new double[Parameters.N_GAMES];
        for(int i = 0; i< Parameters.N_GAMES; i++){
            Subject[] group = getSubjectNeighbors(index);
            profits[i] = game.playGame(getSubject(index), group);
        }
        return DoubleStream.of(profits).sum() / Parameters.N_GAMES;
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
    public Subject[] getSubjectNeighbors(int subject_index) {
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