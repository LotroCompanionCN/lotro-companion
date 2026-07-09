@echo off
cd /d "%~dp0"
start javaw -Xms256m -Xmx1024m -cp "lib\*" delta.games.lotro.Main
