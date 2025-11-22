# PowerShell script to run JavaFX application without Maven

Write-Host "Building and running JavaFX Client Management Application..." -ForegroundColor Cyan
Write-Host ""

# Check if Java is installed
try {
    $javaVersion = java -version 2>&1
    Write-Host "Java found:" -ForegroundColor Green
    $javaVersion | Select-Object -First 1
} catch {
    Write-Host "ERROR: Java is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Java 17 or higher" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

# Check for JAVAFX_HOME
if (-not $env:JAVAFX_HOME) {
    Write-Host "JAVAFX_HOME environment variable is not set." -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Please set it to your JavaFX SDK path, for example:" -ForegroundColor Yellow
    Write-Host '  $env:JAVAFX_HOME = "C:\javafx-sdk-21"' -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Or download JavaFX SDK from: https://openjfx.io/" -ForegroundColor Yellow
    Write-Host ""
    
    $javafxPath = Read-Host "Enter JavaFX SDK path (or press Enter to exit)"
    if ([string]::IsNullOrWhiteSpace($javafxPath)) {
        exit 1
    }
    $env:JAVAFX_HOME = $javafxPath
}

# Check if JavaFX path exists
if (-not (Test-Path "$env:JAVAFX_HOME\lib")) {
    Write-Host "ERROR: JavaFX not found at: $env:JAVAFX_HOME\lib" -ForegroundColor Red
    Write-Host "Please verify the path and try again." -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

# Create output directory
if (-not (Test-Path "out")) {
    New-Item -ItemType Directory -Path "out" | Out-Null
}

# Compile Java files
Write-Host "Compiling Java files..." -ForegroundColor Cyan
javac --module-path "$env:JAVAFX_HOME\lib" --add-modules javafx.controls,javafx.fxml -d out src\main\java\com\example\*.java

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "ERROR: Compilation failed!" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

# Run the application
Write-Host ""
Write-Host "Running application..." -ForegroundColor Green
Write-Host ""
java --module-path "$env:JAVAFX_HOME\lib" --add-modules javafx.controls,javafx.fxml -cp out com.example.ClientManagementApp

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "ERROR: Application failed to run!" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}



