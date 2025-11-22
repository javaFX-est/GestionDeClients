@echo off
echo Building and running JavaFX Client Management Application...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher
    pause
    exit /b 1
)

REM Create output directory
if not exist "out" mkdir out

REM Compile Java files
echo Compiling Java files...
javac --module-path "%JAVAFX_HOME%\lib" --add-modules javafx.controls,javafx.fxml -d out src\main\java\com\example\*.java

if errorlevel 1 (
    echo.
    echo ERROR: Compilation failed!
    echo.
    echo Note: You need to set JAVAFX_HOME environment variable to your JavaFX SDK path
    echo Example: set JAVAFX_HOME=C:\javafx-sdk-21
    echo.
    echo Or download JavaFX SDK from: https://openjfx.io/
    pause
    exit /b 1
)

REM Run the application
echo.
echo Running application...
java --module-path "%JAVAFX_HOME%\lib" --add-modules javafx.controls,javafx.fxml -cp out com.example.ClientManagementApp

pause



