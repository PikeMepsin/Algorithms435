import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;

/* Changes from Project 1:
 *
 * Data member validLocations was changed from a Location array to a
 * Set<Location>, but it still represents the group of locations that may be
 * visited in the maze.
 *
 * Data member validLocationCount was eliminated (because validLocations is no
 * longer an array).
 *
 * We let the compiler deal with the assignment operator, copy constructor, and 
 * for this version of the Maze, you should use the default constructor for the
 * _usual_ and to initally allocate memory for the Set of validLocations using
 * the TreeSet implementation of a Set.
 */

class Maze {
  private Set<Location> validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() {
    // Constructor
    validLocations = new TreeSet<>();
    
    startLocation = new Location();
    endLocation = new Location();
  }

  Location getStartLocation() {
    // returns the start location
    return startLocation;
  }
  boolean isValidLocation(Location loc) {
    // -
    return validLocations.contains(loc);
  }
  boolean isEndLocation(Location loc) {
    // returns the end location
    return endLocation == loc;
  }

  void streamIn(Scanner input) {
    // -
    int validWordCount = input.nextInt();
    for (int x = 0; x < validWordCount; x++) {
      Location tempLoc = new Location();
      tempLoc.streamIn(input);
      validLocations.add(tempLoc);
    }
    
    startLocation.streamIn(input);
    endLocation.streamIn(input);
  }
}