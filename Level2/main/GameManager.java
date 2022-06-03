package Level2.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Level2.entity.Brian;
import Level2.object.ParentObject;
import Level2.tile.TileManager;

public class GameManager extends JPanel implements Runnable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels 1200
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels 900
    
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    // FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyManager keyM = new KeyManager();
    SoundManager music = new SoundManager();
    SoundManager se = new SoundManager();
    public CollisionManager cManager = new CollisionManager(this);
    public ObjectManager oManager = new ObjectManager(this);
    public GUI gui = new GUI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Brian brian = new Brian(this,keyM);
    public ParentObject obj[] = new ParentObject[10];
    
    
    public GameManager() 
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyM);
        this.setFocusable(true);
    }
    
    public void setupGame()
    {
    	oManager.setObject();
    	
    	playMusic(0);
    }
    
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
	@Override
	public void run() 
	{
		double drawInterval = 1000000000/FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null)
	    {
	        update();
	      
	        repaint();
	       
	        try {
		        double remainingTime = nextDrawTime - System.nanoTime();
		        remainingTime = remainingTime/1000000;
		        
		        if(remainingTime < 0)
		        {
		        	remainingTime = 0;
		        }
		        
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
    public void update()
    {
        brian.update();
    }
    
    public void paintComponent(Graphics g)   //bGUIlt in java method
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        
        //TILE
        tileM.draw(g2);
        
        //OBJECT
        for(int i = 0; i < obj.length; i++)
        {
        	if(obj[i] != null)
        	{
        		obj[i].draw(g2, this);
        	}
        }
        
        //PLAYER
        brian.draw(g2);
        
        //GUI
        gui.draw(g2);
        
        g2.dispose();
    }
    
    public void playMusic(int i)
    {
    	music.setFile(i);
    	music.play();
    	music.loop();
    }
    
    public void stopMusic()
    {
    	music.stop();
    }
    
    public void playSE(int i)
    {
    	se.setFile(i);
    	se.play();
    }
}


