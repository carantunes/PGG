package com.pgg.v2.simulator;

import com.pgg.v2.simulator.exceptions.InvalidGameException;
import com.pgg.v2.simulator.games.GameFactory;
import com.pgg.v2.simulator.games.pgg.hs.HonorShame;
import com.pgg.v2.simulator.games.pgg.PGG;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.population.PopulationFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Carina on 30/05/2017.
 */
public class HSSimulator {
    private BufferedWriter writer;
    private PGG game;

    private void writePopulationStatsToFile(double stats){
        try{
            DecimalFormat df = new DecimalFormat("#.###");
            writer.write(df.format(stats).replace(',', '.') + " ");
        } catch (Exception e) {
            System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
        }
    }

    private void trainFitness(){
        int subjectA = new Random().nextInt(Parameters.POPULATION_SIZE);

        int subjectB;
        while ((subjectB = new Random().nextInt(Parameters.POPULATION_SIZE)) == subjectA);

        double fitnessA = game.getFitness(subjectA);
        double fitnessB = game.getFitness(subjectB);

        updateOffer(subjectA, subjectB, fitnessA, fitnessB);
    }

    private void updateOffer(int subject, int neighbour, double subject_fitness, double neighbour_fitness){
        if(new Random().nextDouble() <= getProbability(subject_fitness, neighbour_fitness)) {
            // +EPS, -EPS, 0
            double error = Parameters.EPS *  (new Random().nextInt(3) - 1);
            double new_offer = game.population.getSubject(neighbour).getOffer() + error;

            new_offer = new_offer > 1 ? 1 : (new_offer < 0 ? 0 : new_offer);

            game.population.getSubject(subject).setOffer(new_offer);
        }
    }

    private double getProbability(double subject_fitness, double neighbour_fitness){
        double diff = neighbour_fitness - subject_fitness;
        return 1 / (1 + Math.exp( - Parameters.BETA * diff)) ;
    }

    protected void openWriter(String filename){
        try {
            writer = new BufferedWriter(new FileWriter("stats/" + filename));
            writer.write("  0 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1");

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

    public static boolean isClass(String className) {
        try  {
            Class.forName(className);
            return true;
        }  catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Main method: can only run games in HonorShame Package
     * @param args String[] = [game, mode]
     * @see : com.pgg.v2.HSSimulator.games.Contants;
     */
    public static void main(String[] args) throws InvalidGameException {
        HSSimulator simulator = new HSSimulator();
        String game_name = args[0];
        if(args.length > 1){
            String net = args[1];
            Parameters.NET = Integer.valueOf(net);
        }
        DecimalFormat df = new DecimalFormat("#.##");

        if (!isClass(HonorShame.class.getPackage().getName() + "." + game_name) && !game_name.equals("PGG")) {
            throw new InvalidGameException(game_name);
        }

        Population population = PopulationFactory.createPopulation(Parameters.NET);
        population.dumpNetworkCSV();

        for(Double avg = 0.0; avg <= 1.0; avg += 0.1){

            simulator.openWriter( game_name
                    + "_MATRIX_"
                    + "_NET-"
                    + Parameters.NET
                    + "_stats_F-"
                    + Parameters.FACTOR
                    + "_B-"
                    + Parameters.BETA
                    + "_AVG-"
                    + df.format(avg)
                    +".txt");


            for(double honor = 0; honor < 1; honor += 0.1){
                try{
                    simulator.writer.newLine();
                    df = new DecimalFormat("#.#");
                    simulator.writer.write( df.format(honor).replace(',', '.') + " ");

                } catch (Exception e) {
                    System.out.println("ABORT!!! UNHANDLED EXCEPTION!! HELP \n" + e.toString());
                }
                for(double shame = 0; shame < 1; shame+=0.1 ){
                    population.resetOffers(avg);
                    simulator.game = GameFactory.createPGG(game_name, honor, shame, Parameters.MODE, population);

                    for(int i = 0; i < Parameters.GENERATIONS; i++){
                        simulator.trainFitness();
                    }
                    simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );
                    System.out.println("Done " + honor + " " + shame);
                }
            }
            simulator.closeWriter();

        }
    }
}
