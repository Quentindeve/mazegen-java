package cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import java.util.Random;

import labyrinthe.character.npc.Npc;
import labyrinthe.item.*;
import labyrinthe.mazegen.Orientation;

/**
 * @author quentin This class represents the base of a cell. Each maze
 *         generation algorithm may have it's own extension of this class.
 */
public abstract class Cell {

	/**
	 * The hashmap representing all walls of the cell and its state.
	 */
	private HashMap<Orientation, Boolean> adjacents;

	/**
	 * The X position of the cell.
	 */
	private int posX;
	/**
	 * the Y position of the cell.
	 */
	private int posY;

	/**
	 * The entity in the cell.
	 */
	private final ArrayList<Item> items;

	public Cell(int posX, int posY) {
		this.adjacents = new HashMap<Orientation, Boolean>();
		this.posX = posX;
		this.posY = posY;
		this.adjacents = new HashMap<>();
		this.items = new ArrayList<>();

		// Initializes all the walls
		for (Orientation o : Orientation.values()) {
			this.adjacents.put(o, false);
		}
	}

	/**
	 * @return The X position of the cell.
	 */
	public int getPosX() {
		return this.posX;
	}

	/**
	 * @return the Y position of the cell
	 */
	public int getPosY() {
		return this.posY;
	}

	/**
	 * Breaks the wall at the specified orientation
	 * 
	 * @param orientation the orientation where to break the wall
	 */
	public void breakWall(Orientation orientation) {
		this.adjacents.put(orientation, true);
	}

	/**
	 * @return true if 2 walls is/are opened, false otherwise
	 */
	public boolean isOpened() {
		for (Orientation o : Orientation.values()) {
			if (this.isWallOpened(o)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @param orientation the orientation where to check if the wall is opened
	 * @return the state of the wall: true if opened, false otherwise
	 */
	public boolean isWallOpened(Orientation orientation) {
		return this.adjacents.get(orientation);
	}

	/** METHODS */

	public void generateItems() {
		int probability;

		// Add a jewel in the cell 50% of the time

		probability = new Random().nextInt((100 - 1)) + 1; // [1..100]

		if (probability > 50)
			items.add(new Jewel());

		// Add a parchment in the cell 10% of the time

		probability = new Random().nextInt((100 - 1)) + 1; // [1..100]

		if (probability > 90)
			items.add(new Parchment());
	}

	public ArrayList<Npc> use() {
		return null;
	}

	@Override
	public String toString() {
		return "(" + this.posX + " ; " + this.posY + ")";
	}
}
