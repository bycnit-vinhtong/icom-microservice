FROM openjdk:17-oracle

LABEL author="Tong Thanh Vinh"

ADD target/catalog-service.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar" ]
