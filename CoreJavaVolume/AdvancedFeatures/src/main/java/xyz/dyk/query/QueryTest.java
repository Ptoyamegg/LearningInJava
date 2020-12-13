package xyz.dyk.query;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * This program demonstrates several complex database queries.
 */
public class QueryTest {
    public static final String allQuery = "SELECT Books.Price, Books.Title FROM Books";
    public static final String authorPublisherQuery = "SELECT Books.Price, Books.Title" +
            " FROM Books, Authors, Publishers, BooksAuthors" +
            " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN" +
            " AND Books.Publisher_Id = Publishers.Publisher_Id AND Authors.Name = ?" +
            " AND Publishers.Name = ?";
    public static final String authorQuery = "SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors" +
            " WHERE Authors.Author_Id = BooksAuthors.Author_Id AND BooksAuthors.ISBN = Books.ISBN" +
            " AND Authors.Name = ?";
    public static final String publisherQuery = "SELECT Books.Price, Books.Title FROM Books, Publishers" +
            " WHERE Books.Publisher_Id = Publishers.Publisher_Id AND Publishers.Name = ?";
    public static final String priceUpdate = "UPDATE Books SET Price = Price + ? " +
            " WHERE Books.Publisher_Id = (SELECT Publisher_Id FROM Publishers WHERE Name = ?)";

    private static Scanner in;
    private static ArrayList<String> authors = new ArrayList<>();
    private static ArrayList<String> publishers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (Connection conn = getConnection()) {
            in = new Scanner(System.in);
            authors.add("Any");
            publishers.add("Any");
            try (Statement stat = conn.createStatement()) {
                //  File the authors arrays list
                String query = "SELECT Name From Authors";
                try (ResultSet result = stat.executeQuery(query)) {
                    while (result.next()) {
                        authors.add(result.getString(1));
                    }
                }

                //  File the publishers arrays list
                query = "SELECT Name FROM Publishers";
                try (ResultSet result = stat.executeQuery(query)) {
                    while (result.next()) {
                        publishers.add(result.getString(1));
                    }
                }

                boolean done = false;
                while (!done) {
                    System.out.print("Q)uery C)hange prices E)xit: ");

                    String input = in.next().toUpperCase();
                    if ("Q".equals(input)) {
                        executeQuery(conn);
                    } else if ("C".equals(input)) {
                        changePrices(conn);
                    } else {
                        done = true;
                    }
                }
            }
        } catch (SQLException e) {
            for (Throwable throwable : e) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Executes the selected query.
     *
     * @param conn the database connection
     * @throws SQLException
     */
    public static void executeQuery(Connection conn) throws SQLException {
        String author = select("Authors: ", authors);
        String publisher = select("Publishers: ", publishers);
        PreparedStatement stat;
        String any = "Any";
        if (!any.equals(author) && !any.equals(publisher)) {
            stat = conn.prepareStatement(authorPublisherQuery);
            stat.setString(1, author);
            stat.setString(2, publisher);
        } else if (!any.equals(author)) {
            stat = conn.prepareStatement(authorQuery);
            stat.setString(1, author);
        } else if (!any.equals(publisher)) {
            stat = conn.prepareStatement(publisherQuery);
            stat.setString(1, publisher);
        } else {
            stat = conn.prepareStatement(allQuery);
        }

        try (ResultSet result = stat.executeQuery()) {
            while (result.next()) {
                System.out.println(result.getString(1) + ", " + result.getString(2));
            }
        }
    }

    /**
     * Executes an update statement to change prices.
     *
     * @param conn the database connection
     * @throws SQLException
     */
    public static void changePrices(Connection conn) throws SQLException {
        String publisher = select("Publishers: ", publishers.subList(1, publishers.size()));
        System.out.print("Change prices by: ");
        double priceChange = in.nextDouble();
        PreparedStatement stat = conn.prepareStatement(priceUpdate);
        stat.setDouble(1, priceChange);
        stat.setString(2, publisher);
        int r = stat.executeUpdate();
        System.out.println(r + " records updated.");
    }

    /**
     * Asks the user to select a string.
     *
     * @param prompt  the prompt to display
     * @param options the options from which the user can choose.
     * @return the option that the user chose
     */
    public static String select(String prompt, List<String> options) {
        while (true) {
            System.out.println(prompt);
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%2d) %s%n", i + 1, options.get(i));
            }
            int sel = in.nextInt();
            if (sel > 0 && sel <= options.size()) {
                return options.get(sel - 1);
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
}
