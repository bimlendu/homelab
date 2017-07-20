FROM sonarqube:6.4-alpine

LABEL maintainer "Bimlendu Mishra"

ENV SONARQUBE_HOME=/opt/sonarqube

ADD https://github.com/uartois/sonar-golang/releases/download/v1.2.9-gamma/sonar-golang-plugin-1.2.9-gamma.jar $SONARQUBE_HOME/extensions/plugins/
