package com.pgg.v2.simulator.games.pgg.hs.modes;

import com.pgg.v2.simulator.games.minmax.MinMax;
import com.pgg.v2.simulator.games.subject.Subject;

import static com.pgg.v2.simulator.games.minmax.MinMaxUtils.findMinMax;

/**
 * Created by Carina on 30/05/2017.
 */
public class All implements Mode {
    @Override
    public Double calcHonor(Subject[] group, Double profit, Double honorFactor) {
        MinMax minMax = findMinMax(group);

        //best player
        if( minMax.isMax()){
            profit += honorFactor;
        }
        return profit;
    }

    @Override
    public Double calcShame(Subject[] group, Double profit, Double shameFactor) {
        MinMax minMax = findMinMax(group);

        //worst player
        if(minMax.isMin()){
            profit -= shameFactor;
        }
        return profit;
    }
}
