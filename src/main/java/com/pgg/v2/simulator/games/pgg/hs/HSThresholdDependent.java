package com.pgg.v2.simulator.games.pgg.hs;

import com.pgg.v2.simulator.games.minmax.MinMax;
import com.pgg.v2.simulator.games.pgg.hs.modes.Mode;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.subject.Subject;
import com.pgg.v2.simulator.Parameters;

import static com.pgg.v2.simulator.games.minmax.MinMaxUtils.findMinMax;

/**
 * Created by Carina on 30/05/2017.
 */
public class HSThresholdDependent extends HonorShame{
    private boolean hit_threshold;

    public HSThresholdDependent(Population population,
                                double honorFactor,
                                double shameFactor,
                                Mode mode){
        super(population, honorFactor, shameFactor, mode);
        this.hit_threshold = false;
    }

    public HSThresholdDependent(Population population,
                                Mode mode){
        super(population, mode);
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
    public double playGame(Subject subject, Subject[] neighbours_group)
    {
        Double profit = getProfit(neighbours_group) ;
        MinMax minMax = findMinMax(subject, neighbours_group);

        Double remaining_offer = 1 - subject.getOffer();

        if(hit_threshold){
            remaining_offer = mode.calcHonor(minMax, subject, remaining_offer, honorFactor);
        } else{
            remaining_offer = mode.calcShame(minMax, subject, remaining_offer, shameFactor);
        }

        return profit + remaining_offer ;
    }
}
