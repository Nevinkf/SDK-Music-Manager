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

    JMenuBar menuBar;
    BorderLayout mainFrameLayout;
    MediaPlayer songPlayer;
    SongHolderPanel selectedSong;
    SongHolderPanel currentSong;
    DisplayPanel displayPanel;
    SideBarPanel sideBarPanel;
    OptionsPanel optionsPanel;
    boolean songPaused = true;

    MainFrame()
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Used here to initialize toolkit for jfx media player
        JFXPanel jfxPanel = new JFXPanel();
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

        this.setVisible(true);
        this.pack();
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
    }

    public void changeToNextSong() {
        // take position of current song in list
        List<SongHolderPanel> tempList = displayPanel.getSongHolderList();
        
    }

    public void changeToLastSong() {

    }

    public void changeSelectedSong(SongHolderPanel newSelectedSong) {
        if (selectedSong != null) {
            selectedSong.setIsSelected(false);
            selectedSong = newSelectedSong;
        } else {
            selectedSong = newSelectedSong;
        }
    }

}