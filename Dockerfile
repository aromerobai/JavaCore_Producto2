FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE
COPY Jar/alquilatusvehiculos-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]