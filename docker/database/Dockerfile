FROM postgres:10.2-alpine
LABEL maintainer="Smart Studio" Version="1.0"

ENV POSTGRES_USER smarttpv
ENV POSTGRES_PASSWORD 12345
ENV POSTGRES_DB smarttpvdb

COPY 1.sql /docker-entrypoint-initdb.d/

CMD ["postgres"]
