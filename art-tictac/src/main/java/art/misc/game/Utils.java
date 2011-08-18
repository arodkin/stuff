package art.misc.game;


public class Utils {
	
	
	private static boolean debug = true;
	
	public static<T extends Move> void log(String s, Board b, boolean drawBoard) {
		if(debug) {
			System.out.println(s);
			if(drawBoard)
				b.drawBoard();
		}
	}

}
