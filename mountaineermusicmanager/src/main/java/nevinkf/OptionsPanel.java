package nevinkf;

import javax.swing.*;

import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class OptionsPanel extends JPanel {

    private JButton playButton;
    private JButton nextSongButton;
    private JButton lastSongButton;
    private JSlider songProgressBar;
    private GridBagConstraints optionsGridBagConstraints;
    private MainFrame mainFrameHolder;

    OptionsPanel(MainFrame mainFrame) {
        this.setVisible(true);
        this.setLayout(new GridBagLayout());

        mainFrameHolder = mainFrame;
        optionsGridBagConstraints = new GridBagConstraints();

        lastSongButton = new JButton("Last Song");
        lastSongButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrameHolder.getCurrentSong() != null) {
                    mainFrameHolder.changeToLastSong();
                }
            }
        });
        this.add(lastSongButton);

        playButton = new JButton("Play/Pause");
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // if song is playing, pause song, else play/resume song
                if (mainFrameHolder.isSongPaused() == false) {
                    mainFrameHolder.pauseSong();
                } else {
                    if (mainFrameHolder.getCurrentSong() != null) {
                        try {
                            mainFrameHolder.playSong();
                        } catch (FileNotFoundException | JavaLayerException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        this.add(playButton, optionsGridBagConstraints);

        nextSongButton = new JButton("Next Song");
        nextSongButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrameHolder.getCurrentSong() != null) {
                    mainFrameHolder.changeToNextSong();
                }
            }
        });
        this.add(nextSongButton, optionsGridBagConstraints);

        songProgressBar = new JSlider(); //Switch to Jslider

        optionsGridBagConstraints.gridy = 1;
        optionsGridBagConstraints.gridwidth = 3;
        this.add(songProgressBar, optionsGridBagConstraints);

    }

    public void setProgressBar(int endTime) {
        this.remove(songProgressBar);
        songProgressBar = new JSlider(0, endTime);
        this.add(songProgressBar, optionsGridBagConstraints);
        songProgressBar.revalidate();
    }

    public void updateProgressBar(Duration currentTime) {
        songProgressBar.setValue((int) Math.round(currentTime.toSeconds()));

    }
}
