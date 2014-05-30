package mp3player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;
    BButton prev, play, stop, next, open;
    ArrayList<BButton> buttons;
    Color BACKGROUND_COLOR = new Color(187, 187, 187);
    private BPlayer player;

    private ActionListener playerListener;

    public PlayerPanel(ActionListener l, BPlayer inPlayer) {
        this.playerListener = l;
        player = inPlayer;
        buttons = new ArrayList<BButton>();

        prev = new BButton(new Point(0, 10), 80, 80, PlayerAction.PREVIOUS);
        BPoly prev1 = new BPoly();
        prev1.addPoint(new Point(10, 20));
        prev1.addPoint(new Point(15, 20));
        prev1.addPoint(new Point(15, 60));
        prev1.addPoint(new Point(10, 60));
        prev.addPoly(prev1);
        BPoly prev2 = new BPoly();
        prev2.addPoint(new Point(15, 40));
        prev2.addPoint(new Point(40, 20));
        prev2.addPoint(new Point(40, 60));
        prev.addPoly(prev2);
        BPoly prev3 = new BPoly();
        prev3.addPoint(new Point(40, 40));
        prev3.addPoint(new Point(65, 20));
        prev3.addPoint(new Point(65, 60));
        prev.addPoly(prev3);
        prev.addPlayerListener(this.playerListener);
        buttons.add(prev);


        play = new BButton(new Point(80, 10), 80, 80, PlayerAction.PLAY);
        BPoly play1 = new BPoly();
        play1.addPoint(new Point(30, 10));
        play1.addPoint(new Point(70, 40));
        play1.addPoint(new Point(30, 70));
        play.addPoly(play1);
        play.addPlayerListener(this.playerListener);
        buttons.add(play);


        stop = new BButton(new Point(80, 10), 80, 80, PlayerAction.STOP);
        BPoly stop1 = new BPoly();
        stop1.addPoint(new Point(20, 20));
        stop1.addPoint(new Point(60, 20));
        stop1.addPoint(new Point(60, 60));
        stop1.addPoint(new Point(20, 60));
        stop.addPoly(stop1);
        stop.addPlayerListener(this.playerListener);
        buttons.add(stop);


        next = new BButton(new Point(160, 10), 80, 80, PlayerAction.NEXT);
        BPoly next1 = new BPoly();
        next1.addPoint(new Point(15, 20));
        next1.addPoint(new Point(40, 40));
        next1.addPoint(new Point(15, 60));
        next.addPoly(next1);
        BPoly next2 = new BPoly();
        next2.addPoint(new Point(40, 20));
        next2.addPoint(new Point(65, 40));
        next2.addPoint(new Point(40, 60));
        next.addPoly(next2);
        BPoly next3 = new BPoly();
        next3.addPoint(new Point(65, 20));
        next3.addPoint(new Point(70, 20));
        next3.addPoint(new Point(70, 60));
        next3.addPoint(new Point(65, 60));
        next.addPoly(next3);
        next.addPlayerListener(this.playerListener);
        buttons.add(next);


        open = new BButton(new Point(240, 10), 80, 80, PlayerAction.OPEN);
        BPoly open1 = new BPoly();
        open1.addPoint(new Point(20, 25));
        open1.addPoint(new Point(60, 25));
        open1.addPoint(new Point(60, 60));
        open1.addPoint(new Point(20, 60));
        open1.addPoint(new Point(20, 20));
        open1.addPoint(new Point(40, 20));
        open1.addPoint(new Point(40, 25));
        open.addPoly(open1);
        open.addPlayerListener(this.playerListener);
        buttons.add(open);

//        this.addMouseListener(l);

        this.addMouseListener(new BMouseListener());
        this.addMouseMotionListener(new BMouseListener());
        this.addMouseWheelListener(new BMouseListener());

    }

    public void blake1() {
        prev.setHover();
    }

    @Override
    public void paintComponent(Graphics g) {

        System.out.println("Painting panel");
        System.out.println("isPlaying: " + this.player.isPlaying());
        
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Previous
        prev.paintComponent(g);

        // Play / Stop
        BButton playStop = (this.player.isPlaying()) ? stop : play;
        playStop.paintComponent(g);

        // Next
        next.paintComponent(g);

        // Open
        open.paintComponent(g);
        // Volume bar

    }
    @Override
    public void update(Observable arg0, Object arg1) {
        
        System.out.println("panel update has been called upon.");
        
        if (arg1 instanceof String && arg1.equals("play")) {
//            isPlaying = true;
        }

        if (arg1 instanceof String && arg1.equals("stop")) {
//            isPlaying = false;
        }
        repaint();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        // TODO Auto-generated method stub
        super.addMouseListener(l);
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        // TODO Auto-generated method stub
        super.addMouseMotionListener(l);
    }

    @Override
    public synchronized void addMouseWheelListener(MouseWheelListener l) {
        // TODO Auto-generated method stub
        super.addMouseWheelListener(l);
    }

    class BMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {


        public BMouseListener() {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            System.out.println("mouseWheelMoved()");
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // System.out.println("mouseDragged()");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int changed = 0;
            // System.out.println("mouseMoved()");
            Point mouse = e.getPoint();

            for (BButton b : buttons) {
                changed = !(b.hovered == b.contains(mouse)) ? changed + 1 : changed;
                b.hovered = b.contains(mouse);
            }
            // System.out.println(changed);
            if (changed > 0) {
                repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // System.out.println("mouseClicked()");
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
            Point mouse = e.getPoint();
            
            ActionEvent buttonPress = null;

            if (prev.contains(mouse)) {
                buttonPress = new ActionEvent(this, PlayerAction.PREVIOUS.ordinal(), "");
            }

            if (player.isPlaying()) {
                if (stop.contains(mouse)) {
                    buttonPress = new ActionEvent(this, PlayerAction.STOP.ordinal(), "");
                }
            } else {
                if (play.contains(mouse)) {
                    buttonPress = new ActionEvent(this, PlayerAction.PLAY.ordinal(), "");
                }
            }

            if (next.contains(mouse)) {
                buttonPress = new ActionEvent(this, PlayerAction.NEXT.ordinal(), "");
            }

            if (open.contains(mouse)) {
                buttonPress = new ActionEvent(this, PlayerAction.OPEN.ordinal(), "");
            }

            if (buttonPress != null) {
                playerListener.actionPerformed(buttonPress);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
     //       System.out.println("mouseReleased()");
        }

    }




}
