import javax.swing.*;
import java.awt.*;
import java.io.File;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;

public class DisplayPanel extends JPanel {

    JLabel[] songList;
    GridBagConstraints displayPanelConstraints;

    DisplayPanel(){
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        displayPanelConstraints = new GridBagConstraints();
        List[] songList = {};

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Used to keep spacing between labels consistent
        JPanel gridBagPanel = new JPanel();
        gridBagPanel.setLayout(new GridBagLayout());

        File songsFolder = new File("/songs");
        // For song in songs folder, get meta data and put into a list
        for(File song: songsFolder.listFiles()){
            // song
        }

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
