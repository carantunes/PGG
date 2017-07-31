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

    /** @var network index array
     * int -> int[] */
    public ArrayList<Integer>[] network;

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
