FROM openjdk:8-jdk-alpine
MAINTAINER anuragroy.com
COPY target/image-compressor-1.0.0.jar image-compressor.jar
ENTRYPOINT ["java","-jar","/image-compressor.jar"]