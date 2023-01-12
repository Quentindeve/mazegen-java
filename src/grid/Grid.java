package grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import Orientation;
import cell.Cell;

/**
 * @author quentin This class is the generic Grid that is the base for each
 *         specific grid for each maze generation algorithm.
 * @param <C> the Cell's extension of the algorithm
 */
public abstract class Grid<C extends Cell> {
	/**
	 * A 2-dimensional array representing the maze.
	 */
	protected C[][] cells;
	/**
	 * The sizes of the ``cell`` attribute.
	 */
	private final int length;
	private final int width;

	/**
	 * The constructor of a Grid. Note that this constructor doesn't implement the
	 * ``cells`` attribute: it is the responsibility of the child to initialize it.
	 * 
	 * @param length the length of the grid
	 * @param width the width of the grid
	 */
	public Grid(int length, int width) {
		this.length = length;
		this.width = width;
	}

	/**
	 * This method breaks the wall of a Case at the specified orientation and it's
	 * adjacent at the opposite orientation to connect all cases.
	 * 
	 * @param c the case to break the wall.
	 * @param o the orientation where we break the wall of ``c``.
	 */
	public void breakWall(C c, Orientation o) {
		C adjacent = this.checkAdjacentCells(c, o);
		if (adjacent == null)
			return;

		c.breakWall(o);
		adjacent.breakWall(o.invert());
	}

	/**
	 * @return the 2-dimensional array attribute `cases`
	 */
	public C[][] getCells() {
		return this.cells;
	}

	/**
	 * Checks for the adjacent cell of ``c`` at the orientation ``o``
	 * 
	 * @param c the cell to check for adjacent
	 * @param o the orientation to check
	 * @return the cell at the side ``o``
	 */
	public C checkAdjacentCells(C c, Orientation o) {
		int deltaX = o.getDeltaX();
		int deltaY = o.getDeltaY();

		return this.getCellByPos(c.getPosX() + deltaX, c.getPosY() + deltaY);
	}

	/**
	 * Gets a cell at the specified position
	 * 
	 * @param posX the X position of the case
	 * @param posY the Y position of the case
	 * @return the cell that has {posX; posY} coords, null if non-existent.
	 */
	public C getCellByPos(int posX, int posY) {
		try {
			return this.cells[posY][posX];
		}
		// If coords are out of bounds we return null
		catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * @return the width of the grid
	 */
	public int getWidth() {
		return this.length;
	}

	public int getHeight() {
		return this.width;
	}

	/**
	 * Returns a random cell of the grid.
	 * 
	 * @param r the random used to get a cell.
	 * @return a random cell of the grid.
	 */
	public C randomCell(Random r) {
		int randomX = r.nextInt(this.length);
		int randomY = r.nextInt(this.width);

		return this.cells[randomY][randomX];
	}

	/**
	 * Returns a random adjacent of the ``cell`` parameter.
	 * 
	 * @param cell the cell to get a random adjacent.
	 * @param r    the random to use for getting a random cell.
	 * @return a random adjacent cell to ``cell``.
	 */
	public C randomAdjacent(C cell, Random r) {
		Orientation orientation = Orientation.chooseRandomOrientation(r);
		return this.checkAdjacentCells(cell, orientation);
	}

	/**
	 * @return the cell where the player starts the game
	 */
	public C startCell() {
		return this.getCellByPos(0, 0);
	}

	/**
	 * @return The cell where the player ends the game
	 */
	public C endCell() {
		return this.getCellByPos(this.getWidth() - 1, this.getHeight() - 1);
	}

	public String consoleDisplay() {

		List<String> laby_lignes = new ArrayList<>();
		List<String> laby_l = new ArrayList<>();
		String repeated = "+---".repeat(this.width);
		laby_lignes.add(repeated + "+");
		for (int x = 0; x < this.length; x++) {
			if (x == 0) {
				laby_l.add("|");
			} else {
				laby_l.add("\n" + "|");
			}
			for (int y = 0; y < this.width; y++) {
				if (this.getCellByPos(x, y).isWallOpened(Orientation.SOUTH)) {
					laby_l.add("    ");
				} else {
					laby_l.add("   |");
				}
			}
			laby_lignes.add(String.join("", laby_l));

			laby_l.clear();
			laby_l.add("+");

			for (int y = 0; y < this.width; y++) {

				if (this.cells[y][x].isWallOpened(Orientation.EAST)) {
					laby_l.add("   +");
				} else {
					laby_l.add("---+");
				}

			}

		}

		laby_lignes.add(String.join("", laby_l));
		return String.join("\n", laby_lignes);

	}
}
