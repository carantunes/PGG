package com.pgg.v2.simulator.games.pgg.hs;

import com.pgg.v2.simulator.games.pgg.hs.modes.Mode;
import com.pgg.v2.simulator.games.subject.Subject;
import com.pgg.v2.simulator.Parameters;

/**
 * Created by Carina on 30/05/2017.
 */
public class HSThreshold extends HonorShame{
    private boolean hit_threshold;

    public HSThreshold(double avg,
                      double std_variance,
                      double honorFactor,
                      double shameFactor,
                       Mode mode){
        super(avg, std_variance, honorFactor, shameFactor, mode);
        this.hit_threshold = false;
    }

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
        if(offer_sum < Parameters.THRESHOLD)
            return 0;

        hit_threshold = true;
        return (offer_sum * Parameters.FACTOR) / Parameters.GROUP_SIZE;

    }

    @Override
    public double playGame(int subject_index){
        Subject[] group = pickGroup(subject_index);
        Subject subject = this.population[subject_index];
        Double profit = getProfit(group) ;

        Double remaining_offer = 1 - subject.getOffer();
        profit = mode.calcHonor(group, profit, honorFactor);
        profit = mode.calcShame(group, profit, shameFactor);
        if(hit_threshold){
            remaining_offer = mode.calcHonor(group, remaining_offer, honorFactor);
        } else{
            remaining_offer = mode.calcShame(group, remaining_offer, shameFactor);
        }

        return profit + remaining_offer ;

    }
}
