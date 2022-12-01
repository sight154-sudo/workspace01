@echo off
echo 'start compile project...'
mvn clean package -Dmaven.test.skip=true -gs .\settings.xml -f pom.xml
echo 'compile ending...'