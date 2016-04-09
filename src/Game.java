import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Board b = new Board();
		AIPlayer AI = new AIPlayer(b);
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);

		b.printBoardCommands();

		System.out.println("\n Select turn:\n \n 1. Computer \n 2. User ");
		int choice = scan.nextInt();
		if (choice == 1) {
			Cell c = new Cell(1, 1);
			b.placeAMove(c, 1);
			b.printBoard();
		} 

		while (!b.isGameOver()) {
			boolean validMove = false;
			while(!validMove) {
				System.out.println("\n Your move: ");
				String move = scan.next();
				validMove = b.takeHumanInput(move);
				b.printBoard();
			}

			if (b.isGameOver())
				break;

			// AI PLAYER
			
			AI.minimax(0, 1); // depth, turn in MINIMAX tree 
			AI.placeAMove(AI.returnBestMove(), 1);
			b.printBoard();
		}
		
		if (b.hasXWon())
			System.out.println("\n AI WON!");
		else if (b.hasOWon())
			System.out.println("\n YOY WON!!"); // Can't happen
		else
			System.out.println("IT'S A DRAW!!");
	}

}
