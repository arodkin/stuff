package art.misc.game;

import java.util.ArrayList;
import java.util.List;




public class Node<T> {
    T data;
    Node<T> parent;
    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

    public boolean isLeaf() {
    	return children.size() == 0;
    }

	List<Node<T>> children = new ArrayList<Node<T>>();
    
    public Node(T data, Node<T> parent) {
    	this.data = data;
    	this.parent = parent;
    }
    
    
    
    public void addChild(Node<T> node) {
    	children.add(node);
    }
    
    
    public List<Node<T>> getLeafData() {
    	List<Node<T>> rv = new ArrayList<Node<T>>();
    	if(isLeaf()) {
    		rv.add(this);
    		return rv;
    	}
    	List<Node<T>> children= getChildren();
    	
		for (Node<T> node : children) {
			if(node.isLeaf())
				rv.add(node);
			else
				rv.addAll(node.getLeafData());
		}
		
		return rv;
    }

	public List<Node<T>> findFirstLevelChildren() {
		return getChildren();
	}

	public void clearAll() {
		List<Node<T>> children= getChildren();
		for (Node<T> n : children) {
			if(n.isLeaf()) {
				n = null;
			} else {
				n.clearAll();
			}
		}
		
	}
}
