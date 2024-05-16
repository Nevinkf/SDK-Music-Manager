import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    JLabel[] songList;
    GridBagConstraints displayPanelConstraints;

    DisplayPanel(){
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        displayPanelConstraints = new GridBagConstraints();
        JLabel[] songList = {new JLabel("Hello"), new JLabel("C#"), new JLabel("Unity"), new JLabel("GDScript"), new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart"), new JLabel("Heart")};

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Used to keep spacing between labels consistent
        JPanel gridBagPanel = new JPanel();
        gridBagPanel.setLayout(new GridBagLayout());

        for(int i = 0; i < songList.length; i++){
            displayPanelConstraints.gridx = 0;
            displayPanelConstraints.gridy = i;
            displayPanelConstraints.gridheight = 1;
            displayPanelConstraints.gridwidth = 1;
            displayPanelConstraints.weightx = 0;
            displayPanelConstraints.weighty = 1;
            displayPanelConstraints.anchor = GridBagConstraints.WEST;
            displayPanelConstraints.insets = new Insets(2, 2, 2, 2);
            displayPanelConstraints.fill = GridBagConstraints.NONE;
            gridBagPanel.add(songList[i], displayPanelConstraints);
        }
        
        westPanel.add(gridBagPanel, BorderLayout.WEST);

        this.add(westPanel, BorderLayout.NORTH);
        this.setVisible(true);

    }

}
