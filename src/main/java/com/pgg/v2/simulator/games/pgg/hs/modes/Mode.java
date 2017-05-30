package com.pgg.v2.simulator.games.pgg.hs.modes;

import com.pgg.v2.simulator.games.subject.Subject;

/**
 * Created by Carina on 30/05/2017.
 */
public interface Mode {

    Double calcHonor(Subject[] group, Double profit, Double honorFactor);
    Double calcShame(Subject[] group, Double profit, Double shameFactor);
}
