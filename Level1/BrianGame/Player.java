package Level1.BrianGame;

import java.awt.*;
import Level2.main.Main;
import javax.swing.*;
import Level1.BrianGame.Brian;
import Level1.BrianGame.Sound;

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

    private Image brianImage;

    ImageIcon[] right = {new ImageIcon("Level1//Brian100//brianRight1.png"), new ImageIcon("Level1//Brian100//brianRightIntermediate.png"), new ImageIcon("Level1//Brian100//brianRight2.png"),
            new ImageIcon("Level1//Brian100//Brain_Right2.png"), new ImageIcon("Level1//Brian100//Brain_Right3.png")};
    ImageIcon[] left = {new ImageIcon("Level1//Brian100//brianLeft1.png"), new ImageIcon("Level1//Brian100//brianLeftIntermediate.png"), new ImageIcon("Level1//Brian100//brianLeft2.png"),
            new ImageIcon("Level1//Brian100//Brain_Left2.png"), new ImageIcon("Level1//Brian100//Brain_Left3.png")};
    ImageIcon[] roll = {new ImageIcon("Level1//Brian100//brainroll1.png"), new ImageIcon("Level1//Brian100//brainroll2.png"), new ImageIcon("Level1//Brian100//brainroll3.png"),new ImageIcon("Level1//Brian100//brainroll4.png"), 
            new ImageIcon("Level1//Brian100//brainroll5.png"), new ImageIcon("Level1//Brian100//brainroll6.png"), new ImageIcon("Level1//Brian100//brainroll7.png"), new ImageIcon("Level1//Brian100//brainroll8.png")};
    ImageIcon brianJumpRight = new ImageIcon("Level1//Brian100//brianJumpRight.png");
    ImageIcon brianJumpLeft = new ImageIcon("Level1//Brian100//brianJumpLeft.png");
    ImageIcon spin1 = new ImageIcon("Level1//spin1.png");
    ImageIcon spin2 = new ImageIcon("Level1//spin2.png");

    long start = System.currentTimeMillis();
    int x, y;
    int width, height;

    double xspeed, yspeed;

    Rectangle hitBox;

    boolean keyLeft, keyRight, keyUp, keyDown;

    public Player(int x, int y, GamePanel panel)
    {
        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 60;
        height = 100;
        hitBox = new Rectangle(x - 15, y, width, height);

        brianImage = right[0].getImage();
    }

    public void set()
    {
        if (!win)
        {
            if (!keyLeft && !keyRight && !keyUp && !keyDown)
            {
                if (brianImage == left[1].getImage() || brianImage == left[2].getImage() || brianImage == left[3].getImage() || brianImage == left[4].getImage())
                {
                    brianImage = left[0].getImage();
                    forwardAnimation = true;
                }
                else if (brianImage == right[1].getImage() || brianImage == right[2].getImage() || brianImage == right[3].getImage() || brianImage == right[4].getImage())
                {
                    brianImage = right[0].getImage();
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
                    if (brianImage == left[0].getImage() && forwardAnimation)
                    {
                        brianImage = left[1].getImage();
                    }
                    else if (brianImage == left[1].getImage() && forwardAnimation)
                    {
                        brianImage = left[2].getImage();
                    }
                    else if (brianImage == left[2].getImage() && forwardAnimation)
                    {
                        forwardAnimation = false;
                        brianImage = left[1].getImage();
                    }
                    else if (brianImage == left[1].getImage() && !forwardAnimation)
                    {
                        brianImage = left[0].getImage();
                    }
                    else if (brianImage == left[0].getImage() && !forwardAnimation)
                    {
                        brianImage = left[3].getImage();
                    }
                    else if (brianImage == left[3].getImage() && !forwardAnimation)
                    {
                        brianImage = left[4].getImage();
                    }
                    else if (brianImage == left[4].getImage() && !forwardAnimation)
                    {
                        brianImage = left[3].getImage();
                        forwardAnimation = true;
                    }
                    else if (brianImage == left[3].getImage() && forwardAnimation)
                    {
                        brianImage = left[0].getImage();
                    }
                    else
                    {
                        brianImage = left[0].getImage();
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
                    if (brianImage == right[0].getImage() && forwardAnimation)
                    {
                        brianImage = right[1].getImage();
                    }
                    else if (brianImage == right[1].getImage() && forwardAnimation)
                    {
                        brianImage = right[2].getImage();
                    }
                    else if (brianImage == right[2].getImage() && forwardAnimation)
                    {
                        forwardAnimation = false;
                        brianImage = right[1].getImage();
                    }
                    else if (brianImage == right[1].getImage() && !forwardAnimation)
                    {
                        brianImage = right[0].getImage();
                    }
                    else if (brianImage == right[0].getImage() && !forwardAnimation)
                    {
                        brianImage = right[3].getImage();
                    }
                    else if (brianImage == right[3].getImage() && !forwardAnimation)
                    {
                        brianImage = right[4].getImage();
                    }
                    else if (brianImage == right[4].getImage() && !forwardAnimation)
                    {
                        brianImage = right[3].getImage();
                        forwardAnimation = true;
                    }
                    else if (brianImage == right[3].getImage() && forwardAnimation)
                    {
                        brianImage = right[0].getImage();
                    }
                    else
                    {
                        brianImage = right[0].getImage();
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
                            brianImage = right[0].getImage();
                        }
                        else
                        {
                            brianImage = left[0].getImage();
                        }
                    }
                }
            }
            if (crouched)
            {
                canUncrouch = true;
                if ((System.currentTimeMillis() - start) % 2 == 0 && moveRight)
                {
                    if (brianImage == roll[0].getImage())
                    {
                        brianImage = roll[1].getImage();
                    }
                    else if (brianImage == roll[1].getImage())
                    {
                        brianImage = roll[2].getImage();
                    }
                    else if (brianImage == roll[2].getImage())
                    {
                        brianImage = roll[3].getImage();
                    }
                    else if (brianImage == roll[3].getImage())
                    {
                        brianImage = roll[4].getImage();
                    }
                    else if (brianImage == roll[4].getImage())
                    {
                        brianImage = roll[5].getImage();
                    }
                    else if (brianImage == roll[5].getImage())
                    {
                        brianImage = roll[6].getImage();
                    }
                    else if (brianImage == roll[6].getImage())
                    {
                        brianImage = roll[7].getImage();
                    }
                    else
                    {
                        brianImage = roll[0].getImage();
                    }
                }
                else if ((System.currentTimeMillis() - start) % 2 == 0 && moveLeft)
                {
                    if (brianImage == roll[7].getImage())
                    {
                        brianImage = roll[6].getImage();
                    }
                    else if (brianImage == roll[1].getImage())
                    {
                        brianImage = roll[0].getImage();
                    }
                    else if (brianImage == roll[2].getImage())
                    {
                        brianImage = roll[1].getImage();
                    }
                    else if (brianImage == roll[3].getImage())
                    {
                        brianImage = roll[2].getImage();
                    }
                    else if (brianImage == roll[4].getImage())
                    {
                        brianImage = roll[3].getImage();
                    }
                    else if (brianImage == roll[5].getImage())
                    {
                        brianImage = roll[4].getImage();
                    }
                    else if (brianImage == roll[6].getImage())
                    {
                        brianImage = roll[5].getImage();
                    }
                    else
                    {
                        brianImage = roll[7].getImage();
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
                    brianImage = brianJumpRight.getImage();   
                }
                else if (!crouched && keyLeft)
                {
                    brianImage = brianJumpLeft.getImage();  
                }
                else if (!crouched && brianImage == right[0].getImage() || brianImage == right[1].getImage() || brianImage == right[2].getImage() || brianImage == right[3].getImage() || brianImage == right[4].getImage() || brianImage == brianJumpRight.getImage())
                {
                    brianImage = brianJumpRight.getImage();
                }
                else if (!crouched)
                {
                    brianImage = brianJumpLeft.getImage();
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
                    if (jump && brianImage == brianJumpRight.getImage() && !crouched)
                    {
                        jump = false;
                        brianImage = right[0].getImage();
                    }
                    else if (jump && !crouched)
                    {
                        jump = false;
                        brianImage = left[0].getImage();
                    }
                }
            }

            panel.cameraX += xspeed;
            y += yspeed;

            hitBox.x = x;
            hitBox.y = y;

            if (y > 1200)
            {
                brianImage = right[0].getImage();
                panel.reset();
            }
        }
        else
        {
            if (brianImage == spin2.getImage())
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
            else if (brianImage == spin1.getImage())
            {
                while (!(System.currentTimeMillis() % 2000 == 0))
                {}
                brianImage = spin2.getImage();
            }  
            else
            {
                x += 75;
                brianImage = spin1.getImage();
            }
        }
    }

    public void draw(Graphics gtd)
    {
        gtd.drawImage(brianImage, x - 15, y, panel);
    }
}
