package Lesson_2.classwork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class MainDB {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void main(String[] args) {
        try {
            connect();
            readFile();


            //-------------------------- На уроке
//            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('bob1', 10)");
//            Savepoint sp = connection.setSavepoint();
//            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('bob2', 20)");
//            connection.rollback(sp);
//            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('bob3', 30)");

            //-------------------------- На уроке
//            connection.setAutoCommit(false);
//            pstmt = connection.prepareStatement("INSERT INTO students (name, score) VALUES(?, ?)");
//            for (int i = 0; i < 1000; i++) {
//                pstmt.setString(1, "Bob" + i + 1);
//                pstmt.setInt(2, 20 * (i + 1));
//                pstmt.addBatch();
//            }
//
//            pstmt.executeBatch();
//            connection.setAutoCommit(true);


            //-------------------------- На уроке
//            connection.setAutoCommit(false);
//            for (int i = 1; i < 1000; i++) {
//                stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('unknown', " + i * 10 + ")");
//            }
//            connection.setAutoCommit(true);

//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnType(i) + " " + rsmd.getTableName(i));
//            }
//
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString("name"));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    //-------------------------- На уроке
    public static void readFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\GeekBrains_Курсы\\Java Level 3 (запись)\\update.txt");
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                String[] mass = scanner.nextLine().split(" ");
                updateDB(mass[0], mass[1]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //-------------------------- На уроке
    public static void updateDB(String id, String newVal) {
        String sql = String.format("UPDATE students set score = %s where id = %s", newVal, id);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mydb.db");
            stmt = connection.createStatement();
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
}
