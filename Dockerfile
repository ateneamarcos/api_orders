FROM openjdk:17-jdk-slim

WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
COPY start.sh /app/start.sh

RUN chmod +x /app/start.sh

EXPOSE 8080
ENTRYPOINT ["/app/start.sh"]
