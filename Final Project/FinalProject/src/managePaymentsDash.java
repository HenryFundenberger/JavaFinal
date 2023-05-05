import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class managePaymentsDash extends JFrame {

    // Two buttons one for view Payment Status and one for Make a Payment
    private JButton viewPaymentStatus;
    private JButton makeAPayment;

    // Panel for the buttons
    private JPanel buttonPanel;

    // Add navigation menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;

    // GO Home button
    private JButton goHome;
    private JPanel returnPanel;

    private titleLabel title;

    public managePaymentsDash() {
        setTitle("KC Electric - Manage Payments");
        setSize(375, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
    }

    private void buildPanel() {
        // adds title to top of panel
        title = new titleLabel("Manage Payments");
        add(title, BorderLayout.NORTH);

        // Add navigation menu
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");
        // Add navigaion components
        logout = new JMenuItem("Logout");
        logout.addActionListener(new logoutListener());
        home = new JMenuItem("Home");
        home.addActionListener(new homeListener());
        menu.add(logout);
        menu.add(home);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Add the button panel to the south of the frame
        // Have the buttons line up horizontally
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        viewPaymentStatus = new JButton("View Payment Status");
        viewPaymentStatus.addActionListener(new viewPaymentStatusListener());
        // small amount of verticle space between buttons
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(viewPaymentStatus, c);

        makeAPayment = new JButton("Make a Payment");
        makeAPayment.addActionListener(new makeAPaymentListener());
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(makeAPayment, c);

        // Find largest button and set the size of the other button to match
        Dimension largestButton = viewPaymentStatus.getPreferredSize();
        if (largestButton.width < makeAPayment.getPreferredSize().width) {
            largestButton = makeAPayment.getPreferredSize();
        }
        viewPaymentStatus.setPreferredSize(largestButton);
        makeAPayment.setPreferredSize(largestButton);


        add(buttonPanel, BorderLayout.CENTER);

        // Add the return panel to the south of the frame
        // Have the button line up horizontally
        returnPanel = new JPanel();
        returnPanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();

        goHome = new JButton("Go Home");
        goHome.addActionListener(new goHomeListener());
        c2.insets = new Insets(0, 0, 10, 0);
        c2.gridx = 0;
        c2.gridy = 0;
        returnPanel.add(goHome, c2);

        add(returnPanel, BorderLayout.SOUTH);
    }

    private class viewPaymentStatusListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Open the viewPaymentStatus window
            new FindCustomer("viewPaymentStatus");
            dispose();
        }
    }

    private class makeAPaymentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Open the makeAPayment window
            new FindCustomer("makePayment");
            dispose();
        }
    }

    private class goHomeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Open the home window
            new mainDashboard();
            dispose();
        }
    }

    private class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Open the login window
            new loginMenu();
            dispose();
        }
    }

    private class homeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Open the home window
            new mainDashboard();
            dispose();
        }
    }


}
