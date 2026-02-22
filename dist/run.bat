@echo off
cd /d %~dp0
java -cp "Pharmacie-App.jar;lib/*" main.AppLauncher
pause