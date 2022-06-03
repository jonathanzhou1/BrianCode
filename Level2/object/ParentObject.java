package Level2.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Level2.main.GameManager;

public class ParentObject {
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

		if (worldX + gm.tileSize > gm.brian.worldX - gm.brian.screenX
				&& worldX - gm.tileSize < gm.brian.worldX + gm.brian.screenX
				&& worldY + gm.tileSize > gm.brian.worldY - gm.brian.screenY
				&& worldY - gm.tileSize < gm.brian.worldY + gm.brian.screenY) {
			g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize, null);
		}
	}
}
