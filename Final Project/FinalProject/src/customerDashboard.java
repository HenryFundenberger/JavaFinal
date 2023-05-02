import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class customerDashboard extends JFrame {
    private titleLabel title;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;

    private JButton recordNewCustomer;
    private JButton updateCurrentCustomer;
    private JButton removeCustomer;


    private JPanel buttonPanel;


    public customerDashboard() {
        setTitle("Customer Dashboard");
        setSize(300, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();

        setVisible(true);

    }

    private void buildPanel(){

        title = new titleLabel("Customer Dashboard");
        add(title, BorderLayout.NORTH);

        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");
        logout = new JMenuItem("Logout");
        home = new JMenuItem("Home");
        menu.add(logout);
        menu.add(home);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        logout.addActionListener(new logoutListener());
        home.addActionListener(new homeListener());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        // No Space vertically

        c.weightx = 0;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;

        recordNewCustomer = new JButton("Record New Customer");
        recordNewCustomer.addActionListener(new recordNewCustomerListener());

        buttonPanel.add(recordNewCustomer, c);
        c.gridy = 1;
        updateCurrentCustomer = new JButton("Update Current Customer");
        updateCurrentCustomer.addActionListener(new updateCurrentCustomerListener());
        buttonPanel.add(updateCurrentCustomer, c);
        c.gridy = 2;
        removeCustomer = new JButton("Remove Customer");
        removeCustomer.addActionListener(new removeCustomerListener());
        buttonPanel.add(removeCustomer, c);
        c.gridy = 3;

        add(buttonPanel, BorderLayout.CENTER);

        int maxWidth = 0;

        for (Component comp : buttonPanel.getComponents()) {
            maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
        }

        for (Component comp : buttonPanel.getComponents()) {
            Dimension d = comp.getPreferredSize();
            d.width = maxWidth;
            comp.setPreferredSize(d);
        }




    }

    // Logout listener
    private class logoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new loginMenu();
            dispose();
        }
    }



    // Home listener
    private class homeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new mainDashboard();
            dispose();
        }
    }

    // Action listner for record new customer button
    private class recordNewCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new newCustomer();
            dispose();
        }
    }

    private class updateCurrentCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new FindCustomer("Update");
            dispose();
        }
    }

    private class removeCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new removeCustomer();
            dispose();
        }
    }
    public static void main(String[] args) {
        new customerDashboard();
    }
}
