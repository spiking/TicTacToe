import java.util.ArrayList;
import java.util.HashMap;

public class AIPlayer {

	private Board board;
	private Cell AIMove;
	HashMap<Integer, Cell> rootsChildrenScores;

	public AIPlayer(Board board) {
		rootsChildrenScores = new HashMap<Integer, Cell>();
		this.board = board;
	}

	public void placeAMove(Cell cell, int player) {
		board.placeAMove(cell, player);
	}

	public Cell getAIMoveCell() {
		return AIMove;
	}
	
	public Cell returnBestMove() {
        int maxScore = Integer.MIN_VALUE;
        Cell cell = null;
        for(int score : rootsChildrenScores.keySet()) {
        	if(score > maxScore) {
        		maxScore = score;
        		cell = rootsChildrenScores.get(score);
        	}
        }
        return cell;
    }
	
	public int getMin(ArrayList<Integer> list) {
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

    public int getMax(ArrayList<Integer> list) {
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
		if (board.hasXWon())
			return +10;
		if (board.hasOWon())
			return -10;

		ArrayList<Cell> cellsAvailable = board.getAvailableStates();
		ArrayList<Integer> scores = new ArrayList<>(); 
		
		if (cellsAvailable.isEmpty())
			return 0;
		
		for (int i = 0; i < cellsAvailable.size(); ++i) {
            Cell cell = cellsAvailable.get(i);  
            if (turn == 1) { // OPPONENT - select highest score from MINIMAX()
                placeAMove(cell, 1); 
                int currentScore = minimax(depth + 1, 2);
                scores.add(currentScore);
                if (depth == 0) 
                	rootsChildrenScores.put(currentScore, cell); // put the score and the cell in the map
            } else if (turn == 2) { // COMPUTER - select lowest score from MINIMAX()
                placeAMove(cell, 2); 
                scores.add(minimax(depth + 1, 1));
            }
            board.cells[cell.row][cell.col] = 0; //Reset this point
        }
        return turn == 1 ? getMax(scores) : getMin(scores); // MAX for opponent and MIN for computer
	}

//	private String printPosition(int i) {
//		if (i == 1) {
//			return "00";
//		} else if (i == 2) {
//			return "01";
//		} else if (i == 3) {
//			return "02";
//		} else if (i == 4) {
//			return "10";
//		} else if (i == 5) {
//			return "11";
//		} else if (i == 6) {
//			return "12";
//		} else if (i == 7) {
//			return "20";
//		} else if (i == 8) {
//			return "21";
//		} else if (i == 9) {
//			return "22";
//		} else {
//			return "Null";
//		}
//	}

}
