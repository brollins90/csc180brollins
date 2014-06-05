package mp3player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;

//import mp3player.PlayerPanel.BMouseListener;

public class PlayerList<E> extends JList<E> implements Observer {

    private static final long serialVersionUID = 1L;
    private ActionListener playerListener;
    private BPlayer player;

    public PlayerList(ActionListener l, BPlayer player) {
        this.playerListener = l;
        this.player = player;

        this.addListSelectionListener((e) -> {
            int playingIndex = player.songIndex;
            int selectedIndex = this.getSelectedIndex();
//            System.out.println("Selected index: " + this.getSelectedIndex());
//            System.out.println("Selected value: " + this.getSelectedValue());
//            if (e.getClickCount() == 2) {
//                songIndex = this.locationToIndex(e.getPoint());
//            }
            if (selectedIndex >= 0) {
                if (playingIndex == selectedIndex) {
                    if (player.currentlyPlaying) {
                        playerListener.actionPerformed(new ActionEvent(this, PlayerAction.STOP.ordinal(), ""));
                    } else {
                        playerListener.actionPerformed(new ActionEvent(this, PlayerAction.PLAY.ordinal(), ""));
                    }
                } else {
                    playerListener.actionPerformed(new ActionEvent(this, PlayerAction.SETINDEX.ordinal(), String.valueOf(selectedIndex)));
                    playerListener.actionPerformed(new ActionEvent(this, PlayerAction.PLAY.ordinal(), ""));
                }
                repaint();
            }
        });
//        this.addMouseListener(new PlayerListMouseListener());
//        this.addMouseMotionListener(new PlayerListMouseListener());
//        this.addMouseWheelListener(new PlayerListMouseListener());
    }

//    class PlayerListMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
//
//        @Override
//        public void mouseWheelMoved(MouseWheelEvent e) {
////             System.out.println("mouseWheelMoved()");
//        }
//
//        @Override
//        public void mouseDragged(MouseEvent e) {
////             System.out.println("mouseDragged()");
//        }
//
//        @Override
//        public void mouseMoved(MouseEvent e) {
////             System.out.println("mouseMoved()");
//             //repaint();
//        }
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
////             System.out.println("mouseClicked()");
//
//            JList list = (JList) e.getSource();
//            int songIndex = -1;
//            // if (e.getClickCount() == 1) {
//            // songIndex = list.locationToIndex(e.getPoint());
//            // } else
//            if (e.getClickCount() == 2) {
//                songIndex = list.locationToIndex(e.getPoint());
//            }
//            // else if (e.getClickCount() == 3) { // Triple-click
//            // songIndex = list.locationToIndex(e.getPoint());
//            //
//            // }
//
//            if (songIndex >= 0) {
//                playerListener.actionPerformed(new ActionEvent(this, 5, String.valueOf(songIndex)));
//                repaint();
//            }
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//            // System.out.println("mouseEntered()");
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//            // System.out.println("mouseExited()");
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//            // System.out.println("mousePressed()");
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//            // System.out.println("mouseReleased()");
//        }
//
//    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        repaint();
    }

}
