FROM 967353112876.dkr.ecr.us-east-1.amazonaws.com/java/jdk17:latest

VOLUME /tmp

ADD target/bff-conta-digital-timeline.jar .

USER root

RUN mkdir -p /opt/appdynamics \
    && cd /sharedFiles/AppServerAgent \
    && cp -a . /opt/appdynamics && \
    chmod 777 -R /opt/appdynamics

USER 1001:1001

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTIONS -Djava.security.egd=file:/dev/./urandom -jar bff-conta-digital-timeline.jar"]