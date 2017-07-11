package com.pgg.v2.simulator.games.population;

import com.pgg.v2.simulator.Parameters;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Carina on 25/06/2017.
 */
public class ComplexPopulation implements Population {

    public Subject[] population;
    public double avg;
    public double std_variance;

    /** @var network index array
     * int -> int[] */
    public ArrayList<Integer>[] network;



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

    public ComplexPopulation(double avg, double std_variance) {
        this.avg = avg;
        this.std_variance = std_variance;

        //create population
        Random rnd = new Random();
        population = new Subject[Parameters.POPULATION_SIZE];
        network =  new ArrayList[Parameters.POPULATION_SIZE];

        // start with 10 nodes
        int nodeIndex = 0;
        for (; nodeIndex < 10; nodeIndex++){
            //double offer = (rnd.nextGaussian() / 6) + 0.25;
            double offer = (rnd.nextGaussian() * (std_variance / 3)) + avg;
            if (offer < 0) offer = 0;
            if (offer > 1) offer = 1;

            // double offer = rnd.nextDouble();
            population[nodeIndex] = new Subject(offer);
            network[nodeIndex] = new ArrayList<Integer>();

            for (int edgeIndex = 0; edgeIndex < 10; edgeIndex++){
                if(edgeIndex != nodeIndex)
                    network[nodeIndex].add(edgeIndex);
            }
        }

        //add node
        for (; nodeIndex < Parameters.POPULATION_SIZE; nodeIndex++) {
            //double offer = (rnd.nextGaussian() / 6) + 0.25;
            double offer = (rnd.nextGaussian() * (std_variance / 3)) + avg;
            if (offer < 0) offer = 0;
            if (offer > 1) offer = 1;

            // double offer = rnd.nextDouble();
            population[nodeIndex] = new Subject(offer);


            network[nodeIndex] = new ArrayList<Integer>();
            int totalLinks = countTotalLinks();
            ArrayList<Integer> nodeLinks = network[nodeIndex];

            //add edges
            for (int edgeIndex = 0; edgeIndex < nodeIndex ; edgeIndex++) {
                if(edgeIndex == nodeIndex) continue;

                double attachmentProbability = (totalLinks == 0) ? 0 : countSubjectLinks(edgeIndex) / (double) totalLinks ;
                if(rnd.nextDouble() <= attachmentProbability){
                    nodeLinks.add(edgeIndex);
                    network[edgeIndex].add(nodeIndex);
                }
            }

           // min degree = 1
            if(nodeLinks.size() == 0){
                int linkIndex;
                while ((linkIndex = new Random().nextInt(nodeIndex)) == nodeIndex);

                nodeLinks.add(linkIndex);
                network[linkIndex].add(nodeIndex);
            }

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
    public Subject[] getSubjectNeighborsIndex(int index) {
        ArrayList<Integer> neighbours =network[index];
        Subject[] neighboursArray = new Subject[neighbours.size()];
        for (int i = 0; i < neighbours.size(); i++) {
            neighboursArray[i] = getSubject(neighbours.get(i));
        }
        return  neighboursArray;
    }
}
