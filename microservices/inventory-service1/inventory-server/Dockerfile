FROM openjdk:8-jdk-alpine

LABEL author="Tong Thanh Vinh"

ADD target/inventory-server-1.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
