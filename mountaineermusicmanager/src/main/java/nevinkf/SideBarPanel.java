package nevinkf;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SideBarPanel extends JPanel {

    private JLabel testLabel;
    private List<JLabel> testLabels;
    private GridBagConstraints sideBarConstraints;

    SideBarPanel() {
        this.setLayout(new BorderLayout());

        sideBarConstraints = new GridBagConstraints();
        testLabels = new ArrayList<JLabel>();
        testLabel = new JLabel("Test Playlist");

        // Have a list of playlists titles, each tilte holds a path to the json file that stores it's songs

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Used to keep spacing between labels consistent
        JPanel gridBagPanel = new JPanel();
        gridBagPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < testLabels.size(); i++) {
            sideBarConstraints.gridx = 0;
            sideBarConstraints.gridy = i;
            sideBarConstraints.gridheight = 1;
            sideBarConstraints.gridwidth = 1;
            sideBarConstraints.weightx = 0;
            sideBarConstraints.weighty = 1;
            sideBarConstraints.anchor = GridBagConstraints.WEST;
            sideBarConstraints.insets = new Insets(2, 2, 2, 2);
            sideBarConstraints.fill = GridBagConstraints.NONE;
            gridBagPanel.add(testLabels.get(i), sideBarConstraints);
        }

        westPanel.add(gridBagPanel, BorderLayout.WEST);

        this.add(westPanel, BorderLayout.NORTH);
    }
}
