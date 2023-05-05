import javax.swing.*;
import java.sql.*;


import java.awt.*;
import java.awt.event.*;

public class viewPaymentStatus extends JFrame{
    private titleLabel title;
    private viewPaymentStatusFeilds feilds;
    private JButton goBack;
    private JPanel buttonPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;
    private JMenuItem managePayments;
    private JMenuItem findCustomer;
    private Customer customer;

    public viewPaymentStatus(Customer newCust){
        customer = newCust;
        setTitle("KC Electric - View Payment Status");
        setSize(375, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
        setFields();
    }

    private void buildPanel(){
        // adds title label to top of page
        title = new titleLabel("View Payment Status");
        add(title, BorderLayout.NORTH);

        // Add navigation menu
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");

        // Nav components
        logout = new JMenuItem("Logout");
        logout.addActionListener(new logoutListener());
        home = new JMenuItem("Home");
        home.addActionListener(new homeListener());
        managePayments = new JMenuItem("Manage Payments");
        managePayments.addActionListener(new managePaymentsListener());
        findCustomer = new JMenuItem("Find Customer");
        findCustomer.addActionListener(new findCustomerListener());
        menu.add(logout);
        menu.add(home);
        menu.add(managePayments);
        menu.add(findCustomer);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        feilds = new viewPaymentStatusFeilds();
        add(feilds, BorderLayout.CENTER);

        // Add the button panel to the south of the frame
        // Have the buttons line up horizontally
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        goBack = new JButton("Go Back");
        goBack.addActionListener(new goBackListener());
        // small amount of verticle space between buttons
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(goBack, c);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Auto populates fields with customer information from the databse
    private void setFields(){

        double currentBill;
        String currBill = null;
        String status = null;
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
                status = result.getString("payed");

                currBill = Double.toString(currentBill);
                
                }
            feilds.setLastMonthAmount(currBill);
            feilds.setStatus(status);
            
            

            conn.close();
        }
        
        catch(SQLException e){
            System.out.println(e.getMessage());
        }




    }

    private class logoutListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new loginMenu();
            dispose();
        }
    }

    private class homeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new mainDashboard();
            dispose();
        }
    }

    private class managePaymentsListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new managePaymentsDash();
            dispose();
        }
    }

    private class findCustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new FindCustomer("viewPaymentStatus");
            dispose();
        }
    }

    private class goBackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new managePaymentsDash();
            dispose();
        }
    }


}
