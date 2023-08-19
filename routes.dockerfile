FROM openjdk:11-slim-buster
WORKDIR /app
COPY /build/libs/*.jar /app/app.jar
COPY apps/main/resources/marnet/ /app/marnet/
CMD ["java", "-jar", "/app/app.jar", "routes", "server"]
