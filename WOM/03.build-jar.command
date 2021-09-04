#/bin/bash
cd -- "$(dirname "$BASH_SOURCE")"
export JAVA_HOME="/Library/worlds_of_minecraft_cache/1.16.0/jdk/Contents/Home"
./gradlew -g /Library/worlds_of_minecraft_cache/1.16.0/gradle/ -Dnet.minecraftforge.gradle.test_certs=false --offline build