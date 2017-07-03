FROM jenkins:2.60.1-alpine

LABEL maintainer "Bimlendu Mishra"

ARG HOST_DOCKER_GID 999

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false" \
    JENKINS_USER="admin" \
    JENKINS_PASS="admin" \
    JENKINS_EXECUTORS="5"

RUN /usr/local/bin/install-plugins.sh git workflow-aggregator matrix-auth && \
	groupadd -g ${HOST_DOCKER_GID} docker && \
	usermod -aG docker jenkins

COPY initial-setup.groovy /usr/share/jenkins/ref/init.groovy.d/initial-setup.groovy