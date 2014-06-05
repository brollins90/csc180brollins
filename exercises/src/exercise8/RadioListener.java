package exercise8;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javazoom.jl.decoder.JavaLayerException;
import radio.RadioPlayer;
import radio.RadioPlayer.Playback;

/**
 * This is a program that will test the Observer / Observable interface / class
 * @author Blake Rollins
 *
 */
public class RadioListener implements Observer {

    RadioPlayer player;
    String radioUrlString = "http://stream.xmission.com:8000/kcpw";

    public static void main(String[] args) {
        RadioListener l = new RadioListener();
        l.go();
    }

    void go() {
        player = new RadioPlayer();
        player.addObserver(this);        
        startPlaying(radioUrlString);        
    }
    
    void startPlaying(String u) {
        try {
            this.player.playRadioStream(new URL(u));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
        if (arg instanceof RadioPlayer.Playback) {
            RadioPlayer.Playback temp = (RadioPlayer.Playback) arg;
            if (temp == Playback.STOPPED) {        
                startPlaying(radioUrlString);
            }
        }
    }
}
