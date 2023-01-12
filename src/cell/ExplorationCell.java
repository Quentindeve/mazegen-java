package cell;

/**
 * @author quentin This class is the Cell's extension for the exploration maze
 *         generation algorithm.
 */
public class ExplorationCell extends Cell {
	/**
	 * True if the cell is visited, false otherwise.
	 */
	private boolean visited;

	public ExplorationCell(int posX, int posY) {
		super(posX, posY);
		this.visited = false;
	}

	/**
	 * @return true if the cell is visited, false otherwise.
	 */
	public boolean isVisited() {
		return this.visited;
	}

	/**
	 * Sets the new visit state of the cell.
	 * 
	 * @param b the new visit state of the cell.
	 */
	public void setVisited(boolean b) {
		this.visited = b;
	}
}
