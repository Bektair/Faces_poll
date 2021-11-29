FROM tomee:11-jre-9.0.0-M7-plume

## I want to add DB container, DB UI container, GITHUB container?

WORKDIR /usr/local/tomee/webapps

##RUN mkdir anotherdaily

COPY ./exportme/anotherdaily.war .


CMD ["catalina.sh", "run"]
