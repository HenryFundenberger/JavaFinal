import javax.management.Query;
import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import java.sql.*;


public class updateApplyCustomer extends JFrame {
    private CustomerDataInput updateCustomerInfo;
    private titleLabel title;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;
    private JMenuItem customerDashboard;
    private JButton submit;
    private JButton cancel;
    private JPanel buttonPanel;
    private Customer currCustomer;




    public updateApplyCustomer(Customer customer) {
        setTitle("KC Electric - Update Customer");
        currCustomer = customer;
        setSize(400, 325);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
        autoFill();
    }

    private void buildPanel() {
        title = new titleLabel("Update Customer");
        add(title, BorderLayout.NORTH);
        updateCustomerInfo = new CustomerDataInput();
        updateCustomerInfo.setName(currCustomer.getName());
        updateCustomerInfo.setNameEditable(false);

        add(updateCustomerInfo, BorderLayout.CENTER);

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
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        submit = new JButton("Submit");
        submit.addActionListener(new submitListener());
        buttonPanel.add(submit, c);
        c.gridx = 1;
        cancel = new JButton("Cancel");
        cancel.addActionListener(new cancelListener());
        buttonPanel.add(cancel, c);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void autoFill(){
        updateCustomerInfo.setName(currCustomer.getName());
        updateCustomerInfo.setAddress(currCustomer.getAddress());
        updateCustomerInfo.setCity(currCustomer.getCity());
        updateCustomerInfo.setState(currCustomer.getState());
        updateCustomerInfo.setZipCode(currCustomer.getZipCode());
        updateCustomerInfo.setPhoneNumber(currCustomer.getPhoneNumber());
        updateCustomerInfo.setEmail(currCustomer.getEmail());

        String meterType;
        String energyTarrif;
        databaseInfo db = new databaseInfo();
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(db.getDatabaseURL(), db.getUsername(), db.getPassword());

            // Create a statement object.
            Statement statement = connection.createStatement();

            // Create a string with a SELECT statement.
            String sqlStatement = "SELECT meterType, energyTarrif FROM electricaccount WHERE id = " + currCustomer.getKCElectricID();

            // Send the statement to the DBMS.
            ResultSet result = statement.executeQuery(sqlStatement);

            if (result.next()) {
                meterType = result.getString("meterType");
                energyTarrif = result.getString("energyTarrif");
                updateCustomerInfo.setMeterType(meterType);
                updateCustomerInfo.setEnergyTarrif(energyTarrif);
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Customer does not exist");
                return;
            }


        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            return;
        }

    }

    private class submitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the data from the fields
            String name;
            String address;
            String city;
            String state;
            String phone;
            String zip;
            String energyTarrif;
            String meterType;
            databaseInfo db = new databaseInfo();
            int id;

            try {
                name = updateCustomerInfo.getName();
                address = updateCustomerInfo.getAddress();
                city = updateCustomerInfo.getCity();
                state = updateCustomerInfo.getState();
                phone = updateCustomerInfo.getPhoneNumber();
                zip = updateCustomerInfo.getZipCode();
                energyTarrif = updateCustomerInfo.getEnergyTarrif();
                meterType = updateCustomerInfo.getMeterType();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                return;
            }

            // Update the database
            try {
                Connection conn = DriverManager.getConnection(db.getDatabaseURL(), db.getUsername(), db.getPassword());
                // First of all check to see if there is a user with the same name in the database
                String query = "SELECT * FROM customer WHERE name = '" + name + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    id = rs.getInt("id");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error: Customer does not exist");
                    return;
                }

                // Update customer with id = id and update electricaccount with id = id
                // Customer has fields: name, phone, address, city, state, zip, id
                // ElectricAccount has fields: name, id, energyTarrif, meterType
                query = "UPDATE customer SET name = '" + name + "', phonenumber = '" + phone + "', address = '" + address + "', city = '" + city + "', state = '" + state + "', zip = '" + zip + "' WHERE id = " + id;
                stmt.executeUpdate(query);
                query = "UPDATE electricaccount SET name = '" + name + "', energyTarrif = '" + energyTarrif + "', meterType = '" + meterType + "' WHERE id = " + id;
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer updated successfully");

                new customerDashboard();
                dispose();


            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                return;
            }
        }
    }

    private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new FindCustomer("Update");
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


}
