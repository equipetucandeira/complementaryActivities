#!/bin/bash

PROJECT_NAME="complementary"

echo "Compiling and building the project with Maven..."

mvn clean compile


if [ ! -d "target/classes" ]; then
  echo "Error: Directory cannot be accessed or doesnt exist!"
  exit 1
fi

mv target/classes/application.properties target/classes/br/edu/ifsp/complementary/infrastructure

mv target/classes/app.png target/classes/br/edu/ifsp/complementary/ui

echo "Executing a maven application..."

mvn exec:java -Dexec.mainClass="br.edu.ifsp.complementary.App"
