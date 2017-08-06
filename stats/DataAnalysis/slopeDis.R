library(gplots)

ldf <- list() # creates a list
listcsv <- dir("stats/HS/", pattern = "*") # creates the list of all the csv files in the directory

values <- list()
results <- list()
for (k in 1:length(listcsv)){
  values[k] <- read.csv(paste("stats/HS/",listcsv[k],sep="",collapse = ""), header = FALSE)
  len <- length(values[k][[1]][4000:6000])
  fixed <- 1:len
  fit <- lm(values[k][[1]][4000:6000] ~ fixed)
  results[k] <- fit$coefficients[[2]]
}

C1 <- c(0:10)
C2 <- c(0:11)
C2[1] <- ""
for (k in 2:12){
  C2[k] <- k - 2
}
B <- matrix(c(results),nrow=11,ncol=11)
B <- cbind(C1,B)
B <- rbind(C2,B)
      
write.table(B, file="stats/mymatrix.txt", row.names=FALSE, col.names=FALSE)
matrix <- as.matrix(read.table("stats/mymatrix.txt", row.names=1, header=TRUE))

filled.contour(matrix, col=colorpanel(16,"lightblue", "white","lightgreen"), xlab = "Honor", ylab = "Shame",main="Shame & Honor Cooperation Slope 4000:6000")