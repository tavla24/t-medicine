/opt/usr/dev/support/apache-maven-3.6.1/bin/mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar \
  -Dsonar.projectKey=t-medicine \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=8ea2cd6f6b65ee05ed60be1ed4bdb61b925b6423