package com.pgg.v2.simulator.games.population;

/**
 * Created by Carina on 25/06/2017.
 */
public class PopulationFactory {

    public static Population createPopulation(int networkOption,
                                              Double avg,
                                              Double std_variance
    ){
        Population population;
        switch (networkOption) {
            case Constants.MODE_COMPLEX:
                population = new ComplexPopulation(avg,std_variance);
                break;
            case Constants.MODE_RANDOM:
            default:
                population = new RandomPopulation(avg,std_variance);
                break;
        }
        return population;
    }
}
