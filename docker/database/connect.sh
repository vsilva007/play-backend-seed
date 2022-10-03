#!/usr/bin/env bash
docker exec -it smart-tpv-server-db bash -c 'PGPASSWORD=12345 psql -h localhost -p 5432 -U smarttpv smarttpvdb'
