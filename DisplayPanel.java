import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;

public class DisplayPanel extends JPanel {

    JLabel testSongHolder;

    DisplayPanel(){
        this.setBackground(Color.blue);
        this.setLayout(new GridLayout(9,1));

        testSongHolder = new JLabel("Song Holder");
        this.add(testSongHolder);
        this.setVisible(true);

    }

}
