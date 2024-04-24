#!/bin/bash

#docker build -t jeecg-boot-mysql ./db
container_name=jeecg-boot-mysql
container_id=`docker ps -a | grep ${container_name} | awk '{print $1}'`
if [ -n "${container_id}" ]
then
  docker start ${container_id}
else
  docker run --name jeecg-boot-mysql \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_ROOT_HOST=% \
    -e TZ=Asia/Shanghai \
    --restart always \
    -p 3306:3306 \
    -d jeecg-boot-mysql \
    --character-set-server=utf8mb4 \
    --collation-server=utf8mb4_general_ci \
    --explicit_defaults_for_timestamp=true \
    --lower_case_table_names=1 \
    --max_allowed_packet=128M \
    --default-authentication-plugin=caching_sha2_password
fi