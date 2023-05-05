import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class removeCustomer extends JFrame {
    titleLabel title;
    findCustomerFields findCustomer;

    // Submit and Cancel buttons
    private JButton submit;
    private JButton cancel;

    // Panel for the buttons
    private JPanel buttonPanel;

    // Add navigation menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;
    private JMenuItem customerDashboard;
    private Customer currCustomer;

    public removeCustomer() {
        setTitle("KC Electric - Remove Customer");

        setSize(375, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
    }

    private void buildPanel(){
        // Adds title to the top of the frame
        title = new titleLabel("Find Customer to Remove");
        add(title, BorderLayout.NORTH);
        findCustomer = new findCustomerFields();

        add(findCustomer, BorderLayout.CENTER);

        // Add navigation menu
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");
        // Components for the navigation menu
        logout = new JMenuItem("Logout");
        logout.addActionListener(new logoutListener());
        home = new JMenuItem("Home");
        home.addActionListener(new homeListener());
        customerDashboard = new JMenuItem("Customer Dashboard");
        customerDashboard.addActionListener(new customerDashboardListener());
        menu.add(logout);
        menu.add(home);
        menu.add(customerDashboard);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Add the button panel to the south of the frame
        // Have the buttons line up horizontally
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;

        // Add the submit button
        submit = new JButton("Submit");
        submit.addActionListener(new submitListener());
        // row 0
        // column 0
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        buttonPanel.add(submit, c);

        // Add the cancel button
        cancel = new JButton("Cancel");
        cancel.addActionListener(new cancelListener());
        // column 1
        // row 0
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        buttonPanel.add(cancel, c);

        // Add the button panel to the south of the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new loginMenu();
            dispose();

        }
    }

    private class homeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new mainDashboard();
            dispose();
        }
    }

    private class customerDashboardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new customerDashboard();
            dispose();
        }
    }

    private class submitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Gets database info to connect to local database
            databaseInfo db = new databaseInfo();
            String input = findCustomer.getName();
            // Connect to the database
            try {
                // Connect to the database
                Connection conn = DriverManager.getConnection(db.getDatabaseURL(), db.getUsername(), db.getPassword());

                // First see if the customer exists
                String query = "SELECT * FROM customer WHERE name = '" + input + "'";
                ResultSet rs = conn.createStatement().executeQuery(query);
                // making sure it's a valid entry
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Customer does not exist");
                    new customerDashboard();
                    dispose();
                }
                else {
                    // if it is a valid entry, delete from the database
                    // Delete from customer table where name = input
                    query = "DELETE FROM customer WHERE name = '" + input + "'";
                    conn.createStatement().executeUpdate(query);
                    // delete from the electric account table where name = input
                    query = "DELETE FROM electricaccount WHERE name = '" + input + "'";
                    conn.createStatement().executeUpdate(query);
                    // Display success message
                    JOptionPane.showMessageDialog(null, "Customer Removed Successfully");

                    new customerDashboard();
                    dispose();
                }

            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }

        }
    }

    private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new customerDashboard();
            dispose();
        }
    }
}
