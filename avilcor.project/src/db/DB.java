package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import db.DbException;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn != null) return conn;
        Properties properties = loadProperties();
        String url = properties.getProperty("dburl");
        try {
            conn = DriverManager.getConnection(url, properties); // retorna Connection
            return conn;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeConnection() {
        if (conn == null) return;
        try {
            conn.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps == null) return;
        try {
            ps.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closeStatement(Statement st) {
        if (st == null) return;
        try {
            st.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closeResultSet(ResultSet rs) {
        if (rs == null) return;
        try {
            rs.close();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")) {
            Properties properties = new Properties(); // Properties: Objeto que armazena
            properties.load(fs); // guarda os dados dentro do arquivo
            return properties;
        } catch (IOException e) {
            throw new DbException(e.getMessage()); // runTimeExeption
        }
    }
}
