#!/bin/bash

cd "$(dirname "$0")"

JAR_NAME="ISUMonopolyGame.jar"

javac -cp "./*" -sourcepath src/ src/edu/ilstu/Main.java -d bin/
jar cfe $JAR_NAME edu.ilstu.Main -C bin/ .
jar uf $JAR_NAME resources/*