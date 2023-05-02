import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class displayCustomerEnergySearch extends JFrame {
    findCustomerFields findCustomer;
    String customerName;
    titleLabel title;

    JButton find;
    JButton cancel;
    JPanel buttonPanel;

    // Menu bar
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem mainDashboard;

    public displayCustomerEnergySearch() {
        setTitle("KC Electric - Display Customer Energy Usage");
        setSize(425, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buildPanel();

        setVisible(true);
    }

    private void buildPanel() {
        title = new titleLabel("Search for Customer");
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
        menuBar.add(menu);
        setJMenuBar(menuBar);

        findCustomer = new findCustomerFields();
        add(findCustomer, BorderLayout.CENTER);

        // Button pannel lines buttons up left to right with a small space using bag layout
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 0, 0, 10);



        cancel = new JButton("Cancel");
        cancel.addActionListener(new cancelListener());
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(cancel, c);

        add(buttonPanel, BorderLayout.SOUTH);
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

    private class mainDashboardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new mainDashboard();
            dispose();
        }
    }

    public static void main(String[] args) {
        new displayCustomerEnergySearch();
    }
}
