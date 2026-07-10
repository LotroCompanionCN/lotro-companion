@echo off
cd /d "%~dp0"
start javaw -Xms256m -Xmx1024m -cp "lib\patches;icons;lib\*" delta.games.lotro.Main