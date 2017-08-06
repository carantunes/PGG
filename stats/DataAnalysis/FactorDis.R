values1 <- read.csv("stats/Factor/PGG_stats_F-.00_B-1.0.txt", header = FALSE)
values2 <- read.csv("stats/Factor/PGG_stats_F-1.00_B-1.0.txt", header = FALSE)
values3 <- read.csv("stats/Factor/PGG_stats_F-2.00_B-1.0.txt", header = FALSE)
values4 <- read.csv("stats/Factor/PGG_stats_F-3.00_B-1.0.txt", header = FALSE)
values5 <- read.csv("stats/Factor/PGG_stats_F-4.00_B-1.0.txt", header = FALSE)
values6 <- read.csv("stats/Factor/PGG_stats_F-5.00_B-1.0.txt", header = FALSE)
values7 <- read.csv("stats/Factor/PGG_stats_F-6.00_B-1.0.txt", header = FALSE)
values8 <- read.csv("stats/Factor/PGG_stats_F-7.50_B-1.0.txt", header = FALSE)
len <- length(values1[[1]])
plot(1:len, values1[[1]], ylim = c(0,1), type = "l", main = "Factor Impact on Cooperation", xlab = "Generations", ylab = "AVG Offer", col="darkgoldenrod4")
lines(1:len, values2[[1]], col = "green")
lines(1:len, values3[[1]], col = "purple")
lines(1:len, values4[[1]], col = "red")
lines(1:len, values5[[1]], col = "blue")
lines(1:len, values6[[1]], col = "black")
lines(1:len, values7[[1]], col = "orange")
lines(1:len, values8[[1]], col = "brown")

par(mar=c(5.1, 5, 5, 8.1), xpd=TRUE)
legend(x="topright",inset=c(-0.31,0),
       legend=c("F = 0","F = 1","F = 2","F = 3","F = 4","F = 5","F = 6","F = 7.5"),
       lty=c(1),
       col=c("darkgoldenrod4","green","purple","red","blue","black","orange", "brown") )
