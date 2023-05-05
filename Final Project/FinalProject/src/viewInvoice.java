import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class viewInvoice extends JFrame {

    private Customer customer;
    private KCElectricAccount account;
    private JLabel accountInfoLabel;
    private JLabel invoiceCalculationLabel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;
    private JLabel accountNumberLabel;

    // Input boxes for the labels
    private JTextField nameInput;
    private JTextField phoneInput;
    private JTextField addressInput;
    private JTextField emailInput;
    private JTextField accountNumberInput;

    // Invoice Calculation Labels
    private JLabel invoiceCalculationTitle;
    private JLabel totalMeterCalculationDescription;
    // Three text entries for the total meter calculation prevMonth, currMonth, and total
    private JTextField prevMonthInput;
    private JTextField currMonthInput;
    private JTextField totalcalculationInput1;
    private JTextField totalcalculationInput2;

    // Total Calculation Label
    private JLabel totalCalculationDescription;
    // Three text entries one for totalMeter, one for kwh, and one for total
    private JTextField totalMeterInput1;
    private JTextField totalMeterInput2;
    private JTextField kwhInput;


    private JTextField energyTariffInput;



    private JTextField subTotalCalculationInput1;
    private JTextField subTotalCalculationInput2;

    // add missing labels and text fields
    private JLabel prevMonthLabel;
    private JLabel currMonthLabel;
    private JLabel totalMeterLabel;

    private JLabel totalLabel;
    private JLabel kwhLabel;

    private JLabel subTotalLabel;
    private JLabel energyTariffLabel;


    private JLabel finalTotalLabel;


    private JTextField finalTotalInput;

    // Add meterTypeFee
    private JLabel meterTypeFeeLabel;
    private JTextField meterTypeFeeInput;

    private JLabel calculationInstructionsLabel;

    // Export to PDF button
    private JButton exportToPDF;

    // Go Home Button
    private JButton goHome;

    // Add navigation menu
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem logout;
    private JMenuItem home;
    private JMenuItem findCustomer;




    public viewInvoice(Customer currCustomer) {
        setTitle("KC Electric - Invoice");
        customer = currCustomer;
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        buildPanel();
        setVisible(true);
        autoFill();
    }

    // Auto fill just sets the text fields on the page with already known information
    // From the customer object and then calls teh DB to fill in the info with the info in the DB for the corrisponding user
    private void autoFill() {
        nameInput.setText(customer.getName());
        phoneInput.setText(customer.getPhoneNumber());
        addressInput.setText(customer.getAddress());
        emailInput.setText(customer.getEmail());
        accountNumberInput.setText(Integer.toString(customer.getKCElectricID()));
        // address is the address, city, state, zip

        addressInput.setText(customer.getAddress() + ", " + customer.getCity() + ", " + customer.getState() + " " + customer.getZipCode());

        databaseInfo db = new databaseInfo();
        Connection conn = db.connect();
        try{
            // Connect to the electricaccount table
            // In the table KCElectricID is just called id
            // Get everything from the table where the id is equal to the customer's KCElectricID
            // The values are name, id, energyTariff, meterType, kwhPrice, lastMonthMeter, currentMeter, payed, lastPayment
            String query = "SELECT * FROM electricaccount WHERE id = " + customer.getKCElectricID();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            // Get the values from the table
            double lastMonthMeter = rs.getDouble("lastMonthMeter");
            double currentMeter = rs.getDouble("currentMeter");
            double energyTariff = rs.getDouble("energyTarrif");
            String  meterType = rs.getString("meterType");
            double kwhPrice = rs.getDouble("kwhPrice");
            int payed = rs.getInt("payed");
            String lastPayment = rs.getString("lastPayment");
            // Calculate the total meter
            double totalMeter = currentMeter - lastMonthMeter;
            // Fill out the text fields
            prevMonthInput.setText(Double.toString(lastMonthMeter));
            currMonthInput.setText(Double.toString(currentMeter));
            totalMeterInput1.setText(Double.toString(totalMeter));

            totalMeterInput2.setText(Double.toString(totalMeter));
            kwhInput.setText(Double.toString(kwhPrice));
            totalcalculationInput1.setText(Double.toString(totalMeter * kwhPrice));
            double total = totalMeter * kwhPrice;

            totalcalculationInput2.setText(Double.toString(total));
            energyTariffInput.setText(Double.toString(energyTariff));
            subTotalCalculationInput1.setText(Double.toString( total + (total * energyTariff)));
            double subTotal = total + (total * energyTariff);

            subTotalCalculationInput2.setText(Double.toString(subTotal));
            // If meterType is Residential then add 10 to the subTotal, if meterType is Commercial then add 20 to the subTotal, if meterType is Industrial then add 30 to the subTotal
            if(meterType.equals("Residential")){

                subTotal += 10;
                if (subTotal == 10){
                    subTotal -= 10;
                    meterTypeFeeInput.setText("0");
                }
                else{
                    meterTypeFeeInput.setText("10");
                }
            }
            else if(meterType.equals("Commercial")){

                subTotal += 20;
                if(subTotal == 20){
                    subTotal -= 20;
                    meterTypeFeeInput.setText("0");
                }
                else{
                    meterTypeFeeInput.setText("20");
                }
            }
            else if(meterType.equals("Industrial")){

                subTotal += 30;
                if (subTotal == 30){
                    subTotal -= 30;
                    meterTypeFeeInput.setText("0");
                }
                else{
                    meterTypeFeeInput.setText("30");
                }
            }

            // Create the KCELectricAccount object
            account = new KCElectricAccount(customer.getName(), customer.getKCElectricID(), energyTariff, meterType, kwhPrice, lastMonthMeter, currentMeter, lastPayment);

            // Update the final total text field
            finalTotalInput.setText(Double.toString(subTotal));



        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    private void buildPanel() {


        // Add the menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Navigation");
        logout = new JMenuItem("Logout");
        home = new JMenuItem("Home");
        findCustomer = new JMenuItem("Find Customer");
        menu.add(home);
        // add action listener for home
        home.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new mainDashboard();
                dispose();
            }
        });
        menu.add(findCustomer);
        // add action listener for find customer
        findCustomer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new FindCustomer("invoice");
                dispose();
            }
        });
        menu.add(logout);
        // add action listener for logout
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new loginMenu();
                dispose();
            }
        });
        menuBar.add(menu);
        setJMenuBar(menuBar);


        // On the first line we will have the account information
        // Using Grid Bag Layout with x,y coordinates
        // In the top left should be Account Information
        // On the next line should be Name: and a text field then Phone: and a text field
        // On the next line should be Email: and a text field then Address: and a text field
        // On the next line should be Account Number: and a text field



        GridBagConstraints c = new GridBagConstraints();
        JPanel accountInfoPanel = new JPanel(new GridBagLayout());
        accountInfoLabel = new JLabel("Account Information");
        c.gridx = 0;
        c.gridy = 0;
        // Center Horizontally
        c.anchor = GridBagConstraints.CENTER;
        /// Create a small grey border in borderfactory

        // Make the border be only on the bottom
        accountInfoLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        c.insets = new Insets(0, 0, 10, 0);
        accountInfoPanel.add(accountInfoLabel, c);


        c.anchor = GridBagConstraints.LINE_START;
        nameLabel = new JLabel("Name:");
        c.gridx = 0;
        c.gridy = 1;
        accountInfoPanel.add(nameLabel, c);
        nameInput = new JTextField(20);
        nameInput.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        // add space between the name and phone labels
        c.insets = new Insets(0, 0, 10, 20);
        accountInfoPanel.add(nameInput, c);
        phoneLabel = new JLabel("Phone:");
        c.gridx = 2;
        c.gridy = 1;
        accountInfoPanel.add(phoneLabel, c);
        phoneInput = new JTextField(20);
        phoneInput.setEditable(false);
        c.gridx = 3;
        c.gridy = 1;
        accountInfoPanel.add(phoneInput, c);

        addressLabel = new JLabel("Address:");
        c.gridx = 0;
        c.gridy = 2;
        accountInfoPanel.add(addressLabel, c);
        addressInput = new JTextField(20);
        addressInput.setEditable(false);
        c.gridx = 1;
        c.gridy = 2;
        accountInfoPanel.add(addressInput, c);
        emailLabel = new JLabel("Email:");
        c.gridx = 2;
        c.gridy = 2;
        accountInfoPanel.add(emailLabel, c);
        emailInput = new JTextField(20);
        emailInput.setEditable(false);
        c.gridx = 3;
        c.gridy = 2;
        accountInfoPanel.add(emailInput, c);

        accountNumberLabel = new JLabel("Account Number:");
        c.gridx = 0;
        c.gridy = 3;
        accountInfoPanel.add(accountNumberLabel, c);
        accountNumberInput = new JTextField(10);
        accountNumberInput.setEditable(false);
        c.gridx = 1;
        c.gridy = 3;
        accountInfoPanel.add(accountNumberInput, c);

        add(accountInfoPanel, BorderLayout.NORTH);


        // Calculation Instructions Panel
        JPanel calculationInstructionsPanel = new JPanel(new GridBagLayout());
        // Going to have labels for the following formulas
        // Previous Month Meter - Current Month Meter = Total Meter
        // Total Meter * KWH Price = Total
        // Total * Energy Tariff = Sub Total
        // Sub Total + Meter Type Fee = Final Total

        // Add the labels for the formulas
        calculationInstructionsLabel = new JLabel("Calculation Instructions");
        c.gridx = 0;
        c.gridy = 0;
        // Center Horizontally
        c.anchor = GridBagConstraints.CENTER;
        /// Create a small grey border in borderfactory

        // Make the border be only on the bottom
        calculationInstructionsLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        c.insets = new Insets(0, 0, 10, 0);
        calculationInstructionsPanel.add(calculationInstructionsLabel, c);

        // Add the formulas
        totalMeterCalculationDescription = new JLabel("Current Month Meter - Previous Month Meter = Total Meter");
        c.gridx = 0;
        c.gridy = 1;
        calculationInstructionsPanel.add(totalMeterCalculationDescription, c);
        totalMeterLabel = new JLabel("Total Meter * KWH Price = Total");
        c.gridx = 0;
        c.gridy = 2;
        calculationInstructionsPanel.add(totalMeterLabel, c);
        totalLabel = new JLabel("Total + (Total * Energy Tariff) = Sub Total");
        c.gridx = 0;
        c.gridy = 3;
        calculationInstructionsPanel.add(totalLabel, c);
        subTotalLabel = new JLabel("Sub Total + Meter Type Fee = Final Total");
        c.gridx = 0;
        c.gridy = 4;
        calculationInstructionsPanel.add(subTotalLabel, c);

        add(calculationInstructionsPanel, BorderLayout.CENTER);





        // Invoice Calculation Panel

        JPanel invoiceCalculationPanel = new JPanel(new GridBagLayout());
        invoiceCalculationLabel = new JLabel("Invoice Calculation");
        c.gridx = 1;
        c.gridy = 0;
// Center Horizontally

/// Create a small grey border in borderfactory

// Make the border be only on the bottom
        invoiceCalculationLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        c.insets = new Insets(0, 0, 10, 0);
        invoiceCalculationPanel.add(invoiceCalculationLabel, c);



// Three text entries, one for prevMonth, one for currMonth, and one for totalMeter
        currMonthLabel = new JLabel("Curr Month:");
        c.gridx = 0;
        c.gridy = 2;
        // align the input to the left
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(currMonthLabel, c);
        currMonthInput = new JTextField(10);
        currMonthInput.setEditable(false);
        // Make there be no space between prevMonth Label and input

        c.gridx = 1;
        c.gridy = 2;
        // Make input align to the left
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(currMonthInput, c);





        prevMonthLabel = new JLabel("Prev Month:");
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(prevMonthLabel, c);
        prevMonthInput = new JTextField(10);
        prevMonthInput.setEditable(false);
        c.gridx = 3;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(prevMonthInput, c);

        totalMeterLabel = new JLabel("Total Meter:");
        c.gridx = 4;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(totalMeterLabel, c);
        totalMeterInput1 = new JTextField(10);
        totalMeterInput1.setEditable(false);
        c.gridx = 5;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(totalMeterInput1, c);



        // Three text entries, one for totalMeter, one for kwh, and one for total
        totalMeterInput2 = new JTextField(10);
        totalMeterInput2.setEditable(false);
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(totalMeterInput2, c);
        totalMeterLabel = new JLabel("TotalMeter:");
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(totalMeterLabel, c);
        kwhInput = new JTextField(10);
        kwhInput.setEditable(false);
        c.gridx = 3;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(kwhInput, c);
        kwhLabel = new JLabel("KWH:");
        c.gridx = 2;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(kwhLabel, c);

        totalcalculationInput1 = new JTextField(10);
        totalcalculationInput1.setEditable(false);
        c.gridx = 5;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;

        invoiceCalculationPanel.add(totalcalculationInput1, c);
        totalLabel = new JLabel("Total:");
        c.gridx = 4;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(totalLabel, c);



        // Three text entries, one for total, one for energyTariff, and one for subTotal
        totalcalculationInput2 = new JTextField(10);
        totalcalculationInput2.setEditable(false);

        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(totalcalculationInput2, c);
        totalLabel = new JLabel("Total:");
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(totalLabel, c);
        energyTariffInput = new JTextField(10);
        energyTariffInput.setEditable(false);
        c.gridx = 3;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(energyTariffInput, c);
        energyTariffLabel = new JLabel("EnergyTariff:");

        c.gridx = 2;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(energyTariffLabel, c);



        subTotalLabel = new JLabel("SubTotal:");
        c.gridx = 4;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(subTotalLabel, c);

        subTotalCalculationInput1 = new JTextField(10);
        subTotalCalculationInput1.setEditable(false);
        c.gridx = 5;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(subTotalCalculationInput1, c);




        // Final Total Calculation Label



        // Three text entries, one for subTotal, one for meterTypeFee, and one for finalTotal
        subTotalCalculationInput2 = new JTextField(10);
        subTotalCalculationInput2.setEditable(false);
        c.gridx = 1;
        c.gridy = 8;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(subTotalCalculationInput2, c);

        subTotalLabel = new JLabel("SubTotal:");
        c.gridx = 0;
        c.gridy = 8;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(subTotalLabel, c);
        meterTypeFeeInput = new JTextField(10);

        meterTypeFeeInput.setEditable(false);
        c.gridx = 3;
        c.gridy = 8;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(meterTypeFeeInput, c);
        meterTypeFeeLabel = new JLabel("MeterTypeFee:");

        c.gridx = 2;
        c.gridy = 8;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(meterTypeFeeLabel, c);
        finalTotalInput = new JTextField(10);
        finalTotalInput.setEditable(false);
        c.gridx = 5;
        c.gridy = 8;
        c.anchor = GridBagConstraints.LINE_START;
        invoiceCalculationPanel.add(finalTotalInput, c);
        finalTotalLabel = new JLabel("FinalTotal:");
        c.gridx = 4;
        c.gridy = 8;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(finalTotalLabel, c);

        // add export to pdf button and go home button
        exportToPDF = new JButton("Export to PDF");
        c.gridx = 5;
        c.gridy = 10;
        c.anchor = GridBagConstraints.LINE_START;
        // Onclick run writePDF method
        exportToPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writePDF();
            }
        });
        invoiceCalculationPanel.add(exportToPDF, c);

        goHome = new JButton("Go Home");
        c.gridx = 0;
        c.gridy = 10;
        c.anchor = GridBagConstraints.LINE_END;
        invoiceCalculationPanel.add(goHome, c);
        // add action listener to go home button
        goHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainDashboard();
                dispose();
            }
        });



        // Add the invoiceCalculationPanel to the mainPanel
        add(invoiceCalculationPanel, BorderLayout.SOUTH);



    }


    // Method to write PDF
    private void writePDF() {
        String folderName = "invoices";
        String fileName = customer.getName() + " Invoice.pdf";
        String filePath = folderName + File.separator + fileName;

        Document document = new Document();

        try {
            // Create the invoices folder if it doesn't exist
            File folder = new File(folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Export the PDF to the invoices folder
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Open the document
            document.open();

            // Create a header
            Paragraph p = new Paragraph("KC Electric Invoice\n\n");
            // Make P bold, using helvetica font, size 18
            p.setFont(FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD));
            // Center the header
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            // Account Summary Section
            // Create a table with 2 columns
            PdfPTable table = new PdfPTable(2);
            // Add a cell with the text "Account Summary"
            PdfPCell cell = new PdfPCell(new Phrase("Account Summary"));
            // Make the other cell Invoice Calculation
            PdfPCell cell2 = new PdfPCell(new Phrase("Invoice Calculation"));
            // Make the cell span 2 columns
            cell.setColspan(1);
            cell2.setColspan(1);
            // Center the text in the cell
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Add Customer information to Account Summary
            // Create a new p inside the cell
            Paragraph p2 = new Paragraph();
            // Make the font size size 12
            p2.setFont(FontFactory.getFont(FontFactory.HELVETICA, 10));
            // Add the customer name to the p
            p2.add("Customer Information\n" +
                    "------------------------------------------\n");
            p2.add("Name: " + customer.getName() + "\n");
            p2.add("Account Number: " + customer.getKCElectricID() + "\n");
            // Add the customer address to the p
            p2.add("Phone Number: " + customer.getPhoneNumber() + "\n");
            p2.add("Email: " + customer.getEmail() + "\n");
            p2.add("Address: " + customer.getAddress()  +  "\n");
            p2.add(customer.getCity() + " " + customer.getState() + " " + customer.getZipCode() + "\n");
            // Add the p to the cell
            cell.addElement(p2);

            Paragraph p3 = new Paragraph();
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
            p3.add("Invoice Calculation\n" +
                    "------------------------------------------\n");
            // Make font smaller
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 6));
            p3.add("Current Meter Reading - Previous Month meter reading = Total Meter Read \n");
            // Make font bigger
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
            p3.add(account.getThisMonthMeterReading() + " - " + account.getLastMonthMeterReading() + " = " + Double.toString(account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) + "\n");
            // Make font smaller
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 6));
            p3.add("Total Meter Read * kwhPrice = Total Cost" + "\n");
            // Make font bigger
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
            p3.add(Double.toString(account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) + " * " + Double.toString(account.getAmountPerKWH()) + " = " + Double.toString((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + "\n");
            // Make font smaller
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 6));
            p3.add("Total Cost + (Total Cost * Energy Tarrif) = Subtotal" + "\n");
            // Make font bigger
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
            p3.add(Double.toString((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + " + (" + Double.toString((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + " * " + Double.toString(account.getEnergyTariff()) + ") = " + Double.toString(((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff()) + "\n");
            // Make font smaller
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 6));
            p3.add("Subtotal + Meter Type Fee = Total" + "\n");
            if (account.getMeterType().equals("Residential")){
                p3.add("Residential Meter Type Fee: $10.00" + "\n");
            }
            else if (account.getMeterType().equals("Commercial")){
                p3.add("Commercial Meter Type Fee: $20.00" + "\n");
            }
            else if (account.getMeterType().equals("Industrial")){
                p3.add("Industrial Meter Type Fee: $30.00" + "\n");
            }
            // Make font bigger
            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
            double finalPrice = 0;
            if(account.getMeterType().equals("Residential") && (account.getThisMonthMeterReading() - account.getLastMonthMeterReading() != 0)){
                p3.add(Double.toString(((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff()) + " + " + "10.00" + " = " + Double.toString(((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff() + 10.00) + "\n");

                 finalPrice = ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff() + 10.00;
            }
            else if(account.getMeterType().equals("Commercial")&& (account.getThisMonthMeterReading() - account.getLastMonthMeterReading() != 0)){
                p3.add(Double.toString(((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff()) + " + " + "20.00" + " = " + Double.toString(((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff() + 20.00) + "\n");

                 finalPrice = ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff() + 20.00;
            }
            else if(account.getMeterType().equals("Industrial")&& (account.getThisMonthMeterReading() - account.getLastMonthMeterReading() != 0)){
                p3.add(Double.toString(((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff()) + " + " + "30.00" + " = " + Double.toString(((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff() + 30.00) + "\n");

                    finalPrice = ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) + ((account.getThisMonthMeterReading() - account.getLastMonthMeterReading()) * account.getAmountPerKWH()) * account.getEnergyTariff() + 30.00;
            }
            else{
                p3.add("N/A");
                finalPrice = 0;
            }


            p3.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12));
            p3.add("\n\n\nTotal: $" + Double.toString(finalPrice) + "\n");


            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.addElement(p3);
            // Add the cell to the table
            table.addCell(cell);
            table.addCell(cell2);
            // Add the table to the document
            document.add(table);




            document.close();
            // Make an message dialog saying that the PDF has been created
            JOptionPane.showMessageDialog(null, "PDF has been created, check the invoices folder!");
        }
        catch (Exception e){
            // If this happens, display a message dialog saying to check if they have the PDF open in another program
            JOptionPane.showMessageDialog(null, "An error has occured, one possible cause is that you have the PDF open in another program. Please close the PDF and try again.");
        }
    }

}
