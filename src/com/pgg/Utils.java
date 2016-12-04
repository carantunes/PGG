package com.pgg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Utils {

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

        int iMin = indexMin.contains(new Integer(5)) ? 5 : getRandom(indexMin);

        int iMax = indexMax.contains(new Integer(5)) ? 5 : getRandom(indexMax);
        return new MinMax(iMin,iMax, indexMin.size(), indexMin.size());
        //return new MinMax(getRandom(indexMin),getRandom(indexMax), indexMin.size(), indexMin.size());
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

        int iMin = indexMin.contains(new Integer(5)) ? 5 : getRandom(indexMin);

        int iMax = indexMax.contains(new Integer(5)) ? 5 : getRandom(indexMax);
        //return new MinMax(iMin,iMax, indexMin.size(), indexMin.size());
        return new MinMax(getRandom(indexMin),getRandom(indexMax), indexMin.size(), indexMin.size());
    }


    public static int getRandom(List<Integer> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }

}
