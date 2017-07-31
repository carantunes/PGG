package com.pgg.v2.simulator.games.population;

import com.pgg.v2.simulator.Parameters;
import com.pgg.v2.simulator.games.Game;
import com.pgg.v2.simulator.games.subject.Subject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.DoubleStream;

/**
 * Created by Carina on 25/06/2017.
 * Uniform and complete
 */
public class RandomPopulation extends Population {


    public RandomPopulation() {
        population = new Subject[Parameters.POPULATION_SIZE];
        network =  new ArrayList[Parameters.POPULATION_SIZE];

        // build nodes
        for (int nodeIndex = 0; nodeIndex < Parameters.POPULATION_SIZE; nodeIndex++){
            population[nodeIndex] = new Subject(nodeIndex);
            network[nodeIndex] = new ArrayList<Integer>();
        }

        //build edges
        for (int nodeIndex = 0; nodeIndex < Parameters.POPULATION_SIZE; nodeIndex++) {
            ArrayList<Integer> nodeLinks = network[nodeIndex];
            for (int edgeIndex = 0; edgeIndex < Parameters.POPULATION_SIZE; edgeIndex++) {
                    nodeLinks.add(edgeIndex);
            }
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


    //pick GROUP_SIZE elements randomly
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
