#! /bin/bash

# 建立映射文件夹
rm -rf /opt/kafka/
rm -rf /opt/zookeeper/

mkdir -p /opt/zookeeper/data
mkdir -p /opt/kafka



chmod -R 777 /opt/kafka/
chmod -R 777 /opt/zookeeper/