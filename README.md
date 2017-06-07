# TicketService

To compile the program: 

```
mvn clean install
```

To run:

```
java -jar target/TicketService-1.0-SNAPSHOT.jar
```

The default dimensions of the seats are 9 rows and 33 columns, and the default expiration time is 10 minutes. You can change these values with optional commands `[-r]` or `[-rows]` for rows, `[-c]` or `[-columns]` for columns, and `[-e]` or `[-expire]` for expiration time in seconds. For example, to set 5 rows, 20 columns, and 120 seconds of expiration time, run

```
java -jar target/TicketService-1.0-SNAPSHOT.jar -r 5 -c 20 -e 120
```

To view number of seats available, type

```
number
```

To place a number of seats on hold, type `hold` and then quantity and email, i.e. `hold 10 user@company.com`

```
hold [quantity] [email]
```

If reservation is made successfully, you will receive a confirmation number, and an expiration date for the hold. Make sure you make the reservation before the expiration date. 

To reserve the seats on hold, type `reserve` and then confirmation number and email, i.e. `reserve 704152029 user@company.com`

```
reserve [confirmation ID] [email]
```

To quit the program type

```
exit
```