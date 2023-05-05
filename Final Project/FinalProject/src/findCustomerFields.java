import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class findCustomerFields extends JPanel {
    // We need a label for name
    // We need a text field for name

    private JLabel nameLabel;
    private JTextField nameInput;

    public findCustomerFields(){
        // We need to set the layout to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        // No Space vertically

        c.weightx = 0;
        c.weighty = 0.2;
        // Row 1
        // Column 1
        c.gridx = 0;
        c.gridy = 0;

        nameLabel = new JLabel("Name: ");
        add(nameLabel, c);

        // Column 2
        c.gridx = 1;
        c.gridy = 0;
        nameInput = new JTextField(20);
        add(nameInput, c);
    }

    public String getName(){
        // remove trailing and leading spaces
        nameInput.setText(nameInput.getText().trim());
        return nameInput.getText();
    }

    public void setName(String name){
        nameInput.setText(name);
    }


}
