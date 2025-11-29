FROM amazoncorretto:11-alpine-jdk

ARG JAR_FILE=target/*.jar

ENV PROFILES=""
ENV ENV_VALUE=""

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java \
    -Dspring.profiles.active=${PROFILES} \
    -Dserver.env=${ENV_VALUE} \
    -Dclient.deploy-address=give-portfolio.shop \
    -jar app.jar"]