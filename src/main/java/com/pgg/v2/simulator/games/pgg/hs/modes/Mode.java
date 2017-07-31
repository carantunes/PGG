package com.pgg.v2.simulator.games.pgg.hs.modes;

import com.pgg.v2.simulator.games.minmax.MinMax;
import com.pgg.v2.simulator.games.subject.Subject;

/**
 * Created by Carina on 30/05/2017.
 */
public interface Mode {

    Double calcHonor(MinMax minMax, Subject player, Double profit, Double honorFactor);
    Double calcShame(MinMax minMax, Subject player, Double profit, Double shameFactor);
}
