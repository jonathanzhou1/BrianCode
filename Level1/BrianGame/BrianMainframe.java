package Level1.BrianGame;


import java.awt.Color;
public class BrianMainframe extends javax.swing.JFrame
{
    public BrianMainframe()
    {
        GamePanel panel1 = new GamePanel();
        panel1.setLocation(0,0);
        panel1.setSize(this.getSize());
        panel1.setVisible(true);
        this.add(panel1);
        
        addKeyListener(new KeyChecker(panel1));
    }
}
