# Snake V2

Simple desktop Snake game written for CS257 final


## Windows launcher

Double-click `run.bat` to launch the game on Windows. 

## Run from source

Compile and run from the project folder:

```powershell
javac -cp stdlib.jar src/*.java -d out
java -cp "out;stdlib.jar" SnakeGame
```

On macOS/Linux use `:` instead of `;` in the classpath:

```bash
javac -cp stdlib.jar src/*.java -d out
java -cp "out:stdlib.jar" SnakeGame
```

## Run the packaged JAR

From the project root:

```powershell
java -jar SnakeGame.jar
```

## Build the JAR

If you need to rebuild the executable JAR:

```powershell
javac -cp stdlib.jar src/*.java -d out
Set-Content -Path manifest.txt -Value "Main-Class: SnakeGame`nClass-Path: stdlib.jar`n"
jar cfm SnakeGame.jar manifest.txt -C out .
```



