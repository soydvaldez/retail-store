# Usar la imagen base de OpenJDK
FROM amazoncorretto:17-alpine3.16

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar los archivos de construcción de Gradle
COPY ./gradlew ./gradlew
COPY ./gradle ./gradle
COPY ./build.gradle ./build.gradle
COPY ./settings.gradle ./settings.gradle
COPY ./src ./src

# Copiar el directorio de scripts
COPY ./scripts ./scripts

# Dar permisos de ejecución al gradlew
RUN chmod +x ./gradlew

# Construir el proyecto usando Gradle
RUN ./gradlew clean build

# Establecer el puerto en el que la aplicación correrá
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
