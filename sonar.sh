#!/bin/bash

/opt/usr/dev/support/apache-maven-3.6.1/bin/mvn sonar:sonar \
  -Dsonar.projectKey=com.milaev:t-medicine \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=a1a9cf5e553296e65e90c479ac59a3515534e453