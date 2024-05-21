package nevinkf;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;

public class SongHolderMouseListener implements MouseListener {

    SongHolderPanel songHolderPanelHolder;
    MainFrame mainFrameHolder;

    SongHolderMouseListener(SongHolderPanel songHolderPanel, MainFrame mainFrame) {
        this.songHolderPanelHolder = songHolderPanel;
        this.mainFrameHolder = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        try {
            if (e.getClickCount() >= 2) {
                songHolderPanelHolder.setIsSelected(true);
                mainFrameHolder.changeSelectedSong(songHolderPanelHolder);
                mainFrameHolder.playSong(songHolderPanelHolder.getSongFileName());
            } else {
                songHolderPanelHolder.setIsSelected(true);
                mainFrameHolder.changeSelectedSong(songHolderPanelHolder);
            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (JavaLayerException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseExited'");
    }

}
