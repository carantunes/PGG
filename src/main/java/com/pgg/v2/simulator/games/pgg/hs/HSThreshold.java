package com.pgg.v2.simulator.games.pgg.hs;

import com.pgg.v2.simulator.games.pgg.hs.modes.Mode;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.subject.Subject;
import com.pgg.v2.simulator.Parameters;

/**
 * Created by Carina on 30/05/2017.
 */
public class HSThreshold extends HonorShame{
    private boolean hit_threshold;

    public HSThreshold(Population population,
                       double honorFactor,
                       double shameFactor,
                       Mode calculator){
        super(population, honorFactor, shameFactor, calculator);
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
        hit_threshold = false;
        double offer_sum = 0;
        //sum total of offers
        for (Subject subject : group) {
            offer_sum += subject.getOffer();
        }

        double max_offer = group.length;


        //if offers < threshold returns 0 else returns a players' cut of the pot
        if(offer_sum < (Parameters.THRESHOLD * max_offer))
            return 0;

        hit_threshold = true;
        return (offer_sum * Parameters.FACTOR) / Parameters.GROUP_SIZE;

    }
}
