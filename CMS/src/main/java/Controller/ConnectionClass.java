package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    private static Connection con;

    public static Connection getCon() {
        establishConnection();
        return con;
    }

    public static void setCon(Connection con) {
        ConnectionClass.con = con;
    }

    public static void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeeshop?useSSL=false", "root", "");
            System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnectionOpen() {
        return con != null;
    }

    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void establishConnection() {
        if (isConnectionOpen()) {
            System.out.println("Connection is already open.");
        }
        openConnection();
        System.out.println("Connection established successfully.");
    }
}
