package Level2.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Level2.main.GameManager;

public class TileManager {

    GameManager gm;
    public ParentTile[] parentTile;
    public int mapTileNum[][];

    public TileManager(GameManager gm)
    {
        this.gm = gm;

        parentTile = new ParentTile[10];
        mapTileNum = new int[gm.maxWorldCol][gm.maxWorldRow];

        getTileImage();
        loadMap("/Level2/mapsImport/computerTableMap.txt");
    }

    public void getTileImage()
    {
        try {

            parentTile[0] = new ParentTile();
            parentTile[0].image = ImageIO.read(getClass().getResourceAsStream("/Level2/tilesImport/brownTable.png"));
            parentTile[0].collision = true;

            parentTile[1] = new ParentTile();
            parentTile[1].image = ImageIO.read(getClass().getResourceAsStream("/Level2/tilesImport/pathDark.png"));

            parentTile[2] = new ParentTile();
            parentTile[2].image = ImageIO.read(getClass().getResourceAsStream("/Level2/tilesImport/roundBrownTable.png"));
            parentTile[2].collision = true;

            parentTile[3] = new ParentTile();
            parentTile[3].image = ImageIO.read(getClass().getResourceAsStream("/Level2/tilesImport/whiteTable.png"));
            parentTile[3].collision = true;

            parentTile[4] = new ParentTile();
            parentTile[4].image = ImageIO.read(getClass().getResourceAsStream("/Level2/tilesImport/roundWhiteTable.png"));
            parentTile[4].collision = true;
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath)
    {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gm.maxWorldCol && row < gm.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gm.maxWorldCol)
                {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gm.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e)
        {

        }
    }

    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gm.maxWorldCol && worldRow < gm.maxWorldRow)
        {
            int ParentTileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gm.tileSize;
            int worldY = worldRow * gm.tileSize;
            int screenX = worldX - gm.brian.worldX + gm.brian.screenX;
            int screenY = worldY - gm.brian.worldY + gm.brian.screenY;

            // Stop moving the camera at the edge
            if (gm.brian.screenX > gm.brian.worldX)
            {
                screenX = worldX;    
            }
            if (gm.brian.screenY > gm.brian.worldY)
            {
                screenY = worldY;
            }
            int rightOffset = gm.screenWidth - gm.brian.screenX;
            if (rightOffset > gm.worldWidth - gm.brian.worldX)
            {
                screenX = gm.screenWidth - (gm.worldWidth - worldX);
            }
            int bottomOffset = gm.screenHeight - gm.brian.screenY;
            if (bottomOffset > gm.worldHeight - gm.brian.worldY)
            {
                screenY = gm.screenHeight - (gm.worldHeight - worldY);
            }
    
            if(worldX + gm.tileSize > gm.brian.worldX - gm.brian.screenX &&
            worldX - gm.tileSize < gm.brian.worldX + gm.brian.screenX &&
            worldY + gm.tileSize > gm.brian.worldY - gm.brian.screenY &&
            worldY - gm.tileSize < gm.brian.worldY + gm.brian.screenY)
            {
                g2.drawImage(parentTile[ParentTileNum].image, screenX, screenY, gm.tileSize, gm.tileSize, null);
            }
            else if (gm.brian.screenX > gm.brian.worldX ||
                     gm.brian.screenY > gm.brian.worldY ||
                     rightOffset > gm.worldWidth - gm.brian.worldX ||
                     bottomOffset > gm.worldHeight - gm.brian.worldY)
            {
                g2.drawImage(parentTile[ParentTileNum].image, screenX, screenY, gm.tileSize, gm.tileSize, null);             
            }
            worldCol++;

            if(worldCol == gm.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
