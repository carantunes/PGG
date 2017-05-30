package com.pgg.v2.simulator.games.minmax;

import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Carina on 30/05/2017.
 */


public class MinMaxUtils {
    public static MinMax findMinMax(Subject[] population) {
        if (population == null || population.length < 1)
            return null;

        List<Integer> indexMax = new ArrayList<Integer>();
        List<Integer> indexMin = new ArrayList<Integer>();
        double min = population[0].getOffer();
        double max = population[0].getOffer();

        indexMax.add(0);
        indexMin.add(0);
        for (int i = 1; i < population.length ; i++) {
            if(max == population[i].getOffer()) {
                indexMax.add(i);
            }else if (max < population[i].getOffer()) {
                max = population[i].getOffer();
                indexMax.clear();
                indexMax.add(i);
            }

            if (min == population[i].getOffer()) {
                indexMin.add(i);
            }else if (min > population[i].getOffer()) {
                min = population[i].getOffer();
                indexMin.clear();
                indexMin.add(i);
            }

        }

        //NOTE: subject is always last in group
        boolean isMin = indexMin.contains(population.length - 1);
        boolean isMax = indexMax.contains(population.length - 1);

        return new MinMax(isMin,isMax, indexMin.size(), indexMax.size());
    }

    public static MinMax findMinMaxRnd(Subject[] population) {
        if (population == null || population.length < 1)
            return null;

        List<Integer> indexMax = new ArrayList<Integer>();
        List<Integer> indexMin = new ArrayList<Integer>();
        double min = population[0].getOffer();
        double max = population[0].getOffer();

        indexMax.add(0);
        indexMin.add(0);
        for (int i = 1; i < population.length ; i++) {
            if(max == population[i].getOffer()) {
                indexMax.add(i);
            }else if (max < population[i].getOffer()) {
                max = population[i].getOffer();
                indexMax.clear();
                indexMax.add(i);
            }

            if (min == population[i].getOffer()) {
                indexMin.add(i);
            }else if (min > population[i].getOffer()) {
                min = population[i].getOffer();
                indexMin.clear();
                indexMin.add(i);
            }

        }
        //NOTE: subject is always last in group

        boolean isMin = getRandom(indexMin) == population.length - 1;
        boolean isMax = getRandom(indexMax) == population.length - 1;

        return new MinMax(isMin,isMax, indexMin.size(), indexMax.size());
    }


    public static int getRandom(List<Integer> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }

}
