package art.misc.game;

public class BoardState<M extends Move> {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		BoardState<M> other = (BoardState<M>) obj;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}
	public BoardState(Board<M> b, M move, String player) {
		super();
		this.b = b;
		this.move = move;
		this.player = player;
	}
	Board<M> b;
	public Board<M> getBoard() {
		return b;
	}
	public M getMove() {
		return move;
	}
	public String getPlayer() {
		return player;
	}
	M move;
	String player;

}
