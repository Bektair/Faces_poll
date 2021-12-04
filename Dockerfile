FROM tomee:11-jre-9.0.0-M7-plume

##This is a tomee image bundled with my webapp.war file and my local tomee servers config files

RUN mkdir /home/faces_base

##Catalina base overrides the config settings of catalina home(default)

ENV CATALINA_HOME=/usr/local/tomee

COPY ./server/faces_poll_base /home/faces_base/.

COPY ./anotherdaily/target/*.war /home/faces_base/webapps

ENV CATALINA_BASE=/home/faces_base

CMD ["catalina.sh", "run"]
