package com.pgg.v2.simulator.games.minmax;

import com.pgg.v2.simulator.games.pgg.hs.modes.Mode;
import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Carina on 30/05/2017.
 */


public class MinMaxUtils {

    public static MinMax findMinMax(Subject player, Subject[] population) {
        if (population == null || population.length < 1)
            return null;

        List<Subject> minSubjects = new ArrayList<>();
        List<Subject> maxSubjects = new ArrayList<>();

        minSubjects.add(population[0]);
        maxSubjects.add(population[0]);

        double max = maxSubjects.get(0).getOffer();
        double min = minSubjects.get(0).getOffer();

        for (int i = 1; i < population.length ; i++) {

            if(max == population[i].getOffer()) {
                maxSubjects.add(population[i]);
            }else if (max < population[i].getOffer()) {
                max = population[i].getOffer();
                maxSubjects.clear();
                maxSubjects.add(population[i]);
            }

            if (min == population[i].getOffer()) {
                minSubjects.add(population[i]);
            }else if (min > population[i].getOffer()) {
                min = population[i].getOffer();
                minSubjects.clear();
                minSubjects.add(population[i]);
            }

        }
        return new MinMax(minSubjects,maxSubjects);
    }
}
