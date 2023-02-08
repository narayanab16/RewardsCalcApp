# RewardsCalcApp

# Solution steps
        2 points spent over $100 plus 1 point spent over 50
    Ex: $120 purchase = 2x$20 + 1x$50 = 90 points. -- this is perfect
    Ex: $52.35 purchase to be calculated over $50 i.e 52.35-50 = 2.35 * 1 --> this is my assumption of logic- is that correct?
    
    Step1 : Java JDK 18 installed and java_home Setup done 
    Step2 : Maven Installed and  maven_home Setup done [or] use mvnw.cmd
    step3 : to run test: cmd ---> mvn test 
    step4 : to clean and package cmd ---> mvn clean package
    step5 : to run in BG PS D:\tmp\gitt\RewardsCalcApp> java -jar .\target\pointsapp-0.0.1-SNAPSHOT.jar
    step6 : sample url on browser: http://localhost:8080/points-api/calcRewards?startDate=2022-01-01&monthPeriod=3
    step7 : output: {"Narayana":{"1":192.0,"2":96.0},"Basetty":{"1":100.0,"2":50.0}}
    step8 : Static data hardcoded in code base package db/CustomerDB.java
