package nevinkf;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class OptionsPanel extends JPanel {

    private JButton playButton;
    private JButton nextSongButton;
    private JButton lastSongButton;
    private JSlider songSlider;
    private JSlider volumeSlider;
    private JLabel currentTimeLabel;
    private JLabel endTimeLabel;
    private GridBagConstraints optionsGridBagConstraints;
    private MainFrame mainFrameHolder;
    private boolean mouseIsDragging;

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

        currentTimeLabel = new JLabel("0:00");

        optionsGridBagConstraints.gridx = 0;
        optionsGridBagConstraints.gridy = 1;
        optionsGridBagConstraints.gridwidth = 1;
        this.add(currentTimeLabel, optionsGridBagConstraints);

        songSlider = new JSlider(0, 1, 0);

        optionsGridBagConstraints.gridx = 1;
        optionsGridBagConstraints.gridy = 1;
        optionsGridBagConstraints.gridwidth = 2;
        this.add(songSlider, optionsGridBagConstraints);

        endTimeLabel = new JLabel("0:00");

        optionsGridBagConstraints.gridx = 3;
        optionsGridBagConstraints.gridy = 1;
        optionsGridBagConstraints.gridwidth = 1;
        this.add(endTimeLabel, optionsGridBagConstraints);

        optionsGridBagConstraints.gridx = 3; // Redo gridbag for rest of items    
        optionsGridBagConstraints.gridy = 0;
        optionsGridBagConstraints.gridwidth = 2;
        volumeSlider = new JSlider(0, 100, 100);
        volumeSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (mainFrame.getMediaPlayer() != null){
                    mainFrame.setSongVolume((double) volumeSlider.getValue() / 100);
                }
            }
            
        });
        this.add(volumeSlider, optionsGridBagConstraints);

    }

    public void setProgressBarAndEndTime(Duration endTime) {
        this.remove(songSlider);
        songSlider = new JSlider(0, (int) Math.round(endTime.toSeconds()), 0);
        songSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (mouseIsDragging) {
                    mainFrameHolder.getMediaPlayer().seek(new Duration(songSlider.getValue() * 1000));
                }
            }
        });
        songSlider.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseIsDragging = true;
                mainFrameHolder.getMediaPlayer().pause();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseIsDragging = false;
                mainFrameHolder.getMediaPlayer().play();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
        optionsGridBagConstraints.gridx = 1;
        optionsGridBagConstraints.gridy = 1;
        optionsGridBagConstraints.gridwidth = 2;
        this.add(songSlider, optionsGridBagConstraints);
        songSlider.revalidate(); // Used to make sure slider works without resizing screen

        double seconds = Math.floor(endTime.toSeconds() - 60 * (Math.floorDiv((int) endTime.toSeconds(), 60)));
        int endTimeSeconds = (int) seconds;
        double minutes = Math.floor(endTime.toMinutes());
        int endTimeMinutes = (int) minutes;

        if (endTimeSeconds < 10) {
            endTimeLabel.setText(endTimeMinutes + ":0" + endTimeSeconds);
        } else {
            endTimeLabel.setText(endTimeMinutes + ":" + endTimeSeconds);
        }
    }

    public void updateProgressBarAndCurrentTime(Duration currentTime) {
        songSlider.setValue((int) Math.round(currentTime.toSeconds()));
        double seconds = Math.floor(currentTime.toSeconds() - 60 * (Math.floorDiv((int) currentTime.toSeconds(), 60)));
        int currentTimeSeconds = (int) seconds;
        double minutes = Math.floor(currentTime.toMinutes());
        int currentTimeMinutes = (int) minutes;

        if (currentTimeSeconds < 10) {
            currentTimeLabel.setText(currentTimeMinutes + ":0" + currentTimeSeconds);
        } else {
            currentTimeLabel.setText(currentTimeMinutes + ":" + currentTimeSeconds);
        }

    }

}
