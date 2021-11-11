FROM openjdk:11.0.2-jdk-oraclelinux7
ADD "./build/libs/mutantdna-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.data.mongodb.Host=${ENV}", "app.jar"]


