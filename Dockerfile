# Stage 1: Build the JAR file
FROM gradle:jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Stage 2: Extract the JAR file
FROM amazoncorretto:21-alpine AS extractor
WORKDIR /extracted
COPY --from=builder /app/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 3: Create the final image
FROM amazoncorretto:21-alpine
WORKDIR /application
COPY --from=extractor /extracted/dependencies/ ./
COPY --from=extractor /extracted/spring-boot-loader/ ./
COPY --from=extractor /extracted/snapshot-dependencies/ ./
COPY --from=extractor /extracted/application/ ./

EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=UTC", "-Dlogstash.host.name=logstash", "-Dlogstash.port.number=9999", "org.springframework.boot.loader.launch.JarLauncher"]