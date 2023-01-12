import java.util.Random;
import java.util.Stack;

import cell.ExplorationCell;
import grid.ExplorationGrid;

/**
 * @author quentin This is the implementation of the exploration maze generation
 *         algorithm.
 */
public class ExplorationAlgorithm extends Mazegen<ExplorationGrid> {
	private Random random;
	private Stack<ExplorationCell> visitStack;

	public ExplorationAlgorithm(int sizeX, int sizeY) {
		super(new ExplorationGrid(sizeX, sizeY));
		this.random = new Random();
		this.visitStack = new Stack<>();
		this.grid = new ExplorationGrid(sizeX, sizeY);
	}

	@Override
	public ExplorationGrid apply() {
		ExplorationCell nextVisit = this.grid.randomCell(this.random);
		this.visite(nextVisit);
		return this.grid;
	}

	@Override
	public boolean iterate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finished() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Visites the cell passed as ``visit``. This is a recursive function that is
	 * executing the whole algorithm alone.
	 * 
	 * @param visit the cell to visite.
	 */
	private void visite(ExplorationCell visit) {
		// This cell is now visited
		this.setAsVisited(visit);

		// We break a random wall of the cell, which is adjacent to an unvisited cell
		for (Orientation orientation : Orientation.values()) {
			ExplorationCell adjacent = this.grid.checkAdjacentCells(visit, orientation);

			if (adjacent != null && !adjacent.isVisited()) {
				this.grid.breakWall(visit, orientation);
				ExplorationCell unvisitedAdjacent = this.grid.unvisitedAdjacent(visit);

				if (unvisitedAdjacent != null)
					this.visite(unvisitedAdjacent);

				else {
					ExplorationCell nearestUnvisited = this.nearestUnvisited();
					if (nearestUnvisited == null)
						return;

					else
						this.visite(nearestUnvisited);
				}
			}
		}
	}

	/**
	 * Sets the cell ``c`` as visited. Pushes it into the ``visitStack``.
	 * 
	 * @param c the cell to set as visited and to push.
	 */
	private void setAsVisited(ExplorationCell c) {
		c.setVisited(true);
		this.visitStack.push(c);
	}

	/**
	 * Unwinds the stack until find an unvisited cell.
	 * 
	 * @return null if there is no unvisited cell in the stack, an unvisited cell
	 *         otherwise.
	 */
	private ExplorationCell nearestUnvisited() {
		ExplorationCell top = this.visitStack.pop();

		if (this.visitStack.empty())
			return null;

		while (this.grid.unvisitedAdjacent(top) == null) {
			top = this.visitStack.pop();
		}

		return top;
	}

}
