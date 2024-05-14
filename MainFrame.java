import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainFrame extends JFrame{
    
    JMenuBar menuBar;
    GridBagLayout mainFrameLayout;
    GridBagConstraints mainFrameConstraints;

    MainFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setLocationRelativeTo(null);

        mainFrameLayout = new GridBagLayout();

        this.setLayout(mainFrameLayout);

        mainFrameConstraints = new GridBagConstraints();
        mainFrameConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        mainFrameConstraints.weightx = 1;
        mainFrameConstraints.weighty = 1;
        mainFrameConstraints.fill = GridBagConstraints.BOTH;

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

        mainFrameConstraints.gridx = 0;
        mainFrameConstraints.gridy = 0;
        mainFrameConstraints.gridwidth = 1;
        mainFrameConstraints.gridheight = 1;

        this.add(new SideBarPanel(), mainFrameConstraints);

        mainFrameConstraints.gridx = 1;
        mainFrameConstraints.gridy = 0;

        this.add(new DisplayPanel(), mainFrameConstraints);

        this.pack();
    }

}
