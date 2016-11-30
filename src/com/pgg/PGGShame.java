package com.pgg;

import jdk.internal.util.xml.impl.Pair;

import java.util.Random;
import java.util.stream.DoubleStream;


class PGGShame extends PGG {

    protected double shameFactor;

    public PGGShame(int population_size, int group_size, double factor, int n_games, double shameFactor){
        super(population_size, group_size, factor, n_games);
        this.shameFactor = shameFactor;
    }


    //private methods


    @Override
    protected double playGame(int subject_index){
        Subject[] group = pickGroup(subject_index);
        Subject subject = this.population[subject_index];
        Double profit = getProfit(group) - subject.getOffer() ;

        MinMax minMax = findMinMax(group);
        //worst player
        if(subject.equals(minMax.getMin())){
            profit *= (1 - shameFactor);
        }
        return profit;

    }


    private MinMax findMinMax(Subject[] population) {
        if (population == null || population.length < 1)
            return null;
        Subject min = population[0];
        Subject max = population[0];

        for (int i = 1; i < population.length ; i++) {
            if (max.getOffer() < population[i].getOffer()) {
                max = population[i];
            }

            if (min.getOffer() > population[i].getOffer()) {
                min = population[i];
            }
        }

        return new MinMax(min,max);
    }

      final class MinMax {
        private final Subject min;
        private final Subject max;

        public MinMax(Subject min, Subject max) {
            this.min = min;
            this.max = max;
        }

        public Subject getMin() {
            return min;
        }

        public Subject getMax() {
            return max;
        }
    }
}
