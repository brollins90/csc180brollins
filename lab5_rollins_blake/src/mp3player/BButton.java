package mp3player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BButton implements Observer {

    public boolean hovered;

    Color BUTTON_COLOR = new Color(84, 84, 84);
    Color BUTTON_COLOR_HOVER = new Color(119, 119, 119);
    Color BUTTON_ICON_COLOR = new Color(0, 127, 255);
    Color BUTTON_ICON_HOVER_COLOR = new Color(56, 176, 222);

    Point location, cachedLastClick, cachedLastMove;
    int width, height, radius, originX, originY;
    ArrayList<BPoly> polys;
    Color polyColor;
    protected BMouseListener mList;

    ActionListener playerListener;
    PlayerAction buttonAction;

    public BButton(BMouseListener mList, Point location, int w, int h, PlayerAction action) {
        this.mList = mList;
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

    }

    public void paintComponent(Graphics g) {

        Color backgroundColor = (hovered) ? BUTTON_COLOR_HOVER : BUTTON_COLOR;
        Color foregroundCOlor = (hovered) ? BUTTON_ICON_HOVER_COLOR : BUTTON_ICON_COLOR;

        for (int i = 0; i < radius; i = i + 4) {
            g.setColor(new Color((backgroundColor.getRed() - i) % 255, (backgroundColor.getGreen() - i) % 255, (backgroundColor.getBlue() - i) % 255));
            g.fillOval(location.x + (i / 2), location.y + (i / 2), width - i, height - i);
        }
        g.setColor(foregroundCOlor);
        for (Polygon p : this.getPolys()) {
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

    @Override
    public void update(Observable o, Object arg) {

        int changed = 0;
        Point lastMove = mList.getLastMove();

        if (cachedLastMove == null || cachedLastMove != lastMove) {
            cachedLastMove = lastMove;
            changed = !(hovered == contains(lastMove)) ? changed + 1 : changed;
            hovered = contains(lastMove);
        }

        Point lastClick = mList.getLastClick();

        if (cachedLastClick == null || cachedLastClick != lastClick) {
            cachedLastClick = lastClick;
            ActionEvent buttonPress = null;

            if (contains(lastClick)) {
                buttonPress = new ActionEvent(this, buttonAction.ordinal(), "");

                if (buttonPress != null) {
                    playerListener.actionPerformed(buttonPress);
                }
            }
        }

    }


}
