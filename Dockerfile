FROM openjdk:17
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/complementaryActivity.jar
WORKDIR /app
ENTRYPOINT java -jar complementaryActivity.jar
