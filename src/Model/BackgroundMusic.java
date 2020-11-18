package Model;
import javax.sound.sampled.Clip;

public class BackgroundMusic extends Sound{
	

	public BackgroundMusic() {
		super("Music/playMusic");
	}

//	private static BackgroundMusic bgm;
//
//	public static BackgroundMusic getInstance() {
//	      if(bgm == null) bgm = new BackgroundMusic();
//	      return bgm;
//	   }

	public void loopClip() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
