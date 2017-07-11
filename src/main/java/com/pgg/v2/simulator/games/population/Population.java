package com.pgg.v2.simulator.games.population;

import com.pgg.v2.simulator.Parameters;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Carina on 25/06/2017.
 */
public interface Population {

    public double getOfferAverage();
    public Subject getSubject(int index);
    public Subject[] getSubjectNeighborsIndex(int index);
}
