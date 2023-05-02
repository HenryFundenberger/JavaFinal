import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class loginMenu extends JFrame {
    // Need to include instances of loginBody and titleLabel
    private loginBody body;
    private titleLabel title;
    private JButton loginButton;
    private JPanel loginButtonPanel;

    public loginMenu() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create instances of loginBody and titleLabel
        body = new loginBody();
        title = new titleLabel("KC Electric Login");
        loginButton = new JButton("Login");
        loginButton.addActionListener(new loginButtonListener());

        loginButtonPanel = new JPanel();
        loginButtonPanel.add(loginButton);


        // Add them to the frame
        add(title, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);
        add(loginButtonPanel, BorderLayout.SOUTH);

        setVisible(true);



    }

    // Login button listener
    // Pulls username and password from loginBody useing the getters

    private class loginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = body.getUsername();
            String password = body.getPassword();

            // Create a mysql connectin try/catch block to schema called "kcelectricdb"
            // Create a prepared statement to check if the username and password match
            // If they do, open the mainDashboard
            // If they don't, display an error message
            databaseInfo db = new databaseInfo();

            try {
                // Create a connection to the database.
                Connection connection = DriverManager.getConnection(db.getDatabaseURL(), db.getUsername(), db.getPassword());

                // Create a statement object.
                Statement statement = connection.createStatement();

                // Create a string with a SELECT statement.
                String sqlStatement = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

                // Send the statement to the DBMS.
                ResultSet result = statement.executeQuery(sqlStatement);

                // Display the contents of the result set.
                // The result set will have three columns.
                if (result.next()) {
                    System.out.println("Login successful");
                    new mainDashboard();
                    dispose();
                } else {
                    System.out.println("Login failed");
                }

            } catch (Exception er) {
                System.out.println(er.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new loginMenu();

    }
}
