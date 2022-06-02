package Level1.BrianGame;

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
    public int set(int cameraX)
    {
        x = startX - cameraX;
        hitBox.x = x;
        
        return x;
    }
}
