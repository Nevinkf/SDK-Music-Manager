import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SDKMusicManager{

    //  JFrame mainFrame;
    //  GridBagConstraints mainFrameConstraints;
    //  JPanel navigationPanel;

    // public SDKMusicManager(){
    //     mainFrameConstraints = new GridBagConstraints();

    //     mainFrame= new JFrame();
    //     mainFrame.setTitle("SDK Music Manager");
    //     mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     mainFrame.setLayout(new GridBagLayout());
    //     mainFrame.setSize(750,750);
    //     mainFrame.setVisible(true);

    //     navigationPanel = new JPanel();
    //     navigationPanel.setSize(500, 600);
    //     navigationPanel.setBackground(Color.red);
    //     navigationPanel.setVisible(true);

    //     mainFrameConstraints.gridx = 0;
    //     mainFrameConstraints.gridy = 0;
    //     mainFrame.add(navigationPanel, mainFrameConstraints);
    // }

    public static void main(String[] arg){
        new MainFrame();

    }

}