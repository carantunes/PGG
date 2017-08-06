values1 <- read.csv("stats/Beta/PGG_stats_F-3.0_B-.00.txt", header = FALSE)
values2 <- read.csv("stats/Beta/PGG_stats_F-3.0_B-.50.txt", header = FALSE)
values3 <- read.csv("stats/Beta/PGG_stats_F-3.0_B-1.00.txt", header = FALSE)
values4 <- read.csv("stats/Beta/PGG_stats_F-3.0_B-2.00.txt", header = FALSE)
values5 <- read.csv("stats/Beta/PGG_stats_F-3.0_B-3.00.txt", header = FALSE)
values6 <- read.csv("stats/Beta/PGG_stats_F-3.0_B-4.00.txt", header = FALSE)
values7 <- read.csv("stats/Beta/PGG_stats_F-3.0_B-5.00.txt", header = FALSE)
len <- length(values1[[1]])
plot(1:len, values1[[1]], ylim = c(0,0.5), type = "l", main = "Beta Impact on Cooperation", xlab = "Generations", ylab = "AVG Offer", col="darkgoldenrod4")
lines(1:len, values2[[1]], col = "green")
lines(1:len, values3[[1]], col = "purple")
lines(1:len, values4[[1]], col = "red")
lines(1:len, values5[[1]], col = "blue")
lines(1:len, values6[[1]], col = "black")
lines(1:len, values7[[1]], col = "orange")

par(mar=c(5.1, 5, 5, 8.1), xpd=TRUE)
legend("topright",inset=c(-0.31,0),
      legend=c("Beta = 0","Beta = 0.5","Beta = 1","Beta = 2","Beta = 3","Beta = 4","Beta = 5"),
      lty=c(1),    lwd=1,
      col=c("darkgoldenrod4","green","purple","red","blue","black","orange") )
