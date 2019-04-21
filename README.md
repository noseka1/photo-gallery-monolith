# photo-gallery-monolith

This is a monolithic server application that is composed of the three components:

* Photo component
* Like component
* Query component

Note that each of the components can be deployed as a standalone microservice if so desired.

## Build

You can build this project using:

```
mvn clean package
```

Or you can rebuild the entire monolithic application including its components using:

```
./build-all.sh
```

## Database

This application requires access to a PostgreSQL database. You can create it using:

```
psql -c 'CREATE DATABASE monodb'
psql -c "CREATE USER monouser WITH ENCRYPTED PASSWORD 'password'"
psql -c 'GRANT ALL PRIVILEGES ON DATABASE monodb TO monouser'
```

## Run

You can run this application using:

```
java -jar target/photo-gallery-monolith-1.0-SNAPSHOT-runner.jar
```

After the application starts up you can test it using curl.

To create some photos:

```
curl -v -X POST -H 'Content-Type: application/json' --data '{"name":"Odie","category":"animals"}' localhost:8083/photos
curl -v -X POST -H 'Content-Type: application/json' --data '{"name":"Garfield","category":"animals"}' localhost:8083/photos
curl -v -X POST -H 'Content-Type: application/json' --data '{"name":"Empire state building","category":"buildings"}' localhost:8083/photos
```

To retrieve all created photos:

```
curl -v localhost:8083/photos
```

To add some likes to the photo with ID 00cb8ab367bf4f8486e58cbf905792e0:

```
curl -v -X POST -H 'Content-Type: application/json' --data '{"id":"2","likes":"5"}' localhost:8083/likes
curl -v -X POST -H 'Content-Type: application/json' --data '{"id":"2","likes":"2"}' localhost:8083/likes
```

To retrieve likes received by all photos:

```
curl -v localhost:8083/likes
```

To retrieve all photos from a specific category ordered by the number of likes:

```
curl localhost:8083/query?category=animals
```

## Deploying on Minishift

Create a new project if it doesn't exist:

```
oc new-project photo-gallery-monolith
```

Deploy a PostgreSQL database:

```
oc new-app \
--template postgresql-persistent \
--param DATABASE_SERVICE_NAME=postgresql \
--param POSTGRESQL_USER=monouser \
--param POSTGRESQL_PASSWORD=password \
--param POSTGRESQL_DATABASE=monodb
```

Prepare to connect to the Docker daemon running within the Minishift virtual machine:

```
eval $(minishift docker-env)
```

Build the application image:

```
docker build \
-f src/main/docker/Dockerfile.jvm \
-t 172.30.1.1:5000/photo-gallery-monolith/monolith \
.
```

Push the application image into the Minishift's integrated Docker registry:

```
docker login -u `oc whoami` -p `oc whoami -t` 172.30.1.1:5000
docker push 172.30.1.1:5000/photo-gallery-monolith/monolith
```

Deploy the application:

```
oc new-app \
--image-stream monolith \
--name monolith \
--env QUARKUS_DATASOURCE_URL=jdbc:postgresql://postgresql:5432/monodb
```

Expose the application to the outside world:

```
oc expose svc monolith
```
