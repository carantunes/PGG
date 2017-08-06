values1 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.0.txt", header = FALSE)
values2 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.2.txt", header = FALSE)
values3 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.3.txt", header = FALSE)
values4 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.4.txt", header = FALSE)
values5 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.5.txt", header = FALSE)
values6 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.6.txt", header = FALSE)
values7 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.7.txt", header = FALSE)
values8 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.8.txt", header = FALSE)
values9 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-.9.txt", header = FALSE)
values10 <- read.csv("stats/Honor/PGGHonor_stats_F-3.0_B-1.0_H-1.0.txt", header = FALSE)
len <- length(values1[[1]])
plot(1:len, values1[[1]], ylim = c(0,1), type = "l", main = "Honor Impact on Cooperation", xlab = "Generations", ylab = "AVG Offer", col="darkgoldenrod4")
lines(1:len, values2[[1]], col = "green")
lines(1:len, values3[[1]], col = "purple")
lines(1:len, values4[[1]], col = "red")
lines(1:len, values5[[1]], col = "blue")
lines(1:len, values6[[1]], col = "black")
lines(1:len, values7[[1]], col = "orange")
lines(1:len, values8[[1]], col = "deeppink")
lines(1:len, values9[[1]], col = "olivedrab1")
lines(1:len, values10[[1]], col = "lightsalmon")

par(mar=c(5.1, 5, 5, 8.1), xpd=TRUE)
legend(x="topright",inset=c(-0.31,0),
       legend=c("Honor = 0.1","Honor = 0.2","Honor = 0.3","Honor = 0.4","Honor = 0.5","Honor = 0.6","Honor = 0.7","Honor = 0.8","Honor = 0.9", "Honor = 1.0"),
       lty=c(1),
       lwd=1,
       col=c("darkgoldenrod4","green","purple","red","blue","black","orange","deeppink","olivedrab1","lightsalmon") )
