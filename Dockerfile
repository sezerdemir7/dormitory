FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/dormitory-0.0.1-SNAPSHOT.jar dormitory.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dormitory.jar"]