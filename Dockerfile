FROM openjdk:17
WORKDIR /covid-api
COPY ./target/covid-api-0.0.1-SNAPSHOT.jar /covid-api
EXPOSE 8080
CMD ["java", "-jar", "covid-api-0.0.1-SNAPSHOT.jar"]