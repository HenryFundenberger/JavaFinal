import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginBody extends JPanel {
    private JTextField username;
    private JPasswordField password;

    // Stack them vertically
    public loginBody() {
        // Use GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Username
        JLabel usernameLabel = new JLabel("Username: ");
        c.gridx = 0;
        c.gridy = 0;
        add(usernameLabel, c);

        username = new JTextField(10);
        c.gridx = 1;
        c.gridy = 0;
        add(username, c);

        // Password
        JLabel passwordLabel = new JLabel("Password: ");
        c.gridx = 0;
        c.gridy = 1;
        add(passwordLabel, c);

        password = new JPasswordField(10);
        c.gridx = 1;
        c.gridy = 1;
        add(password, c);



    }

    // Getters
    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }


}
