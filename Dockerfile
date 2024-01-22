FROM amazoncorretto:11
EXPOSE 8080
ADD target/receiptProcessorChallenge-0.0.1-SNAPSHOT.jar receiptProcessorChallenge.jar
ENTRYPOINT ["java","-jar","/receiptProcessorChallenge.jar"]
