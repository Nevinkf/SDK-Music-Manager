import javax.swing.*;
import java.awt.*;

public class SideBarPanel extends JPanel{

    JLabel testLabel;
    GridBagConstraints sideBarConstraints;

    SideBarPanel(){
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());

        sideBarConstraints = new GridBagConstraints();
        JLabel[] testLabels = {new JLabel("Hello"), new JLabel("C#"), new JLabel("Unity"), new JLabel("GDScript"), new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart")};

        testLabel = new JLabel("Test Playlist");

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());

        for(int i = 0; i < testLabels.length; i++){
            sideBarConstraints.gridx = 0;
            sideBarConstraints.gridy = i;
            sideBarConstraints.gridheight = 1;
            sideBarConstraints.gridwidth = 1;
            sideBarConstraints.weightx = 0;
            sideBarConstraints.weighty = 1;
            sideBarConstraints.anchor = GridBagConstraints.WEST;
            sideBarConstraints.insets = new Insets(2, 2, 2, 2);
            sideBarConstraints.fill = GridBagConstraints.NONE;
            northPanel.add(testLabels[i], sideBarConstraints);
        }
        
        this.add(northPanel, BorderLayout.NORTH);
        this.setVisible(true);

    }
}
