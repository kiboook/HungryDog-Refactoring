package Model;

public class BarkSound extends Sound {

	public BarkSound() {
		super("Music/Bark");
	}
	
	private static BarkSound bark;

	public static BarkSound getInstance() {
	      if(bark == null) bark = new BarkSound();
	      return bark;
	   }

}
