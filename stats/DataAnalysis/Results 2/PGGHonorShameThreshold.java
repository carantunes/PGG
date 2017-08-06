package com.pgg;


import static com.pgg.Utils.findMinMax;

class PGGHonorShameThreshold extends PGGHonorShameShare {

    private int threshold;
    private boolean hit_threshold;

    public PGGHonorShameThreshold(int population_size,
                                  int group_size,
                                  double factor,
                                  int n_games,
                                  double avg,
                                  double std_variance,
                                  double honorFactor,
                                  double shameFactor,
                                  int threshold){
        super(population_size, group_size, factor, n_games, avg, std_variance, honorFactor, shameFactor);
        this.threshold = threshold;
        this.hit_threshold = false;
    }


    //private methods


    /**
     * Sums the total of offers and returns a player's cut of the pot
     * after the factor is applied.
     * If total amount of offers is smaller than the threshold
     * returns 0.
     */
    @Override
    protected double getProfit(Subject[] group) {
        double offer_sum = 0;
        //sum total of offers
        for (Subject subject : group) {
            offer_sum += subject.getOffer();
        }
        //if offers < threshold returns 0 else returns a players' cut of the pot
        if(offer_sum < threshold)
            return 0;

        hit_threshold = true;
        return (offer_sum * factor) / group_size;

    }


    @Override
    protected double playGame(int subject_index){
        Subject[] group = pickGroup(subject_index);
        Subject subject = this.population[subject_index];
        Double profit = getProfit(group) ;

        MinMax minMax = findMinMax(group);

        if(hit_threshold){
            //best player
            if(minMax.isMax()){
                profit *= (1 + (honorFactor/ minMax.getTotalMax()));
            }
        } else{
            //worst player
            if(minMax.isMin()){
                profit *= (1 - (shameFactor/ minMax.getTotalMin()));
            }
        }




        return profit + 1 - subject.getOffer() ;

    }
}
