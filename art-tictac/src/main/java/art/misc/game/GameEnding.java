package art.misc.game;


public class GameEnding<M extends Move> {
	
	private int distanceTo;
	public int getDistanceTo() {
		return distanceTo;
	}
	public GameEnding(Node<BoardState<M>> bs) {
		while(true) {
			bs = bs.getParent();
			if(bs == null)
				break;
			distanceTo++;
		}
	}
	
}
