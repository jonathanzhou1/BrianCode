package Level1.BrianGame;


import java.awt.Dimension;
import java.awt.Toolkit;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class Brian
{
    public static BrianMainframe frame;
    public static void main(String[] args)
    {
        frame = new BrianMainframe();
        
        frame.setSize(1200, 900);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(screenSize.getWidth() / 2 - frame.getSize().getWidth() / 2), (int)(screenSize.getHeight() / 2 - frame.getSize().getHeight() / 2));
        
        frame.setResizable(false);
        frame.setTitle("Brian");
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void close()
    {
        frame.dispose();
    }
}
