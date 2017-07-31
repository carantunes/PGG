package com.pgg.v2.simulator.games;

import com.pgg.v2.simulator.games.subject.Subject;

/**
 * Created by Carina on 30/05/2017.
 */
public interface Game {
    public double playGame(Subject subject, Subject[] neighbours_group);
}
