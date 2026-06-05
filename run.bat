@echo off
cd /d "%~dp0"

if not exist "SnakeGame.jar" (
  echo SnakeGame.jar not found. Building the JAR...
  if not exist "stdlib.jar" (
    echo ERROR: stdlib.jar is missing.
    echo Please place stdlib.jar in this folder.
    pause
    exit /b 1
  )
  if not exist "src\SnakeGame.java" (
    echo ERROR: source files are missing.
    pause
    exit /b 1
  )
  if not exist out (
    mkdir out
  )
  javac -cp stdlib.jar src\*.java -d out
  if errorlevel 1 (
    echo Build failed.
    pause
    exit /b 1
  )
  (
    echo Main-Class: SnakeGame
    echo Class-Path: stdlib.jar
  ) > manifest.txt
  jar cfm SnakeGame.jar manifest.txt -C out .
  del manifest.txt
  if errorlevel 1 (
    echo Failed to create SnakeGame.jar.
    pause
    exit /b 1
  )
  echo JAR built successfully.
)

echo Running SnakeGame...
java -jar SnakeGame.jar
pause
