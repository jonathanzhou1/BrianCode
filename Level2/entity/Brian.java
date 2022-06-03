package Level2.entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import Level2.main.GameManager;
import Level2.main.KeyManager;

public class Brian extends Entity
{
    GameManager gm;
    KeyManager keyM;

    public final int screenX;
    public final int screenY;
    public int hasPhone = 0;
    public int totalCollectedPhones = 0;
    
    public Brian(GameManager gm, KeyManager keyM)
    {
        this.gm = gm;
        this.keyM = keyM;

        screenX = gm.screenWidth/2 - (gm.tileSize/2);
        screenY = gm.screenHeight/2 - (gm.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);  //x, y, width, height
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        worldX = gm.tileSize * 23;
        worldY = gm.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-up-2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-right-2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-left-2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Level2/playerImport/brain-down-2.png"));

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if (keyM.upPressed || keyM.downPressed || keyM.rightPressed || keyM.leftPressed)
        {
            if(keyM.upPressed == true)
            {
                direction = "up";
            }
            else if(keyM.downPressed == true)
            {
                direction = "down";
            }
            else if(keyM.rightPressed == true)
            {
                direction = "right";
            }
            else if(keyM.leftPressed == true)
            {
                direction = "left";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gm.cManager.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gm.cManager.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE

            if(collisionOn == false)
            {
                if(direction == "up")
                {
                    worldY -= speed;
                }
                else if(direction == "down")
                {
                    worldY += speed;
                }
                else if(direction == "left")
                {
                    worldX -= speed;
                }
                else
                {
                    worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 13)
            {
                if(spriteNum == 1)
                    spriteNum = 2;
                else if(spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i)
    {
        if(i != 999)
        {
            String objectName = gm.obj[i].name;

            if(objectName.equals("Phone"))
            {
                gm.playSE(2);
                hasPhone++;
                totalCollectedPhones++;
                gm.obj[i] = null;
                gm.gui.showMessage("That phone looked awfully cold...");
            }
            else if (objectName.equals("Pocket1"))
            {
                if(totalCollectedPhones == 5)
                {
                    gm.gui.gameFinished = true;
                    gm.stopMusic();
                    gm.playSE(2);
                }
                else if (hasPhone > 0)
                {
                    gm.playSE(2);
                    hasPhone--;
                    gm.gui.showMessage("I need more phones...");
                }
            }
            else if (objectName.equals("Clipboard"))
            {
                gm.stopMusic();
                gm.playMusic(1);
                speed += 3;
                gm.obj[i] = null;
            }
        }
    }

    public void draw(Graphics2D g2)
    {
        //      g2.setColor(Color.white);
        //      g2.fillRect(x, y, gm.tileSize, gm.tileSize);

        BufferedImage image = null;
        if (direction.equals("up"))
        {
            if(spriteNum == 1)
            {
                image = up1;
            }
            else
            {
                image = up2;
            }
        }
        else if (direction.equals("down"))
        {
            if(spriteNum == 1)
            {
                image = down1;
            }
            else
            {
                image = down2;
            }
        }
        else if (direction.equals("right"))
        {
            if(spriteNum == 1)
            {
                image = right1;
            }
            else
            {
                image = right2;
            }
        }
        else
        {
            if(spriteNum == 1)
            {
                image = left1;          
            }
            else
            {
                image = left2;
            }

        }
        g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize, null);

    }
}




