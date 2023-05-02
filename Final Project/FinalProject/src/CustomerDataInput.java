import javax.swing.*;
import java.awt.*;

public class CustomerDataInput extends JPanel {
    // Need Input Fields for Name, Phone Number, Address, City, State, Zip Code, Energy Tarrif, and Meter Type
    // Where meter type is a drop down menu with the following options:
    // - Residential
    // - Commercial
    // - Industrial

    private JLabel nameLabel;
    private JTextField nameInput;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberInput;
    private JLabel addressLabel;
    private JTextField addressInput;
    private JLabel cityLabel;
    private JTextField cityInput;
    private JLabel stateLabel;
    private JTextField stateInput;
    private JLabel zipCodeLabel;
    private JTextField zipCodeInput;
    private JLabel energyTarrifLabel;
    private JTextField energyTarrifInput;
    private JLabel meterTypeLabel;
    private JComboBox meterTypeInput;
    private JLabel emailLabel;
    private JTextField emailInput;


    public CustomerDataInput(){

        nameInput = new JTextField(20);
        phoneNumberInput = new JTextField(20);
        emailInput = new JTextField(20);
        addressInput = new JTextField(20);
        cityInput = new JTextField(20);
        stateInput = new JTextField(20);
        zipCodeInput = new JTextField(20);
        energyTarrifInput = new JTextField(20);
        meterTypeInput = new JComboBox();
        meterTypeInput.addItem("Residential");
        meterTypeInput.addItem("Commercial");
        meterTypeInput.addItem("Industrial");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        // No Space vertically
        c.weightx = 0;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;

        nameLabel = new JLabel("Name: ");
        add(nameLabel, c);
        c.gridx = 1;
        nameInput = new JTextField(20);
        add(nameInput, c);

        c.gridx = 0;
        c.gridy = 1;
        phoneNumberLabel = new JLabel("Phone Number: ");
        add(phoneNumberLabel, c);
        c.gridx = 1;
        phoneNumberInput = new JTextField(20);
        add(phoneNumberInput, c);

        c.gridx = 0;
        c.gridy = 2;
        emailLabel = new JLabel("Email: ");
        add(emailLabel, c);
        c.gridx = 1;
        emailInput = new JTextField(20);
        add(emailInput, c);

        c.gridx = 0;
        c.gridy = 3;
        addressLabel = new JLabel("Address: ");
        add(addressLabel, c);
        c.gridx = 1;
        addressInput = new JTextField(20);
        add(addressInput, c);

        c.gridx = 0;
        c.gridy = 4;
        cityLabel = new JLabel("City: ");
        add(cityLabel, c);
        c.gridx = 1;
        cityInput = new JTextField(20);
        add(cityInput, c);

        c.gridx = 0;
        c.gridy = 5;
        stateLabel = new JLabel("State: ");
        add(stateLabel, c);
        c.gridx = 1;
        stateInput = new JTextField(20);
        add(stateInput, c);

        c.gridx = 0;
        c.gridy = 6;
        zipCodeLabel = new JLabel("Zip Code: ");
        add(zipCodeLabel, c);
        c.gridx = 1;
        zipCodeInput = new JTextField(20);
        add(zipCodeInput, c);

        c.gridx = 0;
        c.gridy = 7;
        energyTarrifLabel = new JLabel("Energy Tarrif: ");
        add(energyTarrifLabel, c);
        c.gridx = 1;
        energyTarrifInput = new JTextField(20);
        // set placeholder text to be "Please enter a percentage"
        energyTarrifInput.setText("Please enter a percentage");
        add(energyTarrifInput, c);

        c.gridx = 0;
        c.gridy = 8;
        meterTypeLabel = new JLabel("Meter Type: ");
        add(meterTypeLabel, c);
        c.gridx = 1;
        meterTypeInput = new JComboBox();

        // Move the combo box to the left
        c.anchor = GridBagConstraints.WEST;


        meterTypeInput.addItem("Residential");
        meterTypeInput.addItem("Commercial");
        meterTypeInput.addItem("Industrial");

        add(meterTypeInput, c);
    }

    public String getName(){
        return nameInput.getText();
    }
    public String getPhoneNumber(){
        return phoneNumberInput.getText();
    }
    public String getEmail(){
        return emailInput.getText();
    }
    public String getAddress(){
        return addressInput.getText();
    }
    public String getCity(){
        return cityInput.getText();
    }
    public String getState(){
        return stateInput.getText();
    }
    public String getZipCode(){
        return zipCodeInput.getText();
    }
    public String getEnergyTarrif(){
        return energyTarrifInput.getText();
    }
    public String getMeterType(){
        return meterTypeInput.getSelectedItem().toString();
    }

    // setters
    public void setName(String name){
        nameInput.setText(name);
    }
    public void setPhoneNumber(String phoneNumber){
        phoneNumberInput.setText(phoneNumber);
    }
    public void setEmail(String email){
        emailInput.setText(email);
    }
    public void setAddress(String address){
        addressInput.setText(address);
    }
    public void setCity(String city){
        cityInput.setText(city);
    }
    public void setState(String state){
        stateInput.setText(state);
    }
    public void setZipCode(String zipCode){
        zipCodeInput.setText(zipCode);
    }
    public void setEnergyTarrif(String energyTarrif){
        energyTarrifInput.setText(energyTarrif);
    }
    public void setMeterType(String meterType){
        meterTypeInput.setSelectedItem(meterType);
    }


    // Function to make Name field not editable
    public void setNameEditable(boolean editable){
        nameInput.setEditable(editable);
    }


}
