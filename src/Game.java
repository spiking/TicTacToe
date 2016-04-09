import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Board b = new Board();
		AIPlayer AI = new AIPlayer(b);
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);

		b.printBoardCommands();

		System.out.println("\n Select who should start:\n \n 1. AI \n 2. User ");
		int choice = scan.nextInt();
		if (choice == 1) {
			System.out.println("\n AI doing her move...\n");
			Cell cell = new Cell(1, 1);
			b.doMove(cell, 1);
			b.printBoard();
		}

		while (!b.isGameOver()) {
			boolean validMove = false;
			while (!validMove) {
				System.out.println("\n Your move: ");
				String move = scan.next();
				validMove = b.takeHumanInput(move);
				b.printBoard();
			}

			if (b.isGameOver())
				break;

			// AI PLAYER
			
			System.out.println("\n AI doing her move...\n");
			AI.minimax(0, 1); // 0 depth and 1 turn
			AI.doMove(AI.returnBestMove(), 1);
			b.printBoard();
		}

		if (b.hasUserWon())
			System.out.println("\n AI WON!");
		else if (b.hasAIWon())
			System.out.println("\n USER WON!!"); // Impossible
		else
			System.out.println("IT'S A DRAW!!");
	}
}
