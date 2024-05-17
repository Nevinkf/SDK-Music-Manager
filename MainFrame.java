import javax.swing.*;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    JMenuBar menuBar;
    BorderLayout mainFrameLayout;

    MainFrame() throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
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

        JScrollPane sideBarScrollPane = new JScrollPane();
        sideBarScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sideBarScrollPane.setViewportView(new SideBarPanel());

        JScrollPane displayScrollPane = new JScrollPane();
        displayScrollPane.setViewportView(new DisplayPanel());

        JSplitPane sideAndDisplaySplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sideBarScrollPane,
                displayScrollPane);

        this.add(sideAndDisplaySplitPane, BorderLayout.CENTER);

        this.add(new OptionsPanel(), BorderLayout.NORTH);

        this.setVisible(true);
        this.pack();
    }
}