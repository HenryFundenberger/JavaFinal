import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainDashboard extends JFrame {
    // Need a title "KC Electric Dashboard"
    // Need a menu bar with the following options:
    // - Logout

    private titleLabel title;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;

    // Need a button for each of the following:
    // Manage Customer Accounts
    // Display Customer Energy Usage
    // Create Customer Invoices
    // Manage Payments
    private JButton manageCustomerAccounts;
    private JButton displayCustomerEnergyUsage;
    private JButton createCustomerInvoices;
    private JButton managePayments;

    // Panel for the buttons
    private JPanel buttonPanel;


    public mainDashboard() {
        setTitle("KC Electric Dashboard");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buildPanel();

        setVisible(true);
    }

    private void buildPanel(){
        title = new titleLabel("KC Electric Dashboard");
        add(title, BorderLayout.NORTH);

        // Menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");
        logout = new JMenuItem("Logout");
        logout.addActionListener(new logoutListener());
        menu.add(logout);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Add buttons to center panel and stack tehm vertically, and make them the same size
        manageCustomerAccounts = new JButton("Manage Customer Accounts");
        manageCustomerAccounts.addActionListener(new manageCustomerAccountsListener());
        displayCustomerEnergyUsage = new JButton("Display Customer Energy Usage");
        displayCustomerEnergyUsage.addActionListener(new displayCustomerEnergyUsageListener());
        createCustomerInvoices = new JButton("Create Customer Invoices");
        createCustomerInvoices.addActionListener(new createCustomerInvoicesListener());
        managePayments = new JButton("Manage Payments");
        managePayments.addActionListener(new managePaymentsListener());

        buttonPanel = new JPanel();
        // use gridbaglayout
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Add buttons to panel
        // With a little space between them vertically
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 0);
        buttonPanel.add(manageCustomerAccounts, c);
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(displayCustomerEnergyUsage, c);
        c.gridx = 0;
        c.gridy = 2;
        buttonPanel.add(createCustomerInvoices, c);
        c.gridx = 0;
        c.gridy = 3;
        buttonPanel.add(managePayments, c);

        // Calculate the max width of the buttons

        int maxWidth = 0;

        for (Component component : buttonPanel.getComponents()) {
            maxWidth = Math.max(component.getPreferredSize().width, maxWidth);
        }

        // Set the max width of the buttons

        for (Component component : buttonPanel.getComponents()) {
            Dimension d = component.getPreferredSize();
            d.width = maxWidth;
            component.setPreferredSize(d);
        }


        add(buttonPanel, BorderLayout.CENTER);


    }

    // Logout listener
    private class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new loginMenu();
            dispose();
        }
    }

    private class manageCustomerAccountsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new customerDashboard();
            dispose();
        }
    }

    private class displayCustomerEnergyUsageListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new FindCustomer("energyUsage");
            dispose();
        }
    }

    private class createCustomerInvoicesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new FindCustomer("invoice");
            dispose();
        }
    }

    private class managePaymentsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new managePaymentsDash();
            dispose();
        }
    }


    public static void main(String[] args) {
        new mainDashboard();

    }
}
