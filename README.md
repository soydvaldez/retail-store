# Retail-store

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![H2](https://img.shields.io/badge/H2-003545?style=for-the-badge&logo=h2&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)

## Descripción

Esta es una aplicación desarrollada con Spring Boot y Java. Proporciona un servicio RESTful para gestionar una tienda minorista y llevar acabo un inventario de productos.

## Dependencias del proyecto

- **Spring Security:** Para seguridad verifica la autenticacion y autorizacion de los usuarios
- **Spring Data:** Para las anotationes y crear de forma automatica CRUD, Repositorios e Entidades
- **Spring MVC:** Para escuchar y responder peticiones de red
- **Lombok:** Genera constructores, metodos setters and getter, builder, logs de forma rapida con anotaciones
- **H2:** Como base de datos en Memoria
- **Spring devtools:** Para agilizar refrescar cambios en el codigo fuente y agilizar el desarrallo
- **JUnit:** para testing pruebas integracion y unitarias

## Requisitos

Antes de comenzar, asegúrate de tener los siguientes componentes instalados:

- [Amazon Corretto - Java Development Kit (JDK) 17 o superior](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Gradle 8.6 o superior](https://gradle.org/install/)

## Instalación

1. Clona el repositorio en tu máquina local

   A traves de https:

   ```sh
   git clone https://github.com/soydvaldez/retailer-store.git
   ```

   A traves de ssh:

   ```sh
   git clone git@github.com:soydvaldez/retailer-store.git
   ```

2. Navega al directorio del proyecto:
   ```sh retailer-store/
   cd retailer-store/
   ```

## Ejecución

3. Compila el proyecto utilizando Gradle: (Genera un archivo ejecutable .JAR)

   ```sh
   gradle wrapper && ./gradlew clean build
   ```

4. Levanta el proyecto utilizando el comando:
   ```sh
    ./gradlew bootRun
   ```
   Listo, siguiendo los pasos anteriores el servidor debe estar levantado y preparado para escuchar y responder peticiones en la red local.

## Consumo de las apis

El proyecto escucha peticiones por defecto por la red local: [http://localhost:8080/api/](http://localhost:8080/)

Existen dos rutas en la aplicacion: /api/products y /api/categories.

5. Consumir con el cliente http a traves de la terminal:
   Existen dos usuarios: **user** y **admin** los usuario, los cuales comparten la misma contraseña: **password**

   **Ejemplo de consumo con el usuario: user**

   Peticion hacia la ruta productos:

   ```sh
   http -a user:password "localhost:8080/api/products?page=2&size=4"
   ```

   **Ejemplo de consumo con el usuario: admin**

   Peticion hacia la ruta de categories:

   ```sh
   http -a admin:password "localhost:8080/api/products?page=2&size=4"
   ```

6.- (Opcional) Para correr las pruebas del proyecto con Gradle, copia y pega en la terminal el siguiente comando:

```sh
 ./gradlew test
```
