# YugabyteDB Dialect for Apache Spark

## Overview
In Apache Spark, database dialects determine how Spark interacts with a database using JDBC. For PostgreSQL, URL starting with `jdbc:postgresql:`, Spark selects the `PostgresDialect`, which includes support for PostgreSQL-specific data types like `ArrayType`. This is achieved by implementing appropriate type mappings in functions such as `getJDBCType()`.

However, when using the YugabyteDB JDBC driver with a URL starting with `jdbc:yugabytedb:`, Spark fails to match the URL with any known dialect and defaults to the `NoopDialect`. The `NoopDialect` lacks PostgreSQL-compatible features, including handling `ArrayType`. This mismatch causes processing errors when working with YugabyteDB in Spark.

The **YugabyteDBDialectPlugin** resolves this issue by:
- Providing a specific dialect for the YugabyteDB URL pattern.
- Ensuring PostgreSQL-compatible features, including handling of `ArrayType`, are available when working with YugabyteDB.

By using this dialect, you enable seamless integration of YugabyteDB with Apache Spark, ensuring accurate type mappings and efficient processing.

---

## Steps to Run the Application

### Prerequisites
1. **Apache Spark:** Ensure Spark 2.4.2 or later is installed and properly configured.
2. **JDK:** Install JDK 8 or JDK 11.
3. **Maven:** Ensure Maven is installed for building the application.

### Build the Jar locally

#### 1. Clone the Repository
```bash
git clone https://github.com/yugabyte/spark-yugabytedb-dialect-example.git
cd spark-yugabytedb-dialect-example
```

#### 2. Build and Publish the Jar Locally
```bash
mvn clean package
```
This will generate a JAR file in the `target` directory
```shell
mvn install
```
Include the dependency in your application's pom.xml
```xml
<dependency>
    <groupId>com.yugabyte</groupId>
    <artifactId>spark-yugabytedb-dialect</artifactId>
    <version>1.0</version>
</dependency>
```

#### 3. Run the Test
Create `ysql_spark` Schema on your cluster
```bash
create schema ysql_spark;
```
Run the test:
```bash
mvn exec:java -Dexec.mainClass="org.example.SparkYSQLExample" -Dexec.classpathScope="test"
```
Verify Output:
- The application will insert data into the `ysql_spark.student` table and retrieve the following data:
```shell
+---+------------------+
| ID|           details|
+---+------------------+
|  2|[Mark, 23, Python]|
|  1|  [John, 35, Java]|
+---+------------------+
```

