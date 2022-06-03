package Level1.BrianGame;


import java.awt.*;
public class Minion
{
    int x, y, width, height;
    Rectangle hitBox;
   
    
    int startX; 
    public Minion(int x, int y, int width, int height)
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
    
    public void updateHitbox(int num)
    {
        hitBox.x = num;
    }
}
