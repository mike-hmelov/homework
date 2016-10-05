## Synopsis

Loan processing RESTFull web service. Implemented based on [requirements](https://github.com/yurachud/homework/blob/master/README.md).

## Installation

1. Obtain project code
2. Run following command to create package
```bash 
./mvnw package
```
3. Create H2 database by running command
```bash
./update.db.sh
```
Database:
    user: sa
    password: 

4. Run the service by invoking command
```bash
./run.sh
```

## API Reference

After run please open [docs](http://localhost:8080/docs/index.html) page

## Tests

Run all unit tests
```bash
./mvnw test 
```
Run all unit and integration tests
```bash
./mvnw integration-tests
```