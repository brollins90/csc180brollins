package edu.neumont.csc180.brollins.daily.day34;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;

public class MouseController extends Observable implements MouseListener, MouseMotionListener{

    private Point lastMove;
    private Point lastClick;
    
    public MouseController() {
        lastMove = new Point(0,0);
        lastClick = new Point(0,0);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        this.lastMove = e.getLocationOnScreen();
        setChanged();
        notifyObservers(e);        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.lastClick = e.getLocationOnScreen();
        setChanged();
        notifyObservers(e);     
    }
    
    
    public Point getLastClick() {
        return this.lastClick;
    }
    
    public Point getLastMove() {
        return this.lastMove;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
