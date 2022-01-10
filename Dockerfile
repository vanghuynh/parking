# Docker image for java app
FROM bitnami/java:1.8
WORKDIR /app
COPY ./target/parking-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "parking-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
