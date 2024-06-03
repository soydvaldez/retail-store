# Proyecto de Aplicacion Modularizada con Spring boot para una tienda de comestibles

## Descripción

Esta es una aplicación desarrollada con Spring Boot y Java. Proporciona un servicio RESTful para gestionar una tienda de comestibles y llevar un inventario de productos.

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
    cd 
    ```

3. Compila el proyecto utilizando Maven:
    ```sh
    gradle wrapper && ./gradlew build
    ```

## Ejecución

Para levantar la aplicación, ejecuta el siguiente comando desde el directorio del proyecto:

```sh
mvn spring-boot:run
