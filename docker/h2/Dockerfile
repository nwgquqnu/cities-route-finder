FROM openjdk:8-jdk-alpine

# A merge of:
#  https://github.com/zhilvis/docker-h2
#  https://github.com/zilvinasu/h2-dockerfile

MAINTAINER Oscar Fonts <oscar.fonts@geomati.co>

ENV DOWNLOAD http://www.h2database.com/h2-2018-03-18.zip
ENV DATA_DIR /opt/h2-data

RUN apk add --no-cache curl \
    && mkdir -p ${DATA_DIR} \
    && curl ${DOWNLOAD} -o h2.zip \
    && unzip h2.zip -d /opt/ \
    && rm h2.zip

COPY h2.server.properties /root/.h2.server.properties

# EXPOSE 8091 1521

CMD java -cp /opt/h2/bin/h2*.jar org.h2.tools.Server \
 	-web -webAllowOthers -webPort 8091 \
 	-tcp -tcpAllowOthers -tcpPort 1521 \
 	-baseDir ${DATA_DIR}