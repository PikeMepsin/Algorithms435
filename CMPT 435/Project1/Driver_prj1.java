import java.util.Scanner;

public class Driver_prj1 {
  // contains the logic for inputting and solving the maze
  public static void main(String[] args) {
    // stack to store location
    LocationStack mainLocStack = new LocationStack();
    
    // maze to be solved
    Maze theMaze = new Maze();
    
    // flag for ending the program if the solution is found
    boolean done = false;
    
    // for reading input
    Scanner reader = new Scanner(System.in);
    
    // stream the maze in, and begin solving
    theMaze.streamIn(reader);
    theMaze.getStartLocation().start();
    mainLocStack.push(theMaze.getStartLocation());
    // solution loop
    while ((!done) && (!mainLocStack.isEmpty())) {
      if (theMaze.isEndLocation(mainLocStack.getTop())) {
        // if the end location is reached, flag = true and print solution
        System.out.println("Solution found:");
        mainLocStack.streamOut(mainLocStack);
        done = true;
      }
      else if (mainLocStack.getTop().isDone()) {
        // take a location off the stack if all directions have been checked
        mainLocStack.pop();
      }
      else {
        // else check neighboring location
        Location adjacent = new Location();
        adjacent = mainLocStack.getTop().nextNeighbor();
        if (theMaze.isValidLocation(adjacent)) {
          if (!mainLocStack.isOn(adjacent)) {
          // put valid neighbor location on the stack and start solving from it
            adjacent.start();
            mainLocStack.push(adjacent);
          }
        }
      }
    }
    
    if (!done) {
      // if the loop terminates without a solution, there is no solution
      System.out.println("No solution found");
    }
  }
}