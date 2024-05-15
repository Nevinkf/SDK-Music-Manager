import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    JLabel testSongHolder;
    JScrollPane testJScrollPane;

    DisplayPanel(){
        this.setBackground(Color.blue);
        this.setLayout(new GridLayout(9, 1, 0, 0));

        testSongHolder = new JLabel("Song Holder");
        // testJScrollPane = new JScrollPane(testSongHolder);

        this.add(testSongHolder);
        this.setVisible(true);

    }

}
