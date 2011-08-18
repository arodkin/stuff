package art.misc.game.tictac;

import art.misc.game.Move;

public class  TictacMove implements Move {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((horizontal == null) ? 0 : horizontal.hashCode());
		result = prime * result
				+ ((vertical == null) ? 0 : vertical.hashCode());
		return result;
	}
	
	public String toString() {
		return "V" + vertical + "H" + horizontal
;	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TictacMove other = (TictacMove) obj;
		if (horizontal == null) {
			if (other.horizontal != null)
				return false;
		} else if (!horizontal.equals(other.horizontal))
			return false;
		if (vertical == null) {
			if (other.vertical != null)
				return false;
		} else if (!vertical.equals(other.vertical))
			return false;
		return true;
	}

	String vertical;
	String horizontal;
	
	public TictacMove (String vertical, String horizontal) {
		this.vertical = vertical;
		this.horizontal = horizontal;
	}
}