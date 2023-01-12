import java.util.Random;

import cell.FusionCell;
import grid.FusionGrid;

/**
 * @author Quentin
 *         Implementation of the path-fusion maze generation algorithm
 */
public class FusionAlgorithm extends Mazegen<FusionGrid> {
	private Random r;

	/**
	 * construteur de FusionAlgorithme
	 * 
	 * @param sizeX the vertical size
	 * @param sizeY the Horizontal size
	 */
	public FusionAlgorithm(int sizeX, int sizeY) {
		super(new FusionGrid(sizeX, sizeY));
		this.r = new Random();
	}

	@Override
	public FusionGrid apply() {
		int nbIter = 0;
		this.iterate();

		do {
			boolean iterated = this.iterate();
			if (iterated)
				nbIter++;
		} while (!this.finished());

		System.out.println("Algorithm ended. nb_iter = " + nbIter);
		return this.grid;
	}

	public boolean iterate() {
		// Choose random case and random orientation.
		FusionCell c = this.grid.randomCell(this.r);
		Orientation orientation = Orientation.chooseRandomOrientation(r);
		// If adjacent case exists and the wall we want to open isn't yet, we can break
		// the wall
		FusionCell adjacent = this.grid.checkAdjacentCells(c, orientation);
		if (adjacent == null)
			return false;

		if (c.getId() != adjacent.getId()) {
			this.grid.breakWall(c, orientation);
			this.adjacentSetIdOfOpened(c, c.getId());
			return true;
		}
		return false;
	}

	public boolean finished() {
		int lastId = grid.getCells()[0][0].getId();
		for (FusionCell[] line : grid.getCells()) {
			for (FusionCell c : line) {
				if (lastId != c.getId())
					return false;
			}
		}
		return true;
	}

	/**
	 * Checks Sets the ID of c to the the adjacent opened cells
	 * 
	 * @param c     the cell to check
	 * @param newId the id to set
	 */
	public void adjacentSetIdOfOpened(FusionCell c, int newId) {
		if (c == null) {
			return;
		}

		// We check for each orientation the wall state of the Case c
		for (Orientation o : Orientation.values()) {
			// Get the case at the orientation o
			FusionCell caseCheck = this.grid.checkAdjacentCells(c, o);
			// If the case at this orientation doesn't exist then we stop to look
			if (caseCheck == null)
				continue;

			// If wall of c and its adjacent are connected, we set the same id for both.
			if (c.isWallOpened(o) && caseCheck.isWallOpened(o.invert()) && c.getId() != caseCheck.getId()) {
				c.setId(newId);
				caseCheck.setId(newId);
				// Recursive call
				adjacentSetIdOfOpened(caseCheck, newId);
			}
		}
	}
}
