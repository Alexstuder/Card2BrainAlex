FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} card2brain.jar
ENTRYPOINT ["java","-jar","/card2brain.jar"]
