# com.pgg.com.pgg.PGG
Public Goods Game Project for Complex Networks course

***


### Instalation 
    javac com.pgg.com.pgg.PGG.java
    
### Running 
    java com.pgg.com.pgg.PGG [population_size] [group_size] [factor] [times] [beta] [game_name] [optional params]

    Optional params: [shameFactor]

    Param game_name:
        PGG
        PGGShame [shameFactor]
        PGGShame [honorFactor]
        PGGHonorShame [honorFactor] [shameFactor]
#### Example:
    java com.pgg.com.pgg.PGG 50 5 1.5 10 5 PGGShame 0.2
