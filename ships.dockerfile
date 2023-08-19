FROM openjdk:11-slim-buster
WORKDIR /app
COPY /build/libs/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar", "ships", "server"]
