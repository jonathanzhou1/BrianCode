package Level1.BrianGame;


/**
 * Write a description of class Wall here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
public class Wall
{
    int x, y, width, height;
    Rectangle hitBox;
    
    int startX; 
    public Wall(int x, int y, int width, int height)
    {
        this.x = x;
        startX = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        hitBox = new Rectangle(x, y, width, height);
    }
    /**
    public void draw(Graphics2D gtd)
    {
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x, y, width, height);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(x + 1, y + 1, width - 2, height - 2);
    }
    **/
    public int set(int cameraX)
    {
        x = startX - cameraX;
        hitBox.x = x;
        
        return x;
    }
}
