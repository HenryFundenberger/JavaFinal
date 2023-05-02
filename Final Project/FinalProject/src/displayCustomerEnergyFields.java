import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class displayCustomerEnergyFields extends JPanel {
    // Text Fields for
    /*
    Last Month Meter
    This Month Meter
    Cents per KWH
    Energy Tariff
    Account#
    Last Payment
    Current Bill
     */
    /*
    as well as drop down menu for meter type
    that has these three values
     meterTypeInput.addItem("Residential");
        meterTypeInput.addItem("Commercial");
        meterTypeInput.addItem("Industrial");
     */

    private JLabel lastMonthMeterLabel;
    private JTextField lastMonthMeterInput;
    private JLabel thisMonthMeterLabel;
    private JTextField thisMonthMeterInput;
    private JLabel centsPerKWHLabel;
    private JTextField centsPerKWHInput;
    private JLabel energyTarrifLabel;
    private JTextField energyTarrifInput;
    private JLabel accountNumberLabel;
    private JTextField accountNumberInput;
    private JLabel lastPaymentLabel;
    private JTextField lastPaymentInput;
    private JLabel currentBillLabel;
    private JTextField currentBillInput;
    private JLabel meterTypeLabel;
    private JTextField meterTypeInput;



    /*Organize it liuke the following
    Title
    Last Month Meter This Month Meter
    Cents per KWH Energy Tariff
    Meter Type Account#
    Last Payment Current Bill
    goHome
     */

    // Esentially just a 4x2 grid with the title on top and goHome on the bottom

    public displayCustomerEnergyFields(){

        lastMonthMeterInput = new JTextField(20);
        thisMonthMeterInput = new JTextField(20);
        centsPerKWHInput = new JTextField(20);
        energyTarrifInput = new JTextField(20);
        accountNumberInput = new JTextField(20);
        lastPaymentInput = new JTextField(20);
        currentBillInput = new JTextField(20);
        meterTypeInput = new JTextField(20);



        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        // No Space vertically

        c.weightx = 0;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;

        lastMonthMeterLabel = new JLabel("Last Month Meter: ");
        add(lastMonthMeterLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        // Make input non editable
        lastMonthMeterInput.setEditable(false);
        add(lastMonthMeterInput, c);


        c.gridx = 2;
        c.gridy = 0;
        thisMonthMeterLabel = new JLabel("This Month Meter: ");
        add(thisMonthMeterLabel, c);

        c.gridx = 3;
        c.gridy = 0;
        thisMonthMeterInput.setEditable(false);
        add(thisMonthMeterInput, c);

        c.gridx = 0;
        c.gridy = 1;
        centsPerKWHLabel = new JLabel("Cents per KWH: ");
        add(centsPerKWHLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        centsPerKWHInput.setEditable(false);
        add(centsPerKWHInput, c);

        c.gridx = 2;
        c.gridy = 1;
        energyTarrifLabel = new JLabel("Energy Tariff: ");
        add(energyTarrifLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        energyTarrifInput.setEditable(false);
        add(energyTarrifInput, c);

        c.gridx = 0;
        c.gridy = 2;
        meterTypeLabel = new JLabel("Meter Type: ");

        add(meterTypeLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        meterTypeInput.setEditable(false);
        add(meterTypeInput, c);

        c.gridx = 2;
        c.gridy = 2;
        accountNumberLabel = new JLabel("Account #: ");
        add(accountNumberLabel, c);

        c.gridx = 3;
        c.gridy = 2;
        accountNumberInput.setEditable(false);
        add(accountNumberInput, c);

        c.gridx = 0;
        c.gridy = 3;
        lastPaymentLabel = new JLabel("Last Payment: ");
        add(lastPaymentLabel, c);

        c.gridx = 1;
        c.gridy = 3;
        lastPaymentInput.setEditable(false);
        add(lastPaymentInput, c);

        c.gridx = 2;
        c.gridy = 3;
        currentBillLabel = new JLabel("Current Bill: ");

        add(currentBillLabel, c);

        c.gridx = 3;
        c.gridy = 3;
        currentBillInput.setEditable(false);
        add(currentBillInput, c);



    }

    // Set Fields Setters
    public void setLastMonthMeterInput(String lastMonthMeterInput) {
        this.lastMonthMeterInput.setText(lastMonthMeterInput);
    }

    public void setThisMonthMeterInput(String thisMonthMeterInput) {
        this.thisMonthMeterInput.setText(thisMonthMeterInput);
    }

    public void setCentsPerKWHInput(String centsPerKWHInput) {
        this.centsPerKWHInput.setText(centsPerKWHInput);
    }

    public void setEnergyTarrifInput(String energyTarrifInput) {
        this.energyTarrifInput.setText(energyTarrifInput);
    }

    public void setAccountNumberInput(String accountNumberInput) {
        this.accountNumberInput.setText(accountNumberInput);
    }



    public void setCurrentBillInput(String currentBillInput) {
        this.currentBillInput.setText(currentBillInput);
    }

    public void setMeterTypeInput(String meterTypeInput) {
        this.meterTypeInput.setText(meterTypeInput);
    }


    public void setLastPaymentInput(String lastPaymentInput) {
        this.lastPaymentInput.setText(lastPaymentInput);
    }

}
