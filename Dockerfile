ARG BUILD_HOME=/home/gradle/project

# Stage1: Build Usar la imagen base de gradle:8-jdk17-alpine
FROM gradle:8.6.0-jdk17-alpine AS build

# Set the working directory inside the container
WORKDIR /home/gradle/project

# Copiar los archivos de construcción de Gradle
COPY ./gradlew ./gradlew
COPY ./gradle ./gradle
COPY ./build.gradle ./build.gradle
COPY ./settings.gradle ./settings.gradle
COPY ./src ./src

# Copiar el directorio de scripts
COPY ./scripts ./scripts

RUN gradle wrapper

# Dar permisos de ejecución al gradlew
RUN chmod u+x ./gradlew

# Construir el proyecto usando Gradle
RUN ./gradlew clean build

# Usar la imagen base de OpenJDK
FROM amazoncorretto:17-alpine3.16

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# RUN ls /home/gradle/project/

# Copy the built JAR file from the previous stage
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

# Establecer el puerto en el que la aplicación correrá
EXPOSE 8080

# Ejecutar la aplicación

ENTRYPOINT ["java", "-jar", "/app/app.jar"]