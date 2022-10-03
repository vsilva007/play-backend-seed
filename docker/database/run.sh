#!/bin/bash
docker run -d -p 5433:5432 --restart on-failure:10 --name smart-tpv-server-db smartstudio/smart-tpv-server-db
