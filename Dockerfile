# 🐳 Dockerfile

FROM gradle:8-jdk17-alpine AS build
WORKDIR /app

# Kopiera projektet
COPY . .

# Bygg applikationen
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Kopiera JAR-filen
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]