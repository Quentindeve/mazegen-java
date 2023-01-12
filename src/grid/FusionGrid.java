package grid;

import cell.FusionCell;

/**
 * @author quentin This class is the extension of Grid for the path fusion maze
 *         generation algorithm.
 */
public class FusionGrid extends Grid<FusionCell> {

	public FusionGrid(int sizeX, int sizeY) {
		super(sizeX, sizeY);
		this.cells = new FusionCell[sizeY][sizeX];

		int curr_id = 0;
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				this.cells[y][x] = new FusionCell(curr_id, x, y);
				curr_id++;
			}
		}
	}

}
