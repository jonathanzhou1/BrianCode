package Level2.main;

import javax.swing.JFrame;

public class Main 
{
    public static void joe()
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        
        GameManager GameManager = new GameManager();
        window.add(GameManager);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        GameManager.setupGame();
        GameManager.startGameThread();
    }
}