# 🐳 Dockerfile

# Basimage: en liten och snabb OpenJDK 17 med Alpine Linux.
FROM eclipse-temurin:17-jdk-alpine
# För att stödja Spring Boot’s temporära filer.
VOLUME /tmp
# Tillåter att du bygger mot vilken .jar som ligger där (från ditt gradle/maven build).
ARG JAR_FILE=build/libs/*.jar
# Kopierar jar-filen från build-mappen i ditt projekt in till imagen som /app.jar.
COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java", "-jar", "/app.jar"]
ENTRYPOINT ["java", "-jar", "/app.jar"]