@echo off
cd %CD%
cd ..

java -jar cfm build/Automation-test-with-REST-Assured.jar res/manifest.mf -C target/test-classes .

cd build

java -jar Automation-test-with-REST-Assured.jar

pause