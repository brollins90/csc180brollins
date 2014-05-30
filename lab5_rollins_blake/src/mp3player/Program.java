package mp3player;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port.Info;

public class Program {
    public static void main(String[] args) {
        BPlayer p = new BPlayer();
//        p.go();
    }

    public static void setLineGain(float volume) {
        Info source = Info.SPEAKER;

        if (AudioSystem.isLineSupported(source)) {
            try {
                Line speaker = AudioSystem.getLine(source);
                speaker.open();
                FloatControl volumeControl = (FloatControl) speaker.getControl(FloatControl.Type.VOLUME);
                volumeControl.setValue(volume);
                speaker.close();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }
}
