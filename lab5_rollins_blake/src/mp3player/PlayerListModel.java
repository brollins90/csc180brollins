package mp3player;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class PlayerListModel<E> extends AbstractListModel<E> {

    private static final long serialVersionUID = 1L;
    private ArrayList<E> backingList = new ArrayList<E>();

    public void add(E s) {
        backingList.add(s);
        fireIntervalAdded(this, backingList.size() - 1, backingList.size() - 1);
    }

    @Override
    public E getElementAt(int index) {
        return backingList.get(index);
    }

    @Override
    public int getSize() {
        return backingList.size();
    }

}
