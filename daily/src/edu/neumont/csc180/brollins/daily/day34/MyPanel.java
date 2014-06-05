package edu.neumont.csc180.brollins.daily.day34;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

public class MyPanel extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;

    private MouseController controller;

    public MyPanel(MouseController controller) {
        this.controller = controller;
        this.addMouseListener(controller);
        this.addMouseMotionListener(controller);
        controller.addObserver(this);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        Point lastClick = controller.getLastClick();
        Point lastMove = controller.getLastMove();

        g.setColor(Color.BLACK);
        g.drawString("Last Click: " + lastClick.getX() + ", " + lastClick.getY(), 10, 20);
        g.drawString("Last Move: " + lastMove.getX() + ", " + lastMove.getY(), 10, 40);
    }

    @Override
    public void update(Observable o, Object arg) {
        //if (arg instanceof MouseEvent) {
            this.repaint();
        //}

    }

}
