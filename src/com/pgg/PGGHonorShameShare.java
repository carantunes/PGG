package com.pgg;

import static com.pgg.Utils.findMinMax;

class PGGHonorShameShare extends PGG {

    protected double honorFactor;
    protected double shameFactor;

    public PGGHonorShameShare(int population_size, int group_size, double factor, int n_games, double honorFactor, double shameFactor){
        super(population_size, group_size, factor, n_games);
        this.honorFactor = honorFactor;
        this.shameFactor = shameFactor;
    }


    //private methods


    @Override
    protected double playGame(int subject_index){
        Subject[] group = pickGroup(subject_index);
        Subject subject = this.population[subject_index];
        Double profit = getProfit(group) ;

        MinMax minMax = findMinMax(group);

        //NOTE: subject is always last in group

        //worst player
        if(group.length - 1 == minMax.getMin()){
            profit *= (1 - (shameFactor/ minMax.getTotalMin()));
        }

        //best player
        if(group.length - 1 == minMax.getMax()){
            profit *= (1 + (honorFactor/ minMax.getTotalMax()));
        }
        return profit + 1 - subject.getOffer() ;

    }



}
