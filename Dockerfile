# base gradle with jdk - building the project
FROM gradle:8.11.1-jdk21 as builder

WORKDIR /job4j_devops

COPY . .
RUN ls
# unzip to current directory
RUN jar xf build/libs/DevOps-1.0.0.jar
# analyzing class and module deps - leave needed, skip redundant / outdated - all it collects in deps.info
RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    build/libs/DevOps-1.0.0.jar > deps.info
# adds modules from deps.info, remove debug logic from JRE (decreasing its size), compressing max (zip)
# exclude header files and man pages, forms output directory
RUN jlink \
    --add-modules $(cat deps.info) \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /slim-jre

# check what builds
RUN ls

# check build
RUN ls build

# check build
RUN ls build/libs

# creates final image
FROM debian:bookworm-slim
ENV JAVA_HOME /user/java/jdk21
ENV PATH $JAVA_HOME/bin:$PATH

# check build
RUN ls /build/libs
# check build
RUN ls /slim-jre

COPY --from=builder /slim-jre $JAVA_HOME
COPY --from=builder /build/libs/DevOps-1.0.0.jar .
ENTRYPOINT ["java", "-jar", "DevOps-1.0.0.jar"]