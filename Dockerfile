#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim-buster
MAINTAINER anuragroy.com
COPY --from=build /target/image-compressor-1.0.0.jar image-compressor.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","/image-compressor.jar"]
