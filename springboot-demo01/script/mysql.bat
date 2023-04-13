@echo off
setlocal EnableDelayedExpansion

set MYSQL_VERSION=8.0.27
set MYSQL_INSTALL_DIR=C:\Program Files\MySQL\MySQL Server %MYSQL_VERSION%

rem 下载MySQL安装包
curl -o mysql-installer.exe https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-%MYSQL_VERSION%.exe

rem 安装MySQL
mysql-installer.exe --quiet --install-server=mysqld --server-root-password=yourpassword --disable-components=mysql-shell,mysql-router

rem 配置环境变量
setx MYSQL_HOME "%MYSQL_INSTALL_DIR%" /M
setx PATH "%MYSQL_INSTALL_DIR%\bin;%PATH%" /M

rem 清理
del mysql-installer.exe

echo MySQL %MYSQL_VERSION% has been installed successfully!
