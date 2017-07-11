package com.pgg.v2.simulator.games;

import com.pgg.v2.simulator.games.pgg.PGG;
import com.pgg.v2.simulator.games.pgg.hs.HSThreshold;
import com.pgg.v2.simulator.games.pgg.hs.HSThresholdDependent;
import com.pgg.v2.simulator.games.pgg.hs.HonorShame;
import com.pgg.v2.simulator.games.pgg.hs.modes.Mode;
import com.pgg.v2.simulator.games.pgg.hs.modes.ModeFactory;
import com.pgg.v2.simulator.games.population.Population;
import com.pgg.v2.simulator.games.population.PopulationFactory;

/**
 * Created by Carina on 30/05/2017.
 */
public class GameFactory {

    public static PGG createPGG(String game,
                                Double avg,
                                Double std_variance,
                                Double honorFactor,
                                Double shameFactor,
                                int modeOption,
                                int networkOption){
       PGG pgg;

       Population population = PopulationFactory.createPopulation(networkOption, avg, std_variance);
       Mode mode = ModeFactory.createMode(modeOption);

       switch (game){
           case GameConstants.HS:
               pgg = new HonorShame(population, honorFactor,shameFactor, mode);
               break;
           case GameConstants.HS_T:
               pgg = new HSThresholdDependent(population, honorFactor,shameFactor, mode);
               break;
           case GameConstants.HS_T2:
               pgg = new HSThreshold(population, honorFactor,shameFactor, mode);
               break;
           case GameConstants.PGG:
           default:
                pgg = new PGG(population);
                break;
        }

        return pgg;
    }

}
