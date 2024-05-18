package nevinkf;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

public class MountaineerMusicManager {

    public static void main(String[] arg) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        new MainFrame();
    }
}