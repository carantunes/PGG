library(gplots)

matrix <- as.matrix(read.table(
  "/Users/Carina/Documents/Workspace/PGG/stats/DataAnalysis/stats/PGG/0/PGG_MATRIX__NET-0_stats_F-3.0_B-10.0_AVG-0.9.txt", row.names=1, header=TRUE))

levels=20

filled.contour(matrix, 
#               xlab = "Honor", 
#               ylab = "Shame", 
               
               xlab = "Honor", 
               ylab = "Shame", 
               
               zlim=c(0,1),
               nlevels = 60, 
               col=colorpanel(60,"cyan", "yellow", "red"), 
#               main="Shame & Honor Impact on Avg. Offer")
               main="Initial avg. offer impact on final Avg. Offer")


mtext("Initial average offer = 0.5            ",outer=T,side=3,line=-4)



