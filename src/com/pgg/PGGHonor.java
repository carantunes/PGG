package com.pgg;


import static com.pgg.Utils.findMinMax;


class PGGHonor extends PGG {

    protected double honorFactor;

    public PGGHonor(int population_size, int group_size, double factor, int n_games, double honorFactor) {
        super(population_size, group_size, factor, n_games);
        this.honorFactor = honorFactor;
    }


    //private methods


    @Override
    protected double playGame(int subject_index) {
        Subject[] group = pickGroup(subject_index);
        Subject subject = this.population[subject_index];


        Double profit = getProfit(group);

        MinMax minMax = findMinMax(group);


        //NOTE: subject is always last in group
        //best player
        if(group.length - 1 == minMax.getMax()){
            profit *= (1 + (honorFactor/ minMax.getTotalMax()));
        }
        return profit + 1 - subject.getOffer() ;

    }
}