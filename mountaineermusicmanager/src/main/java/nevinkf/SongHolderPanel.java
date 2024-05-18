package nevinkf;
import javax.swing.*;
import java.awt.*;

public class SongHolderPanel extends JPanel{

    JLabel songTitleLabel;
    JLabel artistLabel;
    JLabel albumLabel;
    JLabel trackLabel;
    JLabel timeLabel;
    JLabel genreLabel;
    JLabel playsLabel;
    GridBagConstraints songHoldeGridBagConstraints;

    SongHolderPanel(){
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        songHoldeGridBagConstraints = new GridBagConstraints();


        songTitleLabel = new JLabel("Test");
        artistLabel = new JLabel("Test");
        albumLabel = new JLabel("Test");
        trackLabel = new JLabel("Test");
        timeLabel = new JLabel("Test");
        genreLabel = new JLabel("Test");
        playsLabel = new JLabel("Test");
        
        songHoldeGridBagConstraints.gridx = 0;
        songHoldeGridBagConstraints.weightx = 0;
        songHoldeGridBagConstraints.weighty = 1;
        songHoldeGridBagConstraints.anchor = GridBagConstraints.WEST;
        songHoldeGridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.add(songTitleLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 1;
        this.add(artistLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 2;
        this.add(albumLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 3;
        this.add(trackLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 4;
        this.add(timeLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 5;
        this.add(genreLabel, songHoldeGridBagConstraints);
        songHoldeGridBagConstraints.gridx = 6;
        this.add(playsLabel, songHoldeGridBagConstraints);
    }

    public JLabel getSongTitleLabel(){
        return songTitleLabel;
    }

    public JLabel getArtistLabel(){
        return artistLabel;
    }

    public JLabel getAlbumLabel(){
        return albumLabel;
    }

    public JLabel getTrackLabel(){
        return trackLabel;
    }

    public JLabel getTimeLabel(){
        return timeLabel;
    }

    public JLabel getGenreLabel(){
        return genreLabel;
    }

    public JLabel getPlaysLabel(){
        return playsLabel;
    }

}
