import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class SideBarPanel extends JPanel{

    JLabel testLabel;

    SideBarPanel(){
        this.setBackground(Color.red);
        this.setLayout(new GridLayout(1,1));

        testLabel = new JLabel("Test Playlist");
        testLabel.setHorizontalAlignment(JLabel.CENTER);
        testLabel.setVerticalAlignment(JLabel.CENTER);

        this.add(testLabel);
        this.setVisible(true);

    }

}
