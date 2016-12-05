package com.pgg;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Random;

public class PGGSimulatorNTimes {

    private static final double EPS = 0.05;
    private static double beta;
    private Game game;
    private BufferedWriter writer;


    public PGGSimulatorNTimes(double beta){
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
        PGGSimulatorNTimes simulator = new PGGSimulatorNTimes(beta);
        DecimalFormat df = new DecimalFormat("#.0");



                switch(game_name){
                    case "PGG":

                        for(double b = 0; b <= 5; b += 0.5){

                            df = new DecimalFormat("#.00");
                            simulator.beta = b;
                            simulator.game = new PGG(population_size, group_size, factor, n_games);

                            simulator.openWriter("Beta/" + game_name + "_stats_F-" + factor + "_B-" + df.format(b).replace(',', '.') + ".txt");

                            simulator.game.createPopulation();
                            for(int i = 0; i < generations; i++){
                                simulator.trainFitness();
                                simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );

                            }
                            simulator.closeWriter();

                            System.out.println("beta " + b );
                        }

                        simulator.beta = beta;
                        for(double f = 0; f < group_size + 2; f += 0.5){
                            simulator.game = new PGG(population_size, group_size, f, n_games);
                            simulator.openWriter("Factor/" + game_name + "_stats_F-" + df.format(f).replace(',', '.') + "_B-" + beta + ".txt");

                            simulator.game.createPopulation();
                            for(int i = 0; i < generations; i++){
                                simulator.trainFitness();
                                simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );

                            }
                            simulator.closeWriter();

                            System.out.println("factor " + f );
                        }

                        break;


                    case "PGGHonorShame":
                        for(double honor = 0; honor < 1; honor += 0.1){
                            for(double shame = 0; shame < 1; shame+=0.1 ){
                                simulator.game = new PGGHonorShameShare(population_size, group_size, factor, n_games, honor, shame);
                                simulator.openWriter("HS/" + game_name +  "_stats_F-" + factor + "_B-" + beta + "_H-" + df.format(honor).replace(',', '.') + "_S-" + df.format(shame).replace(',', '.') + ".txt");

                                simulator.game.createPopulation();
                                for(int i = 0; i < generations; i++){
                                    simulator.trainFitness();
                                    simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );

                                }
                                simulator.closeWriter();

                                System.out.println("Done " + honor + " " + shame);
                            }
                        }
                        break;

                    case "PGGHonor":
                        for(double honor = 0; honor < 1; honor += 0.1){
                            simulator.game = new PGGHonor(population_size, group_size, factor, n_games, honor);
                            simulator.openWriter("Honor/" + game_name +  "_stats_F-" + factor + "_B-" + beta + "_H-" + df.format(honor).replace(',', '.') + ".txt");

                            simulator.game.createPopulation();
                            for(int i = 0; i < generations; i++){
                                simulator.trainFitness();
                                simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );

                            }
                            simulator.closeWriter();

                            System.out.println("Done " + honor );
                        }
                        break;
                    case "PGGHonorThreshold":
                        for(double honor = 0; honor < 1; honor += 0.1){
                            simulator.game = new PGGHonorThreshold(population_size, group_size, factor, n_games, 3, honor);
                            simulator.openWriter("HThresh/" + game_name +  "_stats_F-" + factor + "_B-" + beta + "_H-" + df.format(honor).replace(',', '.') + ".txt");

                            simulator.game.createPopulation();
                            for(int i = 0; i < generations; i++){
                                simulator.trainFitness();
                                simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );

                            }
                            simulator.closeWriter();

                            System.out.println("Done " + honor );
                        }
                        break;
                    case "PGGShame":
                        for(double shame = 0; shame < 1; shame += 0.1){
                            simulator.game = new PGGShame(population_size, group_size, factor, n_games, shame);
                            simulator.openWriter("Shame/" + game_name +  "_stats_F-" + factor + "_B-" + beta + "_H-" + df.format(shame).replace(',', '.') + "_S-" + ".txt");

                            simulator.game.createPopulation();
                            for(int i = 0; i < generations; i++){
                                simulator.trainFitness();
                                simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );

                            }
                            simulator.closeWriter();

                            System.out.println("Done " + shame );
                        }
                        break;
                    case "PGGShameThreshold":
                        for(double shame = 0; shame < 1; shame += 0.1){
                            simulator.game = new PGGShameThreshold(population_size, group_size, factor, n_games, 3, shame);
                            simulator.openWriter("Shame/" + game_name +  "_stats_F-" + factor + "_B-" + beta + "_S-" + df.format(shame).replace(',', '.') + "_S-" + ".txt");

                            simulator.game.createPopulation();
                            for(int i = 0; i < generations; i++){
                                simulator.trainFitness();
                                simulator.writePopulationStatsToFile(simulator.game.getOfferAverage() );

                            }
                            simulator.closeWriter();

                            System.out.println("Done " + shame );
                        }
                        break;
                    default:
                        break;
                }



    }

    private void writePopulationStatsToFile(double stats){
        try{

            writer.write(Double.toString(stats));
            writer.newLine();


        } catch (Exception e) {
            System.out.println("Failed writing population \n" + e.toString());
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
            System.out.println("Please create HS folder \n" + e.toString());
        }
    }

    private void closeWriter(){
        try {
            writer.close();
        }catch (Exception e){
            System.out.println("Failed closing the writer \n" + e.toString());
        }

    }
}


