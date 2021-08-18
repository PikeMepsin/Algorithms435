import java.util.Scanner;

/* An AVLNode represents a node in an AVL-balanced binary search tree. Each
 * AVLNode object stores a single item (called "data"). Each object also has
 * left and right references, which point to the left and right subtrees, and it
 * knows its own height (the path length to its deepest descendant).
 *
 * The AVLTree can be seen as superclass of the AVLNode class, so that the 
 * AVLTree may make changes to the internals of an AVLNode.
 *
 * Many of the methods in this class are virtually identical to those in the
 * BSTNode in the previous project (#3), including the constructor,
 * getLeft(), getRight(), getData(), printPreorder(), verifySearchOrder(),
 * minNode(), maxNode(), and the copy constructor.
 *
 * The function verifyBalance() can be used to do verifications that the AVL
 * balance property holds at each node. It also can and should be used for
 * testing purposes. What is its running time?
 *
 * The singleRotateLeft() and singleRotateRight() methods do a single rotation
 * on the node they are called on, and return a reference to the node that takes
 * its place (so that the node's parent's reference can be changed).  Note that
 * these methods should update the heights of some nodes as necessary.
 *
 * The doubleRotateLeftRight() and doubleRotateRightLeft() methods do a double
 * rotation on the node they are called on. This is really simple if you have
 * implemented the single rotation methods; my double rotation methods are two
 * lines each. These methods return a reference to the node which took the place
 * of the node the method was called on (so that the node's parent's reference 
 * can be changed).
 *
 * The getHeight() method is a static method which takes a reference to a node,
 * and returns the height of that node (or -1 if the reference is NULL). This
 * makes it easy to find the height of any node with a reference, without having
 * to check for NULL.
 *
 * The updateHeight() method calculates and updates the value of the height on
 * the node it's called on. It assumes that the height values for the two
 * children of this node are correct, and uses them.
 */
class AVLNode {
  AVLNode(AVLNode t) { assert(false); }

  AVLNode(String d, AVLNode l, AVLNode r, int h) {
    data = d; left = l; right = r; height = h; 
  }
  
  protected String data;
  protected AVLNode left, right;
  protected int height;
  
  protected AVLNode singleRotateLeft() {
    //-
    AVLNode rightNode = this.right;
    this.right = rightNode.left;
    rightNode.left = this;
    this.updateHeight();
    rightNode.updateHeight();
    
    return rightNode;
  }

  protected AVLNode singleRotateRight() {
    //-
    AVLNode leftNode = this.left;
    this.left = leftNode.right;
    leftNode.right = this;
    this.updateHeight();
    leftNode.updateHeight();
    
    return leftNode;
  }

  protected AVLNode doubleRotateLeftRight() {
    //-
    AVLNode ogNode = this.left.singleRotateLeft();
    AVLNode rotatedNode = ogNode.singleRotateRight();
    return rotatedNode;
  }

  protected AVLNode doubleRotateRightLeft() {
    //-
    AVLNode ogNode = this.right.singleRotateRight
    AVLNode rotatedNode = ogNode.singleRotateLeft();
    return rotatedNode();
  }
  
  protected static int getHeight(AVLNode n) { 
    return n != null ? n.height : -1; 
  }

  protected void updateHeight() {
    int lh = getHeight(left);
    int rh = getHeight(right);
    height = (lh > rh ? lh : rh) + 1;
  }


  public AVLNode getLeft()  { return left;  }
  public AVLNode getRight() { return right; }
  public String getData()   { return data;  }

  public void printPreorder(string indent) {
    //- Recursively prints the contents of the tree
    
    System.out.print(indent);
    System.out.println(this.data);
    indent += "  ";
    
    if (getLeft() != null) {
      left.printPreorder(indent);
    }
    else {
      System.out.println(indent + "NULL");
    }
    if (getRight() != null) {
      right.printPreorder(indent);
    }
    else {
      System.out.println(indent + "NULL");
    }
  }
  
  /* professor's implementation of verifySearchOrder(); don't change it */
  public void verifySearchOrder() {
    if (left != null) {
      assert(left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    }
    if (right != null) {
      assert(data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }

  /* professor's implementation of verifyBalance(); don't change it */
  public void verifyBalance() {
    int heightDiff = Math.abs(getHeight(left) - getHeight(right));
    assert(heightDiff <= 1); 
    if (left  != null) left.verifyBalance();
    if (right != null) right.verifyBalance();
  }

  public AVLNode minNode() {
    //-
    AVLNode pointer = this;
    if (this.left != null) {
      this.left.minNode();
    }
    else {
      return this;
    }
    return this;
  }

  public AVLNode maxNode() {
    //-
    AVLNode pointer = this;
    if (this.right != null) {
      this.right.maxNode();
    }
    else {
      return this;
    }
    return this;
  }
}


/* An AVLTree is a String-based class that represents an AVL-balanced binary 
 * search tree. It has one data member, "root", which is a reference to the 
 * root of the tree.
 *
 * Many of the methods in this class are virtually identical to methods in the
 * BST from the previous project (#3), including the constructor,
 * printPreorder(), verifySearchOrder(), and copy constructor.
 *
 * The insert() and remove() methods behave as in the plain BST, but both
 * methods should rebalance the tree as necessary. This is best done by creating
 * an array of references to AVLNode objects as the insert/remove methods search
 * for the place to do their work.  This array of references represents the path
 * taken to get from the root to the place where a change occurs in the tree.
 * Note that for remove(), this path might go deeper than the node removed, in
 * the case of removing a node with two children (think carefully about this).
 * After insert/remove finish updating the tree, they can pass the path to
 * rebalancePathToRoot() which actually does the rebalancing. Think about how
 * large the array of references needs to be, at its largest. An AVL tree with
 * height 30 must have at least 3,524,577 nodes, and if it has height 50, it
 * must have at least 53,316,291,172 nodes -- probably more than we care to put
 * in the tree. These results come from the minimum size of an AVL tree of
 * height h, which is described in your book as: S(h) = S(h - 1) + S(h - 2) + 1
 * (and base cases S(0) = 1, S(1) = 2).
 *
 * The printLevelOrder() method prints out all the nodes in the tree in
 * level-order (root, then the root's children, then their children, etc.). This
 * is like performing a breadth-first search of the tree. The method should put
 * up to 20 nodes on each line, and use multiple lines as necessary. This method
 * should use a Java queue, and it is iterative (not recursive). This method is
 * useful if we want to transmit the information for building exactly the same
 * tree to our correspondent. If we were to take all the non-NULL nodes and
 * insert them in the order printed by this method, we would get the exact same
 * tree. We would not always be able to construct the exact same tree if we were
 * to use printPreorder() instead.
 *
 * The rebalancePathToRoot() method takes an array of references to AVLNode
 * objects, and the number of references that are on the array. This array should
 * represent the path that needs rebalancing after an insert or remove. It's
 * probably best to have the root at the start of the array. This method should
 * walk from the bottom of the path to the root, checking for imbalances, and
 * correcting any it finds by calling rotation methods as necessary to correct
 * imbalances.
 */
class AVLTree {
  protected AVLNode root;
  
  AVLTree(AVLTree t) { assert(false); }

  AVLTree() { root = null; }

  protected void rebalancePathToRoot(AVLNode[] path, int numOnPath) {
    //-
  }

  
  public void insert(String item) {
    //- This method inserts a node into the tree unless the item is already in 
    //- the tree
    
    boolean found = false;
    // this boolean is a flag: it will break the loop if the node is found
    if (this.root == null) {
      this.root = new AVLNode(item, null, null, 0);
      return;
    }
    
    AVLNode checkNode = root;
    while ((checkNode != null) && !found) {
      if (item.compareTo(checkNode.getData()) == 0) {
      // DO NOTHING: The item is in the tree  
        found = true;
      }
      else if (item.compareTo(checkNode.getData()) < 0) {
      // Item is less and will be inserted into the left subtree
        if (checkNode.getLeft() != null) {
          checkNode = checkNode.getLeft();
        }
        else {
          checkNode.left = new AVLNode(item, null, null, 0);
          found = true;
        }
      }
      else if (item.compareTo(checkNode.getData()) > 0) {
      // Item is greater and will appear in the right subtree
        if (checkNode.getRight() != null) {
          checkNode = checkNode.getRight();
        }
        else {
          checkNode.right = new AVLNode(item, null, null, 0);
          found = true;
        }
      }
    }
    // not done
  }

  public void remove(String item) {
    //-
  }

  public void printLevelOrder() {
    //-
  }

  public void printPreorder() { 
    if (root != null) root.printPreorder(""); 
  }

  public void verifySearchOrder() { 
    if (root != null) root.verifySearchOrder(); 
  }

  public void verifyBalance() { 
    if (root != null) root.verifyBalance(); 
  }

}


/* The EncryptionTree for this project is exactly the same as for the previous
 * project, except that it now has an AVLTree as its parent class.
 */
class EncryptionTree extends AVLTree {
  EncryptionTree() {}

  public String encrypt(String item) {
    //- Encrypts the tree from the given input
    if (this.root == null) {
      return "?";
    }
    
    AVLNode node = this.root;
    String word;
    String thisPath = "r";
    String temp[] = item.split("\'");
    
    if (temp.length == 1) {
      word = temp[0];
    }
    else {
      word = temp[1];
    }
    while (true) {
      int LorR = word.trim().compareTo(node.data);
      if (LorR == 0) {
        return thisPath;
      }
      if (node.left == null && node.right == null) {
        return "?";
      }
      if (node.left != null && LorR < 0) {
        node = node.left;
        thisPath += "0";
      }
      else if (node.right != null && LorR > 0) {
        node = node.right;
        thisPath += "1";
      }
      else {
        return "?";
      }
    }
  }

  public String decrypt(String path) {
    //- Decrypts the tree
    if (this.root == null) {
      return "?";
    }
    String LeftRight[];
    AVLNode node = null;
    String temp[] = path.split("\'");
    
    if (temp.length == 1) {
      LeftRight = temp[0].split("");
    }
    else {
      LeftRight = temp[1].split("");
    }
    
    for (int k=0; k<LeftRight.length; k++) {
      if (LeftRight[k].equals("r")) {
        node = this.root;
      }
      else if (LeftRight[k].equals("0")) {
        node = node.left;
      }
      else if (LeftRight[k].equals("1")) {
        node = node.right;
      }
      if (node == null) {
        return "?";
      }
    }
    return node.data;
  }
}