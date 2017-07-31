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
 */
public class ComplexPopulation extends Population {

    public ComplexPopulation() {
        //create population
        Random rnd = new Random();
        population = new Subject[Parameters.POPULATION_SIZE];
        network =  new ArrayList[Parameters.POPULATION_SIZE];

        //initial net size
        int m = 5;
        int edgesToAdd = m - 1;
        // start with 10 nodes
        int nodeIndex = 0;
        for (; nodeIndex < m; nodeIndex++){
            population[nodeIndex] = new Subject(nodeIndex);
            network[nodeIndex] = new ArrayList<Integer>();

            //add edges to all 10 nodes
            for (int edgeIndex = 0; edgeIndex < m; edgeIndex++){
                if(edgeIndex != nodeIndex)
                    network[nodeIndex].add(edgeIndex);
            }
        }

        //add node
        for (; nodeIndex < Parameters.POPULATION_SIZE; nodeIndex++) {

            population[nodeIndex] = new Subject(nodeIndex);
            network[nodeIndex] = new ArrayList<Integer>();

            int totalLinks = countTotalLinks();
            ArrayList<Integer> nodeLinks = network[nodeIndex];

            double degreeIgnore = 0;
            //Add the appropriate number of edges
            for (int m_counter = 0; m_counter < edgesToAdd; m_counter++)
            {
                double randomNumber = rnd.nextDouble();
                double attachmentProbability = 0;

                //add edges
                for (int edgeIndex = 0; edgeIndex < nodeIndex ; edgeIndex++) {


                    //Check for an existing connection between these two nodes
                    if (!(network[nodeIndex].contains(edgeIndex))) {
                        attachmentProbability += countSubjectLinks(edgeIndex) / ((double) totalLinks - degreeIgnore) ;
                    }

                    if(randomNumber <= attachmentProbability){
                        degreeIgnore += countSubjectLinks(edgeIndex);

                        nodeLinks.add(edgeIndex);
                        network[edgeIndex].add(nodeIndex);


                        //Stop iterating for this probability, once we have found a single edge
                        break;
                    }
                }
            }
        }
    }

    @Override
    public double getFitness(int index, Game game) {
        double[] profits = new double[Parameters.N_GAMES];
        //get subject neighbours
        ArrayList<Integer> neighbours = network[index];

        //for all neighb play with their neigh
        for(int i = 0; i < neighbours.size(); i++){
            Subject[] group = getSubjectNeighbors(neighbours.get(i));
            profits[i] = game.playGame(getSubject(index), group);
        }

        return DoubleStream.of(profits).sum() / neighbours.size();
    }

    public int countSubjectLinks(int index){

        ArrayList links = network[index];
        return (links != null) ? links.size() : 0;
    }

    public int countTotalLinks(){
        int total = 0;
        for(ArrayList<Integer> links : network)
        {
            if(links != null)
                total += links.size();
        }
        return total;
    }


    @Override
    public Subject[] getSubjectNeighbors(int index) {
        ArrayList<Integer> neighbours =network[index];
        Subject[] neighboursArray = new Subject[neighbours.size()];
        for (int i = 0; i < neighbours.size(); i++) {
            neighboursArray[i] = getSubject(neighbours.get(i));
        }
        return  neighboursArray;
    }
}
