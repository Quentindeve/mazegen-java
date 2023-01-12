package cell;

/**
 * @author quentin This is the Cell's extension for the path fusion maze
 *         generation algorithm.
 */
public class FusionCell extends Cell {
	/**
	 * The identifier of the cell. This identifier MUST be unique at initialization
	 * of the algorithm. This identifier MUST be common to all cells at the end of
	 * the algorithm.
	 */
	private int id;

	public FusionCell(int id, int posX, int posY) {
		super(posX, posY);
		this.id = id;
	}

	/**
	 * Sets the new ID of the Cell.
	 * 
	 * @param newId the new ID of the Cell.
	 */
	public void setId(int newId) {
		this.id = newId;
	}

	/**
	 * Returns the new ID of the Cell.
	 * 
	 * @return the ID of the cell
	 */
	public int getId() {
		return this.id;
	}
}
