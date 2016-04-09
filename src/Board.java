import java.util.ArrayList;
import java.util.Scanner;

class Board {

	ArrayList<Cell> availableCells;
	int[][] cells = new int[3][3];

	public boolean isGameOver() {
		return (hasUserWon() || hasAIWon() || getAvailableStates().isEmpty());
	}

	public boolean hasUserWon() {
		if ((cells[0][0] == cells[1][1] && cells[0][0] == cells[2][2] && cells[0][0] == 1)
				|| (cells[0][2] == cells[1][1] && cells[0][2] == cells[2][0] && cells[0][2] == 1)) {
			// System.out.println("X Diagonal Win");
			return true;
		}
		for (int i = 0; i < 3; ++i) {
			if (((cells[i][0] == cells[i][1] && cells[i][0] == cells[i][2] && cells[i][0] == 1)
					|| (cells[0][i] == cells[1][i] && cells[0][i] == cells[2][i] && cells[0][i] == 1))) {
				// System.out.println("X Row or Column win");
				return true;
			}
		}
		return false;
	}

	public boolean hasAIWon() {
		if ((cells[0][0] == cells[1][1] && cells[0][0] == cells[2][2] && cells[0][0] == 2)
				|| (cells[0][2] == cells[1][1] && cells[0][2] == cells[2][0] && cells[0][2] == 2)) {
			// System.out.println("O Diagonal Win");
			return true;
		}
		for (int i = 0; i < 3; ++i) {
			if ((cells[i][0] == cells[i][1] && cells[i][0] == cells[i][2] && cells[i][0] == 2)
					|| (cells[0][i] == cells[1][i] && cells[0][i] == cells[2][i] && cells[0][i] == 2)) {
				// System.out.println("O Row or Column win");
				return true;
			}
		}

		return false;
	}

	public ArrayList<Cell> getAvailableStates() {
		availableCells = new ArrayList<>();
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (cells[i][j] == 0) {
					availableCells.add(new Cell(i, j));
				}
			}
		}
		return availableCells;
	}

	private boolean isCellFree(int row, int col) {
		return cells[row][col] == 0 ? true : false;
	}

	public void placeAMove(Cell cell, int player) {
		if (player == 1) {
			// System.out.println("DO MOVE AS A: " + cell.row + cell.col);
		} else {
			// System.out.println("DO MOVE AS PLAYER: " + cell.row + cell.col);
		}
		cells[cell.row][cell.col] = player; // player = 1 for X, 2 for O
	}

	public boolean takeHumanInput(String move) {
		if (move.length() == 2) {
			int row = move.codePointAt(0) - 48;
			int col = move.codePointAt(1) - 48;
			if (row >= 0 && row <= 2 && col >= 0 && col <= 2 && isCellFree(row, col)) {
				Cell cell = new Cell(row, col);
				placeAMove(cell, 2);
				return true;
			}
		}
		System.out.println("\nIncorrect move!");
		return false;
	}

	public String printCell(int row, int col) {
		if (cells[row][col] == 1) {
			return "O";
		} else if (cells[row][col] == 2) {
			return "X";
		} else {
			return " ";
		}
	}

	public void printBoard() {
		String line = "";
		System.out.println();
		line = " " + printCell(0, 0) + " | " + printCell(0, 1) + " | " + printCell(0, 2);
		System.out.println(line);
		System.out.println("-----------");
		line = " " + printCell(1, 0) + " | " + printCell(1, 1) + " | " + printCell(1, 2);
		System.out.println(line);
		System.out.println("-----------");
		line = " " + printCell(2, 0) + " | " + printCell(2, 1) + " | " + printCell(2, 2);
		System.out.println(line);
	}

	public void printBoardCommands() {
		String line = "";
		System.out.println();
		line = " " + "00" + " | " + "01" + " | " + "02";
		System.out.println(line);
		System.out.println("-----------");
		line = " " + "10" + " | " + "11" + " | " + "12";
		System.out.println(line);
		System.out.println("-----------");
		line = " " + "20" + " | " + "21" + " | " + "22";
		System.out.println(line);
	}

	// private String printPosition(int i) {
	// if (i == 1) {
	// return "00";
	// } else if (i == 2) {
	// return "01";
	// } else if (i == 3) {
	// return "02";
	// } else if (i == 4) {
	// return "10";
	// } else if (i == 5) {
	// return "11";
	// } else if (i == 6) {
	// return "12";
	// } else if (i == 7) {
	// return "20";
	// } else if (i == 8) {
	// return "21";
	// } else if (i == 9) {
	// return "22";
	// } else {
	// return "Null";
	// }
	// }

}