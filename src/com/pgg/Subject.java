package com.pgg;

public class Subject {
    private double offer;

    protected Subject(double offer){
        this.offer = offer;
    }

    protected double getOffer(){
        return this.offer;
    }
    protected void setOffer(double offer){
        this.offer = offer;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Subject && ((Subject) obj).getOffer() == offer;
    }
}
