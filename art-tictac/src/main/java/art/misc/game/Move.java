package art.misc.game;

public class Move {

	private String player;
	private Cell from;
	private Cell to;

	public String getPlayer() {
		return player;
	}


	public Move(String player, Cell from, Cell to) {
		super();
		this.player = player;
		this.from = from;
		this.to = to;
	}


	public Cell getFrom() {
		return from;
	}
	public Cell getTo() {
		return to;
	}
}