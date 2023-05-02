import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class payCurrentBill extends JFrame {
    // Label for "Current Balance Due"
    private JLabel currentBalanceDueLabel;
    // Text Field for Current Balance Due
    private JTextField currentBalanceDueInput;

    // Two Buttons one for Pay Now and one for Cancel
    private JButton payNow;
    private JButton cancel;
    // Panel for the buttons
    private JPanel buttonPanel;


    // Add navigation menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;
    private JMenuItem findCustomer;
    private JMenuItem managePayments;
    private Customer customer;

    public payCurrentBill(Customer newCustomer) {
        setTitle("KC Electric - Pay Current Bill");
        customer = newCustomer;
        setSize(375, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
        setFields();
    }

    private void buildPanel() {
        // Add navigation menu
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");

        logout = new JMenuItem("Logout");
        logout.addActionListener(new logoutListener());
        home = new JMenuItem("Home");
        home.addActionListener(new homeListener());
        findCustomer = new JMenuItem("Find Customer");
        findCustomer.addActionListener(new findCustomerListener());
        managePayments = new JMenuItem("Manage Payments");
        managePayments.addActionListener(new managePaymentsListener());
        menu.add(logout);
        menu.add(home);
        menu.add(findCustomer);
        menu.add(managePayments);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Add the button panel to the south of the frame
        // Have the buttons line up horizontally
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        payNow = new JButton("Pay Now");
        payNow.addActionListener(new payNowListener());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new cancelListener());

        // Add the buttons to the button panel
        c.gridx = 0;
        c.gridy = 0;
        // no horizontal expansion
        c.weightx = 0;
        // no vertical expansion
        c.weighty = 0;
        buttonPanel.add(payNow, c);
        c.gridx = 1;
        c.gridy = 0;
        buttonPanel.add(cancel, c);

        // Add the button panel to the south of the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Add current balance due label and text field (both in the center lined up horizontally)
        currentBalanceDueLabel = new JLabel("Current Balance Due: ");
        currentBalanceDueInput = new JTextField(10);
        currentBalanceDueInput.setEditable(false);
        JPanel currentBalanceDuePanel = new JPanel();
        currentBalanceDuePanel.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        currentBalanceDuePanel.add(currentBalanceDueLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        currentBalanceDuePanel.add(currentBalanceDueInput, c);
        add(currentBalanceDuePanel, BorderLayout.CENTER);

    }


    private void setFields(){

        double currentBill;
        String currBill = null;
        databaseInfo db = new databaseInfo();
        Connection conn = db.connect();

        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM electricaccount WHERE id = " + customer.getKCElectricID();
            ResultSet result = stmt.executeQuery(sql);
            // Get the meter type, kwh price, energy tarrif, last month meter, current meter, and status
            while(result.next()){
                currentBill = (Double.parseDouble(result.getString("currentMeter")) - Double.parseDouble(result.getString("lastMonthMeter"))) * Double.parseDouble(result.getString("kwhPrice"));
                currentBill += (currentBill * Double.parseDouble(result.getString("energyTarrif")));
                // If current bill is 0, then the customer has not used any energy so skip this next part
                if (currentBill != 0) {
                    // If the meter type is residential, add 10 to the bill
                    if (result.getString("meterType").equals("Residential")) {
                        currentBill += 10;
                    } else if (result.getString("meterType").equals("Commercial")) {
                        currentBill += 20;
                    } else if (result.getString("meterType").equals("Industrial")) {
                        currentBill += 30;
                    }
                }

                currBill = Double.toString(currentBill);

            }
            // Set the value of the current balance due text field to the current bill
            currentBalanceDueInput.setText(currBill);

            conn.close();
        }

        catch(SQLException e){
            System.out.println(e.getMessage());
        }




    }

    private class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Logout of the system
            // Return to the login screen
            new loginMenu();
            dispose();
        }
    }

    private class homeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Return to the home screen
            new mainDashboard();
            dispose();
        }
    }

    private class findCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Return to the find customer screen

            dispose();
        }
    }

    private class managePaymentsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Return to the manage payments screen
            new managePaymentsDash();
            dispose();
        }
    }

    private class payNowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            databaseInfo db = new databaseInfo();
            Connection conn = db.connect();

            // Connect to the electricaccount table and update the last month meter to the current meter
            // And update status of payed to 1
            try{
                Statement stmt = conn.createStatement();
                String sql = "UPDATE electricaccount SET lastMonthMeter = currentMeter, payed = 1 WHERE id = " + customer.getKCElectricID();
                stmt.executeUpdate(sql);
                conn.close();

                // Show a message dialog that the payment was successful
                JOptionPane.showMessageDialog(null, "Payment Successful");
                // Return to the manage payments screen
                new managePaymentsDash();
                dispose();
            }

            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }

        }
    }

    private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Return to the manage payments screen
            new managePaymentsDash();
            dispose();
        }
    }


}
