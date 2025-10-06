# 🐳 Dockerfile

FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY *.gradle* ./

RUN ./gradlew dependencies || true

COPY . .

RUN chmod +x gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
