# BackendProject Readme

Este repositorio contiene dos proyectos relacionados, el servidor (BackendProject-Server) y el cliente (BackendProject-Client). A continuación, se proporcionan instrucciones básicas para ejecutar la aplicación.

## BackendProject-Server

### Requisitos previos
- Eclipse IDE con plugin Spring Tool Suite 4
- Java Development Kit (JDK) instalado

### Pasos para ejecutar
1. Abre Eclipse IDE.
2. Importa el proyecto **BackendProject-Server** como proyecto maven.
3. Asegúrate de tener configurado un servidor [Spring Boot](https://spring.io/guides/gs/spring-boot/) en Eclipse.
4. Ejecuta la clase `BackendProjectServerApplication` como una aplicación Spring Boot.

El servidor se iniciará en `http://localhost:8080`.

## BackendProject-Client

### Requisitos previos
- Eclipse IDE
- Java Development Kit (JDK) instalado

### Pasos para ejecutar
1. Abre Eclipse IDE.
2. Importa el proyecto **BackendProject-Client** como proyecto maven.
3. Ejecuta la clase `BackendProjectClientApplication` como una aplicación Java.

El cliente se ejecutará y realizará operaciones según la configuración en el código fuente. El cliente se conectará automáticamente a `http://localhost:8080/api` cada vez que se ejecute alguna operación.
