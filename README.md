This repo demonstrates an issue with Maybe returns not always getting caught by an
ExceptionHandler when there's an error in the pipe.

- building

```bash
./gradlew clean build
```

- running
```bash
./gradlew run
```

- endpoints
    
    http://localhost:8080/api/maybe?count=1
    http://localhost:8080/api/list?count=1

Notes:

The maybe endpoint sometimes throws a 400 instead of being caught by the
GlobalExceptionHandler (which then throws a 500). If the Handler *does* catch
the exception, restart the JVM and try again, it will sometimes work,
sometimes not, but you have to restart the JVM to get the behaviour to change.
Once the JVM goes one route, it stays that way.

The list endpoint always catches the error and throws 500 correctly.

This is happening to us in k8s environment in GCP where the same code is
deployed to multiple nodes, and some of them exhibit the behaviour, others
don't.
