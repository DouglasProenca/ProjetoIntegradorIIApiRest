FROM openjdk:8-jre-slim
WORKDIR /app
COPY target/cr7-api-imports.jar /app/cr7-api-imports.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "cr7-api-imports.jar"]