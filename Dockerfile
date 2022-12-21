FROM maven:3.8.3-openjdk-11-slim AS builder
RUN --mount=type=cache,target=/root/.m2 mvn -B package --file pom.xml
COPY pom.xml /app/
COPY src /app/src
FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} card2brain.jar
COPY --from=builder /app/target/ card2brain.jar
ENTRYPOINT ["java","-jar","/card2brain.jar"]
