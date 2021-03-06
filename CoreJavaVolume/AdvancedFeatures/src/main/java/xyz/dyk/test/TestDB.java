package xyz.dyk.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * This program test that the database and the JDBC driver are correctly configured.
 */
public class TestDB {
    public static void main(String[] args) throws IOException {
        try {
            runTest();
        } catch (SQLException sqlException) {
            for (Throwable throwable : sqlException) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Runs a test by creating a table, adding a value, showing the table contents, and removing the table.
     *
     * @throws SQLException
     * @throws IOException
     */
    public static void runTest() throws SQLException, IOException {
        try (Connection conn = getConnection();
             Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('hello,world!')");
            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings")) {
                if (result.next()) {
                    System.out.println(result.getString(1));
                }
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }
    }

    /**
     * Gets a connection from the properties specified in the file database.properties.
     *
     * @return the database connection
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            try {
                Class.forName(drivers);
            } catch (Exception e) {

            }
//            System.setProperty("jdbc.drivers", drivers);
        }
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

//        DriverManager.setLogWriter(new PrintWriter(System.out));
        return DriverManager.getConnection(url, username, password);
    }
}
