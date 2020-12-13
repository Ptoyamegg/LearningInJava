package xyz.dyk.exec;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ExecSQL {
    public static void main(String[] args) throws IOException {
        args = new String[] {"C:\\Users\\k1345\\Downloads\\corejava10\\corejava\\v2ch05\\Authors.sql"};
        try (Scanner in = args.length > 0 ? new Scanner(Paths.get(args[0]), StandardCharsets.UTF_8.name()) :
                new Scanner(System.in)) {
            try (Connection conn = getConnection();
                 Statement stat = conn.createStatement()) {
                while (true) {
                    if (args.length == 0) {
                        System.out.println("Enter command or EXIT to exit: ");
                    }
                    if (!in.hasNextLine()) {
                        return;
                    }
                    String line = in.nextLine().trim();
                    if ("EXIT".equalsIgnoreCase(line)) {
                        return;
                    }
                    if (line.endsWith(";")) {
                        //  remove trailing semicolon
                        line = line.substring(0, line.length() - 1);
                    }
                    try {
                        boolean isResult = stat.execute(line);
                        if (isResult) {
                            try (ResultSet rs = stat.getResultSet()) {
                                showResultSet(rs);
                            }
                        } else {
                            int updateCount = stat.getUpdateCount();
                            System.out.println("updateCount = " + updateCount);
                        }
                    } catch (SQLException e) {
                        for (Throwable throwable : e) {
                            throwable.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            for (Throwable throwable : ex) {
                throwable.printStackTrace();
            }
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
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

//        DriverManager.setLogWriter(new PrintWriter(System.out));
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Prints a result set
     *
     * @param result the result set to be printed
     * @throws SQLException
     */
    public static void showResultSet(ResultSet result) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 0; i < columnCount; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(metaData.getColumnLabel(i + 1));
        }
        System.out.println();

        while (result.next()) {
            for (int i = 0; i < columnCount; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(result.getString(i + 1));
            }
            System.out.println();
        }
    }
}
