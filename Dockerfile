FROM openjdk:16-alpine3.13

COPY target/spring-boot-test-*.jar vasya.jar

CMD ["java", "-jar", "vasya.jar"]