package Lesson_2.homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainDB {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    public static void main(String[] args) {
        try {
            connect();
            ArrayList<String> columnName = new ArrayList<>();
            columnName.add("ID");
            columnName.add("Name");
            columnName.add("GroupName");
            columnName.add("Score");

            ArrayList<String> columnType = new ArrayList<>();
            columnType.add("INTEGER");
            columnType.add("TEXT");
            columnType.add("TEXT");
            columnType.add("INTEGER");

            String[] strArray = {"Name", "GroupName", "Score"};
            String[][] strArray1 = {{"Zhenya", "Commandos", "37"}, {"Julia", "Capral", "28"}, {"Mars Beton", "Sergeant", "15"}, {"Alexandra", "Super star", "100"}, {"Alexey", "Super star", "100"}};

            String[] strings = {"GroupName", "Score"};
            String[] strings1 = {"Griffindor", "8"};

            String[] array = {};

            createTable("Peoples", columnName, columnType);
            insertIntoTable("Peoples", strArray, strArray1);
            updateTable("Peoples", new ArrayList<>(Arrays.asList(strings)), new ArrayList<>(Arrays.asList(strings1)), "WHERE GroupName = \"Slizerin\"");
            deleteTable("Peoples", "WHERE Score=5");

            rs = selectFromTable("Peoples", array, "WHERE Score = 8");
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
                System.out.println(rs.getString("GroupName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String tableName, List<String> columnName, List<String> columnType) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String tableSTR = "CREATE TABLE " + tableName + "(";

        sb.append(tableSTR);
        String strColumns;
        int counter = columnName.size();

        for (int i = 0; i < columnName.size(); i++) {
            if (counter == columnName.size()) strColumns = String.format( "%s %s PRIMARY KEY AUTOINCREMENT NOT NULL", columnName.get(i), columnType.get(i));
            else strColumns = String.format("%s %s NOT NULL", columnName.get(i), columnType.get(i));
            counter--;
            if (counter != 0) sb.append(strColumns).append(",");
            else sb.append(strColumns);
        }

        sb.append(");");
        executeQueryOnUpdate(sb.toString(), tableName);
    }

    public static void insertIntoTable(String tableName, String[] columns, String[][] values) {
        try {
            StringBuilder strColumnsName = new StringBuilder("INSERT INTO " + tableName + " (");
            int counterColumns = columns.length;
            for (String columnName : columns) {
                counterColumns--;
                if (counterColumns != 0) {
                    strColumnsName.append(columnName).append(",");
                }
                else {
                    strColumnsName.append(columnName).append(")").append(" VALUES (");
                }
            }

            String sqlQueryString;
            String strValues;

            for (String[] value : values) {
                sqlQueryString = strColumnsName.toString();
                strValues = "";
                int counterValues = value.length;
                for (String valueName : value) {
                    counterValues--;
                    if (counterValues != 0) {
                        strValues += "\"" + valueName + "\",";
                    } else {
                        strValues += "\"" + valueName + "\"" + ")";
                    }
                }
                sqlQueryString += strValues;
                statement.addBatch(sqlQueryString);
            }
            connection.setAutoCommit(false);
            statement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateTable(String tableName, List<String> columnName, List<String> values, String condition) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String strUpdate = String.format("UPDATE %s SET ", tableName);
        sb.append(strUpdate);
        String strColumns;
        int counterValues = columnName.size();
        for (int i = 0; i < columnName.size(); i++) {
            strColumns = "";
            counterValues--;
            strColumns += columnName.get(i) + "=";
            try {
                Integer.parseInt(values.get(i));
                strColumns += values.get(i) + " ";
            } catch (Exception e) {
                strColumns += "\"" + values.get(i) + "\" ";
            }
            if (counterValues != 0) {
                strColumns += ",";
                sb.append(strColumns);
            } else {
                sb.append(strColumns);
            }
        }
        sb.append(condition);
        executeQueryOnUpdate(sb.toString(), tableName);
    }

    public static void deleteTable(String tableName, String condition) throws SQLException {
        String strDeleteQuery = String.format("DELETE FROM %s %s", tableName, condition);
        executeQueryOnUpdate(strDeleteQuery, tableName);
    }

    public static ResultSet selectFromTable(String tableName, String[] fields, String condition) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        int counterColumns = tableName.length();
        if (fields.length == 0) {
            sb.append("* ");
        }
        else {

            for (String columnName : fields) {
                counterColumns--;
                if (counterColumns != 0) {
                    sb.append(columnName).append(",");
                }
                else {
                    sb.append(columnName);
                }
            }
        }
        sb.append(" FROM ").append(tableName).append(" ").append(condition);
        System.out.println(sb.toString());
        try {
            rs = statement.executeQuery(sb.toString());
            System.out.printf("Table '%s' was successfully updated", tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void executeQueryOnUpdate(String sqlString, String tableName) throws SQLException {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Savepoint sv = null;
        try {
            sv = connection.setSavepoint();
            statement.executeUpdate(sqlString);
            connection.setAutoCommit(true);
            if (stackTraceElements[2].getMethodName().equals("createTable")) {
                System.out.printf("Table '%s' was successfully created", tableName);
            } else {
                System.out.printf("Table '%s' was successfully updated", tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(sv);
        }
    }
}
