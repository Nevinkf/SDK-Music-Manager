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

    JPanel columnNamePanel;
    GridBagConstraints displayPanelConstraints;

    DisplayPanel() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        displayPanelConstraints = new GridBagConstraints();

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Used to keep spacing between labels consistent
        JPanel gridBagPanel = new JPanel();
        gridBagPanel.setLayout(new GridBagLayout());

        JPanel columnNamePanel = new JPanel();
        columnNamePanel.setLayout(new GridBagLayout());

        JLabel songTitleLabel = new JLabel("Song Title");
        JLabel artistLabel = new JLabel("Artist");
        JLabel albumLabel = new JLabel("Album");
        JLabel trackLabel = new JLabel("Track");
        JLabel timeLabel = new JLabel("Time");
        JLabel genreLabel = new JLabel("Genre");
        JLabel playsLabel = new JLabel("Plays");

        GridBagConstraints songHoldeGridBagConstraints = new GridBagConstraints();

        songHoldeGridBagConstraints.gridx = 0;
        songHoldeGridBagConstraints.weightx = 0;
        songHoldeGridBagConstraints.weighty = 1;
        songHoldeGridBagConstraints.anchor = GridBagConstraints.WEST;
        songHoldeGridBagConstraints.insets = new Insets(5, 5, 5, 5);
        columnNamePanel.add(songTitleLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 1;
        columnNamePanel.add(artistLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 2;
        columnNamePanel.add(albumLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 3;
        columnNamePanel.add(trackLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 4;
        columnNamePanel.add(timeLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 5;
        columnNamePanel.add(genreLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 6;
        columnNamePanel.add(playsLabel, songHoldeGridBagConstraints);

        gridBagPanel.add(columnNamePanel);

        File songsFolder = new File("mountaineermusicmanager/songs"); // TODO make this able to be changed by user at a furture point
        // For song in songs folder, get meta data and put into a list

        //Have each song in the list as its own object , have some variable that keeps the columns same size, becauase all have some pixel width, so just change that
        //Invisible line that is extened when the divider is moved
        //Make all songholderpanels align with top
        for(File song: songsFolder.listFiles()){
            AudioFile songFile = AudioFileIO.read(song);
            Tag songTag = songFile.getTag();
            System.out.println(songTag.getFirst(FieldKey.ARTIST));
        }

        for(int i = 0; i < 10; i++){
            displayPanelConstraints.gridx = 0;
            displayPanelConstraints.gridy = i;
            displayPanelConstraints.gridheight = 1;
            displayPanelConstraints.gridwidth = 1;
            displayPanelConstraints.weightx = 0;
            displayPanelConstraints.weighty = 1;
            displayPanelConstraints.anchor = GridBagConstraints.WEST;
            displayPanelConstraints.fill = GridBagConstraints.NONE;
            gridBagPanel.add(new SongHolderPanel(), displayPanelConstraints);
        }
        
        westPanel.add(gridBagPanel, BorderLayout.WEST);

        this.add(westPanel, BorderLayout.NORTH);
        this.setVisible(true);

    }

}
