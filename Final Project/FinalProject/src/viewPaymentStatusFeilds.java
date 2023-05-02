import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class viewPaymentStatusFeilds extends JPanel {
    // Labels "Last Month Amount" and "Status"
    private JLabel currentAmountDueLabel;
    private JLabel statusLabel;

    // Text Fields for Last Month Amount
    private JTextField currentAmountDueInput;
    private JTextField currentStatus;

    public viewPaymentStatusFeilds(){
        currentAmountDueInput = new JTextField(10);
        currentStatus = new JTextField(10);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        // No Space vertically

        c.weightx = 0;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;

        currentAmountDueLabel = new JLabel("Latest Bill: ");
        currentAmountDueLabel.setHorizontalAlignment(JLabel.RIGHT);
        add(currentAmountDueLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        // Make input field uneditable
        currentAmountDueInput.setEditable(false);
        add(currentAmountDueInput, c);

        c.gridx = 0;
        c.gridy = 1;
        statusLabel = new JLabel("Status: ");
        statusLabel.setHorizontalAlignment(JLabel.RIGHT);
        add(statusLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        // Make input field uneditable
        currentStatus.setEditable(false);
        add(currentStatus, c);
    }

    public void setLastMonthAmount(String currentAmount){
        currentAmountDueInput.setText(currentAmount);
    }

    public void setStatus(String status){
        // If status is 0 then the customer is not paid
        // If status is 1 then the customer is paid
        if(status.equals("0")){
            currentStatus.setText("Not Paid");
        } else if(status.equals("1")){
            currentStatus.setText("Paid");
        }
    }


}
