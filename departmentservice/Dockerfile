FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} departmentservice-0.0.1.war
ENTRYPOINT ["java","-jar","/departmentservice-0.0.1.war"]
EXPOSE 9001
