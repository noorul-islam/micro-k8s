FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} userservice-0.0.1.war
ENTRYPOINT ["java","-jar","/userservice-0.0.1.war"]
EXPOSE 9002