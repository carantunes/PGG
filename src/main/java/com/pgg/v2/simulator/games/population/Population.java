package com.pgg.v2.simulator.games.population;

import com.pgg.v2.simulator.Parameters;
import com.pgg.v2.simulator.games.Game;
import com.pgg.v2.simulator.games.subject.Subject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Carina on 25/06/2017.
 */
public abstract class Population {

    /** @var network index array : int -> int[] */
    public Subject[] population;

    /** Average Offer**/
    public double avg;

    /** @var network index array : int -> int[] */
    ArrayList<Integer>[] network;

    /** Abstract methods**/
    abstract public Subject[] getSubjectNeighbors(int index);
    public abstract double getFitness(int index, Game game);


    public void resetOffers(double avg){
        this.avg = avg;
        double std_variance = (1 - avg) <= avg? (1-avg) : avg;

        Random rnd = new Random();

        for (int i = 0 ; i < Parameters.POPULATION_SIZE; i++) {
            double offer = (rnd.nextGaussian() * (std_variance / 3)) + avg;
            if (offer < 0) offer = 0;
            if (offer > 1) offer = 1;

            population[i].setOffer(offer);
        }
    }

    public void dumpNetworkCSV(){
        if(Parameters.NET == 0)
            return;
        BufferedWriter writer;
        double offerAverage = getOfferAverage();
        String filename =  "net-" + this.getClass().getSimpleName() + "_OfferAVG-" + offerAverage + "_AVG-"+ avg + ".csv";

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


        filename =  "netStats-" + this.getClass().getSimpleName() + "_OfferAVG-" + offerAverage + "_AVG-"+ avg + ".csv";
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

    public double getOfferAverage(){
        double offer_sum = 0;
        for (Subject subject : population) {
            offer_sum += subject.getOffer();
        }

        return offer_sum/ Parameters.POPULATION_SIZE;
    }

    public Subject getSubject(int index){
        return population[index];
    }

}
