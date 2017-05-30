package com.pgg.v2.simulator.games.subject;

public class Subject {
    private double offer;

    public Subject(double offer){
        this.offer = offer;
    }

    public double getOffer(){
        return this.offer;
    }
    public void setOffer(double offer){
        this.offer = offer;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Subject && ((Subject) obj).getOffer() == offer;
    }
}
