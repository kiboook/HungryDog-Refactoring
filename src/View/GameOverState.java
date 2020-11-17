package View;

import Controller.GameManager;
import Model.Model;

public class GameOverState implements State {

	private static GameOverState gameOverState;

	private GameOverState() {
	}

	public static GameOverState getInstance() {
		Game game = GameManager.getInstance().getGame();

		game.getContentPane().removeAll();
		game.getContentPane().add(GameOverPanel.getInstance());
		game.repaint();
		game.setVisible(true);

		if (gameOverState == null)
			gameOverState = new GameOverState();

		return gameOverState;
	}

	@Override
	public void mainButton() {
		Game game = GameManager.getInstance().getGame();
		Model barObject = GameManager.getInstance().getModel();
		game.setState(MainState.getInstance());
		barObject.initAll();

	}

	@Override
	public void rankButton() {
	}

	@Override
	public void startButton() {
	}
	
	@Override
	public void gameClear() {
	}
	
	@Override
	public void gameOver() {
	}

	@Override
	public void nextStage() {
	}
}