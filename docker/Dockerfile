FROM openjdk:11-jre-slim-buster
ARG JAR_FILE=build/libs/modyeo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=${USE_PROFILE}","-Dio.netty.tryReflectionSetAccessible=true","--add-opens","java.base/jdk.internal.misc=ALL-UNNAMED","-jar","/app.jar"]