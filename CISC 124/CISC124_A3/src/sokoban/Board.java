package sokoban;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a Sokoban level board.
 * 
 * <p>
 * The class can read a Sokoban level from a plain text file where the following
 * symbols are used:
 * 
 * <ul>
 * <li>space is an empty square
 * <li># is a wall
 * <li>@ is the player
 * <li>$ is a box
 * <li>. is a storage location
 * <li>+ is the player on a storage location
 * <li>* is a box on a storage location
 * </ul>
 * 
 * <p>
 * The class manages a single {@code Player} object and a number of {@code Box},
 * {@code Wall}, and {@code Storage} objects. The class determines whether the
 * player can move to a specified square depending on the configuration of boxes
 * and walls.
 * 
 * <p>
 * The level is won when every box is moved to a storage location.
 * 
 * <p>
 * The class provides several methods that return information about a location
 * on the board .
 *
 */
public class Board {
	
	public List<Wall> walls;
	public List<Box> boxes;
	public List<Storage> storages;
	
	private Player player;
	private int width;
	private int height;
	
	/**
	 * Initialize a board of width 11 and height 11 with a {@code Player} located at
	 * (4, 5), a {@code Box} located at (5, 5), and a storage location located at
	 * (6, 5).
	 */
	public Board() {
		
		this.walls = new ArrayList<Wall>();
		this.boxes = new ArrayList<Box>();
		this.storages = new ArrayList<Storage>();
		
		this.width = 11;
		this.height = 11;
		
		Location playerLocation = new Location(4, 5);
		Location boxLocation = new Location(5, 5);
		Location storageLocation = new Location(6, 5);
		
		this.player = new Player(playerLocation);
		Box _box = new Box(boxLocation);
		this.boxes.add(_box);
		Storage _storage = new Storage(storageLocation);
		this.storages.add(_storage);
	}

	/**
	 * Initialize a board by reading a level from the file with the specified
	 * filename.
	 * 
	 * <p>
	 * The height of the board is determined by the number of lines in the file. The
	 * width of the board is determined by the longest line in the file where
	 * trailing spaces in a line are ignored.
	 * 
	 * @param filename the filename of the level
	 * @throws IOException if the level file cannot be read
	 */
	public Board(String filename) throws IOException {
		
		this.walls = new ArrayList<Wall>();
		this.boxes = new ArrayList<Box>();
		this.storages = new ArrayList<Storage>();
		
		this.readLevel(filename);
		
		
	}

	private final void readLevel(String filename) throws IOException {
		Path path = FileSystems.getDefault().getPath("src", "sokoban", filename);
		List<String> level = Files.readAllLines(path);
		this.height = level.size();
		this.width = 0;
		for (int y = 0; y < this.height; y++) {
			String row = level.get(y);
			if (row.length() > this.width) {
				this.width = row.length();
			}
			for (int x = 0; x < row.length(); x++) {
				// the location of this square
				Location loc = new Location(x, y);
				
				// the symbol at location (x, y)
				char c = row.charAt(x);
				if (c == ' ') {
					continue;
				} else if (c == '#') {
					// there is a wall here
					Wall w = new Wall(loc);
					this.walls.add(w);
					
				} else if (c == '@') {
					Player p = new Player(loc);
					this.player = p;
				} else if (c == '$') {
					// there is a box here
					Box b = new Box(loc);
					this.boxes.add(b);
					
				} else if (c == '.') {
					// there is a storage site here
					Storage s = new Storage(loc);
					this.storages.add(s);
					
				} else if (c == '+') {
					Player p = new Player(loc);
					this.player = p;
					// there is also storage site here
					Storage s = new Storage(loc);
					this.storages.add(s);
					
				} else if (c == '*') {
					// there is a box and a storage site here
					Box b = new Box(loc);
					this.boxes.add(b);
					Storage s = new Storage(loc);
					this.storages.add(s);
					
				}
			}
		}
	}

	/**
	 * Returns the width of this board.
	 * 
	 * @return the width of this board
	 */
	public int width() {
		// ALREADY DONE FOR YOU
		return this.width;
	}

	/**
	 * Returns the height of this board.
	 * 
	 * @return the height of this board
	 */
	public int height() {
		// ALREADY DONE FOR YOU
		return this.height;
	}

	/**
	 * Returns a list of the walls in this board. The order of the walls is
	 * unspecified in the returned list.
	 * 
	 * @return a list of the walls in this board
	 */
	public List<Wall> getWalls() {
		return this.walls;
	}

	/**
	 * Returns a list of the boxes in this board. The order of the boxes is
	 * unspecified in the returned list.
	 * 
	 * @return a list of the boxes in this board
	 */
	public List<Box> getBoxes() {
		return this.boxes;
	}

	/**
	 * Returns a list of the storage locations in this board. The order of the
	 * storage locations is unspecified in the returned list.
	 * 
	 * @return a list of the storage locations in this board
	 */
	public List<Storage> getStorage() {
		return this.storages;
	}

	/**
	 * Returns the player.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		// ALREADY DONE FOR YOU
		return this.player;
	}

	/**
	 * Returns the {@code Box} object located at the specified location on the
	 * board, or {@code null} if there is no such object.
	 * 
	 * @param loc a location
	 * @return the box object located at the specified location on the board, or
	 *         {@code null} if there is no such object
	 */
	public Box getBox(Location loc) {
		
		for (Box b: this.boxes) {
			if (b.location().equals(loc)) {
				return b;
			}
		}
		return null;		
	}

	/**
	 * Returns {@code true} if there is a wall, player, or box at the specified
	 * location, {@code false} otherwise. Note that storage locations are considered
	 * unoccupied if there is no player or box on the location.
	 * 
	 * @param loc a location
	 * @return {@code true} if there is a wall, player, or box at the specified
	 *         location, {@code false} otherwise
	 */
	public boolean isOccupied(Location loc) {
		return hasWall(loc) || hasBox(loc) || hasPlayer(loc);
	}

	/**
	 * Returns {@code true} if the specified location is unoccupied or has only a
	 * storage location, {@code false} otherwise
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location is unoccupied or has only a
	 *         storage location, {@code false} otherwise
	 */
	public boolean isFree(Location loc) {
		return !this.isOccupied(loc);
	}

	/**
	 * Returns {@code true} if the specified location has a wall on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a wall on it,
	 *         {@code false} otherwise
	 */
	public boolean hasWall(Location loc) {
		for (Wall w: this.walls) {
			if (w.location().equals(loc)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a box on it, {@code false}
	 * otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a box on it, {@code false}
	 *         otherwise
	 */
	public boolean hasBox(Location loc) {	
		for (Box b: this.boxes) {
			if (b.location().equals(loc)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a storage location on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a storage location on it,
	 *         {@code false} otherwise
	 */
	public boolean hasStorage(Location loc) {
		for (Storage s: this.storages) {
			if (s.location().equals(loc)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a player on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a player on it,
	 *         {@code false} otherwise
	 */
	public boolean hasPlayer(Location loc) {
		if (this.player.location().equals(loc)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns {@code true} if every storage location has a box on it, {@code false}
	 * otherwise.
	 * 
	 * @return {@code true} if every storage location has a box on it, {@code false}
	 *         otherwise
	 */
	public boolean isSolved() {
		boolean result = true;
		
		for (Storage s: this.storages) {
			Location loc = s.location();
			if (!hasBox(loc)) {
				result = false;
			}
		}
		
		return result;
	}

	
	/**
	 * Moves the player to the left adjacent location if possible. If there is a box
	 * in the left adjacent location then the box is pushed to the adjacent location
	 * left of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the left adjacent location
	 * (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the left adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerLeft() {
		Location desiredLoc = this.player.location().left();
		
		if (isFree(desiredLoc)) {
			this.player.moveLeft();
			return true;
		}
		else if (hasBox(desiredLoc)){
			Box box = getBox(desiredLoc);
			Location boxDesiredLoc = box.location().left();
			
			if (isFree(boxDesiredLoc)) {
				box.moveLeft();
				this.player.moveLeft();
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Moves the player to the right adjacent location if possible. If there is a
	 * box in the right adjacent location then the box is pushed to the adjacent
	 * location right of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the right adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the right adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerRight() {
		Location desiredLoc = this.player.location().right();
		
		if (isFree(desiredLoc)) {
			//Desired location is empty
			this.player.moveRight();
			return true;
		}
		else if (hasBox(desiredLoc)){
			//Desired location contains a box
			Box box = getBox(desiredLoc);
			System.out.println(box);
			Location boxDesiredLoc = box.location().right();
			
			if (isFree(boxDesiredLoc)) {
				box.moveRight();
				this.player.moveRight();
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Moves the player to the above adjacent location if possible. If there is a
	 * box in the above adjacent location then the box is pushed to the adjacent
	 * location above the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the above adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the above adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerUp() {
		Location desiredLoc = this.player.location().up();
		
		if (isFree(desiredLoc)) {
			this.player.moveUp();
			return true;
		}
		else if (hasBox(desiredLoc)){
			Box box = getBox(desiredLoc);
			Location boxDesiredLoc = box.location().up();
			
			if (isFree(boxDesiredLoc)) {
				box.moveUp();
				this.player.moveUp();
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Moves the player to the below adjacent location if possible. If there is a
	 * box in the below adjacent location then the box is pushed to the adjacent
	 * location below of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the below adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the below adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerDown() {
		Location desiredLoc = this.player.location().down();
		
		if (isFree(desiredLoc)) {
			this.player.moveDown();
			return true;
		}
		else if (hasBox(desiredLoc)){
			Box box = getBox(desiredLoc);
			Location boxDesiredLoc = box.location().down();
			
			if (isFree(boxDesiredLoc)) {
				box.moveDown();
				this.player.moveDown();
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Returns a string representation of this board. The string representation
	 * is identical to file format describing Sokoban levels.
	 * 
	 * @return a string representation of this board
	 */
	@Override
	public String toString() {
		// ALREADY DONE FOR YOU
		StringBuilder b = new StringBuilder();
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				Location loc = new Location(x, y);
				if (this.isFree(loc) ) {
					if (this.hasStorage(loc)) {
						b.append(".");					
					}
					else {
						b.append(" ");
					}
				}
				else if (this.hasWall(loc)) {
					b.append("#");
				}
				else if (this.hasBox(loc)) {
					if (this.hasStorage(loc)) {
						b.append("*");
					}
					else {
						b.append("$");
					}
				}
				else if (this.hasPlayer(loc)) {
					if (this.hasStorage(loc)) {
						b.append("+");
					}
					else {
						b.append("@");
					}
				}
			}
			b.append('\n');
		}
		return b.toString();
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		List<Double> dailyT = new ArrayList<>();
		// some code here that adds the daily temperature for each day of September to dailyT
		// dailyT is a list of 30 temperatures from Sep 1 to Sep 30

		List<Integer> warmDays = new ArrayList<>();
		for (int day = 1; day <= 30; i++) {
			if (dailyT.get(day - 1) > 10) {

			    warmDays.add(day);
			}
		}
		
		
		
		
	}
}













