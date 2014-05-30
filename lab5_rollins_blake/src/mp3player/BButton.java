package mp3player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import mp3player.PlayerList.PlayerListMouseListener;

public class BButton {

    public boolean hovered;
    
    Color BUTTON_COLOR = new Color(84,84,84);
    Color BUTTON_COLOR_HOVER = new Color(119,119,119);
    Color BUTTON_ICON_COLOR = new Color(0,127,255);
    Color BUTTON_ICON_HOVER_COLOR = new Color(56,176,222);
    
    Point location;
    int width, height, radius, originX, originY;
    ArrayList<BPoly> polys;
    Color polyColor;

    private MouseListener mListener;
    private MouseMotionListener mmListener;
    private MouseWheelListener mwListener;
    ActionListener playerListener;
    PlayerAction buttonAction;
    
    public BButton(Point location, int w, int h, PlayerAction action) {
        this.buttonAction = action;
        hovered = false;
        this.location = location;
        this.width = w;
        this.height = h;
        this.radius = w / 2;
        this.originX = location.x + radius;
        this.originY = location.y + radius;
        this.polys = new ArrayList<BPoly>();
        this.polyColor = BUTTON_ICON_COLOR;
//
//        this.addMouseListener(new BMouseListener());
//        this.addMouseMotionListener(new BMouseListener());
//        this.addMouseWheelListener(new BMouseListener());
    }

    public void paintComponent(Graphics g) {

        Color backgroundColor = (hovered) ? BUTTON_COLOR_HOVER : BUTTON_COLOR;
        Color foregroundCOlor = (hovered) ? BUTTON_ICON_HOVER_COLOR : BUTTON_ICON_COLOR;
        
        for (int i = 0; i < radius;i = i+4){
            g.setColor(new Color((backgroundColor.getRed() - i) % 255,(backgroundColor.getGreen() - i) % 255,(backgroundColor.getBlue() - i) % 255));    
            g.fillOval(location.x + (i / 2), location.y + (i / 2), width - i, height - i);
        }
        g.setColor(foregroundCOlor);
        for(Polygon p : this.getPolys()) {
            g.fillPolygon(p);
        }
    }
    
    public void setHover() {
        this.polyColor = BUTTON_ICON_HOVER_COLOR;
    }
    
    public void removeHover() {
        this.polyColor = BUTTON_ICON_COLOR;
    }
    
    public boolean contains(Point p) {
        return (Math.pow((p.x - originX), 2) + Math.pow((p.y - originY), 2) < Math.pow(radius, 2)); 
    }

    public void addPoly(BPoly p) {
        this.polys.add(p);
    }

    public Polygon[] getPolys() {
        Polygon[] ret = new Polygon[this.polys.size()];

        for (int i = 0; i < this.polys.size(); i++) {
            ret[i] = this.polys.get(i).getPolygon(location);
        }

        return ret;
    }
    public void addPlayerListener(ActionListener l) {
        this.playerListener = l;
    }

    public synchronized void addMouseListener(MouseListener l) {
        this.mListener = l;
    }

    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        this.mmListener = l;
    }

    public synchronized void addMouseWheelListener(MouseWheelListener l) {
        this.mwListener = l;
    }

//    class BMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
//
//
//        public BMouseListener() {
//
//        }
//
//        @Override
//        public void mouseWheelMoved(MouseWheelEvent e) {
//            System.out.println("mouseWheelMoved()");
//        }
//
//        @Override
//        public void mouseDragged(MouseEvent e) {
//             System.out.println("mouseDragged()");
//        }
//
//        @Override
//        public void mouseMoved(MouseEvent e) {
//            int changed = 0;
//             System.out.println("mouseMoved()");
//            Point mouse = e.getPoint();
//
////            for (BButton b : buttons) {
//                changed = !(hovered == contains(mouse)) ? changed + 1 : changed;
//                hovered = contains(mouse);
////            }
//            // System.out.println(changed);
//            if (changed > 0) {
//                playerListener.actionPerformed(new ActionEvent(this, PlayerAction.REPAINT.ordinal(), ""));
//            }
//        }
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//             System.out.println("mouseClicked()");
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//             System.out.println("mouseEntered()");
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//             System.out.println("mouseExited()");
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//             System.out.println("mousePressed()");
//            Point mouse = e.getPoint();
//            
//            ActionEvent buttonPress = null;
//
//            if (contains(mouse)) {
//                buttonPress = new ActionEvent(this, buttonAction.ordinal(), "");
//            }
////
////            if (player.isPlaying()) {
////                if (stop.contains(mouse)) {
////                    buttonPress = new ActionEvent(this, getAction().ordinal(), "");
////                }
////            } else {
////                if (play.contains(mouse)) {
////                    buttonPress = new ActionEvent(this, getAction().ordinal(), "");
////                }
////            }
////
////            if (next.contains(mouse)) {
////                buttonPress = new ActionEvent(this, getAction().ordinal(), "");
////            }
////
////            if (open.contains(mouse)) {
////                buttonPress = new ActionEvent(this, getAction().ordinal(), "");
////            }
//
//            if (buttonPress != null) {
//                playerListener.actionPerformed(buttonPress);
//            }
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//            System.out.println("mouseReleased()");
//        }
//
//    }

}
