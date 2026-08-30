@echo off
cd /d "%~dp0"
title LotRO Companion (Console Logs)
set JAVA=runtime\bin\java.exe
if not exist "%JAVA%" set JAVA=java
"%JAVA%" -Xms256m -Xmx1024m --add-exports java.desktop/sun.swing.table=ALL-UNNAMED --add-exports java.desktop/sun.swing=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED -cp "lib\patches\*;lib\patches;icons;lib\*" delta.games.lotro.Main
if errorlevel 1 (
    echo.
    echo Application terminated with an error.
    pause
)
