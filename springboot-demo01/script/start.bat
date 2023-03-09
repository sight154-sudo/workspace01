@echo off

rem 设置环境变量
set JAVA_HOME=C:\path\to\your\java\home
set PATH=%JAVA_HOME%\bin;%PATH%

rem 启动应用程序
start java -jar C:\path\to\your\application.jar

echo Application started.
:: 说明：

:: JAVA_HOME 是指向您的 Java 安装目录的路径。
:: PATH 环境变量中包含了 %JAVA_HOME%\bin，以确保可以在命令提示符中访问到 java 命令。
:: start 命令用于启动应用程序，其中 C:\path\to\your\application.jar 是您的可执行 JAR 文件的路径。
:: echo Application started. 用于输出启动信息。