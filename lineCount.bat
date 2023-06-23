@echo off
setlocal enabledelayedexpansion

set "directory=F:\IdeaProjects\pluginTest\src"
set "fileExtensions=.java"

set "totalLineCount=0"

for /r "%directory%" %%F in (*%fileExtensions%) do (
    set "file=%%F"
    set "lineCount=0"

    for /f %%A in ('type "%%F" ^| find /c /v ""') do set "lineCount=%%A"

    echo File: !file! | findstr /r "^File: .*"
    echo Line Count: !lineCount!
    echo.

    set /a "totalLineCount+=lineCount"
)

echo Total Line Count: %totalLineCount%

pause
