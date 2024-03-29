FROM openjdk:11
COPY target/debezium-demo.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]