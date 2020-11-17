package View;

import Controller.GameManager;
import Model.Model;

public class GameClearState implements State {

	private static GameClearState gameClearState;

	private GameClearState() {
	}

	public static GameClearState getInstance() {
		Game game = GameManager.getInstance().getGame();

		game.getContentPane().removeAll();
		game.getContentPane().add(GameClearPanel.getInstance());
		game.repaint();
		game.setVisible(true);

		if (gameClearState == null)
			gameClearState = new GameClearState();

		return gameClearState;
	}

	@Override
	public void gameClear() {
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
	public void gameOver() {
	}

	@Override
	public void nextStage() {
	}
}