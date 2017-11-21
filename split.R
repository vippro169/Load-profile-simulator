setwd('~/Documents/Project 1/household 1/01_plugs_csv/01/06')
filename <- list.files(pattern = "*.csv")
for(i in 1:length(filename)){
  power <- read.csv(filename[i])
  time <- seq(ISOdate(2017,11,7), by='sec', length.out = 86399)
  
  smr <- data.frame(unclass(summary(power)))
  preference <- power[,1]
  preference[preference <= 20] = 0
  a <- data.frame(unclass(rle(preference)))
  
  #preference <- round(preference$X49.2516/10)*10
  listOfSignature <- split(a,cumsum(a$lengths>100), cumsum(a$values==0))
  
  for(l in 1:length(listOfSignature)){
    listOfSignature[[l]]$lengths[listOfSignature[[l]]$values == 0 && listOfSignature[[l]]$lengths > 100] = 1
  }
  outpath=paste('~/Documents/Project 1/household 1/01_plugs_csv/Output/app_6',sep='')
  setwd(outpath)
  for(j in 1:length(listOfSignature)){
    tmp <- inverse.rle(listOfSignature[[j]])
    savename <- paste('6__',filename[i],'__filenum_',j,'.csv', sep='')
    
    write.csv(tmp, file = savename)  
  }
  setwd('~/Documents/Project 1/household 1/01_plugs_csv/01/06')
  # tmp <- inverse.rle(listOfSignature[[1]])
  # plot(tmp, type='l', ylim=c(0,600))  
}
