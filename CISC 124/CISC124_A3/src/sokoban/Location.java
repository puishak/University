package sokoban;

/**
 * A class that represents a location using a pair of integer coordinates
 * {@code (x, y)}. A {@code Location} object is immutable.
 * 
 * <p>
 * The positive x-direction points to the right and the positive y-direction
 * points downwards.
 */
public class Location {

	private int x;
	private int y;

	/**
	 * Initializes this location to (0, 0).
	 */
	public Location() {
		this(0, 0);
	}

	/**
	 * Initializes this location to (x, y).
	 * 
	 * @param x the x-coordinate of this location
	 * @param y the y-coordinate of this location
	 */
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Initializes this location by copying the coordinates of another location.
	 * 
	 * @param other the location to copy
	 */
	public Location(Location other) {
		this(other.x, other.y);
	}

	/**
	 * Return the x-coordinate of this location.
	 * 
	 * @return the x-coordinate of this location
	 */
	public int x() {
		// ALREADY DONE FOR YOU
		return this.x;
	}

	/**
	 * Return the y-coordinate of this location.
	 * 
	 * @return the y-coordinate of this location
	 */
	public int y() {
		// ALREADY DONE FOR YOU
		return this.y;
	}

	/**
	 * Return the location immediately to the left of this location.
	 * 
	 * @return the location immediately to the left of this location
	 */
	public Location left() {
		Location result = new Location(this.x - 1, this.y);
		return result;
	}

	/**
	 * Return the location immediately to the right of this location.
	 * 
	 * @return the location immediately to the right of this location
	 */
	public Location right() {
		Location result = new Location(this.x + 1, this.y);
		return result;
	}

	/**
	 * Return the location immediately above this location.
	 * 
	 * @return the location immediately above this location
	 */
	public Location up() {
		Location result = new Location(this.x, this.y - 1);
		return result;
	}

	/**
	 * Return the location immediately below this location.
	 * 
	 * @return the location immediately below this location
	 */
	public Location down() {
		Location result = new Location(this.x, this.y + 1);
		return result;
	}

	/**
	 * Returns {@code true} if this location is immediately to the left, right,
	 * above, or below another location, {@code false} otherwise. Diagonally
	 * adjacent locations are considered to be not adjacent by this method.
	 * 
	 * @param other the location to test for adjacency
	 * @return true if this location is adjacent to a second location, false
	 *         otherwise
	 */
	public boolean isAdjacentTo(Location other) {
		if (other == this.left() || other == this.right() || other == this.up() || other == this.down()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Compares this location to another location for equality. Two locations are
	 * equal if and only if they are {@code Location} objects having equal
	 * coordinates.
	 * 
	 * @param obj the object to test for equality
	 * @return true if this location has the same coordinates as the other location,
	 *         false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Location)) {
//			throw new IllegalArgumentException("obj is not an instance of Location");
			return false;
		}
		else {
			Location loc = (Location)obj;
			
			if (this.x == loc.x && this.y == loc.y) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	/**
	 * Returns a hash code for this location.
	 * 
	 * @return a hash code for this location
	 */
	@Override
	public int hashCode() {

		int result = Integer.hashCode(this.x);
        int c = Integer.hashCode(this.y);
        result = 31 * result + c;

        return result;
	}

	/**
	 * Returns a string representation for this location. The returned string is the
	 * x-coordinate of this location inside a pair of square brackets followed by
	 * the the y-coordinate of this location inside a pair of square brackets.
	 * For example, the location with coordinates (-5, 10) has a string
	 * representation equal to {@code "[-5][10]"}.
	 * 
	 * @return a string representation of this location
	 */
	@Override
	public String toString() {
		
		return "[" + this.x + "][" + this.y + "]";
	}
	
	public static void main(String[] args) {
		Location loc1 = new Location(1, 2);
		System.out.println(loc1);
		
		Location loc2 = new Location(0, 2);
		System.out.println(loc2);
		
		System.out.println("loc1.equals(loc2): " + loc1.equals(loc2));

		System.out.println("Hashcode comparison: " + (loc1.hashCode() == loc2.hashCode()));
	}
}
