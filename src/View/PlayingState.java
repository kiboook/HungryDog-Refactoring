package View;

import Controller.GameManager;
import Controller.TimeThread;
import Model.MapArray;
import Model.Model;

public class PlayingState implements State {

	private static PlayingState playingState;

	private PlayingState() {
	}

	public static PlayingState getInstance() {
		Game game = GameManager.getInstance().getGame();
		Model barObject = GameManager.getInstance().getModel();

		game.getContentPane().removeAll();
		game.getContentPane().add(new PlayPanel(new MapArray(barObject.getLevel()).getArray()));
		game.getContentPane().getComponent(0).requestFocus();
		game.repaint();
		game.setVisible(true);

		if (playingState == null) {
			playingState = new PlayingState();
		}

		return playingState;
	}

	@Override
	public void gameOver() {
		Game game = GameManager.getInstance().getGame();
		TimeThread time = TimeThread.getInstance();
		game.setState(GameOverState.getInstance());
		time.stop(true);
		time.setNull();
	}

	@Override
	public void gameClear() {
		Game game = GameManager.getInstance().getGame();
		TimeThread time = TimeThread.getInstance();
		game.setState(GameClearState.getInstance());
		time.stop(true);
		time.setNull();
	}

	@Override
	public void nextStage() {
		Game game = GameManager.getInstance().getGame();
		Model barObject = GameManager.getInstance().getModel();
		TimeThread time = TimeThread.getInstance();
		
		barObject.setScore(time.getMinute(),time.getSecond());
		barObject.initMove();
		barObject.levelUp();
		
		time.stop(true);
		time.setNull();

		game.setState(PlayingState.getInstance());
	}

	@Override
	public void mainButton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rankButton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startButton() {
		// TODO Auto-generated method stub

	}
}