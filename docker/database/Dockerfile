FROM postgres

#Define default values for environment variables
ENV POSTGRES_USER=gyro \
     POSTGRES_PASSWORD=gyro \
     POSTGRES_DB=gyro \
     POSTGRES_ROOT_PASSWORD=root

#Add all the initialization files for the database (If we use any), these are only run on a fresh docker container
COPY initdb /docker-entrypoint-initdb.d

CMD ["postgres"]