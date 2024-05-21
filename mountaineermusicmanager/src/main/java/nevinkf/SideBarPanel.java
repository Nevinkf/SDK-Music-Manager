package nevinkf;

import javax.swing.*;
import java.awt.*;

public class SideBarPanel extends JPanel {

    JLabel testLabel;
    GridBagConstraints sideBarConstraints;

    SideBarPanel() {
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());

        sideBarConstraints = new GridBagConstraints();
        JLabel[] testLabels = { new JLabel("Hello"), new JLabel("C#"), new JLabel("Unity"), new JLabel("GDScript"),
                new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart"),
                new JLabel("Heart") };

        testLabel = new JLabel("Test Playlist");

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Used to keep spacing between labels consistent
        JPanel gridBagPanel = new JPanel();
        gridBagPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < testLabels.length; i++) {
            sideBarConstraints.gridx = 0;
            sideBarConstraints.gridy = i;
            sideBarConstraints.gridheight = 1;
            sideBarConstraints.gridwidth = 1;
            sideBarConstraints.weightx = 0;
            sideBarConstraints.weighty = 1;
            sideBarConstraints.anchor = GridBagConstraints.WEST;
            sideBarConstraints.insets = new Insets(2, 2, 2, 2);
            sideBarConstraints.fill = GridBagConstraints.NONE;
            gridBagPanel.add(testLabels[i], sideBarConstraints);
        }

        westPanel.add(gridBagPanel, BorderLayout.WEST);

        this.add(westPanel, BorderLayout.NORTH);
        this.setVisible(true);
    }
}
