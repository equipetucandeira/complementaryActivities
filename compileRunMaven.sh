#!/bin/bash

PROJECT_NAME="tucandeira"

echo "Compiling and building the project with Maven..."

mvn clean compile

if [ ! -d "target/classes" ]; then
  echo "Error: Directory cannot be accessed or doesnt exist!"
  exit 1
fi

mv target/classes/application.properties target/classes/com/tucandeira/

mv target/classes/app.png target/classes/com/tucandeira/ui

echo "Executing a maven application..."

mvn exec:java -Dexec.mainClass="com.tucandeira.App"
