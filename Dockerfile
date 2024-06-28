FROM openjdk:17-slim
COPY ./build/libs/*T.jar app.jar
RUN ["java", "-version"]
# ENV profiles prod
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080