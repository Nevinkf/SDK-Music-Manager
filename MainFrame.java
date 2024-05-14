import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainFrame extends JFrame{
    
    JMenuBar menuBar;
    GridBagLayout mainFrameLayout;
    GridBagConstraints mainFrameConstraints;

    MainFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setPreferredSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);

        mainFrameLayout = new GridBagLayout();

        this.setLayout(mainFrameLayout);

        mainFrameConstraints = new GridBagConstraints();
        mainFrameConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        mainFrameConstraints.insets = new Insets(0, 0, 0, 0);

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        this.setJMenuBar(menuBar);
        

        mainFrameConstraints.gridx = 0;
        mainFrameConstraints.gridy = 1;
        mainFrameConstraints.gridwidth = 1;
        mainFrameConstraints.gridheight = 2;
        mainFrameConstraints.weightx = 0;
        mainFrameConstraints.weighty = 0;
        mainFrameConstraints.fill = GridBagConstraints.VERTICAL;
        this.add(new SideBarPanel(), mainFrameConstraints);

        mainFrameConstraints.gridx = 1;
        mainFrameConstraints.gridy = 1;
        mainFrameConstraints.gridwidth = 2;
        mainFrameConstraints.gridheight = 2;
        mainFrameConstraints.weightx = .5;
        mainFrameConstraints.weighty = .5;
        mainFrameConstraints.fill = GridBagConstraints.BOTH;
        this.add(new DisplayPanel(), mainFrameConstraints);

        mainFrameConstraints.gridx = 0;
        mainFrameConstraints.gridy = 0;
        mainFrameConstraints.gridwidth = 3;
        mainFrameConstraints.gridheight = 1;
        mainFrameConstraints.weightx = 1;
        mainFrameConstraints.weighty = 0;
        mainFrameConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(new OptionsPanel(), mainFrameConstraints);

        // Damm java, you confuse me, This is used as a filler component, so that the other components will scale apporpriately. Make sure it is last in grid position.
        mainFrameConstraints.gridx = 2;
        mainFrameConstraints.gridy = 2;
        mainFrameConstraints.weightx = 1;
        mainFrameConstraints.weighty = 1;
        JPanel filler = new JPanel();
        filler.setOpaque(false);
        this.add(filler, mainFrameConstraints);

        this.setVisible(true);
        this.pack();
    }
}