package com.pgg.v2.simulator.games;

import com.pgg.v2.simulator.games.pgg.PGG;
import com.pgg.v2.simulator.games.pgg.hs.HSThreshold;
import com.pgg.v2.simulator.games.pgg.hs.HSThresholdDependent;
import com.pgg.v2.simulator.games.pgg.hs.HonorShame;
import com.pgg.v2.simulator.games.pgg.hs.modes.ModeFactory;

/**
 * Created by Carina on 30/05/2017.
 */
public class GameFactory {

    public static PGG createPGG(String game, Double avg, Double std_variance, Double honorFactor, Double shameFactor, int modeOption){
       PGG pgg;

       switch (game){
           case GameConstants.HS:
               pgg = GameFactory.createHS(avg,std_variance, honorFactor, shameFactor, modeOption);
               break;
           case GameConstants.HS_T:
               pgg = GameFactory.createHSThreshold(avg,std_variance, honorFactor, shameFactor, modeOption);
               break;
           case GameConstants.HS_T2:
               pgg = GameFactory.createHSThreshold2(avg,std_variance, honorFactor, shameFactor, modeOption);
               break;
           case GameConstants.PGG:
           default:
                pgg = GameFactory.createSimple(avg,std_variance);
                break;
        }

        return pgg;
    }

    private static PGG createSimple(Double avg, Double std_variance){
        return new PGG(avg,std_variance);
    }


    private static PGG createHS(Double avg, Double std_variance, Double honorFactor, Double shameFactor, int modeOption){
        return new HonorShame(avg,std_variance, honorFactor,shameFactor, ModeFactory.createMode(modeOption));
    }

    private static PGG createHSThreshold(Double avg, Double std_variance, Double honorFactor, Double shameFactor, int modeOption){
        return new HSThresholdDependent(avg,std_variance, honorFactor,shameFactor, ModeFactory.createMode(modeOption));
    }

    private static PGG createHSThreshold2(Double avg, Double std_variance, Double honorFactor, Double shameFactor, int modeOption){
        return new HSThreshold(avg,std_variance, honorFactor,shameFactor, ModeFactory.createMode(modeOption));
    }
}
