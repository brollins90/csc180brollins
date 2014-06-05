package edu.neumont.csc180.brollins.daily.day34;

import javax.swing.JFrame;

public class Program {
    public static void main(String[] args) {
        JFrame frame = new JFrame("My Frame!");
        
        MouseController controller = new MouseController();
        frame.addMouseListener(controller);
        frame.addMouseMotionListener(controller);
        
        MyPanel p = new MyPanel(controller);  
        
        
        
        frame.add(p);
        
        
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        while (true) {
//            p.repaint();
//
////            try {
////                Thread.sleep(5);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////
////            }
//        }
    }
}
