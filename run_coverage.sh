#!/usr/bin/env bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"

JUNIT_VERSION="6.0.3"
JUNIT_JAR=".test-tools/junit-platform-console-standalone-${JUNIT_VERSION}.jar"

mkdir -p .test-tools

if [[ ! -f "$JUNIT_JAR" ]]; then
    curl -L -o "$JUNIT_JAR" "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/${JUNIT_VERSION}/junit-platform-console-standalone-${JUNIT_VERSION}.jar"
fi

mkdir -p coverage/classes
mkdir -p coverage/test-classes

find coverage/classes -type f -name '*.class' -delete
find coverage/test-classes -type f -name '*.class' -delete
rm -f coverage/jacoco.exec coverage/jacoco.csv coverage/jacoco.xml
rm -rf coverage/html

javac -d coverage/classes \
    port/*.java \
    lib/expression/*.java \
    lib/legacy/*.java
    lib/visitors/*.java

javac -cp "coverage/classes:$JUNIT_JAR" -d coverage/test-classes \
    spec/l/*.java

java \
    -javaagent:.coverage-tools/jacocoagent.jar=destfile=coverage/jacoco.exec \
    -jar "$JUNIT_JAR" execute \
    --class-path "coverage/classes:coverage/test-classes" \
    --scan-class-path \
    --details tree \
    --disable-banner

java -jar .coverage-tools/jacococli.jar report coverage/jacoco.exec \
    --classfiles coverage/classes/lib/visitors \
    --sourcefiles lib/visitors \
    --html coverage/html \
    --xml coverage/jacoco.xml \
    --csv coverage/jacoco.csv

echo "Coverage report written to coverage/html/index.html"
echo "Coverage CSV written to coverage/jacoco.csv"