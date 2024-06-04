package nevinkf;

import javax.swing.*;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javazoom.jl.decoder.JavaLayerException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private BorderLayout mainFrameLayout;
    private MediaPlayer songPlayer;
    private File currentSong;
    private String currentPlayList;
    private DisplayPanel displayPanel;
    private SideBarPanel sideBarPanel;
    private OptionsPanel optionsPanel;
    private boolean songPaused = true;

    MainFrame()
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Mountaineer Music Manager");

        // write the mp3 files to a json file and display that to the

        if (!new File("mountaineermusicmanager/playlists/songLibary.json").exists()) {
            writeMainJsonFile("mountaineermusicmanager/playlists/songLibary.json");
        }
        currentPlayList = "mountaineermusicmanager/playlists/songLibary.json";
        updateJsonFile("mountaineermusicmanager/playlists/songLibary.json");

        JFXPanel jfxPanel = new JFXPanel(); // Used here to initialize toolkit for jfx media player
        displayPanel = new DisplayPanel(this);
        sideBarPanel = new SideBarPanel();
        optionsPanel = new OptionsPanel(this);

        mainFrameLayout = new BorderLayout();

        this.setLayout(mainFrameLayout);

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        this.setJMenuBar(menuBar);

        JScrollPane sideBarScrollPane = new JScrollPane();
        sideBarScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sideBarScrollPane.setViewportView(sideBarPanel);

        JScrollPane displayScrollPane = new JScrollPane();
        displayScrollPane.setViewportView(displayPanel);

        JSplitPane sideAndDisplaySplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideBarScrollPane,
                displayScrollPane);

        this.add(sideAndDisplaySplitPane, BorderLayout.CENTER);

        this.add(optionsPanel, BorderLayout.NORTH);

        this.pack();
        this.setVisible(true);
    }

    public void playSong() throws JavaLayerException, FileNotFoundException {
        songPaused = false;
        songPlayer.play();
    }

    public void pauseSong() {
        if (songPlayer != null) {
            songPaused = true;
            songPlayer.pause();
        }
    }

    public void changeSong(File songFile) {
        if (songPlayer != null) {
            songPlayer.stop();
        }
        currentSong = songFile;
        Media media = new Media(songFile.toURI().toString());
        songPlayer = new MediaPlayer(media);
        songPlayer.setOnReady(() -> {
            optionsPanel.setProgressBarAndEndTime(songPlayer.getTotalDuration());
            songPlayer.currentTimeProperty().addListener((observable, oldTime, newTime) -> {
                optionsPanel.updateProgressBarAndCurrentTime(newTime);
            });
        });

        songPlayer.setOnEndOfMedia(() -> {
            List<File> tempList = displayPanel.getMP3FileNameList();
            if (tempList.indexOf(currentSong) < tempList.size() - 1) {
                changeToNextSong();
            }
        });
    }

    public void deleteMediaPlayer() {
        if (songPlayer != null){
            songPlayer.stop();
            songPlayer.dispose();
            System.gc(); // Runs garbage collector to ensure media player gets rid of refernce to mp3 file
            songPlayer = null; 
        }
    }

    public void changeToNextSong() {
        // take position of current song in list
        List<File> tempList = displayPanel.getMP3FileNameList();

        if (tempList.indexOf(currentSong) >= tempList.size() - 1) {
            changeSong(tempList.get(0));
        } else {
            changeSong(tempList.get(tempList.indexOf(currentSong) + 1));
        }

        try {
            playSong();
        } catch (FileNotFoundException | JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void changeToLastSong() {
        List<File> tempList = displayPanel.getMP3FileNameList();
        System.out.println(tempList.indexOf(currentSong) + ", " + tempList.size());
        if (tempList.indexOf(currentSong) == 0) {
            changeSong(tempList.get(tempList.size() - 1));
        } else {
            changeSong(tempList.get(tempList.indexOf(currentSong) - 1));
        }

        try {
            playSong();
        } catch (FileNotFoundException | JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void writeMainJsonFile(String jsonFilePath)
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        ObjectMapper jsonMapper = new ObjectMapper();
        File songsFolder = new File("mountaineermusicmanager/songs");
        List<HashMap<String, String>> listToJson = new ArrayList<>();

        for (File song : songsFolder.listFiles()) {
            AudioFile songFile = AudioFileIO.read(song);
            Tag songTag = songFile.getTag();
            HashMap<String, String> tempHashMap = new HashMap<String, String>();
            tempHashMap.put("Title", songTag.getFirst(FieldKey.TITLE));
            tempHashMap.put("Artist", songTag.getFirst(FieldKey.ARTIST));
            tempHashMap.put("Album", songTag.getFirst(FieldKey.ALBUM));
            tempHashMap.put("Track", songTag.getFirst(FieldKey.TRACK));
            tempHashMap.put("Time", "Placeholder"); // Figure out how to get time later
            tempHashMap.put("Genre", songTag.getFirst(FieldKey.GENRE));
            tempHashMap.put("MP3File", song.getName());

            listToJson.add(tempHashMap);
        }

        try {
            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), listToJson);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateJsonFile(String jsonFilePath) {
        // Grab the json file, put it's values into a hashmap,
        ObjectMapper jsonMapper = new ObjectMapper();
        File jsonFile = new File(jsonFilePath);
        List<HashMap<String, String>> jsonToList = new ArrayList<HashMap<String, String>>();

        try {
            jsonToList = jsonMapper.readValue(new File(jsonFilePath), new TypeReference<ArrayList<HashMap<String, String>>>() {});
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void addToJsonFile(String jsonFilePath){

    }

    public void removeFromJsonFile(String jsonFilePath, int positionInJsonFile) {
        ObjectMapper jsonMapper = new ObjectMapper();
        File jsonFile = new File(jsonFilePath);
        List<HashMap<String, String>> jsonToList = new ArrayList<HashMap<String, String>>();
        
        try {
            jsonToList = jsonMapper.readValue(jsonFile, new TypeReference<ArrayList<HashMap<String, String>>>() {});
            File toDeleteFile = new File("mountaineermusicmanager/songs/" + jsonToList.get(positionInJsonFile).get("MP3File"));
            jsonToList.remove(positionInJsonFile);
            
            if (new File(toDeleteFile.getAbsolutePath()).delete()) {
                System.out.println("Success");
            } else {
                System.out.println("Failure");
            }

            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonToList);

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public File getCurrentSong() {
        return currentSong;
    }

    public String getCurrentPlaylist() {
        return currentPlayList;
    }

    public MediaPlayer getMediaPlayer() {
        return songPlayer;
    }
    
    public void setSongVolume(double newSongVolume) {
        songPlayer.setVolume(newSongVolume);
    }

    public boolean isSongPaused() {
        return songPaused;
    }

    public void setSongPaused(boolean newBool) {
        songPaused = newBool;
    }

}