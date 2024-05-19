package nevinkf;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MountaineerMusicManager {

    public static void main(String[] arg) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, JavaLayerException {
        new MainFrame();
    }

}