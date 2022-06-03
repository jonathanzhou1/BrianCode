package Level2.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Level2.main.GameManager;

public class ParentObject 
{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GameManager gm) {
        int screenX = worldX - gm.brian.worldX + gm.brian.screenX;
        int screenY = worldY - gm.brian.worldY + gm.brian.screenY;

        // STOP MOVING CAMERA
        if(gm.brian.worldX < gm.brian.screenX) {
            screenX = worldX;
        }
        if(gm.brian.worldY < gm.brian.screenY) {
            screenY = worldY;
        }   
        int rightOffset = gm.screenWidth - gm.brian.screenX;      
        if(rightOffset > gm.worldWidth - gm.brian.worldX) {
            screenX = gm.screenWidth - (gm.worldWidth - worldX);
        } 
        int bottomOffset = gm.screenHeight - gm.brian.screenY;
        if(bottomOffset > gm.worldHeight - gm.brian.worldY) {
            screenY = gm.screenHeight - (gm.worldHeight - worldY);
        }

        if (worldX + gm.tileSize > gm.brian.worldX - gm.brian.screenX
        && worldX - gm.tileSize < gm.brian.worldX + gm.brian.screenX
        && worldY + gm.tileSize > gm.brian.worldY - gm.brian.screenY
        && worldY - gm.tileSize < gm.brian.worldY + gm.brian.screenY) {
            g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize, null);
        }
        // If brian is around the edge, draw everything
        else if(gm.brian.worldX < gm.brian.screenX ||
        gm.brian.worldY < gm.brian.screenY ||
        rightOffset > gm.worldWidth - gm.brian.worldX ||
        bottomOffset > gm.worldHeight - gm.brian.worldY) {
            g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize, null); 
        }
    }
}
