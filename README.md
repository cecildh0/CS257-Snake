# Snake V2

Simple desktop Snake game written for CS257 final

## Project contents

- `src/` — Java source files
- `stdlib.jar` — `StdDraw` library dependency
- `SnakeGame.jar` — executable JAR built from the project
- `run.bat` — Windows launcher
- `out/` — compiled class output (ignored by git)

## Requirements

- Java 17 or newer
- `stdlib.jar` present in the project root

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

Make sure these files sit next to `SnakeGame.jar`:
- `stdlib.jar`
- `logo.png`
- `snakehead.png`
- `snakeheadgameover.png`
- `apple.png`

## Build the JAR

If you need to rebuild the executable JAR:

```powershell
javac -cp stdlib.jar src/*.java -d out
Set-Content -Path manifest.txt -Value "Main-Class: SnakeGame`nClass-Path: stdlib.jar`n"
jar cfm SnakeGame.jar manifest.txt -C out .
```



