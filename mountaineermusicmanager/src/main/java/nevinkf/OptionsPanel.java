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

        songSlider = new JSlider(0, 1, 0); //Switch to Jslider

        optionsGridBagConstraints.gridy = 1;
        optionsGridBagConstraints.gridwidth = 3;
        this.add(songSlider, optionsGridBagConstraints);

    }

    public void setProgressBar(int endTime) {
        this.remove(songSlider);
        songSlider = new JSlider(0, endTime, 0);
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
        this.add(songSlider, optionsGridBagConstraints);
        songSlider.revalidate();
    }

    public void updateProgressBar(Duration currentTime) {
        songSlider.setValue((int) Math.round(currentTime.toSeconds()));

    }
}
