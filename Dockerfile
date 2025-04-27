# base gradle with jdk : copy gradle files and download dependencies
FROM gradle:8.11.1-jdk21 as builder
RUN mkdir job4j_devops
WORKDIR /job4j_devops
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
RUN gradle --no-daemon dependencies

# copy sources and build skipping tests
COPY . .
RUN gradle --no-daemon build -x test

# unzip to current directory
RUN jar xf /job4j_devops/build/libs/DevOps-1.0.0.jar

# analyzing class and module deps - leave needed, skip redundant / outdated - all it collects in deps.info
RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    /job4j_devops/build/libs/DevOps-1.0.0.jar > deps.info

# adds modules from deps.info, remove debug logic from JRE (decreasing its size), compressing max (zip)
# exclude header files and man pages, forms output directory
RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /slim-jre

# creates final image
FROM debian:bookworm-slim

# set env variables
ENV JAVA_HOME /user/java/jdk21
ENV PATH $JAVA_HOME/bin:$PATH

# copy slim-jre from base image (and jdeps + jlink work)
COPY --from=builder /slim-jre $JAVA_HOME

# copy project jar to work directory
COPY --from=builder /job4j_devops/build/libs/DevOps-1.0.0.jar .

ENTRYPOINT java -jar DevOps-1.0.0.jar