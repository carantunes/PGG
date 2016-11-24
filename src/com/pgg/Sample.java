package com.pgg;

import java.util.Random;

/**
 * Created by diogo on 24-11-2016.
 */
public class Sample {

    private double offer;
    private double fitness;

    protected Sample(double offer){
        this.offer = offer;
    }

    protected double getOffer(){
        return this.offer;
    }
    protected double getFitness(){
        return this.fitness;
    }

    protected void setOffer(double offer){
        this.offer = offer;
    }

    protected void setFitness(double fitness){
        this.fitness = fitness;
    }

    public void update(){

    }

}
