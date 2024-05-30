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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

        columnNameList = new String[]{"Song Title", "Artist", "Album", "Track", "Time", "Genre"};
        songList = new ArrayList<List<Object>>();
        mp3FileNameList = new ArrayList<File>();

        setSongTable("mountaineermusicmanager/test/songLibary.json");

        JScrollPane jTableScrollPane = new JScrollPane(songTable);
        westPanel.add(jTableScrollPane, BorderLayout.CENTER);

        this.add(westPanel, BorderLayout.CENTER);
    }

    // public List<Object> readJsonFile(String jsonFilePath) {
    //     File songJson = new File(jsonFilePath);
    //     // ObjectMapper jsonReader = new ObjectMapper();

    //     List<Object> testList = new ArrayList<>();

    //     try {
    //         testList = new ObjectMapper().readerFor(ArrayList.class).readValue(songJson);
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }

    //     return testList;

    // }

    public void setSongTable(String jsonFilePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
        // File songsFolder = new File("mountaineermusicmanager/songs"); // TODO make this able to be changed by user at a
        File jsonSongFile = new File(jsonFilePath);
        List<List<Object>> jsonSongList = new ArrayList<>();
        jsonSongList = new ObjectMapper().readerFor(ArrayList.class).readValue(jsonSongFile);

        for (List<Object> song : jsonSongList) {
            AudioFile songFile = AudioFileIO.read(new File("mountaineermusicmanager/songs/" + song.get(6).toString()));
            Tag songTag = songFile.getTag();
            List<Object> tempList = new ArrayList<Object>();
            tempList.add(song.get(0));
            tempList.add(song.get(1));
            tempList.add(song.get(2));
            tempList.add(song.get(3));
            tempList.add(song.get(4)); // Figure out how to get time later
            tempList.add(song.get(5));

            mp3FileNameList.add(new File("mountaineermusicmanager/songs/" + song.get(6).toString()));
            songList.add(tempList);
        }

        Object[][] songArray = new Object[songList.size()][6]; // make this much better

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
