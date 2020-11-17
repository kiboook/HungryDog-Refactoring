package Model;
public class Model {
	private int nScore;
	private int nMove;
	private int nLevel;

	public void setScore(int minute, int second) {
		nScore = nScore + ((minute * 60 + second) * nLevel - nMove); // 점수계산
	}

	public void moveUp() {
		nMove = nMove + 1;
	}

	public void moveDown() {
		nMove = nMove - 1;
	}

	public void initMove() {
		nMove = 0;
	}

	public void levelUp() {
		nLevel = nLevel + 1;
	}

	public void initAll() {
		nScore = 0;
		nMove = 0;
		nLevel = 1;
	}

	public int getScore() {
		return nScore;
	}

	public int getMove() {
		return nMove;
	}

	public int getLevel() {
		return nLevel;
	}

	public Model() {
		nScore = 0;
		nMove = 0;
		nLevel = 1;
	}

}