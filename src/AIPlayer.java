import java.util.ArrayList;
import java.util.HashMap;

public class AIPlayer {

	private Board board;
	private HashMap<Integer, Cell> cellAndScores;

	public AIPlayer(Board board) {
		cellAndScores = new HashMap<Integer, Cell>();
		this.board = board;
	}

	public void doMove(Cell cell, int player) {
		board.doMove(cell, player);
	}

	public Cell returnBestMove() {
		int maxScore = Integer.MIN_VALUE;
		Cell cell = null;
		for (int score : cellAndScores.keySet()) {
			if (score > maxScore) {
				maxScore = score;
				cell = cellAndScores.get(score);
			}
		}
		return cell;
	}

	private int getMin(ArrayList<Integer> list) {
		int minScores = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i) < minScores) {
				minScores = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	private int getMax(ArrayList<Integer> list) {
		int maxScore = Integer.MIN_VALUE;
		int index = -1;
		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i) > maxScore) {
				maxScore = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	public int minimax(int depth, int turn) {
		if (board.hasUserWon())
			return +10;
		if (board.hasAIWon())
			return -10;

		ArrayList<Cell> cellsAvailable = board.getAvailableStates();
		ArrayList<Integer> scores = new ArrayList<>();

		if (cellsAvailable.isEmpty())
			return 0;

		for (Cell cell : cellsAvailable) {
			if (turn == 1) { // OPPONENT - select highest score from MINIMAX()
				doMove(cell, 1); // Test a move
				int currentScore = minimax(depth + 1, 2);
				scores.add(currentScore); 
				if (depth == 0)
					cellAndScores.put(currentScore, cell);
			} else if (turn == 2) { // AI - select lowest score from MINIMAX()
				doMove(cell, 2);
				scores.add(minimax(depth + 1, 1));
			}
			board.getCells()[cell.getRow()][cell.getCol()] = 0; // Reset this point
		}
		return turn == 1 ? getMax(scores) : getMin(scores); 
	}
}
