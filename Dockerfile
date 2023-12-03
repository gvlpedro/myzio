FROM azul/zulu-openjdk-alpine:11

COPY target/scala-3.3.1/app.jar /app/app.jar
WORKDIR /app

EXPOSE 8080

ENTRYPOINT [ "java", "-Dport=8080", "-DisProd=true", "-jar", "app.jar" ]