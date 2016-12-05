package com.pgg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

public class PGGSimulatorMatrix {

    private static final double EPS = 0.05;
    private static double beta;
    private Game game;
    private BufferedWriter writer;


    public PGGSimulatorMatrix(double beta){
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
        PGGSimulatorMatrix simulator = new PGGSimulatorMatrix(beta);

       simulator.openWriter(game_name + "_MATRIX_"+ "_stats_F-" + factor + "_B-" + beta + ".txt");

        try{
            simulator.writer.write("  0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1");

        } catch (Exception e) {
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }
        for(double honor = 0; honor < 1; honor += 0.1){
            try{
                simulator.writer.newLine();
                DecimalFormat df = new DecimalFormat("#.#");
                simulator.writer.write( df.format(honor).replace(',', '.') + " ");

            } catch (Exception e) {
                System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
            }
            for(double shame = 0; shame < 1; shame+=0.1 ){
                switch(game_name){
                    case "PGGHonorShameRnd":
                        simulator.game = new PGGHonorShameRnd(population_size, group_size, factor, n_games, honor, shame);

                        break;
                    case "PGGHonorShameAll":
                        simulator.game = new PGGHonorShameAll(population_size, group_size, factor, n_games, honor, shame);

                        break;
                    case "PGGHonorShameShare":
                        simulator.game = new PGGHonorShameShare(population_size, group_size, factor, n_games, honor, shame);

                        break;

                    case "PGGHonorShameThreshold":
                        int threshold = 3;
                        simulator.game = new PGGHonorShameThreshold(population_size, group_size, factor, n_games, threshold, honor, shame);

                        break;
                    default:
                        simulator.game = new PGGHonorShame(population_size, group_size, factor, n_games, honor, shame);
                        break;
                }

                simulator.game.createPopulation();
                for(int i = 0; i < generations; i++){
                    simulator.trainFitness();
                }
                simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );
                System.out.println("Done " + honor + " " + shame);
            }
        }
        simulator.closeWriter();

    }

    private void writePopulationStatsToFile(double stats){
        try{


            DecimalFormat df = new DecimalFormat("#.###");
            writer.write(df.format(stats).replace(',', '.') + " ");


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
        double diff =  neighbour_fitness - subject_fitness;
        if(new Random().nextDouble() <= getProbability(subject_fitness, neighbour_fitness)) {
            // +EPS, -EPS, 0
            double error = EPS *  (new Random().nextInt(3) - 1);
            double new_offer = game.population[neighbour].getOffer() + error;

            new_offer = new_offer > 1 ? 1 : (new_offer < 0 ? 0 : new_offer);

            game.population[subject].setOffer(new_offer);
        }
    }

    private double getProbability(double subject_fitness, double neighbour_fitness){
        double diff = neighbour_fitness - subject_fitness;
        return 1 / (1 + Math.exp( - beta * diff)) ;
    }

    protected void openWriter(String filename){
        try {
            writer = new BufferedWriter(new FileWriter("stats/" + filename));
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


