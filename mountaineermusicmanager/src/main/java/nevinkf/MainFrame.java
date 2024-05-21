package nevinkf;

import javax.swing.*;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MainFrame extends JFrame {

    JMenuBar menuBar;
    BorderLayout mainFrameLayout;
    AdvancedPlayer songPlayer;
    SongHolderPanel selectedSong;
    boolean songPaused = true;
    int pausedFrame;
    int currentPosition;

    MainFrame()
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

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

    public void playSong(String mp3String) throws JavaLayerException, FileNotFoundException {
        CompletableFuture<Void> asyncFuture = CompletableFuture.runAsync(() -> {
            try {
                stopSong();
                songPaused = false;
                String tempFilePath = "mountaineermusicmanager/songs/" + mp3String;
                FileInputStream songFileInputStream = new FileInputStream(tempFilePath);

                songFileInputStream = new FileInputStream(tempFilePath);

                songPlayer = new AdvancedPlayer(songFileInputStream);
                songPlayer.setPlayBackListener(new PlaybackListener() {
                    
                    public void playbackFinished(PlaybackEvent e){
                        //When switching songs, check if frame is last frame
                        pausedFrame = e.getFrame();
                        currentPosition += pausedFrame;
                        System.out.println("Hello!" + e.getFrame());
                    }
                });
                songPlayer.play(0, Integer.MAX_VALUE);

            } catch (JavaLayerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Task completed!");
        });
    }

    public void stopSong() {
        if (songPlayer != null) {
            songPaused = true;
            songPlayer.close();
        }
    }

    public void pauseSong() {
        if (songPlayer != null) {
            songPaused = true;
            songPlayer.stop();
        }
    }

    public void resumeSong() {

    }

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