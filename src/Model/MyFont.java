package Model;
import java.awt.Font;

public class MyFont {
	private static Font mainFont,playFont,ranksmallFont,rankbigFont;
	public static Font getMainFont() {
	      if (mainFont == null)
	         mainFont = new Font("a타임머신", Font.BOLD, 30);

	      return mainFont;
	   }
	
	public static Font getPlayFont() {
	      if (playFont == null)
	         playFont = new Font("Verdana", Font.BOLD, 20);

	      return playFont;
	   }
	public static Font getRankSmallFont() {
	      if (ranksmallFont == null)
	    	  ranksmallFont = new Font("Verdana", Font.BOLD, 35);

	      return ranksmallFont;
	   }
	public static Font getRankBIgFont() {
	      if (rankbigFont == null)
	    	  rankbigFont = new Font("Verdana", Font.BOLD, 50);

	      return rankbigFont;
	   }
	   
}
