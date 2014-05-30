package mp3player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BPlay implements Runnable {

    Player jzPlayer;
    public BPlay(String fileName) throws FileNotFoundException, JavaLayerException {
        jzPlayer = new Player(new FileInputStream(new File(fileName)));        
    }
    
    public void stopSong() {
        jzPlayer.close();
    }
    
    @Override
    public void run() {
        try {
            jzPlayer.play();
        } catch (JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
