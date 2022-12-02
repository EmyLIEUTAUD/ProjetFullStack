FROM maven:3.6.3-openjdk-17 as builder
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn package -f /usr/src/app/pom.xml -Dmaven.test.skip


FROM openjdk:17
#WORKDIR /covid-api
COPY --from=builder /usr/src/app/target/covid-api-0.0.1-SNAPSHOT.jar /usr/app/
EXPOSE 8080
CMD ["java", "-jar", "/usr/app/covid-api-0.0.1-SNAPSHOT.jar"]