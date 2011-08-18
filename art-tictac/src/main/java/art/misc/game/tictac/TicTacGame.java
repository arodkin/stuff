package art.misc.game.tictac;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import art.misc.game.Cell;
import art.misc.game.GameEngine;
import art.misc.game.Move;

public class TicTacGame {

	public static void main(String[] args) {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		TicTacBoard b = new TicTacBoard();
		
		try {
			
			GameEngine gp = new GameEngine(b,"X");
			b.drawBoard();
			while(true) {
				Move m = gp.bestMove("X");
				gp.placeMove(m);

				b.drawBoard();
				
				if(b.isWinningPosition("X")) {
					System.out.println("I win");
					break;
				}
					

				System.out.println("MOVE!");

				String s = in.readLine();
				Move m1 = parse(s);
				gp.placeMove(m1);
				
				b.drawBoard();
				if(b.isWinningPosition("O")) {
					System.out.println("U win");
					break;
				}
				
			}

			// An empty line or Ctrl-Z terminates the program


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Move parse(String s) {
		String vertical = s.substring(0, 1);
		String horizontal = s.substring(1);
		return new Move("O",new Cell(vertical,horizontal), new Cell(vertical,horizontal));
	}

}
