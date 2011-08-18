package art.misc.tictac.Board;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import art.misc.game.Board;
import art.misc.game.tictac.TicTacBoard;
import art.misc.game.tictac.TictacMove;

public class BoardTest {
	
	
	@Test
	public void testDiagonal() {
		List<TictacMove> xMoves = new ArrayList<TictacMove>();
		xMoves.add(new TictacMove("A", "1"));
		xMoves.add(new TictacMove("B","2"));
		xMoves.add(new TictacMove("C","3"));
		
		List<TictacMove> oMoves = new ArrayList<TictacMove>();
		oMoves.add(new TictacMove("A", "2"));
		oMoves.add(new TictacMove("B","3"));
		oMoves.add(new TictacMove("C","2"));
		
		TicTacBoard b = new TicTacBoard();
		
		populateBoard(xMoves, oMoves, b);
		
		assert(b.isWinningPosition("X"));
		
		xMoves = new ArrayList<TictacMove>();
		xMoves.add(new TictacMove("A", "3"));
		xMoves.add(new TictacMove("B","2"));
		xMoves.add(new TictacMove("C","1"));
		
		oMoves = new ArrayList<TictacMove>();
		oMoves.add(new TictacMove("A", "2"));
		oMoves.add(new TictacMove("B","3"));
		oMoves.add(new TictacMove("C","2"));
		
		b = new TicTacBoard();
		
		populateBoard(xMoves, oMoves, b);
		
		assert(b.isWinningPosition("X"));
	}

	private void populateBoard(List<TictacMove> xMoves, List<TictacMove> oMoves, Board<TictacMove> b) {
		try {
			for(TictacMove m : xMoves) {
				b.placeMove(m, "X");
			}
			
			for(TictacMove m : oMoves) {
				b.placeMove(m, "O");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
