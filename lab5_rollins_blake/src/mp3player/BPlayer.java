package mp3player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port.Info;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BPlayer extends Observable {

    // Player play;
    public int songIndex;
    private BPlay currentSong;
    private Thread currentSongThread;
    boolean currentlyPlaying;
    public PlayerListModel<File> listModel = new PlayerListModel<File>();
    private JFileChooser chooser;
    private PlayerGUI gui;

    public BPlayer() {

        this.songIndex = 0;
        gui = new PlayerGUI(new BListener(), this);
        this.addObserver(gui.panel);
        this.addObserver(gui.list);

        this.currentlyPlaying = false;


        // listModel.add("c:\\01-01- Die To Save You.mp3");
        // listModel.add("c:\\01-02- Theres No Going Back.mp3");
        // listModel.add("c:\\01-03- Walking Away.mp3");
        // listModel.add("c:\\01-04- Gunfight.mp3");


        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3", "mp3");
        chooser.setFileFilter(filter);


    }

    private void addSong(File file) {
        listModel.add(file);
        songIndex = listModel.getSize() - 1;
        startSong();
    }

    public PlayerListModel<File> getListModel() {
        return this.listModel;
    }

    public boolean isPlaying() {
        return this.currentlyPlaying;
    }

    private void nextSong() {

        if (songIndex < listModel.getSize() - 1) {
            songIndex++;
            startSong();
        }
    }

    private void previousSong() {

        if (songIndex > 0) {
            songIndex--;
            startSong();
        }
    }

    private void setupNewSongThread(String filename) {
        stopSong();

        try {

            currentSong = new BPlay(filename, new BListener());
            currentSongThread = new Thread(currentSong);
            currentSongThread.start();
            currentlyPlaying = true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(gui.getFrame(), e.getMessage());
            stopSong();
        }
    }

    private void startSong() {
        if (songIndex >= 0 && listModel.getSize() > 0) {
            setupNewSongThread(listModel.getElementAt(songIndex).getAbsolutePath());
            
        }
    }

    private void startSongAtIndex(int index) {
        if (index >= 0 && index < listModel.getSize()) {
            songIndex = index;
            setupNewSongThread(listModel.getElementAt(songIndex).getAbsolutePath());
        }
    }

    private void stopSong() {
        try {
            currentSong.stopSong();
        } catch (Exception e) {

        } finally {
            currentlyPlaying = false;
        }
    }

    class BListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
//            System.out.println("actionPerformed()");
//            System.out.println(arg0.getActionCommand());

            PlayerAction current = PlayerAction.values()[arg0.getID()];
            // switch (arg0.getID()) {

            if (current == PlayerAction.NEXT) {
                nextSong();
            } else if (current == PlayerAction.OPEN) {

                int returnVal = chooser.showOpenDialog(gui.getFrame());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String newFileName = chooser.getSelectedFile().getAbsolutePath();
                    // System.out.println("You chose: " + newFileName);
                    addSong(new File(newFileName));
                }
            } else if (current == PlayerAction.PAUSE) {
                //
            } else if (current == PlayerAction.PLAY) {
                if (!currentlyPlaying) {
                startSong();
                }
            } else if (current == PlayerAction.PREVIOUS) {
                previousSong();
            } else if (current == PlayerAction.SETINDEX) {
                startSongAtIndex(Integer.parseInt(arg0.getActionCommand()));
            } else if (current == PlayerAction.STOP) {
                if (currentlyPlaying) {stopSong();}
            } else if (current == PlayerAction.SETVOLUME) {
                setVolume(Float.parseFloat(arg0.getActionCommand()));
            }
            setChanged();
            notifyObservers(arg0);

        }

        private void setVolume(float volume) {
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



}
