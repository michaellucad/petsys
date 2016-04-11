echo off
del stopServer.bat
setlocal
set pid=test
for /f "tokens=2 delims=," %%F in ('tasklist /nh /fi "imagename eq java.exe" /fo csv') do (
	set pid=%pid%%%~F
)
echo %pid% >> stopServer.bat