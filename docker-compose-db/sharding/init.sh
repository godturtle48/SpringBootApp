#!/bin/bash
# Generate global auth key between cluster nodes
# openssl rand -base64 756 > mongodb.key
# chmod 600 mongodb.key

# ## Start the whole stack
# docker-compose up -d 


# sleep 30


docker-compose exec config01 sh -c "mongo --port 27019 < /scripts/init-configserver.js"
docker-compose exec shard01a sh -c "mongo --port 27018 < /scripts/init-shard01.js"
docker-compose exec shard02a sh -c "mongo --port 27018 < /scripts/init-shard02.js"
docker-compose exec shard03a sh -c "mongo --port 27018 < /scripts/init-shard03.js"
# docker-compose exec shard04a sh -c "mongo --port 27021 < /scripts/init-shard04.js"
# docker-compose exec shard05a sh -c "mongo --port 27022 < /scripts/init-shard05.js"

sleep 20
docker-compose exec router sh -c "mongo < /scripts/init-router.js"
# docker-compose exec router mongo
# docker-compose exec router sh -c "mongo < /scripts/init-auth.js"



