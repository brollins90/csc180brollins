package mp3player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BPlay implements Runnable {

    Player jzPlayer;
    ActionListener playerListener;

    public BPlay(String fileName, ActionListener playerListener) throws FileNotFoundException, JavaLayerException {
        this.playerListener = playerListener;
        jzPlayer = new Player(new FileInputStream(new File(fileName)));
        
    }

    public void stopSong() {
        jzPlayer.close();
    }

    @Override
    public void run() {
        try {
            jzPlayer.play();
            if (jzPlayer.isComplete()) {
                playerListener.actionPerformed(new ActionEvent(this, PlayerAction.NEXT.ordinal(), ""));
            }
        } catch (JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
