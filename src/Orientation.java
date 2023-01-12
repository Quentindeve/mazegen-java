import java.util.Random;

/**
 * @author quentin This enumeration contains the 4 orientations of the maze.
 *         It is used everywhere in the code when a relative location of a cell
 *         from another is needed.
 */
public enum Orientation {
	/**
	 * North direction
	 */
	NORTH(0, -1, "north"),
	/**
	 * West direction
	 */
	WEST(-1, 0, "west"),
	/**
	 * South direction
	 */
	SOUTH(0, 1, "south"),
	/**
	 * East direction
	 */
	EAST(1, 0, "east");

	private int deltaX;
	private int deltaY;
	private String name;

	private Orientation(int dx, int dy, String name) {
		this.deltaX = dx;
		this.deltaY = dy;
		this.name = name;
	}

	/**
	 * @return the name of the enum constant
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return int x.
	 */
	public int getDeltaX() {
		return this.deltaX;
	}

	/**
	 * @return int y.
	 */
	public int getDeltaY() {
		return this.deltaY;
	}

	public static Orientation fromString(String s) {
		String str = s.trim();
		for (Orientation o : Orientation.values()) {
			if (o.getName().equalsIgnoreCase(str))
				return o;
		}

		return null;
	}

	/**
	 * @return Orientation the opposite side of the instance.
	 */
	public Orientation invert() {
		switch (this) {
			case NORTH:
				return SOUTH;
			case EAST:
				return WEST;
			case SOUTH:
				return NORTH;
			case WEST:
				return EAST;
			default:
				return null;
		}
	}

	/**
	 * choose a random values of Orientation .
	 * 
	 * @param r random orientation.
	 * @return Orientation value of orientation.
	 */
	public static Orientation chooseRandomOrientation(Random r) {
		int random = r.nextInt(2);
		return Orientation.values()[random];
	}
}
