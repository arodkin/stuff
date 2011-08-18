package art.misc.game.tictac;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import art.misc.game.GameEngine;

public class TicTacGame {

	public static void main(String[] args) {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		TicTacBoard b = new TicTacBoard();
		

		TictacMove m;
		try {
			
			GameEngine<TictacMove> gp = new GameEngine<TictacMove>(b,"X");
			b.draw();
			while(true) {
				m = gp.bestMove("X");
				gp.placeMove(m, "X");

				b.draw();
				
				if(b.isWinningPosition("X")) {
					System.out.println("I win");
					break;
				}
					

				System.out.println("MOVE!");

				String s = in.readLine();
				TictacMove m1 = parse(s);
				gp.placeMove(m1, "O");
				
				b.draw();
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

	private static TictacMove parse(String s) {
		String vertical = s.substring(0, 1);
		String horizontal = s.substring(1);
		return new TictacMove(vertical,horizontal);
	}

}
