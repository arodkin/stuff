package art.misc.game;

import java.util.Collection;
import java.util.LinkedList;


public abstract class Board {
	
	private LinkedList<Move> moves = new LinkedList<Move>();
	
	

	public  void placeMove(Move move, String player)
			throws Exception {
		moves.add(move);
	}

	public abstract Collection<Move> getPossibleMoves();

	/* (non-Javadoc)
	 * @see art.misc.tictac.board.Board#gameOver()
	 */
	@Override
	public boolean gameOver() {
		Move lastMove = 
		if(allFilled())
			return true;
		return isWinningPosition();
	}


	public abstract int score(String player);
	
	public abstract void draw();
	
	public abstract Board clone();
	
	public abstract boolean isWinningPosition(String player);

}