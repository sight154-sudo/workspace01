@echo off

:: 定义变量
set SOURCE_DIR=.\src
set OUTPUT_DIR=.\bin
set LIB_DIR=.\lib

:: 清空输出目录
rd /s /q %OUTPUT_DIR%
mkdir %OUTPUT_DIR%

:: 编译Java源代码
javac -sourcepath %SOURCE_DIR% -cp %LIB_DIR%\* -d %OUTPUT_DIR% %SOURCE_DIR%\com\example\*.java

:: 打包成jar文件
cd %OUTPUT_DIR%
jar cvf myproject.jar *

echo Build completed.
