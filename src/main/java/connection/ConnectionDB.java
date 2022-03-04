package connection;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {
    //  Database credentials
    static final String DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "postgres";

    private static Connection con = null;

    static {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
