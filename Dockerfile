FROM maven:3.8.6-amazoncorretto-17 AS builder
COPY pom.xml /app/
COPY src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} card2brain.jar
COPY --from=builder /app/target/ card2brain.jar
ENTRYPOINT ["java","-jar","/card2brain.jar"]
