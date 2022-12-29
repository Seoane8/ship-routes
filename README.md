# 🚢📦🗺 Ship Routes

[![CI pipeline status](https://github.com/Seoane8/ship-routes/workflows/CI/badge.svg)](https://github.com/Seoane8/ship-routes/actions)

## 🏁 How To Start

1. Install Java 11: `brew cask install corretto`
2. Set it as your default
   JVM: `export JAVA_HOME='/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home'`
3. Clone this repository: `git clone https://github.com/Seoane8/ship-routes`.
4. Bring up the Docker environment: `make up`.
5. Execute some [Gradle lifecycle tasks](https://docs.gradle.org/current/userguide/java_plugin.html#lifecycle_tasks) in
   order to check everything is OK:
    1. Create [the project JAR](https://docs.gradle.org/current/userguide/java_plugin.html#sec:jar): `make build`
    2. Run the tests and plugins verification tasks: `make test`
6. Start developing!

## ☝️ How to update dependencies

*

Gradle ([releases](https://gradle.org/releases/)): `./gradlew wrapper --gradle-version=WANTED_VERSION --distribution-type=bin`
