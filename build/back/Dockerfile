FROM maven:3.6.3-openjdk-17 as builder
#WORKDIR ../../
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn package -f /usr/src/app/pom.xml -Dmaven.test.skip


FROM openjdk:17
COPY --from=builder /usr/src/app/target/covid-api-0.0.1-SNAPSHOT.jar /usr/app/
EXPOSE 8081
CMD ["java", "-jar", "/usr/app/covid-api-0.0.1-SNAPSHOT.jar"]