call gradlew build
if %ERRORLEVEL% == 0 goto rename
echo.
echo gradlew build has errors - breaking work
goto fail

:rename
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if %ERRORLEVEL% == 0 goto stoptomcat
echo.
echo CANNOT RENAME FILE - error
goto fail

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:copyfile
copy build\libs\crud.war %CATALINA_HOME%\webapps
if %ERRORLEVEL% == 0 goto runtomcat
echo.
echo cannot copy file
goto fail

:runtomcat
call %CATALINA_HOME%\bin\startup.bat
goto end

:fail
echo there were errors

:end
echo.
echo end of work