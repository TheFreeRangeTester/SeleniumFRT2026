FROM jenkins/jenkins:lts-jdk21

USER root

RUN apt-get update \
    && apt-get install --yes --no-install-recommends chromium chromium-driver git \
    && ln -s /usr/bin/chromium /usr/local/bin/google-chrome \
    && rm -rf /var/lib/apt/lists/*

USER jenkins

COPY --chown=jenkins:jenkins plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY --chown=jenkins:jenkins jenkins.yaml /usr/share/jenkins/ref/jenkins.yaml

ENV CASC_JENKINS_CONFIG=/usr/share/jenkins/ref/jenkins.yaml
ENV JAVA_OPTS=-Djenkins.install.runSetupWizard=false
