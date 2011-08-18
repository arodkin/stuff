package art.misc.game;

public class Cell {
	
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
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
	public String getVertical() {
		return vertical;
	}

	public String getHorizontal() {
		return horizontal;
	}

	String horizontal;
	
	public Cell (String vertical, String horizontal) {
		this.vertical = vertical;
		this.horizontal = horizontal;
	}

}
