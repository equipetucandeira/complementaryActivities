#!/bin/bash

PROJECT_NAME="tucandeira"

echo "Compiling and building the project with Maven..."

mvn clean compile

if [ ! -d "target/classes" ]; then
  echo "Error: Directory cannot be accessed or doesnt exist!"
  exit 1
fi

echo "Executing a maven application..."

mvn exec:java -Dexec.mainClass="com.tucandeira.App"
