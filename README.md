chain
=====

Chain is a library to interact with RDBMS. It provides simple chain-function like style for basic 
SQL CRUD operations : select, update, insert and delete.


For example, we have a table CUSTOMER with fields ID and NAME. We need to update field NAME with a new value for 
a record with specified ID. Using Chain library this task can be done this way:

```
// Just a single line of code
sql.update("CUSTOMER").set("NAME", name).where("ID").equal(id).run();
```

Lets compare it with plain JDBC realization of the same task:

```
// Query string
private static final String UPDATE_NAME = "UPDATE CUSTOMER SET NAME = ? WHERE ID = ?";
...
// Query execution and error handling
PreparedStatement statement = null;
try {
  statement = connection.prepareStatement(UPDATE_NAME);
  statement.setString(1, name);
  statement.setLong(2, id);
  statement.execute();
} catch (SQLException ex) {
  // logging error
} finally {
  if (statement != null) {
    try {
      statement.close();
    } catch (SQLException ex) {
      // logging output
    }
  }
}
```
As we can see Chain library help us to avoid boiler-plate code and, therefore, code is more expressive.
It's easier to understand and to maintain it.

Lets make an overview of Chain library facilities to solve some typical tasks on interaction with RDBMS:
```
// Delete record by Id
sql.delete("CUSTOMER").where("ID").equal(1L).run();

// Update record by Id
sql.update("CUSTOMER").set("NAME", name).where("ID").equal(id).run();

// Update records using Map<String, Object> data set
sql.updateData("CUSTOMER", input).includeAll().all().run();

// Insert new record
sql.insert("CUSTOMER").columns("ID", "NAME").values(4L, "Bob").run();

// Insert record using Map<String, Object> data set
sql.insertData("CUSTOMER", input).includeAll().run();

// Select record count
int count = sql.count("CUSTOMER").where("NAME").equal("John").get();

// Select single record by Id
Map<String, Object> data = sql.selectSingle("CUSTOMER").includeAll().where("ID").equal(2L).get();

// Select all records from the table
List<Map<String, Object>> data = sql.select("CUSTOMER").includeAll().all().get();

```

So Chain library has the following advantages:
* works over plain JDBC, no third-party frameworks and libraries needed.
* uses compact syntax, you need write no boiler-plate code.
* some support for compile time validation provided by chain-function coding style.
* non-checking exceptions, there is no need to catch them without reason.

The single disadvantage in comparison with JDBC is performance because of string concatination, 
multiple function invokations, auxiliary object creation and so on. But this lack isn't critical.
