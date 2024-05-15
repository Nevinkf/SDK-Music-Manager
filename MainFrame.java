import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    
    JMenuBar menuBar;
    BorderLayout mainFrameLayout;

    MainFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        mainFrameLayout = new BorderLayout();

        this.setLayout(mainFrameLayout);

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        this.setJMenuBar(menuBar);

        JScrollPane SideBarScrollPane = new JScrollPane();
        SideBarScrollPane.setViewportView(new SideBarPanel());
        this.add(SideBarScrollPane, BorderLayout.WEST);

        this.add(new DisplayPanel(), BorderLayout.CENTER);

        this.add(new OptionsPanel(), BorderLayout.NORTH);

        this.setVisible(true);
        this.pack();
    }
}