#!/bin/bash

# 设置环境变量
export JAVA_HOME=/path/to/your/java/home
export PATH=$JAVA_HOME/bin:$PATH

# 启动应用程序
java -jar /path/to/your/application.jar > /dev/null 2>&1 &

echo "Application started."
#说明：
#
#JAVA_HOME 是指向您的 Java 安装目录的路径。
#PATH 环境变量中包含了 $JAVA_HOME/bin，以确保可以在终端中访问到 java 命令。
#java -jar 命令用于启动您的应用程序，其中 /path/to/your/application.jar 是您的可执行 JAR 文件的路径。
#> /dev/null 2>&1 & 用于将输出重定向到 /dev/null，并将应用程序作为后台进程运行。