FROM openjdk:17-jdk-apline

WORKDIR /app

ADD target/blog_system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]