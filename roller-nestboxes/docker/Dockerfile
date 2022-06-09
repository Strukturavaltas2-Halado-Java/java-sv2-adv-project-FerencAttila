FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR application
COPY ./target/*.jar roller-nestboxes.jar
RUN java -Djarmode=layertools -jar roller-nestboxes.jar extract

FROM eclipse-temurin:17-jre-alpine

RUN apk add --no-cache bash

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /opt/bin/
RUN chmod +x /opt/bin/wait-for-it.sh

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
CMD ["java", \
  "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080