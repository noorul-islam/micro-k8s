FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} service-registry-0.0.1.war
ENTRYPOINT ["java","-jar","/service-registry-0.0.1.war"]
EXPOSE 8761