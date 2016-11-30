package com.pgg;

abstract class Game {

    protected Subject[] population;
    protected int population_size;
    protected int group_size;
    protected double factor;
    protected int n_games;

    protected Game(int population_size, int group_size, double factor, int n_games){
        this.population_size = population_size;
        this.group_size = group_size;
        this.factor = factor;
        this.n_games = n_games;
    }

    //abstract classes
    protected abstract double getFitness(int index);
    protected abstract void createPopulation();
  //  protected abstract double playGame(int subject_index);
    protected double getOfferAverage(){
            double offer_sum = 0;
            for (Subject subject : population) {
                offer_sum += subject.getOffer();
            }

            return offer_sum/population_size;
    }
}
