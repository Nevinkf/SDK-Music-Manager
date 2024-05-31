package nevinkf;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.datatransfer.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import com.fasterxml.jackson.databind.ObjectMapper;

import javazoom.jl.decoder.JavaLayerException;


public class DisplayPanel extends JPanel {

    private String[] columnNameList; //Switch to list later so that more columns can be added or removed
    private JTable songTable;
    private JScrollPane jTableScrollPane;
    private JPanel westPanel;
    private JPopupMenu songTablePopupMenu;
    private List<List<Object>> songList;
    private List<File> mp3FileNameList;
    private GridBagConstraints displayPanelConstraints;
    private MainFrame mainFrameHolder;
    private SongHolderPanel testHolderPanel;
    private String songDurationText;

    DisplayPanel(MainFrame mainFrame)
            throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        this.setLayout(new BorderLayout());
        mainFrameHolder = mainFrame;

        displayPanelConstraints = new GridBagConstraints();

        // Used to keep components to the left of the side bar
        westPanel = new JPanel();
        jTableScrollPane = new JScrollPane();
        westPanel.setLayout(new BorderLayout());
        songTablePopupMenu = new JPopupMenu();
        songTablePopupMenu.add("Delete Song");


        // Allow user to drag and drop a file to add to songs folder, check if mp3 file exists, if so throw error at user, else at it to main song folder and playlist at later date

        columnNameList = new String[]{"Song Title", "Artist", "Album", "Track", "Time", "Genre"};
        songList = new ArrayList<List<Object>>();
        mp3FileNameList = new ArrayList<File>();

        setSongTable("mountaineermusicmanager/playlists/songLibary.json");

        westPanel.add(jTableScrollPane, BorderLayout.CENTER);
        
        this.setDropTarget(new DropTarget(this, new DropTargetListener() {

            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                // TODO Auto-generated method stub
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                // TODO Auto-generated method stub
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
                // TODO Auto-generated method stub
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                // TODO Auto-generated method stub
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                // TODO Auto-generated method stub
                dtde.acceptDrop(DnDConstants.ACTION_COPY);

                Transferable transferable = dtde.getTransferable();
                DataFlavor[] testDataFlavors = transferable.getTransferDataFlavors();

                System.out.println(testDataFlavors);

                for (DataFlavor flavor : testDataFlavors) {
                    try {
                        // If the drop data is a list of files
                        if (flavor.isFlavorJavaFileListType()) {
                            List<File> files = (List<File>) transferable.getTransferData(flavor);
                            for (File file : files) {
                                InputStream inputStream = new FileInputStream(file);
                                OutputStream outputStream = new FileOutputStream("mountaineermusicmanager/songs/" + file.getName());
                                
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = inputStream.read(buffer)) != -1) {
                                    outputStream.write(buffer, 0, bytesRead);
                                }

                                inputStream.close();
                                outputStream.close();

                                mainFrame.writeMainJsonFile("mountaineermusicmanager/playlists/songLibary.json");
                                setSongTable("mountaineermusicmanager/playlists/songLibary.json");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            
        }));

        this.add(westPanel, BorderLayout.CENTER);

    }

    public void setSongTable(String jsonFilePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
        File jsonSongFile = new File(jsonFilePath);
        List<HashMap<String, String>> jsonSongList = new ArrayList<>();
        jsonSongList = new ObjectMapper().readerFor(ArrayList.class).readValue(jsonSongFile);

        songList.clear();
        mp3FileNameList.clear();

        for (HashMap<String, String> song : jsonSongList) {
            List<Object> tempList = new ArrayList<Object>();
            tempList.add(song.get("Title"));
            tempList.add(song.get("Artist"));
            tempList.add(song.get("Album"));
            tempList.add(song.get("Track"));
            tempList.add(song.get("Time")); // Figure out how to get time later TIME
            tempList.add(song.get("Genre"));

            mp3FileNameList.add(new File("mountaineermusicmanager/songs/" + song.get("MP3File").toString()));
            songList.add(tempList);
        }

        Object[][] songArray = new Object[songList.size()][6]; // make the column amount adjustable

        for (int i = 0; i < songList.size(); i++) {
            List<Object> sublist = songList.get(i);
            songArray[i] = sublist.toArray(new Object[0]);
        }

        if (songTable != null) {
            songTable.setEnabled(true);
            westPanel.remove(jTableScrollPane);
            jTableScrollPane.remove(songTable);
        }
        

        songTable = new JTable(songArray, columnNameList);
        songTable.setEnabled(false); // Change to allow for editing of metadata
        songTable.setFillsViewportHeight(true);
        songTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        songTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (e.getClickCount() == 2) {
                    mainFrameHolder.changeSong(mp3FileNameList.get(songTable.rowAtPoint(e.getPoint())));
                    try {
                        mainFrameHolder.playSong();
                    } catch (FileNotFoundException | JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                } else if (e.getButton() == e.BUTTON3) {
                    mainFrameHolder.removeFromJsonFile(jsonFilePath, songTable.rowAtPoint(e.getPoint()));

                    try {
                        setSongTable("mountaineermusicmanager/playlists/songLibary.json");
                    } catch (CannotReadException | IOException | TagException | ReadOnlyFileException
                            | InvalidAudioFrameException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    // songTablePopupMenu.show(jTableScrollPane, e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
            }
            
        });
       
        jTableScrollPane = new JScrollPane(songTable);
        jTableScrollPane.setViewportView(songTable);
        jTableScrollPane.repaint();
        jTableScrollPane.revalidate();

        westPanel.add(jTableScrollPane);
        westPanel.repaint();
        westPanel.revalidate();

    }

    public List<File> getMP3FileNameList() {
        return mp3FileNameList;
    }

}
