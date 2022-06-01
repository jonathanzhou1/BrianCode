package Level1.BrianGame;

import java.net.URL;
import javax.sound.sampled.*;

public class Sound
{
    public static Clip clip;
    URL soundtrack;
 
    public Sound()
    {
        soundtrack = getClass().getResource("/Level1/soundtrack.wav");
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundtrack);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e)
        {
            System.out.print(e);
        }
    }

    public void play()
    {
        clip.start();
    }
    
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public static void soundStop()
    {
        clip.stop();
    }

}
