import com.sun.tools.jconsole.JConsoleContext;

import javax.management.Query;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FindCustomer extends JFrame {
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
    private String travelPage;
    private Customer customer;

    public FindCustomer(String nextPage) {
        setTitle("KC Electric - Customer Lookup");
        setSize(375, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel(nextPage);
        setVisible(true);
        travelPage = nextPage;

    }

    private void buildPanel(String nextPage) {
        title = new titleLabel("Find Customer");
        add(title, BorderLayout.NORTH);
        findCustomer = new findCustomerFields();

        add(findCustomer, BorderLayout.CENTER);

        // Add navigation menu
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");

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
        // No Space vertically

        c.weightx = 0;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;

        submit = new JButton("Submit");
        submit.addActionListener(new submitListener());
        buttonPanel.add(submit, c);

        c.gridx = 1;
        cancel = new JButton("Cancel");
        cancel.addActionListener(new cancelListener());
        buttonPanel.add(cancel, c);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class submitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String name;
            int id;
            String phoneNumber;
            String address;
            String city;
            String state;
            String zipCode;
            String email;
            databaseInfo db = new databaseInfo();
            // Get the string from the text field
            String customerName = findCustomer.getName();
            try {
                // Create a connection to the database.
                Connection connection = DriverManager.getConnection(db.getDatabaseURL(), db.getUsername(), db.getPassword());

                // Create a statement object.
                Statement statement = connection.createStatement();

                // Creates Customer object with the data from the database
                // Querey the database to select * from customer where name = customerName
                String query = "SELECT * FROM customer WHERE name = '" + customerName + "'";

                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    name = resultSet.getString("name");
                    id = resultSet.getInt("id");
                    phoneNumber = resultSet.getString("phoneNumber");
                    address = resultSet.getString("address");
                    city = resultSet.getString("city");
                    state = resultSet.getString("state");
                    zipCode = resultSet.getString("zip");
                    email = resultSet.getString("email");



                     customer = new Customer(name, id, phoneNumber, address, city, state, zipCode, email);

                     if (travelPage.equals("Update")) {
                        new updateApplyCustomer(customer);
                        dispose();
                    }
    
                    if (travelPage.equals("Remove")) {
    
                        new removeCustomer();
                        dispose();
                    }
    
                    if (travelPage.equals("energyUsage")) {
                        new displayCustomerEnergy(customer);
                        dispose();
                    }
    
                    if (travelPage.equals("viewPaymentStatus")) {
                        new viewPaymentStatus(customer);
                        dispose();
                    }

                    if(travelPage.equals("makePayment")){
                        new payCurrentBill(customer);
                        dispose();
                    }

                    if(travelPage.equals("invoice")){
                        new viewInvoice(customer);
                        dispose();

                    }


                } else {

                    // If the customer does not exist, display an error message
                    JOptionPane.showMessageDialog(null, "Customer does not exist");
                }

                

            } catch (Exception er) {
                System.out.println(er.getMessage());
            }

        }
    }

    private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(travelPage.equals("energyUsage")){
                new mainDashboard();
                dispose();
            }
            if(travelPage.equals("viewPaymentStatus")){
                new managePaymentsDash();
                dispose();
            }

            else{
                new mainDashboard();
                dispose();

            }

        }
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

    public static void main(String[] args) {
        new FindCustomer("Update");
    }


}
