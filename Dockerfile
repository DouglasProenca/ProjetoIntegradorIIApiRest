FROM openjdk:8
WORKDIR /app
COPY target/cr7-api-imports.jar /app/cr7-api-imports.jar
EXPOSE 8088
CMD ["java", "-jar", "cr7-api-imports.jar"]