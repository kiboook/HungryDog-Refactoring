package View;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.GameManager;
import Controller.TimeThread;
import Model.BarObject;
import Model.BarkSound;
import Model.Bone;
import Model.MyIcon;
import Model.Label;
import Model.Player;
import Model.RiceBowl;
import Model.Undo;

public class PlayPanel extends JPanel {
//   private int mapArray[][]; // 화면에 보여질 맵 정보
//   private Map map;
//   private Undo undo;
//   private boolean isMovable = true, isGameOver; // 움직였는지, 게임 오버됐는지 반환
//   private ImageIcon stageIcon,scoreIcon,moveIcon;
//   private JLabel lblStage, lblScore, lblMove;
//   
//// *******
//   Player player;
//   ArrayList<Bone> boneList = new ArrayList<>();
//   ArrayList<RiceBowl> riceBowlList = new ArrayList<>();
	private int mapArray[][]; // 화면에 보여질 맵 정보
	private Map map;
//	private JLabel Map[][] = new JLabel[12][12]; // 화면에
//	private Icon icon;
	private Undo undo;
	private boolean isMovable = true; // 움직였는지, 게임 오버됐는지 반환
//	private ImageIcon dogFrontImage, dogBackImage, dogRightImage, dogLeftImage, wallImage, groundImage, boneImage,
//			treeImage, bowlImage, fullBowlImage; // 아이콘 이름들
	private ImageIcon stageIcon,scoreIcon,moveIcon;
	JLabel lblStage, lblScore;
	private JLabel lblMove;
	boolean isGameOver;
	private Game game = GameManager.getInstance().getGame();
	private BarObject barObject = GameManager.getInstance().getBarObject();
	
// *******
	Player player = new Player();
	ArrayList<Bone> boneList = new ArrayList<>();
	ArrayList<RiceBowl> riceBowlList = new ArrayList<>();

// *******
   public PlayPanel(int mapArray[][]) {
      
      //mapArray = a; // 맵 배열 전달 받기
      map = new Map(mapArray);
      undo = new Undo();
      
      // 패널 기본 설정
      setBounds(0, 100, 600, 600);
      setBackground(Color.red);
      setLayout(null);
      getGame().listener.addPlayPanelKeyListner(this);


          stageIcon= new MyIcon("stage" + getBarObject().getLevel() + ".png").getIcon(100,100);
          lblStage = new Label(stageIcon).setPlayLabelWithPosition(0, 0, 100, 100);
            
          scoreIcon= new MyIcon("ScoreBoard.png").getIcon(200,100);
          lblScore = new Label(scoreIcon, SwingConstants.CENTER).setLableWithColorNPosition(Color.blue,Color.black,100, 0, 200, 100);
          lblScore.setText(Integer.toString(getBarObject().getScore()));
            
          moveIcon= new MyIcon("MoveBoard.png").getIcon(150,100);
          setLblMove(new Label(moveIcon, SwingConstants.CENTER).setLableWithColorNPosition(Color.red,Color.black,300, 0, 150, 100));
          getLblMove().setText(Integer.toString(getBarObject().getMoveCount()));
          

      TimeThread lblTime = TimeThread.getInstance();

      add(lblStage);
      add(lblScore);
      add(getLblMove());
      add(lblTime);

      map.DrawObject(this, player, boneList, riceBowlList);
      map.DrawMap(this);

      // ---------------------------------맵 그리기------------------------------------
      
   }



   public void move(int key) { // 캐릭터와 뼈다귀, 밥그릇 좌표 옮기는 메소드
	   BarObject barObject = GameManager.getInstance().getBarObject();
      

      switch (key) { // 방향키 값을 받아와서 그 값에 따라 움직임
      case 38: // UP-------------------------------------------------------------------------------------------
    	  getGame().getController().moveUp(player, undo, map, boneList, riceBowlList);
    	  BarkSound.getInstance().startMusic();
          
    	  break; // 아래, 왼쪽, 오른쪽도 같은 방법으로 바꿔준다.
      case 40: // DOWN-------------------------------------------------------------------------------
    	  getGame().getController().moveDown(player, undo, map, boneList, riceBowlList);
     	 
         BarkSound.getInstance().startMusic();
         break;
      case 37: // LEFT----------------------------------------------------------------------------------
    	  getGame().getController().moveLeft(player, undo, map, boneList, riceBowlList);
    	  BarkSound.getInstance().startMusic();
          
         break;
      case 39: // RIGHT--------------------------------------------------------------------------------------
    	  getGame().getController().moveRight(player, undo, map, boneList, riceBowlList);
    	  BarkSound.getInstance().startMusic();
          
         break;
      case 90:
    	  getGame().getController().undo(player, undo, map, boneList, riceBowlList);
    	  BarkSound.getInstance().startMusic();
    	  break;
      }
      
   } // move

   public void undo() {
	   getGame().getController().undo(player, undo, map, boneList, riceBowlList);
   } // undo

   public void view(int key) { // 화면에 보이게 하기
      getGame().view.inputKeyValueView(key, player, undo, map, boneList, riceBowlList);

   } // view()

   public boolean isGameClear() { // 라운드 클리어 했는지 반환
      return getGame().getController().isGameClear(player, undo, map, boneList, riceBowlList);
   }

   public boolean isGameOver() { // 움직일 수 없는 상황에 도달했는지(박스가 ㄱ자 벽에 붙으면 게임 오버)
	   return getGame().getController().isGameOver(player, undo, map, boneList, riceBowlList);

   }



public JLabel getLblMove() {
	return lblMove;
}



public void setLblMove(JLabel lblMove) {
	this.lblMove = lblMove;
}



public BarObject getBarObject() {
	return barObject;
}



public void setBarObject(BarObject barObject) {
	this.barObject = barObject;
}



public Game getGame() {
	return game;
}



public void setGame(Game game) {
	this.game = game;
}

}