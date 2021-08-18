/* Class declaration for a simple stack of Location objects. It is not
 * a difficult class; it can contain only Location objects. It can
 * grow and shrink because it is a linked structure. The class
 * LocationStackNode (below) encapsulates the nodes of the stack.
 *
 * Methods push(), pop(), and getTop() provide standard stack methods.
 * Using assert() to check for problems in these three methods could
 * be useful (hint).  isEmpty() returns true if the stack is empty,
 * false otherwise. isOn() returns true if the given Location is on
 * the stack, otherwise returns false.
 *
 * streamOut() streams out the stack from bottom to top. This method
 * should NOT make a copy of the stack. Instead, it should require two
 * passes over the stack to print the stack. The first pass will
 * traverse the stack to top->bottom, reversing the links of the nodes
 * as it goes. The second pass will traverse from bottom->top,
 * printing each Location as it is visited, and undoing the reversing
 * of the node links.
 *
 * The default constructor initializes the data members as
 * appropriate. The copy constructor is not usable in this class;
 * therefore it is private and will fail on an assert() if called.
 *
 * The data member top is a reference to the top node.
 */

class LocationStack {
  // initialize variables for linked list stack simulation
  private LocationStack(LocationStack s) { assert(false); }
  private LocationStackNode top;
  int nextDirection; // mutable
  
  LocationStack() {
    // constructor
    top = null;
  }

  void push(Location loc) {
    // pushes location to the top of the "stack"
    top = new LocationStackNode(loc, top);
  }
  void pop() {
    // pops last element
    top = top.getNextNode();
  }
  Location getTop() {
    // returns the location on top or null if the "stack" is empty
    if (!isEmpty()) {
      return top.getLocation();
    }
    return null;
  }

  boolean isEmpty() {
    // returns true if the "stack" is empty
    // returns true if the top node is null
    if (top == null) {
      return true;
    }
    else {
      return false;
    }
  }
  boolean isOn(Location loc) {
    // checks the "stack" for the specific location
    LocationStackNode tempNode = top;
    while (tempNode != null) {
      Location tempLoc = tempNode.getLocation();
      if (tempLoc.isEqual(loc)) {
        return true;
      }
      else {
        tempNode = tempNode.getNextNode();
      }
    }
    return false;
  }

  void streamOut(LocationStack s) {
    // prints solution
    LocationStack stream = new LocationStack();
    LocationStackNode trace = top;
    Location temp;
    
    while (!s.isEmpty()) {
      // reverses the order of the stack so it is output in the correct order
      trace = top;
      stream.push(trace.getLocation());
      s.pop();
    }
    while (!stream.isEmpty()) {
      // prints the reversed stack
      temp = stream.getTop();
      temp.streamOut();
      stream.pop();
    }
  }
}

/* Class declaration for a Node on a LocationStack. Each node contains
 * a Location and a link to the next LocationStackNode (the one below
 * it on the stack). 
 *
 * The only constructor that may be used for this class is the one
 * which takes values to initialize its data members. Other
 * constructors may not be called as they are not necessary. These
 * restrictions help prevent us from accidentally making multiple
 * nodes that all point to the same next node.
 *
 * If we set a LocationStack objet to null, this should invoke the garbage
 * collector and delete the nextNode in LocationStackNode, so that deleting
 * the top of the stack has a chaining effect that deletes every node
 * on the stack.
 *
 * The get/set methods for this class are self-explanatory, and are
 * the interface by which you should modify a node as necessary.
 */
class LocationStackNode {
  // initialize node variables
  private LocationStackNode() { assert(false); }
  private LocationStackNode(LocationStackNode s) { assert(false); }

  private Location location;
  private LocationStackNode nextNode;

  LocationStackNode(Location loc, LocationStackNode next) {
    // constructor
    location = loc;
    nextNode = next;
  }

  Location getLocation() {
    // returns the location
    return location;
  }
  LocationStackNode getNextNode() {
    // points to next node on the "stack"
    return nextNode;
  }
  void setNextNode(LocationStackNode next) {
    // sets nextNode to the node after it on the "stack"
    nextNode = next;
  }
}
