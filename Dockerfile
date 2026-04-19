# Stage 1: Build
FROM eclipse-temurin:23-jdk-alpine AS build
WORKDIR /app

# Copy wrapper and build files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# FIX: Convert Windows line endings to Unix and make it executable
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

RUN ./mvnw dependency:go-offline

# Copy source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:23-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*-with-dependencies.jar app.jar

LABEL authors="andre"
ENTRYPOINT ["java", "-jar", "app.jar"]