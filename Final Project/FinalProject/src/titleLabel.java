import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class titleLabel extends JPanel {
    private JLabel title;

    // Title label is used just to add a consistent label to the top of every page that calls it
    public titleLabel(String newTitle) {
        title = new JLabel(newTitle);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        add(title);
    }
}
