package nevinkf;

import javax.swing.*;
import java.awt.*;

public class SongHolderPanel extends JPanel {

    JLabel songTitleLabel;
    JLabel artistLabel;
    JLabel albumLabel;
    JLabel trackLabel;
    JLabel timeLabel;
    JLabel genreLabel;
    JLabel playsLabel;
    String songFileName;

    Boolean isSelected;

    SongHolderPanel() {
        //Obsolote
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        songTitleLabel = new JLabel("Test");
        songTitleLabel.setPreferredSize(new Dimension(100, 10)); // IMPORTANT
        artistLabel = new JLabel("Test");
        albumLabel = new JLabel("Test");
        trackLabel = new JLabel("Test");
        timeLabel = new JLabel("Test");
        genreLabel = new JLabel("Test");
        playsLabel = new JLabel("Test");

        this.add(songTitleLabel);
        this.add(Box.createRigidArea(new Dimension(10, 5)));
        this.add(artistLabel);
        this.add(Box.createRigidArea(new Dimension(10, 5)));
        this.add(albumLabel);
        this.add(Box.createRigidArea(new Dimension(10, 5)));
        this.add(trackLabel);
        this.add(Box.createRigidArea(new Dimension(10, 5)));
        this.add(timeLabel);
        this.add(Box.createRigidArea(new Dimension(10, 5)));
        this.add(genreLabel);
        this.add(Box.createRigidArea(new Dimension(10, 5)));
        this.add(playsLabel);

        isSelected = false;
    }

    public void setSongTitleLabel(String newSongTitle) {
        /**
         * Description:
         * Arguments:
         * Return:
         * 
         **/
        songTitleLabel.setText(newSongTitle);
    }

    public JLabel getSongTitleLabel() {
        return songTitleLabel;
    }

    public void setArtistLabel(String newArtist) {
        artistLabel.setText(newArtist);
    }

    public JLabel getArtistLabel() {
        return artistLabel;
    }

    public void setAlbumLabel(String newAlbumLabel) {
        albumLabel.setText(newAlbumLabel);
    }

    public JLabel getAlbumLabel() {
        return albumLabel;
    }

    public void setTrackLabel(String newTrackLabel) {
        trackLabel.setText(newTrackLabel);
    }

    public JLabel getTrackLabel() {
        return trackLabel;
    }

    public void setTimeLabel(String newTimeLabel) {
        timeLabel.setText(newTimeLabel);
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setGenreLabel(String newGenreLabel) {
        genreLabel.setText(newGenreLabel);
    }

    public JLabel getGenreLabel() {
        return genreLabel;
    }

    public void setPlaysLabel(String newPlaysLabel) {
        playsLabel.setText(newPlaysLabel);
    }

    public JLabel getPlaysLabel() {
        return playsLabel;
    }

    public void setSongFileName(String newSongFileName) {
        songFileName = newSongFileName;
    }

    public String getSongFileName() {
        return songFileName;
    }

    public void setIsSelected(boolean newIsSelected) {
        isSelected = newIsSelected;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

}
