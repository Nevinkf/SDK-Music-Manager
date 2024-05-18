package nevinkf;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class DisplayPanel extends JPanel {

    JLabel[] songList;
    GridBagConstraints displayPanelConstraints;

    DisplayPanel() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
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

        File songsFolder = new File("mountaineermusicmanager/songs"); // TODO make this able to be changed by user at a furture point
        // For song in songs folder, get meta data and put into a list

        //Have each song in the list as its own object , have some variable that keeps the columns same size, 
        for(File song: songsFolder.listFiles()){
            AudioFile songFile = AudioFileIO.read(song);
            Tag songTag = songFile.getTag();
            System.out.println(songTag.getFirst(FieldKey.ARTIST));
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
