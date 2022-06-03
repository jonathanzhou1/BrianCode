package Level2.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager 
{
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public SoundManager()
	{
		soundURL[0] = getClass().getResource("/Level2/soundImport/sneakySnitch.wav");
		soundURL[1] = getClass().getResource("/Level2/soundImport/missionImpossible.wav");
		soundURL[2] = getClass().getResource("/Level2/soundImport/instagramThud.wav");
	}
	
	public void setFile(int i)
	{
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e)
		{
			
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
	public void stop()
	{
		clip.stop();
	}
}
