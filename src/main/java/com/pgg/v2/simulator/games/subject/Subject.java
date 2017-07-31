package com.pgg.v2.simulator.games.subject;

public class Subject {
    private double offer;
    private int id;

    public Subject(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getOffer(){
        return this.offer;
    }
    public void setOffer(double offer){
        this.offer = offer;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Subject
                && ((Subject) obj).getId() == id
                && ((Subject) obj).getOffer() == offer;
    }
}
