FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=build/libs/receiptProcessorChallenge-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
