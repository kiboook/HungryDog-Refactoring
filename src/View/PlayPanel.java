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
	private Map map;
	private Undo undo;
	private ImageIcon stageIcon, scoreIcon, moveIcon;
	private JLabel lblStage, lblScore;
	private JLabel lblMove;
	boolean isGameOver;
	private Game game = GameManager.getInstance().getGame();
	private BarObject barObject = GameManager.getInstance().getBarObject();

	Player player = new Player();
	ArrayList<Bone> boneList = new ArrayList<>();
	ArrayList<RiceBowl> riceBowlList = new ArrayList<>();

	public PlayPanel(int mapArray[][]) {

		map = new Map(mapArray);
		undo = new Undo();

		setBounds(0, 100, 600, 600);
		setBackground(Color.red);
		setLayout(null);
		getGame().listener.addPlayPanelKeyListner(this);

		stageIcon = new MyIcon("stage" + getBarObject().getLevel() + ".png").getIcon(100, 100);
		lblStage = new Label(stageIcon).setPlayLabelWithPosition(0, 0, 100, 100);

		scoreIcon = new MyIcon("ScoreBoard.png").getIcon(200, 100);
		lblScore = new Label(scoreIcon, SwingConstants.CENTER).setLableWithColorNPosition(Color.blue, Color.black, 100,
				0, 200, 100);
		lblScore.setText(Integer.toString(getBarObject().getScore()));

		moveIcon = new MyIcon("MoveBoard.png").getIcon(150, 100);
		setLblMove(new Label(moveIcon, SwingConstants.CENTER).setLableWithColorNPosition(Color.red, Color.black, 300, 0,
				150, 100));
		getLblMove().setText(Integer.toString(getBarObject().getMoveCount()));

		TimeThread lblTime = TimeThread.getInstance();

		add(lblStage);
		add(lblScore);
		add(getLblMove());
		add(lblTime);

		map.DrawObject(this, player, boneList, riceBowlList);
		map.DrawMap(this);
	}

	public void move(int key) { // 캐릭터와 뼈다귀, 밥그릇 좌표 옮기는 메소드
		BarObject barObject = GameManager.getInstance().getBarObject();

		switch (key) { // 방향키 값을 받아와서 그 값에 따라 움직임
		case 38: // UP
			getGame().getController().moveUp(player, undo, map, boneList, riceBowlList);
			BarkSound.getInstance().startMusic();
			break;
			
		case 40: // DOWN
			getGame().getController().moveDown(player, undo, map, boneList, riceBowlList);
			BarkSound.getInstance().startMusic();
			break;
			
		case 37: // LEFT
			getGame().getController().moveLeft(player, undo, map, boneList, riceBowlList);
			BarkSound.getInstance().startMusic();
			break;
			
		case 39: // RIGHT
			getGame().getController().moveRight(player, undo, map, boneList, riceBowlList);
			BarkSound.getInstance().startMusic();
			break;
			
		case 90:
			getGame().getController().undo(player, undo, map, boneList, riceBowlList);
			BarkSound.getInstance().startMusic();
			break;
		}

	}

	public void undo() {
		getGame().getController().undo(player, undo, map, boneList, riceBowlList);
	}

	public void view(int key) {
		getGame().view.inputKeyValueView(key, player, undo, map, boneList, riceBowlList);
	}

	public boolean isGameClear() {
		return getGame().getController().isGameClear(player, undo, map, boneList, riceBowlList);
	}

	public boolean isGameOver() {
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