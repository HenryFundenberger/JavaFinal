import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;


public class displayCustomerEnergy extends JFrame {
    titleLabel title;

    // Menu bar
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem mainDashboard;
    private JMenuItem search;
    private displayCustomerEnergyFields displayCustomerEnergyFields;
    private JButton home;
    private JPanel buttonPanel;

    private Customer customer;
    public displayCustomerEnergy(Customer passedCustomer) {
        customer = passedCustomer;
        setTitle("KC Electric - Display Customer Energy Usage");
        setSize(675, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buildPanel();

        setVisible(true);
        setFields();
    }

    private void buildPanel() {
        title = new titleLabel(customer.getName() + " Energy Usage");
        add(title, BorderLayout.NORTH);

        // Menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");
        logout = new JMenuItem("Logout");
        logout.addActionListener(new logoutListener());
        menu.add(logout);
        mainDashboard = new JMenuItem("Main Dashboard");
        mainDashboard.addActionListener(new mainDashboardListener());
        menu.add(mainDashboard);
        search = new JMenuItem("Search");
        search.addActionListener(new searchListener());
        menu.add(search);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        displayCustomerEnergyFields = new displayCustomerEnergyFields();
        add(displayCustomerEnergyFields, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, 0, 10);

        home = new JButton("Home");
        home.addActionListener(new homeListener());
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(home, c);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void setFields () {
        databaseInfo db = new databaseInfo();
        try {
            // Create a connection to the database.
            Connection connection = DriverManager.getConnection(db.getDatabaseURL(), db.getUsername(), db.getPassword());
            // Create a Statement object.
            Statement statement = connection.createStatement();
            // Create the string for the SQL statement.
            String sqlStatement = "SELECT * FROM electricaccount where id = " + customer.getKCElectricID();
            // Send the statement to the DBMS.
            ResultSet result = statement.executeQuery(sqlStatement);
            // Process the result set.
            while (result.next()) {
                displayCustomerEnergyFields.setLastMonthMeterInput(result.getString("lastMonthMeter"));
                displayCustomerEnergyFields.setThisMonthMeterInput(result.getString("currentMeter"));
                displayCustomerEnergyFields.setCentsPerKWHInput(result.getString("kwhPrice"));
                displayCustomerEnergyFields.setEnergyTarrifInput(result.getString("energyTarrif"));
                displayCustomerEnergyFields.setMeterTypeInput(result.getString("meterType"));
                displayCustomerEnergyFields.setAccountNumberInput(result.getString("id"));
                displayCustomerEnergyFields.setLastPaymentInput(result.getString("lastPayment"));
                // Set the current bill (last month meter - this month meter) * cents per kwh
                // Then calculate the energy tarrif and add it to the current bill
                // Then calculate the meter type and add it to the current bill
                double currentBill = (Double.parseDouble(result.getString("currentMeter")) - Double.parseDouble(result.getString("lastMonthMeter"))) * Double.parseDouble(result.getString("kwhPrice"));
                currentBill += (currentBill * Double.parseDouble(result.getString("energyTarrif")));
                if (result.getString("meterType").equals("Residential")) {
                    currentBill += 10;
                    if (currentBill == 10){
                        currentBill -= 10;
                    }
                } else if (result.getString("meterType").equals("Commercial")) {
                    currentBill += 20;
                    if (currentBill == 20){
                        currentBill -= 20;
                    }
                } else if (result.getString("meterType").equals("Industrial")) {
                    currentBill += 30;
                    if (currentBill == 30){
                        currentBill -= 30;
                    }
                }
                displayCustomerEnergyFields.setCurrentBillInput(Double.toString(currentBill));
            }
            // Close the connection.
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

    }


    private class homeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new mainDashboard();
        }
    }

    private class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new loginMenu();
        }
    }

    private class mainDashboardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new mainDashboard();
        }
    }

    private class searchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            new displayCustomerEnergySearch();
        }
    }



}
