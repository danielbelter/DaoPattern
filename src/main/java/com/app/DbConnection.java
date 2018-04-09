package com.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
    private static DbConnection ourInstance = new DbConnection();

    public static DbConnection getInstance() {
        return ourInstance;
    }

    private final String DRIVER = "org.sqlite.JDBC";
    private final String DATABASE = "jdbc:sqlite:Test.db";

    private Connection connection;

    private DbConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE);

            createTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void createTable()
    {
        try {
            String sqlPerson =
                    "CREATE TABLE IF NOT EXISTS Person ( " +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name VARCHAR(50) NOT NULL, " +
                            "surname VARCHAr(50) NOT NULL, " +
                            "age INTEGER NOT NULL" +
                            " );";




            Statement statement = connection.createStatement();
            statement.execute(sqlPerson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
