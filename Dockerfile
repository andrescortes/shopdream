#FROM gradle:8.10-alpine AS build
#WORKDIR /usr/app/
#COPY build.gradle settings.gradle ./
#COPY src ./src
#RUN gradle clean build --no-daemon

#FROM amazoncorretto:17-alpine
#ENV APP_HOME=/usr/app
#ENV JAR_FILE=shopdream-0.0.1-SNAPSHOT.jar
#COPY --from=build $APP_HOME/build/libs/$JAR_FILE .
#ENTRYPOINT ["sh", "-c", "java -jar $JAR_FILE"]

FROM amazoncorretto:17-alpine
ENV APP_HOME=/usr/app
ENV JAR_FILE=shopdream-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
COPY ./build/libs/$JAR_FILE .
EXPOSE 9000
ENTRYPOINT ["sh", "-c", "java -jar $JAR_FILE"]
