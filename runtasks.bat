call runcrud.bat
if %ERRORLEVEL% == 0 goto startchrome
echo.
echo error occured running runcrud.bat
goto fail

:startchrome
start chrome http://localhost:8080/crud/v1/tasks
goto end

:fail
echo there were errors

:end
echo end of work