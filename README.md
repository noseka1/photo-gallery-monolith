# photo-gallery-monolith

Photo Gallery monolithic application

This is a monolithic server application that is composed of the three components:

* Photo component
* Like component
* Query component

Note that each of the components can be deployed as a standalone microservice if so desired.

You can build this project using:

```
mvn clean package
```

You can run this application using:

```
java -jar ./target/photo-gallery-monolith-1.0-SNAPSHOT-fat.jar
```

After the application starts up you can test it using curl.

To create some photos:

```
curl -v -X POST --data '{"name":"Odie","category":"animals"}' localhost:8083/photos
curl -v -X POST --data '{"name":"Garfield","category":"animals"}' localhost:8083/photos
curl -v -X POST --data '{"name":"Empire state building","category":"buildings"}' localhost:8083/photos
```

To retrieve all created photos:

```
curl -v localhost:8083/photos
```

To add some likes to the photo with ID 00cb8ab367bf4f8486e58cbf905792e0:

```
curl -v -X POST --data '{"id":"00cb8ab367bf4f8486e58cbf905792e0","likes":"5"}' localhost:8083/likes
curl -v -X POST --data '{"id":"00cb8ab367bf4f8486e58cbf905792e0","likes":"2"}' localhost:8083/likes
```

To retrieve likes received by all photos:

```
curl -v localhost:8083/likes
```

To retrieve all photos from a specific category ordered by number of likes:

```
curl localhost:8083/query?category=animals
```
