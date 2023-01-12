package labyrinthe.mazegen;

import labyrinthe.mazegen.grid.Grid;

/**
 * 
 * @author quentin This abstract class represents basic functions to implement
 *         for a maze generator algorithm.
 */
public abstract class Mazegen<G extends Grid> {
	protected G grid;

	public Mazegen(G grid) {
		this.grid = grid;
	}

	/**
	 * Applies the maze generation algorithm.
	 * 
	 * @return a processed Grid.
	 */
	public abstract G apply();

	/**
	 * Applies one iteration of the maze generation algorithm
	 * 
	 * @return true if operated, false otherwise
	 */
	public abstract boolean iterate();

	/**
	 * Indicates if the maze generation algorithm finished its job or not
	 * 
	 * @return true if finished, false otherwise
	 */
	public abstract boolean finished();
}
