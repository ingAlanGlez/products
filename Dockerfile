#
# Build del proyecto (Multi-Stage)
# --------------------------------
#
# Usamos una imagen de Maven para hacer build de proyecto con Java
# Llamaremos a este sub-entorno "build"
# Copiamos todo el contenido del repositorio
# Ejecutamos el comando mvn clean package (Generara un archivo JAR para el despliegue)
FROM eclipse-temurin:22-jdk AS build
COPY . .
ENV ELASTICSEARCH_HOST=unir-cluster-6455179952.us-east-1.bonsaisearch.net ELASTICSEARCH_USER=evn3c7h37o ELASTICSEARCH_PWD=9ofx819x28
RUN apt-get update && \
	apt-get install -y maven && \
	apt-get clean;
RUN mvn clean package

# Usamos una imagen de Openjdk
# Exponemos el puerto que nuestro componente va a usar para escuchar peticiones
# Copiamos desde "build" el JAR generado (la ruta de generacion es la misma que veriamos en local) y lo movemos y renombramos en destino como 
# Marcamos el punto de arranque de la imagen con el comando "java -jar app.jar" que ejecutará nuestro componente.
FROM openjdk:22-jdk
EXPOSE 8086
COPY --from=build /target/products-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]