package com.pgg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.lang.Math;

public class PGGSimulator {

    private static final double EPS = 0.05;
    private static double beta;
    private Game game;
    private BufferedWriter writer;


    public PGGSimulator(double beta){
        this.beta = beta;
    }

    private void setGame(Game game){
        this.game = game;
    }

    /**
     * Main method
     **/
    public static void main(String[] args){
        int population_size = Integer.valueOf(args[0]);
        int group_size = Integer.valueOf(args[1]);
        double factor = Double.valueOf(args[2]);
        int n_games = Integer.valueOf(args[3]);
        double beta = Double.valueOf(args[4]);
        int generations = Integer.valueOf(args[5]);
        String game_name = args[6];
        PGGSimulator simulator = new PGGSimulator(beta);


        switch(game_name){
            case "PGG":
                simulator.game = new PGG(population_size, group_size, factor, n_games);
                simulator.openWriter(game_name + "_stats_F-" + factor + "_B-" + beta + ".txt");

                break;

            case "PGGShame":

                double shameFactor = Double.valueOf(args[7]);
                simulator.game = new PGGShame(population_size, group_size, factor, n_games, shameFactor);
                simulator.openWriter(game_name + "_stats_F-" + factor + "_B-" + beta + "_shameFactor-" + shameFactor+  ".txt");

                break;

            case "PGGHonor":

                double honorFactor = Double.valueOf(args[7]);
                simulator.game = new PGGHonor(population_size, group_size, factor, n_games, honorFactor);
                simulator.openWriter(game_name + "_stats_F-" + factor + "_B-" + beta + "_honorFactor-" + honorFactor +  ".txt");

                break;

            case "PGGHonorShame":

                honorFactor = Double.valueOf(args[7]);
                shameFactor = Double.valueOf(args[8]);
                simulator.game = new PGGHonorShame(population_size, group_size, factor, n_games, honorFactor, shameFactor);
                simulator.openWriter(game_name + "_stats_F-" + factor + "_B-" + beta + "_honorFactor-" + honorFactor +  ".txt");

                break;
            default:
                //default is PGG
                simulator.game = new PGG(population_size, group_size, factor, n_games);
                simulator.openWriter(game_name + "_stats_F-" + factor + "_B-" + beta + ".txt");
                break;

        }

        simulator.game.createPopulation();
        for(int i = 0; i < generations; i++){
            simulator.trainFitness();
            simulator.writePopulationStatsToFile(simulator.game.getOfferAverage());
        }
        simulator.closeWriter();

    }

    private void writePopulationStatsToFile(double stats){
        try{

            writer.write(Double.toString(stats));
            writer.newLine();

        } catch (Exception e) {
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }
    }

    private void trainFitness(){
        int subjectA = new Random().nextInt(game.population_size);

        int subjectB;
        while ((subjectB = new Random().nextInt(game.population_size)) == subjectA);

        double fitnessA = game.getFitness(subjectA);
        double fitnessB = game.getFitness(subjectB);

        updateOffer(subjectA, subjectB, fitnessA, fitnessB);
    }

    private void updateOffer(int subject, int neighbour, double subject_fitness, double neighbour_fitness){
        double diff = subject_fitness - neighbour_fitness;
        if(diff < 0){
            if(new Random().nextDouble() <= getProbability(subject_fitness, neighbour_fitness)) {
                game.population[subject].setOffer(game.population[neighbour].getOffer());
            }
        }
    }

    private double getProbability(double subject_fitness, double neighbour_fitness){
        double diff = neighbour_fitness - subject_fitness;

        // +EPS, -EPS, 0
        double error = EPS *  (new Random().nextInt(3) - 1);
        return 1 / (1 + Math.exp( - beta * diff)) + error;
    }

    protected void openWriter(String filename){
        try {
            writer = new BufferedWriter(new FileWriter(filename));
        }catch (Exception e){
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }
    }

    private void closeWriter(){
        try {
            writer.close();
        }catch (Exception e){
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }

    }
}


