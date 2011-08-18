package art.misc.game.tictac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import art.misc.game.Board;

public class TicTacBoard extends Board{
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((filledCellsByPlayers == null) ? 0 : filledCellsByPlayers
						.hashCode());
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
		TicTacBoard other = (TicTacBoard) obj;
		if (filledCellsByPlayers == null) {
			if (other.filledCellsByPlayers != null)
				return false;
		} else if (!filledCellsByPlayers.equals(other.filledCellsByPlayers))
			return false;
		return true;
	}
	private LinkedHashMap<TictacMove,String> filledCellsByPlayers = new LinkedHashMap<TictacMove,String>();
	
	static List<TictacMove> allCells;
	String lastPlayerMoved;
	
	
	static {
		allCells = new ArrayList<TictacMove>();
		allCells.add(new TictacMove("A","1"));
		allCells.add(new TictacMove("A","2"));
		allCells.add(new TictacMove("A","3"));
		allCells.add(new TictacMove("B","1"));
		allCells.add(new TictacMove("B","2"));
		allCells.add(new TictacMove("B","3"));
		allCells.add(new TictacMove("C","1"));
		allCells.add(new TictacMove("C","2"));
		allCells.add(new TictacMove("C","3"));
	}
	
	public TicTacBoard() {
		
	}
	private TicTacBoard(LinkedHashMap<TictacMove, String> filledCellsByPlayer2) {
		this.filledCellsByPlayers = filledCellsByPlayer2;
	}

	
	public TicTacBoard clone() {
		return new TicTacBoard(new LinkedHashMap<TictacMove,String>(filledCellsByPlayers));
	}
	
	/* (non-Javadoc)
	 * @see art.misc.tictac.board.Board#getPossibleMoves()
	 */
	@Override
	public Collection<TictacMove> getPossibleMoves() {
		Set<TictacMove> filledCells = filledCellsByPlayers.keySet();
		Collection<TictacMove> rv = new ArrayList<TictacMove>();
		for ( TictacMove c : allCells) {
			if(!filledCells.contains(c))
				rv.add(c);
		}
		
		return rv;
	}
	
	private boolean allFilled() {
		return filledCellsByPlayers.size()  == 9;
	}
	
	
	@Override
	public boolean isWinningPosition(String player) {
		Collection<TictacMove> cellsFilledByPlayer = new ArrayList<TictacMove>();
		for(TictacMove c : filledCellsByPlayers.keySet()) {
			if(player.equals(filledCellsByPlayers.get(c)))
				cellsFilledByPlayer.add(c);
		}
		if(cellsFilledByPlayer.size() < 3) 
			return false;
		
		return threeSameVerticals(cellsFilledByPlayer)  || threeSameHorizontals(cellsFilledByPlayer) || diagonal(cellsFilledByPlayer);
		
	}

	private boolean diagonal(Collection<TictacMove> cellsFilledByPlayer) {
		boolean center = false;
		boolean a1 = false,a3 = false,c1 = false, c3 = false;
		for(TictacMove c :cellsFilledByPlayer) {
			if(c.horizontal.equals("2")) {
				if(c.vertical.equals("B")) 
					center = true;
			}
			else {
				if(c.vertical.equals("A"))
					if(c.horizontal.equals("1")) a1 = true; else a3 = true;
				else if(c.vertical.equals("C")) 
					if(c.horizontal.equals("1")) c1 = true; else c3 = true;
					
				
			}
		}
		return a1 && center && c3 || a3 && center && c1;
		
	}

	private boolean threeSameHorizontals(Collection<TictacMove> cellsFilledByPlayer) {
		return gotThreecounts(cellsFilledByPlayer, false);
	}
	
	private boolean gotThreecounts(Collection<TictacMove> cellsFilledByPlayer, boolean vertical) {
		Map<String,Integer> hCounts = new HashMap<String,Integer>();
			for (TictacMove c : cellsFilledByPlayer) {
				String h;
				if(vertical)
					h = c.vertical;
				else
					h = c.horizontal;
				Integer count = hCounts.get(h);
				if(count == null) {
					count = 0;
				}
				count++;
				hCounts.put(h, count);

			}
			for(Integer ct : hCounts.values())
				if(ct == 3)
					return true;
			return false;
	}
	
	/* (non-Javadoc)
	 * @see art.misc.tictac.board.Board#gameOver()
	 */
	@Override
	public boolean gameOver() {
		if(allFilled())
			return true;
		return isWinningPosition(lastPlayerMoved);
	}

	private boolean threeSameVerticals(Collection<TictacMove> cellsFilledByPlayer) {
		return gotThreecounts(cellsFilledByPlayer, true);
	}
	
	
	@Override
	public void draw() {
		System.out.print((filledCellsByPlayers.get(allCells.get(0)) != null) ? filledCellsByPlayers.get(allCells.get(0)): " ");
		System.out.print("|");
		System.out.print((filledCellsByPlayers.get(allCells.get(1)) != null) ? filledCellsByPlayers.get(allCells.get(1)): " ");
		System.out.print("|");
		System.out.println((filledCellsByPlayers.get(allCells.get(2)) != null) ? filledCellsByPlayers.get(allCells.get(2)): " ");
		
		System.out.println("_ _ _" );
		
		System.out.print((filledCellsByPlayers.get(allCells.get(3)) != null) ? filledCellsByPlayers.get(allCells.get(3)): " ");
		System.out.print("|");
		System.out.print((filledCellsByPlayers.get(allCells.get(4)) != null) ? filledCellsByPlayers.get(allCells.get(4)): " ");
		System.out.print("|");
		System.out.println((filledCellsByPlayers.get(allCells.get(5)) != null) ? filledCellsByPlayers.get(allCells.get(5)): " ");
		
		System.out.println("_ _ _" );
		
		
		System.out.print((filledCellsByPlayers.get(allCells.get(6)) != null) ? filledCellsByPlayers.get(allCells.get(6)): " ");
		System.out.print("|");
		System.out.print((filledCellsByPlayers.get(allCells.get(7)) != null) ? filledCellsByPlayers.get(allCells.get(7)): " ");
		System.out.print("|");
		System.out.print((filledCellsByPlayers.get(allCells.get(8)) != null) ? filledCellsByPlayers.get(allCells.get(8)): " ");
		
		
		System.out.println("");
		
	}
	/* (non-Javadoc)
	 * @see art.misc.tictac.board.Board#score(java.lang.String)
	 */
	@Override
	public int score(String player) {
		if(isWinningPosition(player))
			return 1;
		if(allFilled())
			return 0;
		return -2;
		
	}
	
	

}
