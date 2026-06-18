# ---- Etapa 1: build con Maven + JDK 21 ----
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Compilar el jar
COPY src ./src
RUN mvn clean package -DskipTests -B

# ---- Etapa 2: ejecución con JRE 21 ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/tayta-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
