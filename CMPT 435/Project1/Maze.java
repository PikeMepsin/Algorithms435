/* A Maze object contains three things: the starting location, the
 * ending location, and a list of valid locations. It does not contain
 * any logic for actually solving the maze.
 *
 * The Maze constructor initializes all the data members that it can,
 * but the validLocationCount will not yet be known when the Maze is
 * constructed, so use a sensible value here.
 *
 * getStartLocation() returns the starting location of the maze.
 * isEndLocation() returns true if the given Location is the end of
 * the maze, otherwise false.  isValidLocation() returns true if the
 * given Location is in the list of valid Locations, otherwise false.
 *
 * streamIn() provides a means of streaming in a Maze object.  In
 * this version, streamIn() does not do any error checking.  We
 * assume that the entered data is perfect. Consider the following
 * when writing this function: what if we stream in a Maze object with
 * this function, and later call the same function on the same object?
 *
 * validLocationCount keeps the number of valid locations, which will
 * not be known until the object is streamed in. This is the length of
 * the validLocations array.
 *
 * validLocations is an array that contains all the locations that may
 * be used to solve the maze.
 *
 * startLocation holds the starting location of the maze, and
 * endLocation holds the target location of the maze.
 *
 * In this class we make the copy constructor private and illegal to
 * call, because it is not needed in this project, and we don't
 * want the compiler to provide them for us (since this class uses
 * dynamically allocated memory).  If these methods are called, they
 * will intentionally crash the program by the call to assert(false).
 */
import java.util.Scanner;
class Maze {
  private Maze(Maze m) { assert(false); }
  // declare parameters for inputs
  private int validLocationCount;
  private Location[] validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() {
    // constructor for maze parameters
    validLocationCount = 0;
    Location[] validLocations = null;
    
    startLocation = null;
    endLocation = null;
  }

  Location getStartLocation() {
    // returns starting location
    return startLocation;
  }
  boolean isValidLocation(Location loc) {
    // checks to see if a neighboring location is valid
    for (int j=0; j<validLocations.length; j++) {
      if (loc.isEqual(validLocations[j])) {
        return true;
      }
    }
    return false;
  }
  boolean isEndLocation(Location loc) {
    // returns true if the location on top of the stack is the end location
    if (loc.isEqual(endLocation)) {
      return true;
    }
    return false;
  }

  void streamIn(Scanner input) {
    // get number of valid locations
    validLocationCount = input.nextInt();
    // populate valid locations array
    validLocations = new Location[validLocationCount];
    for (int i=0; i<validLocationCount; i++) {
      validLocations[i] = new Location();
      validLocations[i].streamIn(input);
    }
    startLocation = new Location();
    endLocation = new Location();
    // get startLocation
    startLocation.streamIn(input);
    // get endLocation
    endLocation.streamIn(input);
  }
}
