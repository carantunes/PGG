package com.pgg;


class PGGShameThreshold extends PGGShame{

    protected int threshold;

    public PGGShameThreshold(int population_size, int group_size, double factor, int n_games, int threshold, double shameFactor){
        super(population_size, group_size, factor, n_games, shameFactor);
        this.threshold = threshold;
    }


    //private methods

    @Override
    protected double getProfit(Subject[] group) {
        double offer_sum = 0;
        for (Subject subject : group) {
            offer_sum += subject.getOffer();
        }
        if(offer_sum < threshold)
            return 0;

        return (offer_sum * factor) / group_size;

    }
}
