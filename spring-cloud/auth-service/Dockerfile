FROM openjdk:8-jdk-alpine

LABEL author="Tong Thanh Vinh"

ADD target/auth-service.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]