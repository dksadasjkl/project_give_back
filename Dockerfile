FROM amazoncorretto:11-alpine-jdk

ARG JAR_FILE=target/*.jar
ARG PROFILES
ARG ENV

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java \
    -Dspring.profiles.active=$PROFILES \
    -Dserver.env=$ENV \
    -Dserver.port=8001 \
    -Dclient.deploy-address=give-portfolio.shop \
    -jar app.jar"]

