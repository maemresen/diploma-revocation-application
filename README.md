# JavaFX Example Registration App
An example JavaFX destop application with CRUD.

* H2 is using for database. So that, to test applicaton, you just need to run main method.
* For database connection, raw JDBC is used.
* For query creation a simple helper class created with custom annotations.
   * [see](./src/main/java/com/maemresen/fxregisterapp/database/jdbc/utility/QueryHelper.java)