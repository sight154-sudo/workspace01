@echo off

rem 查找应用程序的 PID
for /f "tokens=2" %%i in ('tasklist /nh /fi "imagename eq java.exe" /fi "CommandLine like %application.jar%"') do set PID=%%i

if not defined PID (
  echo Application is not running.
) else (
  rem 终止应用程序
  taskkill /f /pid %PID%

  echo Application stopped.
)
:: 说明：

:: tasklist 命令用于列出所有进程。
:: /nh 参数用于去除列名。
:: /fi 参数用于过滤进程。"imagename eq java.exe" 筛选 Java 进程，"CommandLine like %application.jar%" 筛选包含应用程序路径的进程。
:: for /f "tokens=2" 用于提取 PID。
:: taskkill 命令用于终止进程。