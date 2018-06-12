FROM openjdk:8-jre

COPY /application.yml /application.yml
COPY build/libs/stations*.jar /app.jar
EXPOSE 80
EXPOSE 9001
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]