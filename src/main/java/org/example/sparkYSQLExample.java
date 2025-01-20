package org.example;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.spark.sql.jdbc.YugabytedbDialect;

public class sparkYSQLExample {

    public static void main(String[] args) {
        YugabytedbDialect.register();

        //Create the spark session to work with spark
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic example")
                .config("spark.master", "local")
                .getOrCreate();

        //Connection URL
        String jdbcUrlYB = "jdbc:yugabytedb://localhost:5433/yugabyte";
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "yugabyte");
        connectionProperties.put("password", "yugabyte");

        List<Student> data = Arrays.asList(
                new Student("1", new String[]{"John", "35", "Java"}),
                new Student("2", new String[]{"Mark", "23", "Python"})
        );

        // Create a DataFrame directly from the list of objects
        Dataset<Row> df = spark.createDataFrame(data, Student.class);
        df.write().jdbc(jdbcUrlYB,"ysql_spark.student",connectionProperties);

        // Read data from the table and show it
        Dataset<Row> retrievedData = spark.read().jdbc(jdbcUrlYB, "ysql_spark.student", connectionProperties);
        retrievedData.show();

        spark.stop();
    }
}


