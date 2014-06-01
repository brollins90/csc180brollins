package mp3player;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;

public class PlayerGUI {

    private ActionListener playerListener;
    PlayerPanel panel;
    private BPlayer player;
    private JFrame frame;
    private BMouseListener mList;

    public PlayerGUI(ActionListener l, BPlayer inPlayer) {
        this.playerListener = l;
        this.player = inPlayer;


        frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 400));
        this.mList = new BMouseListener();
        
        panel = new PlayerPanel(this.playerListener, this.player, this.mList);

        PlayerList<File> list = new PlayerList<File>(this.playerListener, this.mList);
        list.setModel(this.player.listModel);
        PlayerListCellRenderer rend = new PlayerListCellRenderer(this.player);
        list.setCellRenderer(rend);

        

        frame.setLayout(new GridLayout(1, 2));
        frame.add(panel);
        frame.add(list);


        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public PlayerPanel getPanel() {
        return panel;
    }

    public Component getFrame() {
        return this.frame;
    }


}
