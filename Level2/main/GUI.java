package Level2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Level2.object.Phone;

public class GUI 
{
    GameManager gm;
    Font calibri_25, calibri_80B;
    BufferedImage phoneImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    
    public GUI(GameManager gm)
    {
        this.gm = gm;
        calibri_25 = new Font("Calibri", Font.PLAIN, 25);
        calibri_80B = new Font("Calibri", Font.BOLD, 80);
        Phone phone = new Phone();
        phoneImage = phone.image;
    }

    public void showMessage(String text)
    {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2)
    {
        if (gameFinished)
        {
            String text;
            int textLength;
            int x;
            int y;
            
            g2.setFont(calibri_25);
            g2.setColor(Color.white);
            text = "mmmm... cozy phones...";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gm.screenWidth/2 - textLength/2;
            y = gm.screenHeight/2 - (gm.tileSize * 3);
            g2.drawString(text, x, y);
            
            g2.setFont(calibri_80B);
            g2.setColor(Color.yellow);
            text = "Dunlea.";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gm.screenWidth/2 - textLength/2;
            y = gm.screenHeight/2 + (gm.tileSize * 2);
            g2.drawString(text, x, y);
            
            gm.gameThread = null;
        }
        else
        {
            g2.setFont(calibri_25);
            g2.setColor(Color.white);
            g2.drawImage(phoneImage, gm.tileSize/2, gm.tileSize/2, gm.tileSize, gm.tileSize, null );
            g2.drawString("x " + gm.brian.hasPhone, 70, 55);
            
            //TIME
            //playTime += (double)1/60;
            //g2.drawString("Time: " + playTime, gm.tileSize * 11, 65);
            
            // MESSAGE
            if(messageOn == true)
            {
                g2.setFont(g2.getFont().deriveFont(20));
                g2.drawString(message, 400, gm.tileSize * 5);

                messageCounter++;

                if(messageCounter > 180)
                {
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        }
    }

}
