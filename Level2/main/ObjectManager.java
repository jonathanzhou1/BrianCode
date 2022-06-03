package Level2.main;

import Level2.object.Clipboard;
import Level2.object.Phone;
import Level2.object.OpenPocket;
//import object.ClosedPocket;

public class ObjectManager 
{
	GameManager gm;
 
	public ObjectManager(GameManager gm)
	{
		this.gm = gm;
	}
	
	public void setObject()
	{
		gm.obj[0] = new Phone();
		gm.obj[0].worldX = 32 * gm.tileSize;
		gm.obj[0].worldY = 6 * gm.tileSize;
		
		gm.obj[1] = new Phone();
		gm.obj[1].worldX = 44 * gm.tileSize;
		gm.obj[1].worldY = 25 * gm.tileSize;
		
		gm.obj[2] = new Phone();
		gm.obj[2].worldX = 27 * gm.tileSize;
		gm.obj[2].worldY = 25 * gm.tileSize;
		
		gm.obj[3] = new Phone();
		gm.obj[3].worldX = 28 * gm.tileSize;
		gm.obj[3].worldY = 43 * gm.tileSize;
		
		gm.obj[4] = new Phone();
		gm.obj[4].worldX = 8 * gm.tileSize;
		gm.obj[4].worldY = 43 * gm.tileSize;
		
		gm.obj[5] = new OpenPocket();
		gm.obj[5].worldX = 8 * gm.tileSize;
		gm.obj[5].worldY = 9 * gm.tileSize;
		
		gm.obj[6] = new Clipboard();
		gm.obj[6].worldX = 13 * gm.tileSize;
		gm.obj[6].worldY = 34 * gm.tileSize;
	}
}
