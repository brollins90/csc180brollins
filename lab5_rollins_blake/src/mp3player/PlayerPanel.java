package mp3player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import mp3player.BPlayer.BListener;

public class PlayerPanel extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;
    BButton prev, play, stop, next, open, volume;
    ArrayList<BButton> buttons;
    Color BACKGROUND_COLOR = new Color(187, 187, 187);
    private BPlayer player;

    private ActionListener playerListener;
    private BMouseListener mList;

    public PlayerPanel(ActionListener l, BPlayer inPlayer, BMouseListener mList) {
        this.mList = mList;
        mList.addObserver(this);

        this.playerListener = l;
        player = inPlayer;
        buttons = new ArrayList<BButton>();

        prev = new BButton(this.mList, new Point(0, 10), 80, 80, PlayerAction.PREVIOUS);
        mList.addObserver(prev);
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


        play = new BButton(this.mList, new Point(80, 10), 80, 80, PlayerAction.PLAY);
        mList.addObserver(play);
        BPoly play1 = new BPoly();
        play1.addPoint(new Point(30, 10));
        play1.addPoint(new Point(70, 40));
        play1.addPoint(new Point(30, 70));
        play.addPoly(play1);
        play.addPlayerListener(this.playerListener);
        buttons.add(play);


        stop = new BButton(this.mList, new Point(160, 10), 80, 80, PlayerAction.STOP);
        mList.addObserver(stop);
        BPoly stop1 = new BPoly();
        stop1.addPoint(new Point(20, 20));
        stop1.addPoint(new Point(60, 20));
        stop1.addPoint(new Point(60, 60));
        stop1.addPoint(new Point(20, 60));
        stop.addPoly(stop1);
        stop.addPlayerListener(this.playerListener);
        buttons.add(stop);


        next = new BButton(this.mList, new Point(240, 10), 80, 80, PlayerAction.NEXT);
        mList.addObserver(next);
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


        open = new BButton(this.mList, new Point(320, 10), 80, 80, PlayerAction.OPEN);
        mList.addObserver(open);
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


        volume = new BButtonRect(this.mList, new Point(40, 120), 240, 80, PlayerAction.SETVOLUME);
        mList.addObserver(volume);
        BPoly volume1 = new BPoly();
        volume1.addPoint(new Point(20, 20));
        volume1.addPoint(new Point(120, 20));
        volume1.addPoint(new Point(120, 60));
        volume1.addPoint(new Point(20, 60));
        volume.addPoly(volume1);
        volume.addPlayerListener(this.playerListener);
        buttons.add(volume);

        // this.addMouseListener(l);

        this.addMouseListener(mList);
        this.addMouseMotionListener(mList);
        this.addMouseWheelListener(mList);

    }

    public void blake1() {
        prev.setHover();
    }

    @Override
    public void paintComponent(Graphics g) {

//        System.out.println("Painting panel");
//        System.out.println("isPlaying: " + this.player.isPlaying());

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Previous
        prev.paintComponent(g);

        // Play / Stop
//        BButton playStop = (this.player.isPlaying()) ? stop : play;
//        playStop.paintComponent(g);
        stop.paintComponent(g);
        play.paintComponent(g);
        // Next
        next.paintComponent(g);

        // Open
        open.paintComponent(g);
        // Volume bar
        volume.paintComponent(g);

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        repaint();
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        super.addMouseMotionListener(l);
    }

    @Override
    public synchronized void addMouseWheelListener(MouseWheelListener l) {
        super.addMouseWheelListener(l);
    }

}
