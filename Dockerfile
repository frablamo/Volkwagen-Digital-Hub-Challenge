# syntax=docker/dockerfile:1

FROM eclipse-temurin:22-jdk AS build
WORKDIR /app

# Install Gradle
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.7-bin.zip && \
    unzip gradle-8.7-bin.zip -d /opt && \
    ln -s /opt/gradle-8.7/bin/gradle /usr/bin/gradle

COPY . .
RUN gradle build

FROM eclipse-temurin:22-jdk
WORKDIR /app
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
