import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class singleCustomerInvoiceFields extends JPanel {
    // Gonna display all of this information in one section of the panel
    /*
    name
    address
    amount per KwH
    Meter Type
    Meter Tax
    Prev Month Reading
    Current Month Reading
     */

    // Labels
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel amountPerKwHLabel;
    private JLabel meterTypeLabel;
    private JLabel meterTaxLabel;
    private JLabel prevMonthReadingLabel;
    private JLabel currentMonthReadingLabel;

    // Text Fields
    private JTextField nameInput;
    private JTextField addressInput;
    private JTextField amountPerKwHInput;
    private JTextField meterTypeInput;
    private JTextField meterTaxInput;
    private JTextField prevMonthReadingInput;
    private JTextField currentMonthReadingInput;

    // Calculation Section
    /*Show the following calcualations
    (This Month's Reading - Last Month's Reading) * Amount per KwH = Subtotal
    Subtotal + (Subtotal * Meter Tax) = taxSubtotal
    taxSubtotal + (taxSubtotal * MeterTypeNumber) = total


     */

    // Labels
    private JLabel subtotalLabel;
    private JLabel taxSubtotalLabel;
    private JLabel totalLabel;

    // Text Fields
    private JTextField subtotalInput;
    private JTextField taxSubtotalInput;
    private JTextField totalInput;

    // Using Grid Bag Layout

    public singleCustomerInvoiceFields() {
        // Set up the text fields
        nameInput = new JTextField(10);
        addressInput = new JTextField(10);
        amountPerKwHInput = new JTextField(10);
        meterTypeInput = new JTextField(10);
        meterTaxInput = new JTextField(10);
        prevMonthReadingInput = new JTextField(10);
        currentMonthReadingInput = new JTextField(10);

        // Set up the labels
        nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(JLabel.RIGHT);
        addressLabel = new JLabel("Address: ");
        addressLabel.setHorizontalAlignment(JLabel.RIGHT);
        amountPerKwHLabel = new JLabel("Amount per KwH: ");
        amountPerKwHLabel.setHorizontalAlignment(JLabel.RIGHT);
        meterTypeLabel = new JLabel("Meter Type: ");
        meterTypeLabel.setHorizontalAlignment(JLabel.RIGHT);
        meterTaxLabel = new JLabel("Meter Tax: ");
        meterTaxLabel.setHorizontalAlignment(JLabel.RIGHT);
        prevMonthReadingLabel = new JLabel("Previous Month Reading: ");
        prevMonthReadingLabel.setHorizontalAlignment(JLabel.RIGHT);
        currentMonthReadingLabel = new JLabel("Current Month Reading: ");
        currentMonthReadingLabel.setHorizontalAlignment(JLabel.RIGHT);

        // Set up the calculation section
        subtotalLabel = new JLabel("Subtotal: ");
        subtotalLabel.setHorizontalAlignment(JLabel.RIGHT);
        taxSubtotalLabel = new JLabel("Tax Subtotal: ");
        taxSubtotalLabel.setHorizontalAlignment(JLabel.RIGHT);
        totalLabel = new JLabel("Total: ");
        totalLabel.setHorizontalAlignment(JLabel.RIGHT);

        subtotalInput = new JTextField(10);
        taxSubtotalInput = new JTextField(10);
        totalInput = new JTextField(10);

        // Set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        // No Space vertically

        c.weightx = 0;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;

        add(nameLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        add(nameInput, c);

        c.gridx = 0;
        c.gridy = 1;
        add(addressLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        add(addressInput, c);

        c.gridx = 0;
        c.gridy = 2;
        add(amountPerKwHLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        add(amountPerKwHInput, c);

        c.gridx = 0;
        c.gridy = 3;
        add(meterTypeLabel, c);

        c.gridx = 1;
        c.gridy = 3;
        add(meterTypeInput, c);

        c.gridx = 0;
        c.gridy = 4;
        add(meterTaxLabel, c);

        c.gridx = 1;
        c.gridy = 4;
        add(meterTaxInput, c);

        c.gridx = 0;
        c.gridy = 5;
        add(prevMonthReadingLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        add(prevMonthReadingInput, c);

        c.gridx = 0;
        c.gridy = 6;
        add(currentMonthReadingLabel, c);

        c.gridx = 1;
        c.gridy = 6;
        add(currentMonthReadingInput, c);

        // Calculation Section
        c.gridx = 0;
        c.gridy = 7;
        add(subtotalLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        add(subtotalInput, c);

        c.gridx = 0;
        c.gridy = 8;
        add(taxSubtotalLabel, c);

        c.gridx = 1;
        c.gridy = 8;
        add(taxSubtotalInput, c);

        c.gridx = 0;
        c.gridy = 9;
        add(totalLabel, c);

        c.gridx = 1;
        c.gridy = 9;
        add(totalInput, c);


    }


}
