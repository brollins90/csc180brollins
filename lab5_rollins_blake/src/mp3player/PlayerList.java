package mp3player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JList;

//import mp3player.PlayerPanel.BMouseListener;

public class PlayerList<E> extends JList<E> {

    private static final long serialVersionUID = 1L;
    private ActionListener playerListener;

    public PlayerList(ActionListener l, BPlayer inPlayer) {
        this.playerListener = l;

        this.addMouseListener(new PlayerListMouseListener());
        this.addMouseMotionListener(new PlayerListMouseListener());
        this.addMouseWheelListener(new PlayerListMouseListener());
    }

    class PlayerListMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
             System.out.println("mouseWheelMoved()");
        }

        @Override
        public void mouseDragged(MouseEvent e) {
             System.out.println("mouseDragged()");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
             System.out.println("mouseMoved()");
        }

        @Override
        public void mouseClicked(MouseEvent e) {
             System.out.println("mouseClicked()");

            JList list = (JList) e.getSource();
            int songIndex = -1;
            // if (e.getClickCount() == 1) {
            // songIndex = list.locationToIndex(e.getPoint());
            // } else
            if (e.getClickCount() == 2) {
                songIndex = list.locationToIndex(e.getPoint());
            }
            // else if (e.getClickCount() == 3) { // Triple-click
            // songIndex = list.locationToIndex(e.getPoint());
            //
            // }

            if (songIndex >= 0) {
                playerListener.actionPerformed(new ActionEvent(this, 5, String.valueOf(songIndex)));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // System.out.println("mouseEntered()");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // System.out.println("mouseExited()");
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // System.out.println("mousePressed()");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // System.out.println("mouseReleased()");
        }

    }

}
