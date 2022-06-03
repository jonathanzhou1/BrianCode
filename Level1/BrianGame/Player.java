package Level1.BrianGame;

import java.awt.*;
import Level2.main.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player
{
    GamePanel panel;

    boolean onSomething = true;
    boolean jump = false;
    boolean forwardAnimation = true;
    boolean crouched = false;
    boolean canUncrouch = true;
    boolean moveLeft = false;
    boolean moveRight = true;
    boolean win = false;
    boolean stop = false;
    
    BufferedImage r1, r2, r3, r4, r5;
    BufferedImage l1, l2, l3, l4, l5;
    BufferedImage c1, c2, c3, c4,c5, c6, c7, c8;
    BufferedImage brianJumpRight, brianJumpLeft;
    BufferedImage spin1, spin2;

    private Image brianImage;

    long start = System.currentTimeMillis();
    int x, y;
    int width, height;

    double xspeed, yspeed;

    Rectangle hitBox;

    boolean keyLeft, keyRight, keyUp, keyDown;

    public Player(int x, int y, GamePanel panel)
    {
    	
    	try {
        	r1 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brianRight1.png"));
        	r2 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/BrianRightIntermediate.png"));
        	r3 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brianRight2.png"));
        	r4 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/Brain_Right2.png"));
        	r5 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/Brain_Right3.png"));
        	l1 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brianLeft1.png"));
        	l2 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brianLeftIntermediate.png"));
        	l3 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brianLeft2.png"));
        	l4 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/Brain_Left2.png"));
        	l5 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/Brain_Left3.png"));
        	c1 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll1.png"));
        	c2 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll2.png"));
        	c3 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll3.png"));
        	c4 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll4.png"));
        	c5 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll5.png"));
        	c6 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll6.png"));
        	c7 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll7.png"));
        	c8 = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brainroll8.png"));
        	brianJumpLeft = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brianJumpLeft.png"));
        	brianJumpRight = ImageIO.read(getClass().getResourceAsStream("/Level1/Brian100/brianJumpRight.png"));
        	spin1 = ImageIO.read(getClass().getResourceAsStream("/Level1/spin1.png"));
        	spin2 = ImageIO.read(getClass().getResourceAsStream("/Level1/spin2.png"));
        	
        	
        }catch(IOException e) {
            e.printStackTrace();
        }
    	
        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 60;
        height = 100;
        hitBox = new Rectangle(x - 15, y, width, height);

        brianImage = r1;
    }

    public void set()
    {
        if (!win)
        {
            if (!keyLeft && !keyRight && !keyUp && !keyDown)
            {
                if (brianImage == l5 || brianImage == l2 || brianImage == l3 || brianImage == l4)
                {
                    brianImage = l1;
                    forwardAnimation = true;
                }
                else if (brianImage == r2 || brianImage == r3 || brianImage == r4 || brianImage == r5)
                {
                    brianImage = r1;
                    forwardAnimation = true;
                }  
            }
            if (keyLeft && keyRight || !keyLeft && !keyRight)
            {
                xspeed *= 0.8;
            }
            else if (keyLeft && !keyRight)
            {
                moveRight = false;
                moveLeft = true;
                xspeed--;
                if ((System.currentTimeMillis() - start) % 5 == 0 && onSomething && !crouched) 
                {
                    if (brianImage == l1 && forwardAnimation)
                    {
                        brianImage = l2;
                    }
                    else if (brianImage == l2 && forwardAnimation)
                    {
                        brianImage = l3;
                    }
                    else if (brianImage == l3 && forwardAnimation)
                    {
                        forwardAnimation = false;
                        brianImage = l2;
                    }
                    else if (brianImage == l2 && !forwardAnimation)
                    {
                        brianImage = l1;
                    }
                    else if (brianImage == l1 && !forwardAnimation)
                    {
                        brianImage = l4;
                    }
                    else if (brianImage == l4 && !forwardAnimation)
                    {
                        brianImage = l5;
                    }
                    else if (brianImage == l5 && !forwardAnimation)
                    {
                        brianImage = l4;
                        forwardAnimation = true;
                    }
                    else if (brianImage == l4 && forwardAnimation)
                    {
                        brianImage = l1;
                    }
                    else
                    {
                        brianImage = l1;
                        forwardAnimation = true;
                    }
                } 
            }
            else if (keyRight && !keyLeft)
            {
                moveLeft = false;
                moveRight = true;
                xspeed++;
                if ((System.currentTimeMillis() - start) % 5 == 0 && onSomething && !crouched)
                {
                    if (brianImage == r1 && forwardAnimation)
                    {
                        brianImage = r2;
                    }
                    else if (brianImage == r2 && forwardAnimation)
                    {
                        brianImage = r3;
                    }
                    else if (brianImage == r3 && forwardAnimation)
                    {
                        forwardAnimation = false;
                        brianImage = r2;
                    }
                    else if (brianImage == r2 && !forwardAnimation)
                    {
                        brianImage = r1;
                    }
                    else if (brianImage == r1 && !forwardAnimation)
                    {
                        brianImage = r4;
                    }
                    else if (brianImage == r4 && !forwardAnimation)
                    {
                        brianImage = r5;
                    }
                    else if (brianImage == r5 && !forwardAnimation)
                    {
                        brianImage = r4;
                        forwardAnimation = true;
                    }
                    else if (brianImage == r4 && forwardAnimation)
                    {
                        brianImage = r1;
                    }
                    else
                    {
                        brianImage = r1;
                        forwardAnimation = true;
                    }
                } 
            }

            if (xspeed > 0 && xspeed < 0.75)
                xspeed = 0;
            if (xspeed < 0 && xspeed > -0.75) 
                xspeed = 0;

            if (xspeed > 7)
            {
                xspeed = 7;
            }
            if (xspeed < -7)
            {
                xspeed = -7;
            }

            if (keyDown)
            {
                if (!crouched)
                {
                    crouched = true;
                    onSomething = false;
                    hitBox.height = 50;
                }
            }
            else
            {
                if (onSomething && crouched)
                {
                    hitBox.y -= 50;
                    for (Wall wall : panel.walls)
                    {
                        if (wall.hitBox.intersects(hitBox))
                        {
                            canUncrouch = false;
                            hitBox.y += 50;
                            break;
                        }
                    }
                    if (canUncrouch)
                    {
                        crouched = false;
                        y -= 50;
                        hitBox.height = 100;
                        if (moveRight == true)
                        {
                            brianImage = r1;
                        }
                        else
                        {
                            brianImage = l1;
                        }
                    }
                }
            }
            if (crouched)
            {
                canUncrouch = true;
                if (moveRight)
                {
                    if (brianImage == c1)
                    {
                        brianImage = c2;
                    }
                    else if (brianImage == c2)
                    {
                        brianImage = c3;
                    }
                    else if (brianImage == c3)
                    {
                        brianImage = c4;
                    }
                    else if (brianImage == c4)
                    {
                        brianImage = c5;
                    }
                    else if (brianImage == c5)
                    {
                        brianImage = c6;
                    }
                    else if (brianImage == c6)
                    {
                        brianImage = c7;
                    }
                    else if (brianImage == c7)
                    {
                        brianImage = c8;
                    }
                    else
                    {
                        brianImage = c1;
                    }
                }
                else if (moveLeft)
                {
                    if (brianImage == c8)
                    {
                        brianImage = c7;
                    }
                    else if (brianImage == c2)
                    {
                        brianImage = c1;
                    }
                    else if (brianImage == c3)
                    {
                        brianImage = c2;
                    }
                    else if (brianImage == c4)
                    {
                        brianImage = c3;
                    }
                    else if (brianImage == c5)
                    {
                        brianImage = c4;
                    }
                    else if (brianImage == c6)
                    {
                        brianImage = c5;
                    }
                    else if (brianImage == c7)
                    {
                        brianImage = c6;
                    }
                    else
                    {
                        brianImage = c8;
                    }
                }
            }

            if (keyUp)
            {
                onSomething = false;
                jump = true;
                hitBox.y++;
                for (Wall wall : panel.walls)
                {
                    if (wall.hitBox.intersects(hitBox))
                    {
                        yspeed = -14;
                    }
                }
                hitBox.y--;
            }
            yspeed += 0.6; //gravity

            if (jump)
            {
                if (!crouched && keyRight)
                {
                    brianImage = brianJumpRight;   
                }
                else if (!crouched && keyLeft)
                {
                    brianImage = brianJumpLeft;  
                }
                else if (!crouched && brianImage == r1 || brianImage == r2 || brianImage == r3 || brianImage == r4 || brianImage == r5 || brianImage == brianJumpRight)
                {
                    brianImage = brianJumpRight;
                }
                else if (!crouched)
                {
                    brianImage = brianJumpLeft;
                }
            }

            //horiz Collisions
            hitBox.x += xspeed;
            for (Wall wall : panel.walls)
            {
                if (wall.hitBox.intersects(hitBox))
                {
                    hitBox.x -= xspeed;
                    while (!wall.hitBox.intersects(hitBox))
                    {
                        hitBox.x += Math.signum(xspeed);
                    }
                    hitBox.x -= Math.signum(xspeed);
                    xspeed = 0;
                    x = hitBox.x;
                }
            }
            if (panel.computer.hitBox.intersects(hitBox))
            {
                win = true;
            }
            if (panel.leanMinion.hitBox.intersects(hitBox))
                panel.reset();
            //vert Collisions
            hitBox.y += yspeed;
            for (Wall wall : panel.walls)
            {
                if (wall.hitBox.intersects(hitBox))
                {
                    onSomething = true;
                    hitBox.y -= yspeed;
                    while (!wall.hitBox.intersects(hitBox))
                    {
                        hitBox.y += Math.signum(yspeed);
                    }
                    hitBox.y -= Math.signum(yspeed);
                    yspeed = 0;
                    y = hitBox.y;
                    if (jump && brianImage == brianJumpRight && !crouched)
                    {
                        jump = false;
                        brianImage = r1;
                    }
                    else if (jump && !crouched)
                    {
                        jump = false;
                        brianImage = l1;
                    }
                }
            }

            panel.cameraX += xspeed;
            y += yspeed;

            hitBox.x = x;
            hitBox.y = y;

            if (y > 1200)
            {
                brianImage = r1;
                panel.reset();
            }
        }
        else
        {
            if (brianImage == spin2)
            {
                while (!(System.currentTimeMillis() % 4000 == 0))
                {}
                if (!stop)
                {
                    Main.joe();
                    Sound.soundStop();
                    Brian.close();
                    stop = true;
                }
            }
            else if (brianImage == spin1)
            {
                while (!(System.currentTimeMillis() % 2000 == 0))
                {}
                brianImage = spin2;
            }  
            else
            {
                x += 75;
                brianImage = spin1;
            }
        }
    }

    public void draw(Graphics gtd)
    {
        gtd.drawImage(brianImage, x - 15, y, panel);
    }
}
