#!/bin/bash

# 定义变量
SOURCE_DIR=./src
OUTPUT_DIR=./bin
LIB_DIR=./lib

# 清空输出目录
rm -rf $OUTPUT_DIR/*
mkdir -p $OUTPUT_DIR

# 编译Java源代码
javac -sourcepath $SOURCE_DIR -cp $LIB_DIR/* -d $OUTPUT_DIR $SOURCE_DIR/com/example/*.java

# 打包成jar文件
cd $OUTPUT_DIR
jar cvf myproject.jar *

echo "Build completed."
