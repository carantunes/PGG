package com.pgg.v2.simulator.games.minmax;

/**
 * Created by Carina on 04/12/2016.
 */
public class MinMax {
    private final boolean isMin;
    private final boolean isMax;
    private final int totalMin;
    private final int totalMax;

    public MinMax(boolean isMin, boolean isMax, int totalMin, int totalMax) {
        this.isMin = isMin;
        this.isMax = isMax;
        this.totalMax = totalMax;
        this.totalMin = totalMin;
    }

    /**
     * Returns true if the subject is a player who offered the
     * smaller value
     *
     * */
    public boolean isMin() {
        return isMin;
    }

    /**
     * Returns true if the subject is a player who offered the
     * higher value
     *
     * */
    public boolean isMax() {
        return isMax;
    }

    /**
     * Returns total numbers of simultaneous players who offered
     * the smaller value
     *
     * */
    public int getTotalMin(){   return totalMin;}

    /**
     * Returns total numbers of simultaneous players who offered
     * the higher value
     *
     * */
    public int getTotalMax(){ return totalMax;}

}
