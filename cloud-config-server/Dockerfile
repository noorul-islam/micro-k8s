FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} cloud-config-server-0.0.1.war
ENTRYPOINT ["java","-jar","/cloud-config-server-0.0.1.war"]
EXPOSE 9296