# Docker
Either run the containers manually or use the docker-compose script

## docker-compose
Before trying to use docker-compose copy the `.env.template` file and rename the new file to `.env`, change any values if you want to (not needed) and then you can continue

This is the preferred way to use the docker images, either open the file in your IDE and click the `run` button on the services
you wish to use, or on the `run all` button next to `services`

Otherwise, you can open that directory in your terminal and run `docker-compose up -d`, or omit the -d to have the container
logs show in your terminal window. These logs can also be viewed inside intelliJ if you install the docker plugin and go to
the services tab and click your way to the container you wish to view.

### commands
```bash
docker-compose up -d #starts all containers in the background (detached)
docker-compose up #starts all containers and outputs their logs into the terminal
docker-compose logs [container_name] #shows the log output from all containers, or the specified containers if you add them
docker-compose logs --tail=100 -f [container_name] #shows the 100 latest log rows and then keeps checking for new entries until cancelled
docker-compose build #builds all containers
docker-compose down #stops and removes all containers
docker-compose down -v #stops and removes all containers and their volumes
docker-compose stop #stops all containers
```

# Database docker image
Default values for the database are `gyro` for all values (username, password, db name)