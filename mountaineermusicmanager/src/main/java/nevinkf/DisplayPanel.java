package nevinkf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import com.fasterxml.jackson.databind.ObjectMapper;

import javazoom.jl.decoder.JavaLayerException;


public class DisplayPanel extends JPanel {

    private String[] columnNameList; //Switch to list later so that more columns can be added or removed
    private JTable songTable;
    private List<List<Object>> songList;
    private List<File> mp3FileNameList;
    private GridBagConstraints displayPanelConstraints;
    private MainFrame mainFrameHolder;
    private SongHolderPanel testHolderPanel;
    private String songDurationText;

    DisplayPanel(MainFrame mainFrame)
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setLayout(new BorderLayout());
        mainFrameHolder = mainFrame;

        displayPanelConstraints = new GridBagConstraints();

        // Used to keep components to the left of the side bar
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Allow user to drag and drop a file to add to songs folder, check if mp3 file exists, if so throw error at user, else at it to main song folder and playlist at later date

        columnNameList = new String[]{"Song Title", "Artist", "Album", "Track", "Time", "Genre"};
        songList = new ArrayList<List<Object>>();
        mp3FileNameList = new ArrayList<File>();

        setSongTable("mountaineermusicmanager/playlists/songLibary.json");

        JScrollPane jTableScrollPane = new JScrollPane(songTable);
        westPanel.add(jTableScrollPane, BorderLayout.CENTER);

        this.add(westPanel, BorderLayout.CENTER);
    }

    public void setSongTable(String jsonFilePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
        File jsonSongFile = new File(jsonFilePath);
        List<HashMap<String, String>> jsonSongList = new ArrayList<>();
        jsonSongList = new ObjectMapper().readerFor(ArrayList.class).readValue(jsonSongFile);

        for (HashMap<String, String> song : jsonSongList) {
            List<Object> tempList = new ArrayList<Object>();
            tempList.add(song.get("Title"));
            tempList.add(song.get("Artist"));
            tempList.add(song.get("Album"));
            tempList.add(song.get("Track"));
            tempList.add(song.get("Time")); // Figure out how to get time later TIME
            tempList.add(song.get("Genre"));

            mp3FileNameList.add(new File("mountaineermusicmanager/songs/" + song.get("MP3File").toString()));
            songList.add(tempList);
        }

        Object[][] songArray = new Object[songList.size()][6]; // make the column amount adjustable

        for (int i = 0; i < songList.size(); i++) {
            List<Object> sublist = songList.get(i);
            songArray[i] = sublist.toArray(new Object[0]);
        }

        songTable = new JTable(songArray, columnNameList);
        songTable.setEnabled(false); // Change to allow for editing of metadata
        songTable.setFillsViewportHeight(true);
        songTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        songTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getClickCount() == 2) {
                    mainFrameHolder.changeSong(mp3FileNameList.get(songTable.rowAtPoint(e.getPoint())));
                    try {
                        mainFrameHolder.playSong();
                    } catch (FileNotFoundException | JavaLayerException e1) {
                        e1.printStackTrace();
                    }
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
    }

    public List<File> getMP3FileNameList() {
        return mp3FileNameList;
    }

}
