#!/bin/bash

container_name=jeecg-boot-redis
container_id=`docker ps -a | grep ${container_name} | awk '{print $1}'`
if [ -n "${container_id}" ]
then
  docker start ${container_id}
else
  docker run --name jeecg-boot-redis \
    -p 6379:6379 \
    --restart always \
    --hostname jeecg-boot-redis \
    -d redis:5.0
fi
