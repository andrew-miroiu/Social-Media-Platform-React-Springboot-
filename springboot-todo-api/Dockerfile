# Stage 1: Build the JAR with Maven
FROM maven:3.9.1-eclipse-temurin-23-jdk AS build

WORKDIR /app

# Copiază pom.xml și sursele
COPY pom.xml .
COPY src ./src

# Build JAR fără teste
RUN mvn clean package -DskipTests

# Stage 2: Final image cu JDK slim
FROM eclipse-temurin:23-jdk-slim

WORKDIR /app

# Copiază JAR-ul construit
COPY --from=build /app/target/springboot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]