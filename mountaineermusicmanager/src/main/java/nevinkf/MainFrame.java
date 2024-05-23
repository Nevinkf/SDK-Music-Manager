package nevinkf;

import javax.swing.*;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import javazoom.jl.decoder.JavaLayerException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.*;

import java.awt.*;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private BorderLayout mainFrameLayout;
    private MediaPlayer songPlayer;
    private SongHolderPanel selectedSong;
    private SongHolderPanel currentSong;
    private DisplayPanel displayPanel;
    private SideBarPanel sideBarPanel;
    private OptionsPanel optionsPanel;
    private boolean songPaused = true;

    MainFrame()
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

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

    public void changeSong(SongHolderPanel songHolderPanel) {
        if (songPlayer != null) {
            songPlayer.stop();
        }
        currentSong = songHolderPanel;
        String tempFilePath = "mountaineermusicmanager/songs/" + songHolderPanel.getSongFileName();
        Media media = new Media(new File(tempFilePath).toURI().toString());
        songPlayer = new MediaPlayer(media);
        songPlayer.setOnReady(() -> {
            int songDuration = (int) Math.round(songPlayer.getTotalDuration().toSeconds());
            optionsPanel.setProgressBar(songDuration);
            songPlayer.currentTimeProperty().addListener((observable, oldTime, newTime) -> {
                optionsPanel.updateProgressBar(newTime);
            });
        });

    }

    public void changeToNextSong() {
        // take position of current song in list
        List<SongHolderPanel> tempList = displayPanel.getSongHolderList();
        System.out.println(tempList.indexOf(currentSong) + ", " + tempList.size());

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
        List<SongHolderPanel> tempList = displayPanel.getSongHolderList();
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

    public void changeSelectedSong(SongHolderPanel newSelectedSong) {
        if (selectedSong != null) {
            selectedSong.setIsSelected(false);
            selectedSong = newSelectedSong;
        } else {
            selectedSong = newSelectedSong;
        }
    }

    public SongHolderPanel getCurrentSong() {
        return currentSong;
    }

    public MediaPlayer getMediaPlayer() {
        return songPlayer;
    }

    public boolean isSongPaused() {
        return songPaused;
    }

    public void setSongPaused(boolean newBool) {
        songPaused = newBool;
    }

}