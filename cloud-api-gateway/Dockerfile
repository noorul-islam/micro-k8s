FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} cloud-api-gateway-0.0.1.war
ENTRYPOINT ["java","-jar","/cloud-api-gateway-0.0.1.war"]
EXPOSE 9191