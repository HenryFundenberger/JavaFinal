import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class titleLabel extends JPanel {
    private JLabel title;

    public titleLabel(String newTitle) {
        title = new JLabel(newTitle);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        add(title);
    }
}
