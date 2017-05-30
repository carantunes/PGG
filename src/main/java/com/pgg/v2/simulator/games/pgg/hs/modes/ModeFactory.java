package com.pgg.v2.simulator.games.pgg.hs.modes;

/**
 * Created by Carina on 30/05/2017.
 */
public class ModeFactory {

    public static Mode createMode(int modeOption){
        Mode mode;
        switch (modeOption) {
            case Constants.MODE_RND:
                mode = new Rnd();
                break;
            case Constants.MODE_ALL:
                mode = new All();
                break;
            case Constants.MODE_SHARE:
            default:
                mode = new Share();
                break;
        }
        return mode;
    }
}
