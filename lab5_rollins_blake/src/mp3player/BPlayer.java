package mp3player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BPlayer extends Observable {

    // Player play;
    private int songIndex;
    private BPlay currentSong;
    private Thread currentSongThread;
    private boolean currentlyPlaying;
    public PlayerListModel<String> listModel = new PlayerListModel<String>();
    private JFileChooser chooser;
    private PlayerGUI gui;

    public BPlayer() {

        this.songIndex = 0;
        gui = new PlayerGUI(new BListener(), this);
        this.addObserver(gui.panel);

        this.currentlyPlaying = false;


        // listModel.add("c:\\01-01- Die To Save You.mp3");
        // listModel.add("c:\\01-02- Theres No Going Back.mp3");
        // listModel.add("c:\\01-03- Walking Away.mp3");
        // listModel.add("c:\\01-04- Gunfight.mp3");


        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3", "mp3");
        chooser.setFileFilter(filter);


    }

    private void addSong(String filename) {
        listModel.add(filename);
        songIndex = listModel.getSize() - 1;
        startSong();
    }

    public PlayerListModel<String> getListModel() {
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
            currentSong = new BPlay(filename);
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
            setupNewSongThread(listModel.getElementAt(songIndex));
        }
    }

    private void startSongAtIndex(int index) {
        if (index >= 0 && index < listModel.getSize()) {
            songIndex = index;
            setupNewSongThread(listModel.getElementAt(songIndex));
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
            System.out.println("actionPerformed()");
            System.out.println(arg0.getActionCommand());

            PlayerAction current = PlayerAction.values()[arg0.getID()];
            // switch (arg0.getID()) {

            if (current == PlayerAction.NEXT) {
                nextSong();
            } else if (current == PlayerAction.OPEN) {

                int returnVal = chooser.showOpenDialog(gui.getFrame());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String newFileName = chooser.getSelectedFile().getAbsolutePath();
                    // System.out.println("You chose: " + newFileName);
                    addSong(newFileName);
                }
            } else if (current == PlayerAction.PAUSE) {
                //
            } else if (current == PlayerAction.PLAY) {
                startSong();
            } else if (current == PlayerAction.PREVIOUS) {
                previousSong();
            } else if (current == PlayerAction.SETINDEX) {
                startSongAtIndex(Integer.parseInt(arg0.getActionCommand()));
            } else if (current == PlayerAction.STOP) {
                stopSong();
            }
            setChanged();
            notifyObservers(arg0);

        }

    }



}
