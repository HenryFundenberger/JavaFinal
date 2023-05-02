import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class newCustomer extends JFrame {
    CustomerDataInput newCustomerInfo;
    titleLabel title;

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



    public newCustomer() {
        setTitle("KC Electric - Create a New Customer");
        setSize(375, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
    }

    private void buildPanel() {
        title = new titleLabel("Create a New Customer");
        add(title, BorderLayout.NORTH);
        newCustomerInfo = new CustomerDataInput();

        add(newCustomerInfo, BorderLayout.CENTER);

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
            String phoneNumber;
            String email;
            String address;
            String city;
            String state;
            String zip;
            String energyTariff;
            // convert energy tariff to double
            double energyTariffDouble;
            String meterType;
            // Get current YYYY-MM-DD
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            databaseInfo db = new databaseInfo();

            try {

                 name = newCustomerInfo.getName();
                 phoneNumber = newCustomerInfo.getPhoneNumber();
                 email = newCustomerInfo.getEmail();
                 address = newCustomerInfo.getAddress();
                 city = newCustomerInfo.getCity();
                 state = newCustomerInfo.getState();
                 zip = newCustomerInfo.getZipCode();
                 energyTariff = newCustomerInfo.getEnergyTarrif();
                // convert energy tariff to double
                 energyTariffDouble = Double.parseDouble(energyTariff);
                 meterType = newCustomerInfo.getMeterType();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Please fill out all fields");
                return;
            }




            try {
                // Create a connection to the database.
                Connection connection = DriverManager.getConnection(db.getDatabaseURL(), db.getUsername(), db.getPassword());

                // First of all see if customer already exists in the database
                String query0 = "SELECT name FROM customer WHERE name = '" + name + "'";
                Statement statement0 = connection.createStatement();
                ResultSet rs0 = statement0.executeQuery(query0);
                String nameCheck = "";
                while (rs0.next()) {
                    nameCheck = rs0.getString("name");
                }
                if (nameCheck.equals(name)) {
                    JOptionPane.showMessageDialog(null, "Customer already exists in the database");
                    return;
                }

                // Insert into the customer table
                // The customer table is layed out like
                // name, phoneNumber, address, City, state, zip
                String query = "INSERT INTO customer (name, phonenumber, email,  address, City, state, zip) VALUES ('" + name + "', '" + phoneNumber + "', '" + email + "', '" + address + "', '" + city + "', '" + state + "', '" + zip + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);

                // now create a Customer object which has the fields
                // name, phoneNumber, address, city, state, zip, id

                // Get the KCElectricID from the customer table
                String query2 = "SELECT id FROM customer WHERE name = '" + name + "'";
                Statement statement2 = connection.createStatement();
                ResultSet rs = statement2.executeQuery(query2);
                int id = 0;
                while (rs.next()) {
                    id = rs.getInt("id");
                }

                // Now create an input statement for the electricaccount table
                // It has teh fields in this order
                // name, id, energyTarrif(decimal), meterType, kwhPrice (decimal), lastMonthMeter (decimal), currentMeter(decimal), payed (which is an int)
                String query3 = "INSERT INTO electricaccount (name, id, energyTarrif, meterType, kwhPrice, lastMonthMeter, currentMeter, payed, lastPayment ) VALUES ('" + name + "', '" + id + "', '" + energyTariffDouble + "', '" + meterType + "', '0.15', '0.0', '1000.0', '0', '" + date + "')";
                Statement statement3 = connection.createStatement();
                statement3.executeUpdate(query3);

                // return to the customer dashboard
                new customerDashboard();
                dispose();

            } catch (Exception er) {
                System.out.println(er.getMessage());
            }
        }

    }

    private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new mainDashboard();
            dispose();
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
        new newCustomer();
    }
}
