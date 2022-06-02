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
		gm.obj[0].worldX = 23 * gm.tileSize;
		gm.obj[0].worldY = 7 * gm.tileSize;
		
		gm.obj[1] = new Phone();
		gm.obj[1].worldX = 23 * gm.tileSize;
		gm.obj[1].worldY = 40 * gm.tileSize;
		
		gm.obj[2] = new Phone();
		gm.obj[2].worldX = 37 * gm.tileSize;
		gm.obj[2].worldY = 7 * gm.tileSize;
		
		gm.obj[5] = new OpenPocket();
		gm.obj[5].worldX = 10 * gm.tileSize;
		gm.obj[5].worldY = 7 * gm.tileSize;
		
		gm.obj[6] = new Clipboard();
		gm.obj[6].worldX = 37 * gm.tileSize;
		gm.obj[6].worldY = 42 * gm.tileSize;
	}
}
