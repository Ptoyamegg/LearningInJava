package xyz.dyk.view;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ViewDB {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ViewDBFrame();
            frame.setTitle("ViewDB");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * The frame that holds the data panel and the navigation buttons.
 */
class ViewDBFrame extends JFrame {
    private JButton previousButton;
    private JButton nextButton;
    private JButton deleteButton;
    private JButton saveButton;
    private DataPanel dataPanel;
    private Component scrollPane;
    private JComboBox<String> tableNames;
    private Properties props;
    private CachedRowSet crs;
    private Connection conn;

    public ViewDBFrame() throws HeadlessException {
        tableNames = new JComboBox<>();
        try {
            readDatabaseProperties();
            conn = getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            try (ResultSet mrs = meta.getTables(null, null, null, new String[]{"TABLE"})) {
                while (mrs.next()) {
                    String tableName = mrs.getString(3);
                    if ("sys_config".equals(tableName)) continue;
                    tableNames.addItem(tableName);
                }
            }
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableNames.addActionListener(e -> showTable((String) tableNames.getSelectedItem(), conn));
        add(tableNames, BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e1) {
                    for (Throwable t2 : e1) {
                        t2.printStackTrace();
                    }
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        previousButton = new JButton("Previous");
        previousButton.addActionListener(e -> showPreviousRow());
        buttonPanel.add(previousButton);

        nextButton = new JButton("Next");
        nextButton.addActionListener(e -> showNextRow());
        buttonPanel.add(nextButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteRow());
        buttonPanel.add(deleteButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveChanges());
        buttonPanel.add(saveButton);
        if (tableNames.getItemCount() > 0) {
            showTable(tableNames.getItemAt(0), conn);
        }
    }

    /**
     * Prepares the text fields for showing a new table, and shows the first row.
     *
     * @param tableName the name of the table to display
     * @param conn      the database connection
     */
    public void showTable(String tableName, Connection conn) {
        try (Statement stat = conn.createStatement();
             ResultSet result = stat.executeQuery("SELECT * FROM " + tableName)) {
            // get result set

            // copy int cached row set
            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.setTableName(tableName);
            crs.populate(result);

            if (scrollPane != null) {
                remove(scrollPane);
            }
            dataPanel = new DataPanel(crs);
            scrollPane = new JScrollPane(dataPanel);
            add(scrollPane, BorderLayout.CENTER);
            pack();
            showNextRow();
        } catch (SQLException e) {
            for (Throwable throwable : e) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Moves to previous table row.
     */
    public void showPreviousRow() {
        try {
            if (crs == null || crs.isFirst()) {
                return;
            }
            crs.previous();
            dataPanel.showRow(crs);
        } catch (SQLException e) {
            for (Throwable throwable : e) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Moves to the next table row.
     */
    public void showNextRow() {
        try {
            if (crs == null || crs.isLast()) {
                return;
            }
            crs.next();
            dataPanel.showRow(crs);
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
    }

    public void deleteRow() {
        if (crs == null) {
            return;
        }
        new SwingWorker<Void, Void>() {

            @Override
            public Void doInBackground() throws SQLException {
                crs.deleteRow();
                crs.acceptChanges(conn);
                if (crs.isAfterLast()) {
                    if (!crs.last()) {
                        crs = null;
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                dataPanel.showRow(crs);
            }
        }.execute();
    }

    /**
     * save all changes.
     */
    public void saveChanges() {
        if (crs == null) {
            return;
        }
        new SwingWorker<Void, Void>() {

            @Override
            public Void doInBackground() throws SQLException {
                dataPanel.setRow(crs);
                crs.acceptChanges(conn);
                return null;
            }
        }.execute();
    }

    private void readDatabaseProperties() throws IOException {
        props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
        }
        String driver = props.getProperty("jdbc.drivers");
        if (driver != null) {
            System.setProperty("jdbc.drivers", driver);
        }
    }

    /**
     * Get a connection from the properties specified in the file database.properties.
     *
     * @return the database connection
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}

/**
 * This panel displays the contents of result set.
 */
class DataPanel extends JPanel {
    private List<JTextField> fields;

    /**
     * Constructs the data panel.
     *
     * @param rs the result set whose contents this panel displays
     * @throws SQLException
     */
    public DataPanel(RowSet rs) throws SQLException {
        fields = new ArrayList<>();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            gbc.gridy = i;

            String columnName = rsmd.getColumnLabel(i + 1);
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel(columnName), gbc);

            int columnWidth = rsmd.getColumnDisplaySize(i + 1);
            JTextField tb = new JTextField(columnWidth);
            if (!"java.lang.String".equals(rsmd.getColumnClassName(i + 1))) {
                tb.setEditable(false);
            }
            fields.add(tb);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(tb, gbc);
        }
    }

    /**
     * Shows a database row by populating all text fields with the column values.
     *
     * @param rs
     */
    public void showRow(ResultSet rs) {
        try {
            if (rs == null) {
                return;
            }
            for (int i = 0; i < fields.size(); i++) {
                String field = rs == null ? "" : rs.getString(i + 1);
                JTextField tb = fields.get(i);
                tb.setText(field);
            }
        } catch (SQLException e) {
            for (Throwable throwable : e) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Updates changed data into the current row of row set.
     *
     * @param rs
     * @throws SQLException
     */
    public void setRow(RowSet rs) throws SQLException {
        for (int i = 0; i < fields.size(); i++) {
            String field = rs.getString(i + 1);
            JTextField tb = fields.get(i);
            if (!field.equals(tb.getText())) {
                rs.updateString(i + 1, tb.getText());
            }
            rs.updateRow();
        }
    }
}