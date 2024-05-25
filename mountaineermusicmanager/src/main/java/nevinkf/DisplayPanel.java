package nevinkf;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.io.File;
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

public class DisplayPanel extends JPanel {

    private JPanel columnNamePanel;
    private List<SongHolderPanel> songHolderList;
    private JTable songTable;
    private List<List<Object>> songList; 
    private GridBagConstraints displayPanelConstraints;
    private MainFrame mainFrameHolder;
    private SongHolderPanel testHolderPanel;

    DisplayPanel(MainFrame mainFrame)
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setLayout(new BorderLayout());
        mainFrameHolder = mainFrame;
        songHolderList = new ArrayList<SongHolderPanel>();

        displayPanelConstraints = new GridBagConstraints();

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Used to keep spacing between labels consistent
        // JPanel songHolderPanel = new JPanel();
        // songHolderPanel.setLayout(new BoxLayout(songHolderPanel, BoxLayout.PAGE_AXIS));

        // JPanel columnNamePanel = new JPanel();
        // columnNamePanel.setLayout(new BoxLayout(columnNamePanel, BoxLayout.LINE_AXIS));

        // JLabel songTitleLabel = new JLabel("Song Title");
        // songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        // JLabel artistLabel = new JLabel("Artist");
        // songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        // JLabel albumLabel = new JLabel("Album");
        // songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        // JLabel trackLabel = new JLabel("Track");
        // songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        // JLabel timeLabel = new JLabel("Time");
        // songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        // JLabel genreLabel = new JLabel("Genre");
        // songTitleLabel.setHorizontalAlignment(JLabel.LEFT);
        // JLabel playsLabel = new JLabel("Plays");
        // songTitleLabel.setHorizontalAlignment(JLabel.LEFT);


        // columnNamePanel.add(songTitleLabel);
        // columnNamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
        // columnNamePanel.add(artistLabel);
        // columnNamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
        // columnNamePanel.add(albumLabel);
        // columnNamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
        // columnNamePanel.add(trackLabel);
        // columnNamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
        // columnNamePanel.add(timeLabel);
        // columnNamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
        // columnNamePanel.add(genreLabel);
        // columnNamePanel.add(Box.createRigidArea(new Dimension(5, 5)));
        // columnNamePanel.add(playsLabel);
        // columnNamePanel.add(Box.createRigidArea(new Dimension(5, 5)));

        // songHolderPanel.add(columnNamePanel);

        Object[] columnNames = {"Song Title", "Artist", "Album", "Track", "Time"};
        songList = new ArrayList<List<Object>>();

        File songsFolder = new File("mountaineermusicmanager/songs"); // TODO make this able to be changed by user at a
        // use a jtable
        for (File song : songsFolder.listFiles()) {
            AudioFile songFile = AudioFileIO.read(song);
            Tag songTag = songFile.getTag();
            List<Object> tempList = new ArrayList<Object>();
            tempList.add(songTag.getFirst(FieldKey.TITLE));
            tempList.add(songTag.getFirst(FieldKey.ARTIST));
            tempList.add(songTag.getFirst(FieldKey.ALBUM));
            tempList.add(songTag.getFirst(FieldKey.TRACK));
            tempList.add(songTag.getFirst(FieldKey.GENRE));

            // SongHolderPanel tempSongHolderPanel = new SongHolderPanel();
            // songHolderList.add(tempSongHolderPanel);
            // tempSongHolderPanel.setSongTitleLabel(songTag.getFirst(FieldKey.TITLE));
            // tempSongHolderPanel.setArtistLabel(songTag.getFirst(FieldKey.ARTIST));
            // tempSongHolderPanel.setAlbumLabel(songTag.getFirst(FieldKey.ALBUM));
            // tempSongHolderPanel.setTrackLabel(songTag.getFirst(FieldKey.TRACK));
            // // tempSongHolderPanel.setTimeLabel(songTag.getValue(FieldKey., ABORT); Figure
            // tempSongHolderPanel.setGenreLabel(songTag.getFirst(FieldKey.GENRE));
            // tempSongHolderPanel.setSongFileName(song.getName());
            // tempSongHolderPanel.addMouseListener(new SongHolderMouseListener(tempSongHolderPanel, mainFrameHolder));

            songList.add(tempList);
            // songHolderPanel.add(tempSongHolderPanel);
            // songHolderPanel.add(Box.createRigidArea(new Dimension(50, 5)));
        }

        Object[][] songArray = new Object[songList.size()][6];

        for (int i = 0; i < songList.size(); i++) {
            List<Object> sublist = songList.get(i);
            songArray[i] = sublist.toArray(new Object[0]);
            System.out.println(i);
        }

        songTable = new JTable(songArray, columnNames);
        songTable.setFillsViewportHeight(true);
        songTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane jTablesScrollPane = new JScrollPane(songTable);
        westPanel.add(jTablesScrollPane, BorderLayout.WEST);

        this.add(westPanel, BorderLayout.CENTER);
    }

    public List<SongHolderPanel> getSongHolderList() {
        return songHolderList;
    }

}
