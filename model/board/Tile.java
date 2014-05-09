package model.board;

public class Tile {
	private char color;
	
	public Tile(char color){
		this.color = color;
	}
	
	public boolean isEmpty() {
		return color == ' ';
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
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
		Tile other = (Tile) obj;
		if (color != other.color)
			return false;
		return true;
	}
	
	
}