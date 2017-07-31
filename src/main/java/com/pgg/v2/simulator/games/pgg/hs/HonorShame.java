package com.pgg.v2.simulator.games.pgg.hs;

import com.pgg.v2.simulator.games.minmax.MinMax;
import com.pgg.v2.simulator.games.pgg.PGG;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.subject.Subject;
import com.pgg.v2.simulator.games.pgg.hs.modes.Mode;

import static com.pgg.v2.simulator.games.minmax.MinMaxUtils.findMinMax;

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

    public HonorShame(Population population,
                      Mode mode) {
        super(population);
        this.honorFactor = honorFactor;
        this.shameFactor = shameFactor;
        this.mode = mode;
    }

    //private methods


    public void setHonorFactor(double honorFactor) {
        this.honorFactor = honorFactor;
    }

    public void setShameFactor(double shameFactor) {
        this.shameFactor = shameFactor;
    }

    @Override
    public double playGame(Subject subject, Subject[] neighbours_group)
    {
        Double profit = getProfit(neighbours_group);

        MinMax minMax = findMinMax(subject, neighbours_group);

        profit = mode.calcHonor(minMax, subject, profit, honorFactor);
        profit = mode.calcShame(minMax, subject, profit, shameFactor);

        return profit + 1 - subject.getOffer() ;
    }

}
