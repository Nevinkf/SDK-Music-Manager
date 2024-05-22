package nevinkf;

import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class OptionsPanel extends JPanel {

    JButton playButton;
    GridBagConstraints optionsGridBagConstraints;
    MainFrame mainFrameHolder;

    OptionsPanel(MainFrame mainFrame) {
        this.setBackground(Color.green);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());

        mainFrameHolder = mainFrame;
        optionsGridBagConstraints = new GridBagConstraints();
        playButton = new JButton("Play/Pause");
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // if song is playing, pause song, else play/resume song
                if (mainFrameHolder.songPaused == false){
                    mainFrameHolder.pauseSong();
                } else {
                    if (mainFrameHolder.selectedSong != null) {
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
    }

}
