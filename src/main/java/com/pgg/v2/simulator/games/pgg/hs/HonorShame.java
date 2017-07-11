package com.pgg.v2.simulator.games.pgg.hs;

import com.pgg.v2.simulator.games.pgg.PGG;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.subject.Subject;
import com.pgg.v2.simulator.games.pgg.hs.modes.Mode;

/**
 * Created by Carina on 30/05/2017.
 */
public class HonorShame extends PGG {

    protected double honorFactor;
    protected double shameFactor;
    protected Mode mode;

    public HonorShame(Population population,
                      double honorFactor,
                      double shameFactor,
                      Mode mode) {
        super(population);
        this.honorFactor = honorFactor;
        this.shameFactor = shameFactor;
        this.mode = mode;
    }


    //private methods


    @Override
    public double playGame(int subject_index){
        Subject[] group = pickGroup(subject_index);
        Subject subject = this.population.getSubject(subject_index);
        Double profit = getProfit(group);

        profit = mode.calcHonor(group, profit, honorFactor);
        profit = mode.calcShame(group, profit, shameFactor);

        return profit + 1 - subject.getOffer() ;
    }

}
