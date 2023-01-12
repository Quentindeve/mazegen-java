package grid;

import cell.ExplorationCell;
import Orientation;

/**
 * @author quentin This is the Cell's extension for the exploration maze
 *         generation algorithm.
 */
public class ExplorationGrid extends Grid<ExplorationCell> {

	public ExplorationGrid(int sizeX, int sizeY) {
		super(sizeX, sizeY);

		this.cells = new ExplorationCell[sizeY][sizeX];

		// Initializing each cell
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				this.cells[y][x] = new ExplorationCell(x, y);
			}
		}
	}

	/**
	 * Gets a unvisited adjacent of ``cell``.
	 * 
	 * @param cell the cell to get an unvisited adjacent.
	 * @return An unvisited adjacent of ``cell``.
	 */
	public ExplorationCell unvisitedAdjacent(ExplorationCell cell) {
		for (Orientation orientation : Orientation.values()) {
			ExplorationCell adjacent = this.checkAdjacentCells(cell, orientation);

			if (adjacent == null)
				continue;

			if (!adjacent.isVisited())
				return adjacent;
		}
		return null;
	}
}
