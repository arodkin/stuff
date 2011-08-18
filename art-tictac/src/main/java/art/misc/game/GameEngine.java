package art.misc.game;

import java.util.List;
import java.util.TreeMap;


public class GameEngine<M extends Move> {
	
	
	private Node<BoardState<M>> decisionTree;
	private Board<M> board;
	private int count = 0;
	
	public  GameEngine(Board<M> b, String firstPlayer) throws Exception{
		decisionTree  = new Node<BoardState<M>>(null,null);
		board = b;
		initDecisionTree(board,firstPlayer, decisionTree);
	}
	
	private  void initDecisionTree(Board<M> b, String player, Node<BoardState<M>> parent) throws Exception {
		for (M c : b.getPossibleMoves()) {
			Board<M> b1 = b.clone();
			b1.placeMove(c, player);
			
			BoardState<M> ttdd = new BoardState<M>(b1, c, player);
			Node<BoardState<M>> n = createNode(ttdd,parent);
			
			
			parent.addChild(n);
			if(!b1.gameOver()) {
				String pl1 = switchPlayer(player);
				initDecisionTree(b1,pl1,n);
			} else {
				Utils.log("Stopping at this combination, count " + ++count, b1, true);
			}
		}
		
	}
	
	public M bestMove(String player) throws Exception {
		List<Node<BoardState<M>>> children = decisionTree.findFirstLevelChildren();
		TreeMap<Integer,M> sortedMoves = new TreeMap<Integer,M>();
		for (M m : board.getPossibleMoves()) {
			Board<M> b = board.clone();
			b.placeMove(m, player);
			//Circumvent
			if(b.isWinningPosition(player))
				return m;
			BoardState<M> bs = new BoardState<M>(b, m, player);
			Node<BoardState<M>> newPossibleState = findAppropriatePosition(bs,children );
			List<Node<BoardState<M>>> possibleFinalStates = getPossibleOutcomesForPosition(newPossibleState);
			int score = scoreMove(possibleFinalStates, player);
			sortedMoves.put(score, m);
		}
		
		return sortedMoves.pollLastEntry().getValue();
	}
	
	
	private int scoreMove(List<Node<BoardState<M>>> possibleFinalStates, String player) {
		int totalScore = 0;
		for(Node<BoardState<M>> nbs : possibleFinalStates) {
			Board<M> b = nbs.getData().getBoard();
			GameEnding<M> ge = new GameEnding<M>(nbs);
			String oppositePlayer = switchPlayer(player);
			if(b.isWinningPosition(player))
				totalScore +=10 - ge.getDistanceTo();
			else if(b.isWinningPosition(oppositePlayer))
				totalScore -=10;

		}
		
		return totalScore;
	}


	private Node<BoardState<M>> findAppropriatePosition(BoardState<M> bs,
			List<Node<BoardState<M>>> children) throws Exception {
		for(Node<BoardState<M>> n : children) {
			if(n.getData().equals(bs))
				return n;
		}
		
		throw new Exception("Unable to find appropriate board state for " + bs.toString());
	}


	public void placeMove(M m, String player) throws Exception{
		board.placeMove(m, player);
		BoardState<M> bs = new BoardState<M>(board, m, player);
		List<Node<BoardState<M>>> children = decisionTree.findFirstLevelChildren();
		for(Node<BoardState<M>> nbs : children) {
			if(nbs.getData().equals(bs)) {
				decisionTree = nbs;
			} else {
				nbs.clearAll();
				nbs = null;
			}
		}
		
	}

	
	
	private List<Node<BoardState<M>>> getPossibleOutcomesForPosition(Node<BoardState<M>> n) {
		List<Node<BoardState<M>>> nodes = n.getLeafData();
		return nodes;
	}
	
	
	private static String switchPlayer(String player) {
		if(player.equals("X"))
			return "O";
		return "X";
	}

	private  Node<BoardState<M>> createNode(BoardState<M> ttdd, Node<BoardState<M>> parent) {
		Node<BoardState<M>> n = new Node<BoardState<M>>(ttdd,parent);
		return n;
	}

}
