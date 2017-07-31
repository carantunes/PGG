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
    /** @var network index array
     * int -> int[] */
    public ArrayList<Integer>[] network;

    @Override
    public double getFitness(int index, Game game) {
        double[] profits = new double[Parameters.N_GAMES];
        for(int i = 0; i< Parameters.N_GAMES; i++){
            Subject[] group = getSubjectNeighbors(index);
            profits[i] = game.playGame(getSubject(index), group);
        }

        return DoubleStream.of(profits).sum() / Parameters.N_GAMES;
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

    public void dumpNetworkCSV(){
        BufferedWriter writer;
        double offerAverage = getOfferAverage();
        String filename = "net_" + offerAverage +".csv";

        try {
            writer = new BufferedWriter(new FileWriter("stats/" + filename));

            for (int node = 0; node < network.length ; node++) {

                ArrayList<Integer> edges = network[node];
                String edges_string = Integer.toString(node);

                for(int edge = 0; edge < edges.size(); edge++ ){
                    edges_string += "," + Integer.toString(edges.get(edge));
                }

                writer.write(edges_string);
                writer.newLine();

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        filename = "net_stats" + offerAverage +".txt";
        try {
            writer = new BufferedWriter(new FileWriter("stats/" + filename));

            double degreeAverage = getDegreeAverage();
            double minimumDegree = getMinimumDegree();

            writer.write("Offer Average: " + offerAverage);
            writer.newLine();
            writer.write("Degree Average: " + degreeAverage);
            writer.newLine();
            writer.write("Min degree: " + minimumDegree);
            writer.newLine();
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public RandomPopulation() {
        //create population
        Random rnd = new Random();
        population = new Subject[Parameters.POPULATION_SIZE];
        network =  new ArrayList[Parameters.POPULATION_SIZE];

        //initial net size
        int edgesToAdd = 6;

        // build nodes
        for (int nodeIndex = 0; nodeIndex < Parameters.POPULATION_SIZE; nodeIndex++){
            population[nodeIndex] = new Subject(nodeIndex);
            network[nodeIndex] = new ArrayList<Integer>();
        }

        //build edges
        for (int nodeIndex = 0; nodeIndex < Parameters.POPULATION_SIZE; nodeIndex++) {

            ArrayList<Integer> nodeLinks = network[nodeIndex];

            //Add the appropriate number of edges
            for (int edgeIndex = 0; edgeIndex < Parameters.POPULATION_SIZE; edgeIndex++) {
                    nodeLinks.add(edgeIndex);
            }
        }

        dumpNetworkCSV();
    }

    public double getMinimumDegree(){
        double max_degree =  Parameters.POPULATION_SIZE * Parameters.POPULATION_SIZE;
        double min_degree = max_degree;
        for (ArrayList links : network) {
            if(links.size() < min_degree)
                min_degree =  links.size();
        }

        return min_degree;
    }

    public double getDegreeAverage(){
        double degree_sum = 0;
        for (ArrayList links : network) {
            degree_sum += links.size() ;
        }

        return degree_sum/ Parameters.POPULATION_SIZE;
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
