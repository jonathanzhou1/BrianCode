
package Level2.main;

import Level2.entity.Entity;

public class CollisionManager 
{
	GameManager gm;

	public CollisionManager(GameManager gm) {
		this.gm = gm;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		int entityLeftCol = entityLeftWorldX / gm.tileSize;
		int entityRightCol = entityRightWorldX / gm.tileSize;
		int entityTopRow = entityTopWorldY / gm.tileSize;
		int entityBottomRow = entityBottomWorldY / gm.tileSize;

		int tileNum1, tileNum2;

		if (entity.direction == "up") {
			entityTopRow = (entityTopWorldY - entity.speed) / gm.tileSize;
			tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
			if (gm.tileM.parentTile[tileNum1].collision == true || gm.tileM.parentTile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
		} else if (entity.direction == "down") {
			entityBottomRow = (entityBottomWorldY + entity.speed) / gm.tileSize;
			tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gm.tileM.parentTile[tileNum1].collision == true || gm.tileM.parentTile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
		} else if (entity.direction == "left") {
			entityLeftCol = (entityLeftWorldX - entity.speed) / gm.tileSize;
			tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if (gm.tileM.parentTile[tileNum1].collision == true || gm.tileM.parentTile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
		} else {
			entityRightCol = (entityRightWorldX + entity.speed) / gm.tileSize;
			tileNum1 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if (gm.tileM.parentTile[tileNum1].collision == true || gm.tileM.parentTile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
		}
	}

	public int checkObject(Entity entity, boolean player)
	{
		int index = 999;

		for(int i = 0; i < gm.obj.length; i++)
		{
			if(gm.obj[i] != null)
			{
				// Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;

				//Get the object's solid area position
				gm.obj[i].solidArea.x = gm.obj[i].worldX + gm.obj[i].solidArea.x;
				gm.obj[i].solidArea.y = gm.obj[i].worldY + gm.obj[i].solidArea.y;

				if(entity.direction == "up")
				{
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gm.obj[i].solidArea))
					{
						if(gm.obj[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
				}
				else if(entity.direction == "down")
				{
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gm.obj[i].solidArea))
					{
						if(gm.obj[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}

				}
				else if(entity.direction == "left")
				{
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gm.obj[i].solidArea))
					{
						if(gm.obj[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
				}
				else
				{
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gm.obj[i].solidArea))
					{
						if(gm.obj[i].collision == true)
						{
							entity.collisionOn = true;
						}
						if(player == true)
						{
							index = i;
						}
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gm.obj[i].solidArea.x = gm.obj[i].solidAreaDefaultX;
				gm.obj[i].solidArea.y = gm.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
}

