# com.pgg.PGGSimulator
Public Goods Game Project for Masters Thesis

***


### Instalation 
    javac com.pgg.v2.simulator
    
### Parameters
    Change static parameters in file  com.pgg.v2.simulator.Parameters;
    
    double EPS = 0.05;
    int POPULATION_SIZE = 600;
    int GROUP_SIZE = 6;
    double FACTOR = 3;
    int N_GAMES = 600;
    double BETA = 10;
    int GENERATIONS = 10000;
    int THRESHOLD = 3;

    /**
     * @see : com.pgg.v2.HSSimulator.games.HonorShame.modes.Constants;
     */
    int MODE = 2;
    
        Options:    
        int MODE_ALL = 0;
        int MODE_RND = 1;
        int MODE_SHARE = 2;
        
### Running 
    java com.pgg.v2.simulator.HSSimulator [game_name]

    Param game_name:
    @see: package com.pgg.v2.simulator.games.GameConstants;
    
        HonorShame;
        HSThresholdDependent;
        HSThreshold;
#### Example:
    java com.pgg.v2.simulator.HSSimulator HSThreshold
