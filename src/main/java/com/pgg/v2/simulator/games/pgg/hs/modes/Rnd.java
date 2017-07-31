package com.pgg.v2.simulator.games.pgg.hs.modes;

import com.pgg.v2.simulator.games.minmax.MinMax;
import com.pgg.v2.simulator.games.minmax.MinMaxUtils;
import com.pgg.v2.simulator.games.subject.Subject;

/**
 * Created by Carina on 30/05/2017.
 */
public class Rnd implements Mode {


    @Override
    public Double calcHonor(MinMax minMax, Subject player, Double profit, Double honorFactor)
    {
        //best player
        if( minMax.getRandomMax().equals(player)){
            profit += honorFactor;
        }

        return profit;
    }

    @Override
    public Double calcShame(MinMax minMax, Subject player, Double profit, Double shameFactor)
    {
        //worst player
        if(minMax.getRandomMin().equals(player)){
            profit -= shameFactor;
        }

        return profit;
    }
}
