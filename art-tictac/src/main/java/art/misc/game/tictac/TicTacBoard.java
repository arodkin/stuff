package art.misc.game.tictac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import art.misc.game.Board;
import art.misc.game.Cell;
import art.misc.game.Move;

public class TicTacBoard extends Board{
	
	static LinkedList<Cell> allCells;

	
	
	static {
		allCells = new LinkedList<Cell>();
		allCells.add(new Cell("A","1"));
		allCells.add(new Cell("A","2"));
		allCells.add(new Cell("A","3"));
		allCells.add(new Cell("B","1"));
		allCells.add(new Cell("B","2"));
		allCells.add(new Cell("B","3"));
		allCells.add(new Cell("C","1"));
		allCells.add(new Cell("C","2"));
		allCells.add(new Cell("C","3"));
	}
	
	protected TicTacBoard newInstance() {
		return new TicTacBoard();
	}
	
	/* (non-Javadoc)
	 * @see art.misc.tictac.board.Board#getPossibleMoves()
	 */
	@Override
	public Collection<Move> getRemainingMoves(LinkedList<Move> madeMoves, String player) {
		Collection<Cell> filledCells = new ArrayList<Cell>();
		for (Move m: madeMoves) {
			filledCells.add(m.getTo());
		}
		Collection<Move> rv = new ArrayList<Move>();
		for ( Cell c : allCells) {
			if(!filledCells.contains(c))
				rv.add(new Move(player, c,c));
		}
		
		return rv;
	}
	
	@Override
	protected boolean noMoreMoves(LinkedList<Move> moveList) {
		return moveList.size()  == 9;
	}
	
	
	@Override
	public boolean doIsWinningPosition(String player, LinkedList<Move> madeMoves) {
		
		Collection<Cell> cellsFilledByPlayer = new LinkedList<Cell>();
		for(Move m : madeMoves) {
			if(player.equals(m.getPlayer()))
				cellsFilledByPlayer.add(m.getTo());
		}
		if(cellsFilledByPlayer.size() < 3) 
			return false;
		
		return threeSameVerticals(cellsFilledByPlayer)  || threeSameHorizontals(cellsFilledByPlayer) || diagonal(cellsFilledByPlayer);
		
	}

	private boolean diagonal(Collection<Cell> cellsFilledByPlayer) {
		boolean center = false;
		boolean a1 = false,a3 = false,c1 = false, c3 = false;
		for(Cell c :cellsFilledByPlayer) {
			if(c.getHorizontal().equals("2")) {
				if(c.getVertical().equals("B")) 
					center = true;
			}
			else {
				if(c.getVertical().equals("A"))
					if(c.getHorizontal().equals("1")) a1 = true; else a3 = true;
				else if(c.getVertical().equals("C")) 
					if(c.getHorizontal().equals("1")) c1 = true; else c3 = true;
					
				
			}
		}
		return a1 && center && c3 || a3 && center && c1;
		
	}

	private boolean threeSameHorizontals(Collection<Cell> cellsFilledByPlayer) {
		return gotThreecounts(cellsFilledByPlayer, false);
	}
	
	private boolean gotThreecounts(Collection<Cell> cellsFilledByPlayer, boolean vertical) {
		Map<String,Integer> hCounts = new HashMap<String,Integer>();
			for (Cell c : cellsFilledByPlayer) {
				String h;
				if(vertical)
					h = c.getVertical();
				else
					h = c.getHorizontal();
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
	
	
	private boolean threeSameVerticals(Collection<Cell> cellsFilledByPlayer) {
		return gotThreecounts(cellsFilledByPlayer, true);
	}
	
	
	@Override
	public void doDrawBoard(LinkedList<Move> moves) {
		
		HashMap<Cell,String> filledCellsByPlayers = new HashMap<Cell, String>();
		
		for (Move m : moves) {
			filledCellsByPlayers.put(m.getTo(), m.getPlayer());
		}
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
		if(isDraw())
			return 0;
		return -2;
		
	}
	
	

}
