@echo off
cd /d "%~dp0"
set JAVA=runtime\bin\javaw.exe
if not exist "%JAVA%" set JAVA=javaw
start "%JAVA%" -Xms256m -Xmx1024m --add-exports java.desktop/sun.swing.table=ALL-UNNAMED --add-exports java.desktop/sun.swing=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED -cp "lib\patches;icons;lib\*" delta.games.lotro.Main