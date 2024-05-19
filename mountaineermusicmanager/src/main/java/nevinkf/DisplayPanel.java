package nevinkf;
import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import javazoom.jl.decoder.JavaLayerException;

public class DisplayPanel extends JPanel {

    JPanel columnNamePanel;
    List<SongHolderPanel> songHolderList;
    GridBagConstraints displayPanelConstraints;
    MainFrame mainFrameHolder;
    SongHolderPanel testHolderPanel;

    DisplayPanel(MainFrame mainFrame) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());
        mainFrameHolder = mainFrame;
        songHolderList = new ArrayList<SongHolderPanel>();


        displayPanelConstraints = new GridBagConstraints();

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Used to keep spacing between labels consistent
        JPanel songHolderPanel = new JPanel();
        songHolderPanel.setLayout(new BoxLayout(songHolderPanel, BoxLayout.PAGE_AXIS));

        JPanel columnNamePanel = new JPanel();
        columnNamePanel.setLayout(new BoxLayout(columnNamePanel, BoxLayout.LINE_AXIS));

        JLabel songTitleLabel = new JLabel("Song Title");
        songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        JLabel artistLabel = new JLabel("Artist");
        songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        JLabel albumLabel = new JLabel("Album");
        songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        JLabel trackLabel = new JLabel("Track");
        songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        JLabel timeLabel = new JLabel("Time");
        songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        JLabel genreLabel = new JLabel("Genre");
        songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        JLabel playsLabel = new JLabel("Plays");
        songTitleLabel.setHorizontalAlignment(JLabel.LEFT);

        columnNamePanel.add(songTitleLabel);
        columnNamePanel.add(Box.createRigidArea(new Dimension(5,5)));
        columnNamePanel.add(artistLabel);
        columnNamePanel.add(Box.createRigidArea(new Dimension(5,5)));
        columnNamePanel.add(albumLabel);
        columnNamePanel.add(Box.createRigidArea(new Dimension(5,5)));
        columnNamePanel.add(trackLabel);
        columnNamePanel.add(Box.createRigidArea(new Dimension(5,5)));
        columnNamePanel.add(timeLabel);
        columnNamePanel.add(Box.createRigidArea(new Dimension(5,5)));
        columnNamePanel.add(genreLabel);
        columnNamePanel.add(Box.createRigidArea(new Dimension(5,5)));
        columnNamePanel.add(playsLabel);
        columnNamePanel.add(Box.createRigidArea(new Dimension(5,5)));

        songHolderPanel.add(columnNamePanel);

        File songsFolder = new File("mountaineermusicmanager/songs"); // TODO make this able to be changed by user at a furture point
        // For song in songs folder, get meta data and put into a list

        //Have each song in the list as its own object , have some variable that keeps the columns same size, becauase all have some pixel width, so just change that
        //Invisible line that is extened when the divider is moved
        //Make all songholderpanels align with top
        for(File song: songsFolder.listFiles()){
            AudioFile songFile = AudioFileIO.read(song);
            Tag songTag = songFile.getTag();

            SongHolderPanel tempSongHolderPanel = new SongHolderPanel();
            testHolderPanel = tempSongHolderPanel;
            songHolderList.add(tempSongHolderPanel);
            tempSongHolderPanel.setSongTitleLabel(songTag.getFirst(FieldKey.TITLE));
            tempSongHolderPanel.setArtistLabel(songTag.getFirst(FieldKey.ARTIST));
            tempSongHolderPanel.setAlbumLabel(songTag.getFirst(FieldKey.ALBUM));
            tempSongHolderPanel.setTrackLabel(songTag.getFirst(FieldKey.TRACK));
            // tempSongHolderPanel.setTimeLabel(songTag.getValue(FieldKey., ABORT); Figure out later
            tempSongHolderPanel.setGenreLabel(songTag.getFirst(FieldKey.GENRE));
            tempSongHolderPanel.setSongFileName(song.getName());
            tempSongHolderPanel.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        mainFrameHolder.playSong("Heaven Pierce Her - ULTRAKILL- INFINITE HYPERDEATH - 01 The Fire Is Gone (for Piano, Saxophone and Trumpet).mp3");
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (JavaLayerException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                }
                
            });

            songHolderPanel.add(tempSongHolderPanel);
            songHolderPanel.add(Box.createRigidArea(new Dimension(50,5)));
        }
        
        westPanel.add(songHolderPanel, BorderLayout.WEST);

        this.add(westPanel, BorderLayout.NORTH);
        this.setVisible(true);

    }

}
