package model.board;

public class Tile {
	public static enum Color {  // TODO: Place Color in right class. Change for array.
		RED('1'), BLUE('2'), GREEN('3'), YELLOW('4'), ORANGE('5'),
		VIOLET('6'), PINK('7'), BROWN('8'), GRAY('9'), EMPTY(' '); 
		
		private final char c;
		
		private Color(char c){
			this.c = c; 
		}
		
		public char getChar(){
			return c;
		}
	}
	
	private Color color;
	
	public Tile(Color color){
		this.color = color;
	}
	
	public boolean isEmpty() {
		return color == Color.EMPTY;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color.c;
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

	public Color getColor() {
		return color;
	}
	
	public String toString(){
		return color.name();
	}
}