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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MainFrame extends JFrame {

    JMenuBar menuBar;
    BorderLayout mainFrameLayout;
    // AdvancedPlayer songPlayer;
    MediaPlayer songPlayer;
    SongHolderPanel selectedSong;
    boolean songPaused = true;
    int pausedFrame;
    int currentPosition;

    MainFrame() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Used here to initialize toolkit for jfx media player
        JFXPanel jfxPanel = new JFXPanel();

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
        sideBarScrollPane.setViewportView(new SideBarPanel());

        JScrollPane displayScrollPane = new JScrollPane();
        displayScrollPane.setViewportView(new DisplayPanel(this));

        JSplitPane sideAndDisplaySplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideBarScrollPane,
                displayScrollPane);

        this.add(sideAndDisplaySplitPane, BorderLayout.CENTER);

        this.add(new OptionsPanel(this), BorderLayout.NORTH);

        this.setVisible(true);
        this.pack();
    }

    public void playSong() throws JavaLayerException, FileNotFoundException {
        songPaused = false;
        songPlayer.play();
    }

    //new function to change song

    public void changeSong(String mp3String) {
        String tempFilePath = "mountaineermusicmanager/songs/" + mp3String;
        Media media = new Media(new File(tempFilePath).toURI().toString());  
        songPlayer = new MediaPlayer(media);
    }

    public void pauseSong() {
        if (songPlayer != null) {
            songPaused = true;
            songPlayer.pause();
        }
    }

    // public void resumeSong() {
    //     if (songPlayer != null){
    //         songPlayer.play();
    //     }
    // }

    public void changeSelectedSong(SongHolderPanel newSelectedSong) {
        if (selectedSong != null){
            selectedSong.setIsSelected(false);
            selectedSong = newSelectedSong;
        } else {
            selectedSong = newSelectedSong;
        }
        currentPosition = 0;
    }

}