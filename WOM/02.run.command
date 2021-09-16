#/bin/bash
cd -- "$(dirname "$BASH_SOURCE")"
export JAVA_HOME="/Library/worlds_of_minecraft_cache/1.16.0/jdk/Contents/Home"
cp libs/libglfw.dylib build/natives/libglfw.dylib
./gradlew  -g /Library/worlds_of_minecraft_cache/1.16.0/gradle/ -DXmx2G -DXX:+UnlockExperimentalVMOptions -DXX:+UseG1GC -DXX:G1NewSizePercent=20 -DXX:G1ReservePercent=20 -DXX:MaxGCPauseMillis=50 -DXX:G1HeapRegionSize=32 -Dfml.earlyprogresswindow=false -Dnet.minecraftforge.gradle.test_certs=false --offline runClient
