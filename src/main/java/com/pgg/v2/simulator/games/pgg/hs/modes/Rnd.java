package com.pgg.v2.simulator.games.pgg.hs.modes;

import com.pgg.v2.simulator.games.minmax.MinMax;
import com.pgg.v2.simulator.games.minmax.MinMaxUtils;
import com.pgg.v2.simulator.games.subject.Subject;

/**
 * Created by Carina on 30/05/2017.
 */
public class Rnd implements Mode {


    @Override
    public Double calcHonor(Subject[] group, Double profit, Double honorFactor) {
        MinMax minMax = MinMaxUtils.findMinMaxRnd(group);
        //best player
        if( minMax.isMax()){
            profit += honorFactor;
        }
        return profit;
    }

    @Override
    public Double calcShame(Subject[] group, Double profit, Double shameFactor) {
        MinMax minMax = MinMaxUtils.findMinMaxRnd(group);
        //worst player
        if(minMax.isMin()){
            profit -= shameFactor;
        }
        return profit;
    }
}
