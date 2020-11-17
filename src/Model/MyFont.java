package Model;
import java.awt.Font;

public class MyFont {
	private static Font mainFont,playFont,rankSmallFont,rankBigFont;
	public static Font getMainPanelFont() {
	      if (mainFont == null)
	         mainFont = new Font("a타임머신", Font.BOLD, 30);

	      return mainFont;
	   }
	
	public static Font getPlayPanelFont() {
	      if (playFont == null)
	         playFont = new Font("Verdana", Font.BOLD, 20);

	      return playFont;
	   }
	public static Font getRankSmallFont() {
	      if (rankSmallFont == null)
	    	  rankSmallFont = new Font("Verdana", Font.BOLD, 35);

	      return rankSmallFont;
	   }
	public static Font getRankBIgFont() {
	      if (rankBigFont == null)
	    	  rankBigFont = new Font("Verdana", Font.BOLD, 50);

	      return rankBigFont;
	   }
	   
}
