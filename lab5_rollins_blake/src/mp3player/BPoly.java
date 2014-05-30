package mp3player;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;

public class BPoly {

    ArrayList<Integer> xVals;
    ArrayList<Integer> yVals;
    
    public BPoly() {
        xVals = new ArrayList<Integer>();
        yVals = new ArrayList<Integer>();
    }
    
    public void addPoint(Point p) {
        xVals.add(p.x);
        yVals.add(p.y);
    }
    
    
    
    public Polygon getPolygon(Point translate) {
        return new Polygon(getIntsFromListWithTranslate(xVals, translate.x), getIntsFromListWithTranslate(yVals, translate.y), xVals.size());
    }
    
    public int[] getIntsFromListWithTranslate(ArrayList<Integer> a, int translateInt) {
        int[] ret = new int[a.size()];
        
        Iterator<Integer> it = a.iterator();
        for (int i = 0; i < a.size(); i++) {
            ret[i] = it.next().intValue() + translateInt;
        }        
        return ret;
    }
}
