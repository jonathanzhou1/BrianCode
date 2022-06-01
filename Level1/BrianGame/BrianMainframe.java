package Level1.BrianGame;


import java.awt.Color;
//import javax.swing.ImageIcon;
public class BrianMainframe extends javax.swing.JFrame
{
    //ImageIcon background = new ImageIcon("background.jfif");
    public BrianMainframe()
    {
        GamePanel panel1 = new GamePanel();
        panel1.setLocation(0,0);
        panel1.setSize(this.getSize());
        //panel1.setBackground(Color.WHITE);
        panel1.setVisible(true);
        this.add(panel1);
        
        addKeyListener(new KeyChecker(panel1));
    }
}
