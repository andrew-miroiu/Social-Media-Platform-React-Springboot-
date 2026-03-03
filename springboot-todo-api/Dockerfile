# Stage 1: Build the JAR with Maven
FROM maven:3.9.1-eclipse-temurin-23 AS build

WORKDIR /app

# Copiază pom.xml și codul sursă
COPY pom.xml .
COPY src ./src

# Rulează Maven build (fără teste pentru viteză)
RUN mvn clean package -DskipTests

# Stage 2: Crează imaginea finală cu JDK Slim
FROM eclipse-temurin:23-jdk-slim

WORKDIR /app

# Copiază JAR-ul construit în etapa anterioară
COPY --from=build /app/target/springboot-0.0.1-SNAPSHOT.jar app.jar

# Expune portul 8080
EXPOSE 8080

# Pornirea aplicației
ENTRYPOINT ["java", "-jar", "app.jar"]