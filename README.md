chain
=====

Chain is a library to interact with RDBMS. It provides simple chain-function like style for basic 
SQL CRUD operations : select, update, insert and delete.

For example, we have a table CUSTOMER with fields ID and NAME. We need to update field NAME with a new value for 
a record with ID = 3. Using Chain library this task can be done this way:
```
sql.update("CUSTOMER").set("NAME", "noname").where("ID").equal(3).run();
```

So Chain library has the following advantages:
* works over plain JDBC, no third-party frameworks and libraries needed.
* uses compact syntax, you need write no boiler-plate code.
* some support for compile time validation provided by chain-function coding style.
* non-checking exceptions, there is no need to catch them without reason.

The single disadvantage in comparison with JDBC is performance because of string concatination, 
multiple function invokations, auxiliary object creation and so on. But this lack isn't critical.
