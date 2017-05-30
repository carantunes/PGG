package com.pgg.v2.simulator.exceptions;

import com.pgg.v2.simulator.games.pgg.hs.HonorShame;
/**
 * Created by Carina on 30/05/2017.
 */
public class InvalidGameException extends Exception {

    public InvalidGameException(String game) {
        super("Invalid game '" + game + "'. simulator runs only games in " + HonorShame.class.getPackage().toString());
    }
}
