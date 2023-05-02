import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class makePayment extends JFrame {

    private Customer customer;
    private JLabel amountDueLabel;
    private JTextField amountDueInput;

    // Add navigation menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;
    private JMenuItem managePayments;
    private JMenuItem findCustomer;


    // Pay or Cancel buttons
    private JButton pay;
    private JButton cancel;



    public makePayment(Customer newCust){
        setTitle("KC Electric - Make Payment");
        customer = newCust;
        setSize(375, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
    }


    private void buildPanel(){
        // In the center of the screen we will have Amount Due: and a text field
        // That will display the current amount due


    }
}
