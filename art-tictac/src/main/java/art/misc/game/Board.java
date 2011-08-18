package art.misc.game;

import java.util.Collection;
import java.util.LinkedList;


public abstract class Board {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moves == null) ? 0 : moves.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (moves == null) {
			if (other.moves != null)
				return false;
		} else if (!moves.equals(other.moves))
			return false;
		return true;
	}


	private LinkedList<Move> moves = new LinkedList<Move>();
	
	

	public  void placeMove(Move move)
			throws Exception {
		moves.add(move);
	}

	public Collection<Move> getPossibleMoves(String player) {
		return getRemainingMoves(moves, player);
	}

	
	protected abstract Collection<Move> getRemainingMoves(LinkedList<Move> moves, String player);

	public boolean gameOver() {
		String playerLastMoved = moves.getLast().getPlayer();
		
		return isWinningPosition(playerLastMoved) || isDraw();
	}

	public  boolean isDraw() {
		String playerLastMoved = moves.getLast().getPlayer();
		return !isWinningPosition(playerLastMoved) && noMoreMoves(moves);
	}

	protected abstract boolean noMoreMoves(LinkedList<Move> moveList);

	public abstract int score(String player);
	
	public void drawBoard() {
		doDrawBoard(moves);
	}
	
	protected abstract void doDrawBoard(LinkedList<Move> moves);

	
	public Board clone() {
		Board b = newInstance();
		b.moves = new LinkedList<Move>(moves);
		return b;
	}
	
	protected abstract Board newInstance();
	
	public  boolean isWinningPosition(String player) {
		return doIsWinningPosition(player,moves);
	}
	
	
	protected abstract boolean doIsWinningPosition(String player, LinkedList<Move> moves) ; 

}