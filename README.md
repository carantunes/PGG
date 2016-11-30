# com.pgg.PGGSimulator
Public Goods Game Project for Complex Networks course

***


### Instalation 
    javac com.pgg.PGGSimulator
    
### Running 
    java com.pgg.PGGSimulator [population_size] [group_size] [factor] [times] [beta] [game_name] [optional params]

    Param game_name:
        PGG
        PGGShame [shameFactor]
        PGGShame [honorFactor]
        PGGHonorShame [honorFactor] [shameFactor]
#### Example:
    java com.pgg.PGGSimulator 50 5 1.5 10 5 PGGShame 0.2
