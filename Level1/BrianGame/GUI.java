package Level1.BrianGame;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class GUI 
{
    GamePanel gp;
    Graphics2D g2;
    BufferedImage titleScreenImage, downwardArrow;
    public int commandNum = 0;
    
    public GUI(GamePanel gp)
    {
        this.gp = gp;
        try {
            titleScreenImage = ImageIO.read(getClass().getResourceAsStream("/Level1/titleScreen.png"));
            downwardArrow = ImageIO.read(getClass().getResourceAsStream("/Level1/downwardArrow.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
       this.g2 = g2;
        
        // TITLE STATE
       if (gp.gameState == gp.titleState)
       {
           drawTitleScreen();    
       }
    }
    
    public void drawTitleScreen()
    {
        //DUNGEON TITLE SCREEN

        g2.drawImage(titleScreenImage, 0, 0, 1200, 875, null);
        
        if (commandNum == 0)
        {
            g2.drawImage(downwardArrow, 342, 670, 50, 50, null);
        }
        if(commandNum == 1)
        {
            g2.drawImage(downwardArrow, 802, 670, 50, 50, null);
        }
    }
}