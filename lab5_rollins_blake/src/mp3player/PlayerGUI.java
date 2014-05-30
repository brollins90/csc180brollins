package mp3player;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PlayerGUI {

    private ActionListener playerListener;
    PlayerPanel panel;
    private BPlayer player;
    private JFrame frame;

    public PlayerGUI(ActionListener l, BPlayer inPlayer) {
        this.playerListener = l;
        this.player = inPlayer;


        frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 800));
        panel = new PlayerPanel(this.playerListener, this.player);

        PlayerList<String> list = new PlayerList<String>(this.playerListener, this.player);
        list.setModel(this.player.listModel);

        

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
