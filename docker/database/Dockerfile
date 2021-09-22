FROM postgres

#Define default values for environment variables
ENV

#Add the entrypoint script for the image (Which automatically run after container start
COPY docker-entrypoint /usr/local/bin/
ENTRYPOINT [ "docker-entrypoint" ]

#Add all the initialization files for the database (If we use any), these are only run on a fresh docker container
COPY initdb /docker-entrypoint-initdb.d

CMD ["postgres"]