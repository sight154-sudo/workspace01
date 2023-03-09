#!/bin/bash

# 查找应用程序的 PID
pid=$(ps -ef | grep "java -jar /path/to/your/application.jar" | grep -v grep | awk '{print $2}')

if [ -z "$pid" ]
then
  echo "Application is not running."
else
  # 终止应用程序
  kill $pid

  echo "Application stopped."
fi

#说明：
#
#ps -ef 命令用于列出所有运行中的进程。
#grep "java -jar /path/to/your/application.jar" 用于筛选包含该字符串的进程。
#grep -v grep 用于去除 grep 命令本身所产生的进程。
#awk '{print $2}' 用于提取 PID。
#kill $pid 用于终止进程。