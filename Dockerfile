FROM openjdk:17-jdk-slim-buster
MAINTAINER anuragroy.com
COPY target/image-compressor-1.0.0.jar image-compressor.jar
ENTRYPOINT ["java","-jar","/image-compressor.jar"]