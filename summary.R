setwd('~/Documents/Project 1/household 1/01_plugs_csv/Output/app_2')
filename <- list.files(pattern = "*.csv")
val=NULL
cy=NULL
max=NULL
j=1
for(i in 1:length(filename)){
  data<-read.csv(filename[i])
  if(mean(data[,2]!=0)){
    val[j]=mean(data[,2])
    cy[j]=max(data[,1])
    max[j]=max(data[,2])
    j=j+1
  }
}
plot(val,cy)
