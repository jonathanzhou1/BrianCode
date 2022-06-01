package Level1.BrianGame;

import java.awt.event.*;
import java.awt.Graphics;
import java.util.*;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GamePanel extends javax.swing.JPanel implements ActionListener
{
    Player player;
    Timer gameTimer;
    ArrayList<Wall> walls = new ArrayList<Wall>();
    Computer computer = new Computer(5050, 450, 100, 100);
    Minion leanMinion = new Minion(1750, 300, 40, 50);

    ImageIcon[] platforms = {new ImageIcon("Level1//tilesReady//100.png"), new ImageIcon("Level1//tilesReady//200.png"), new ImageIcon("Level1//tilesReady//1200.png"), new ImageIcon("Level1//Brian100//Brick.png")};
    ImageIcon[] columns = {new ImageIcon("Level1//columns//150.png"), new ImageIcon("Level1//columns//400.png"), new ImageIcon("Level1//columns//450.png")};
    //Background image credits: https://cdnb.artstation.com/p/assets/images/images/025/031/659/large/mathieu-chauderlot-room-0010-layer-18.jpg?1584370523
    ImageIcon background = new ImageIcon("Level1//background.jpg");
    ImageIcon compPic = new ImageIcon("Level1//computer.png");
    ImageIcon leanMinionPic = new ImageIcon("Level1//leanMinion.png");
    Sound sound = new Sound();

    boolean minionForward = true;
    int minionMove = 0;

    int cameraX, cameraY;

    public GamePanel()
    {
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
            }, 0, 16);
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
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        gtd.drawImage(background.getImage(), (-cameraX / 10) - 20, 0, this);
        for (Wall wall : walls)
        {
            //wall.draw(gtd);
            if (wall.width == 50 && wall.height == 50)
            {
                gtd.drawImage(platforms[3].getImage(), wall.x, wall.y, this);
            }
            else if (wall.width == 100)
            {
                gtd.drawImage(platforms[0].getImage(), wall.x, wall.y, this);
            }
            else if (wall.width == 200)
            {
                gtd.drawImage(platforms[1].getImage(), wall.x, wall.y, this);
            }
            else if (wall.width == 1200)
            {
                gtd.drawImage(platforms[2].getImage(), wall.x, wall.y, this);
            }
            else if (wall.height == 150)
            {
                gtd.drawImage(columns[0].getImage(), wall.x, wall.y, this);
            }
            else if (wall.height == 400)
            {
                gtd.drawImage(columns[1].getImage(), wall.x, wall.y, this);
            }
            else if (wall.height == 450)
            {
                gtd.drawImage(columns[2].getImage(), wall.x, wall.y, this);
            }
        }
        gtd.drawImage(compPic.getImage(), computer.x, computer.y, this);
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
            gtd.drawImage(leanMinionPic.getImage(), leanMinion.x + minionMove++, leanMinion.y, this);
        }
        else
        {
            gtd.drawImage(leanMinionPic.getImage(), leanMinion.x + --minionMove, leanMinion.y, this);
        }
        leanMinion.updateHitbox(leanMinion.x + minionMove);
        player.draw(gtd);
    }

    void keyPressed(KeyEvent e)
    {
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
}
