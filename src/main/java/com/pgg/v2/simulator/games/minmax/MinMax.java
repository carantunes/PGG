package com.pgg.v2.simulator.games.minmax;

import com.pgg.v2.simulator.games.subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Carina on 04/12/2016.
 */
public class MinMax {
    List<Subject> minSubjects = new ArrayList<>();
    List<Subject> maxSubjects = new ArrayList<>();

    public MinMax(List<Subject> minSubjects,List<Subject>  maxSubjects) {
        this.maxSubjects = maxSubjects;
        this.minSubjects = minSubjects;
    }

    /**
     * Returns true if the subject is a player who offered the
     * smaller value
     *
     * */
    public boolean isInMinPlayers(Subject player) {
        return minSubjects.contains(player);
    }

    /**
     * Returns true if the subject is a player who offered the
     * higher value
     *
     * */
    public boolean isInMaxPlayers(Subject player) {
        return maxSubjects.contains(player);
    }

    /**
     * Returns true if the subject is the random player who offered the
     * smaller value
     *
     * */
    public Subject getRandomMin() {
        int rnd = new Random().nextInt(minSubjects.size());
        return minSubjects.get(rnd);
    }

    /**
     * Returns true if the subject is the random player who offered the
     * smaller value
     *
     * */
    public Subject getRandomMax() {
        int rnd = new Random().nextInt(maxSubjects.size());
        return maxSubjects.get(rnd);
    }

    /**
     * Returns total numbers of simultaneous players who offered
     * the smaller value
     *
     * */
    public int getTotalMin(){
        return minSubjects.size();
    }

    /**
     * Returns total numbers of simultaneous players who offered
     * the higher value
     *
     * */
    public int getTotalMax(){
        return maxSubjects.size();
    }



}
