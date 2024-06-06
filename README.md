# Proyecto de Aplicacion Modularizada con Spring boot para una tienda de comestibles

## Descripción

Esta es una aplicación desarrollada con Spring Boot y Java. Proporciona un servicio RESTful para gestionar una tienda minorista y llevar acabo un inventario de productos.

## Dependencias del proyecto

**Spring Security:** Para seguridad verifica la autenticacion y autorizacion de los usuarios
**Spring Data:** Para las anotationes y crear de forma automatica CRUD, Repositorios e Entidades
**Spring MVC:** Para escuchar y responder peticiones de red
**Lombok:** Genera constructores, metodos setters and getter, builder, logs de forma rapida con anotaciones
**H2:** Como base de datos en Memoria
**Spring devtools:** Para agilizar refrescar cambios en el codigo fuente y agilizar el desarrallo
**JUnit:** para testing pruebas integracion y unitarias

## Requisitos

Antes de comenzar, asegúrate de tener los siguientes componentes instalados:

- [Amazon Corretto - Java Development Kit (JDK) 17 o superior](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Gradle 8.6 o superior](https://gradle.org/install/)

## Instalación

1. Clona el repositorio en tu máquina local:

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

El proyecto escucha peticiones por defecto por la red local: [http://localhost:8080/api/categories](http://localhost:8080/api/categories)
Existen dos rutas en la aplicacion: /api/products y /api/categories.

5. Consumir con el cliente http a traves de la terminal: Existen dos usuarios: user y admin los usuario, los cuales comparten la misma contraseña: password. (Los filtros de spring security verificaran la autenticacion y la autorizacion sobre los recursos del sistema)

   #Consumo con el usuario: user. Peticion hacia la ruta productos
   ```sh
   http -a user:password "localhost:8080/api/products?page=2&size=4"
   ```

   #Consumo con el usuario: admin. Peticion hacia la ruta de categories
   ```sh
   http -a admin:password "localhost:8080/api/products?page=2&size=4"
   ```

6.- (Opcional) Para realizar testing con Gradle, copia y pega en la terminal sobre la raiz del proyecto clonado:

    ```sh
    ./gradlew test
    ```
