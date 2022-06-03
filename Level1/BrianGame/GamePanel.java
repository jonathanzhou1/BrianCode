package Level1.BrianGame;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import java.util.*;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class GamePanel extends javax.swing.JPanel implements ActionListener
{
    Player player;
    Timer gameTimer;
    ArrayList<Wall> walls = new ArrayList<Wall>();
    GUI gui = new GUI(this);
    Computer computer = new Computer(5050, 450, 100, 100);
    Minion leanMinion = new Minion(1750, 300, 40, 50);
    BufferedImage p1, p2, p3, brick, c1, c2, c3, bg, compPic, leanMinionPic;
    
    //Background image credits: https://cdnb.artstation.com/p/assets/images/images/025/031/659/large/mathieu-chauderlot-room-0010-layer-18.jpg?1584370523
    Sound sound = new Sound();

    boolean minionForward = true;
    int minionMove = 0;

    int cameraX, cameraY;

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public GamePanel()
    {
    	
    	try {
        	p1 = ImageIO.read(getClass().getResourceAsStream("/Level1/tilesReady/100.png"));
        	p2 = ImageIO.read(getClass().getResourceAsStream("/Level1/tilesReady/200.png"));
        	p3 = ImageIO.read(getClass().getResourceAsStream("/Level1/tilesReady/1200.png"));
        	brick = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/Brick.png"));
        	c1 = ImageIO.read(getClass().getResourceAsStream("/Level1/columns/150.png"));
        	c2 = ImageIO.read(getClass().getResourceAsStream("/Level1/columns/400.png"));
        	c3 = ImageIO.read(getClass().getResourceAsStream("/Level1/columns/450.png"));
        	compPic = ImageIO.read(getClass().getResourceAsStream("/Level1/computer.png"));
        	bg = ImageIO.read(getClass().getResourceAsStream("/Level1/background.jpg"));
        	leanMinionPic = ImageIO.read(getClass().getResourceAsStream("/Level1/leanMinion.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    	
        player = new Player(400, 650, this);

        makeWalls();

        sound.play();
        sound.loop();

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
                @Override
                public void run() 
                {
                    player.set();
                    for (Wall wall: walls)
                    {
                        wall.set(cameraX);
                    }
                    computer.set(cameraX);
                    leanMinion.set(cameraX);
                    repaint();
                }
            }, 0, 10);
    }

    public void makeWalls()
    {
        //ground
        walls.add(new Wall(150, 750, 1200, 50));

        //platform above little stick thing up
        walls.add(new Wall(1300, 400, 200, 50));

        //highest platform above little stick thing up
        walls.add(new Wall(2000, 200, 200, 50));

        //little bridge out of thing you have to roll under
        walls.add(new Wall(1150, 500, 50, 50));

        //floating platform in air
        walls.add(new Wall(1750, 350, 200, 50));

        //floating platform in air
        walls.add(new Wall(2950, 350, 200, 50));

        //floating platform in air
        walls.add(new Wall(1050, 250, 200, 50));

        //floating platform in air after x2 stick thingy
        walls.add(new Wall(3450, 250, 200, 50));

        //floating platform in air after x2 stick thingy
        walls.add(new Wall(4100, 550, 1200, 50));

        //little stick thing up x2
        walls.add(new Wall(3100, 150, 50, 150));

        //little stick thing up
        walls.add(new Wall(1300, 600, 50, 150));

        //middle wall
        walls.add(new Wall(2550, 350, 50, 450));

        //floating stick thing you have to roll under
        walls.add(new Wall(1100, 300, 50, 400));

    }

    public void reset() {
        player.x = 400;
        player.y = 350;
        cameraX = 350;
        player.xspeed = 0;
        player.yspeed = 0;
    }

    public void paint(Graphics g)
    {
        //super.paint(g);
        Graphics2D gtd = (Graphics2D) g;

        if (gameState == titleState)
        {
            gui.draw(gtd);
        }
        else
        {
            gtd.drawImage(bg, (-cameraX / 9) - 90, 0, this);
            for (Wall wall : walls)
            {
                //wall.draw(gtd);
                if (wall.width == 50 && wall.height == 50)
                {
                    gtd.drawImage(brick, wall.x, wall.y, this);
                }
                else if (wall.width == 100)
                {
                    gtd.drawImage(p1, wall.x, wall.y, this);
                }
                else if (wall.width == 200)
                {
                    gtd.drawImage(p2, wall.x, wall.y, this);
                }
                else if (wall.width == 1200)
                {
                    gtd.drawImage(p3, wall.x, wall.y, this);
                }
                else if (wall.height == 150)
                {
                    gtd.drawImage(c1, wall.x, wall.y, this);
                }
                else if (wall.height == 400)
                {
                    gtd.drawImage(c2, wall.x, wall.y, this);
                }
                else if (wall.height == 450)
                {
                    gtd.drawImage(c3, wall.x, wall.y, this);
                }
            }
            gtd.drawImage(compPic, computer.x, computer.y, this);
            if (minionForward && minionMove > 150)
            {
                minionForward = false;
            }
            else if (!(minionForward) && minionMove < 0)
            {
                minionForward = true;
            }
            if (minionForward)
            {
                gtd.drawImage(leanMinionPic, leanMinion.x + minionMove++, leanMinion.y, this);
            }
            else
            {
                gtd.drawImage(leanMinionPic, leanMinion.x + --minionMove, leanMinion.y, this);
            }
            leanMinion.updateHitbox(leanMinion.x + minionMove);
            player.draw(gtd);
        }
    }

    void keyPressed(KeyEvent e)
    {
        // TITLE STATE
        if (gameState == titleState)
        {
            if (e.getKeyChar() == ' ' || e.getKeyChar() == 'a')
            {
                gui.commandNum--;
                if (gui.commandNum < 0)
                {
                    gui.commandNum = 1;
                }
            }
            if (e.getKeyChar() == 'd')
            {
                gui.commandNum++;
                if (gui.commandNum > 1)
                {
                    gui.commandNum = 0;
                }
            }
            if (e.getKeyChar() == KeyEvent.VK_ENTER)
            {
                if (gui.commandNum == 0)
                {
                    gameState = 1;
                    playMusic();    
                }
                else if (gui.commandNum == 1)
                    System.exit(0);
            }
        }

        // PLAY STATE
        if (e.getKeyChar() == 'a')
        {
            player.keyLeft = true;
        }
        if (e.getKeyChar() == 'd')
        {
            player.keyRight = true;
        }
        if (e.getKeyChar() == ' ' || e.getKeyChar() == 'w')
        {
            player.keyUp = true;
        }
        if (e.getKeyChar() == 's')
        {
            player.keyDown = true;
        }
    }

    void keyReleased(KeyEvent e)
    {
        if (e.getKeyChar() == 'a')
        {
            player.keyLeft = false;
        }  
        if (e.getKeyChar() == 'd')
        {
            player.keyRight = false;
        }
        if (e.getKeyChar() == ' ' || e.getKeyChar() == 'w')
        {
            player.keyUp = false;
        }
        if (e.getKeyChar() == 's')
        {
            player.keyDown = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {

    }
    
    public void playMusic()
    {
        sound.play();
        sound.loop();
    }

    public void stopMusic()
    {
        sound.soundStop();
    }
}
