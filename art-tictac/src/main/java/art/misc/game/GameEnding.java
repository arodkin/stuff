package art.misc.game;


public class GameEnding {
	
	private int distanceTo;
	public int getDistanceTo() {
		return distanceTo;
	}
	public GameEnding(Node<Board> bs) {
		while(true) {
			bs = bs.getParent();
			if(bs == null)
				break;
			distanceTo++;
		}
	}
	
}
