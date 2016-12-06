# com.pgg.PGGSimulator
Public Goods Game Project for Complex Networks course

***


### Instalation 
    javac com.pgg.PGGSimulator
    
### Running 
    java com.pgg.PGGSimulatorNTimes [population_size] [group_size] [factor] [times] [beta] [game_name] [optional params]
    java com.pgg.PGGSimulatorMatrix [population_size] [group_size] [factor] [times] [beta] [game_name] [optional params]

    Param game_name:
        PGG
        PGGShame 
        PGGHonor 
        PGGHonorShameShare 
        PGGHonorShameAll
        PGGHonorShameRnd
        PGGHonorShameThreshold
#### Example:
    java com.pgg.PGGSimulatorMatrix 500 6 1.5 500 1 10000 PGGHonorShame
