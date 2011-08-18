package art.misc.game;

import java.util.List;
import java.util.TreeMap;


public class GameEngine {
	
	
	private Node<Board> decisionTree;
	private Board board;
	private int count = 0;
	
	public  GameEngine(Board b, String firstPlayer) throws Exception{
		decisionTree  = new Node<Board>(null,null);
		board = b;
		initDecisionTree(board,firstPlayer, decisionTree);
	}
	
	private  void initDecisionTree(Board b, String player, Node<Board> parent) throws Exception {
		for (Move m : b.getPossibleMoves(player)) {
			Board b1 = b.clone();
			b1.placeMove(m);
			
			Node<Board> n = createNode(b1,parent);
			
			
			parent.addChild(n);
			if(!b1.gameOver()) {
				String pl1 = switchPlayer(player);
				initDecisionTree(b1,pl1,n);
			} else {
				Utils.log("Stopping at this combination, count " + ++count, b1, true);
			}
		}
		
	}
	
	public Move bestMove(String player) throws Exception {
		List<Node<Board>> children = decisionTree.findFirstLevelChildren();
		TreeMap<Integer,Move> sortedMoves = new TreeMap<Integer,Move>();
		for (Move m : board.getPossibleMoves(player)) {
			Board b = board.clone();
			b.placeMove(m);
			//Circumvent
			if(b.isWinningPosition(player))
				return m;
			Node<Board> newPossibleState = findAppropriatePosition(b,children );
			List<Node<Board>> possibleFinalStates = getPossibleOutcomesForPosition(newPossibleState);
			int score = scoreMove(possibleFinalStates, player);
			sortedMoves.put(score, m);
		}
		
		return sortedMoves.pollLastEntry().getValue();
	}
	
	
	private int scoreMove(List<Node<Board>> possibleFinalStates, String player) {
		int totalScore = 0;
		for(Node<Board> nbs : possibleFinalStates) {
			Board b = nbs.getData();
			GameEnding ge = new GameEnding(nbs);
			String oppositePlayer = switchPlayer(player);
			if(b.isWinningPosition(player))
				totalScore +=10 - ge.getDistanceTo();
			else if(b.isWinningPosition(oppositePlayer))
				totalScore -=10;

		}
		
		return totalScore;
	}


	private Node<Board> findAppropriatePosition(Board b,
			List<Node<Board>> children) throws Exception {
		for(Node<Board> n : children) {
			if(n.getData().equals(b))
				return n;
		}
		
		throw new Exception("Unable to find appropriate board state for " + b.toString());
	}


	public void placeMove(Move m) throws Exception{
		board.placeMove(m);
		List<Node<Board>> children = decisionTree.findFirstLevelChildren();
		for(Node<Board> nbs : children) {
			if(nbs.getData().equals(board)) {
				decisionTree = nbs;
			} else {
				nbs.clearAll();
				nbs = null;
			}
		}
		
	}

	
	
	private List<Node<Board>> getPossibleOutcomesForPosition(Node<Board> n) {
		List<Node<Board>> nodes = n.getLeafData();
		return nodes;
	}
	
	
	private static String switchPlayer(String player) {
		if(player.equals("X"))
			return "O";
		return "X";
	}

	private  Node<Board> createNode(Board b, Node<Board> parent) {
		Node<Board> n = new Node<Board>(b,parent);
		return n;
	}

}
