
public class Cell {
	
	private int row;
	private int col;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "[" + row + ", " + col + "]";
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}
