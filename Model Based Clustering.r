library("mclust")
library("factoextra")
library("scatterplot3d")
library("rgl")
library("RColorBrewer")
library("ChemoSpec")

#Split
rm(list = ls(pattern = "*"))
setwd('./Output/app_5')
filename <- list.files(pattern = "*.csv")
file <- filename
val=NULL
cy=NULL
max=NULL
j=1
for(i in 1:length(filename)){
  data<-read.csv(filename[i])
  if(mean(data[,2]!=0 & max(data[,1])>10)){
    val[j]=mean(data[,2])
    cy[j]=max(data[,1])
    max[j]=max(data[,2])
    j=j+1
  }
  else file <- file[!file %in% filename[i]]
}
#3D plot
#plot3d(max, val, cy)

df <- data.frame(max, val, cy)
#df <- odata[!(odata$cy<=10),]
#ds <- scale(df)

#Model Based Clustering
mc <- Mclust(df)
class <- mc$classification
#summary(mc, parameters = TRUE)
for(a in 1:mc$G){
 div <- NULL
 z <- 0
  for(b in 1:length(class)){
     if(class[b]==a){
       newdata<-read.csv(file[b])
       dt <- newdata[,2]
       if(length(div)<length(dt)) div = dt + c(div, rep(0, length(dt)-length(div)))
       else if(length(div)>length(dt)) div = div+c(dt, rep(0, length(div)-length(dt)))
       else div = div+dt
       z = z+1;
     }
  }
 div = div/z;
 assign(paste("load",a,sep=""), div)
}

