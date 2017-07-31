package com.pgg.v2.simulator.games.population;

/**
 * Created by Carina on 25/06/2017.
 */
public class PopulationFactory {

    public static Population createPopulation(int networkOption
    ){
        Population population;
        switch (networkOption) {
            case Constants.MODE_COMPLEX:
                population = new ComplexPopulation();
                break;
            case Constants.MODE_RANDOM:
                population = new RandomPopulation();
                break;
            case Constants.MODE_UNIFORM:
            default:
                population = new UniformPopulation();
                break;
        }
        return population;
    }
}
