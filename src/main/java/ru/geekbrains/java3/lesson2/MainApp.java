package ru.geekbrains.java3.lesson2;

import java.sql.*;

public class MainApp {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();
            deleteTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }
    private static void deleteTable() throws SQLException {
        statement.executeUpdate("delete from cats;");
    }

    private static void deleteRecord() throws SQLException {
        statement.executeUpdate("delete from cats where name = 'tigra';");
    }

    private static void updateRecord() throws SQLException {
        statement.executeUpdate("update cats set age = 6 where age > 5;");
    }

    private static void reedTable() throws SQLException {
        try (ResultSet rs = statement.executeQuery("select * from cats where age > 6;")) {
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + "  " + rs.getInt(3));
            }
        }
    }
    private static void createRecord() throws SQLException {
        statement.executeUpdate("insert into cats (name, age) values ('tigra', 10);");
    }

    public static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MyDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Невозможно подключится к Базе Данных!!!");
        }

    }
    public static void disconnect(){
        if (connection != null){
            try {
                connection.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }
}
