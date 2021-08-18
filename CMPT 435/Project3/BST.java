import java.util.Scanner;

/* A BSTNode represents a node in a binary search tree. Each BSTNode object
 * stores a single item (called "data"). Each object also has left and right
 * pointers, which point to the left and right subtrees.
 *
 * The BST can be seen as superclass of the BSTNode class, so that the BST 
 * may make changes to the internals of a BSTNode.
 *
 * The constructor is provided for you; read it carefully.
 *
 * The getLeft(), getRight(), and getData() methods are useful for the
 * EncryptionTree class (or any class that wants to have read-only access to the
 * nodes of a BST).
 *
 * The printPreorder() traverses this node and its children recursively in
 * pre-order and prints each node it visits to standard output (i.e.
 * System.in). It presumes that "data" can be printed; that is, 
 * "System.out.print(this.data)" is a statement that makes sense. At each 
 * level of the tree it adds two spaces of indentation to show the structure 
 * of the tree. The run-time of printPreorder() is O(n). Can you figure out 
 * why?  Could it be made more efficient?
 *
 * The minNode() and maxNode() methods are useful in verifySearchOrder(). They
 * should find the leftmost and rightmost node at or below the node they are
 * called on. These can be implemented recursively or iteratively.
 *
 * The function verifySearchOrder() can be used to do verifications of the
 * binary search tree's order. It can and should be used for testing purposes.
 * If you implement minNode() and maxNode() efficiently, the run-time of
 * verifySearchOrder() is O(n^2) for this (potentially unbalanced) tree. Can you
 * figure out why?  Could it be made more efficient using different techniques?
 *
 * No one may call the copy constructor on a BSTNode, it is hereby forbidden,
 * so it is protected and will crash the program if called.
 */

class BSTNode {
  protected  BSTNode(BSTNode t) { assert(false); }

  protected  String data;
  protected  BSTNode left;
  protected  BSTNode right;

  public BSTNode(String d, BSTNode l, BSTNode r) {
    data = d; left = l; right = r;
  }

  public BSTNode getLeft()  { return left;  }
  public BSTNode getRight()  { return right; }
  public String getData()    { return data;  }
  
  public void printPreorder(String indent) {
    //- Recursively prints the contents of the tree
    
    // String indent = "";
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
   
  public BSTNode minNode() { 
    //- Recursively finds the node containing the minimum value in the tree
    
    BSTNode pointer = this;
    if (this.left != null) {
      this.left.minNode();
    }
    else {
      return this;
    }
    return this;
  }
  
  public BSTNode maxNode() { 
    //- Recurseively finds the node containing the maximum value in the tree
    
    BSTNode pointer = this;
    if (this.right != null) {
      this.right.maxNode();
    }
    else {
      return this;
    }
    return this;
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
}

/* A BST is a String-based class, but could easily be coded as a generic-type 
 * type class (e.g. with T), that represents a binary search tree. It has one
 * data member, "root", which is a pointer to the root of the tree.
 *
 * The constructor is provided for you.
 *
 * The insert() method places the given item in the tree, unless the item is
 * already in the tree. The insertion should follow the algorithm we discuss in
 * class.
 *
 * The remove() method removes the given item from the tree, if it is in the
 * tree. The remove should follow the algorithm we discuss in class.
 *
 * The printPreorder() and verifySearchOrder() methods simply calls the relevant
 * per-node methods on the root.
 *
 * No one may call the copy constructor on a BST, it is hereby forbidden, so
 * it is protected and will crash the program if called.
 */
class BST {
  protected BST(BST t) { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }
  
  protected BSTNode root;

  public BST() {
    root = null; 
  }

  public void insert(String item) {
    //- This method inserts a node into the tree unless the item is already in 
    //- the tree
    
    boolean WhoompThereItIs = false;
    // this boolean is a flag: it will break the loop if the node is found
    if (this.root == null) {
      this.root = new BSTNode(item, null, null);
      return;
    }
    
    BSTNode checkNode = root;
    while ((checkNode != null) && !WhoompThereItIs) {
      if (item.compareTo(checkNode.getData()) == 0) {
      // DO NOTHING: The item is in the tree  
        WhoompThereItIs = true;
      }
      else if (item.compareTo(checkNode.getData()) < 0) {
      // Item is less and will be inserted into the left subtree
        if (checkNode.getLeft() != null) {
          checkNode = checkNode.getLeft();
        }
        else {
          checkNode.left = new BSTNode(item, null, null);
          WhoompThereItIs = true;
        }
      }
      else if (item.compareTo(checkNode.getData()) > 0) {
      // Item is greater and will appear in the right subtree
        if (checkNode.getRight() != null) {
          checkNode = checkNode.getRight();
        }
        else {
          checkNode.right = new BSTNode(item, null, null);
          WhoompThereItIs = true;
        }
      }
    }
  }
  
  public void remove(String item) {
    //- Removes the node containing the item
    
    if (this.root == null) {
      return;
    }
    BSTNode remove = null;
    BSTNode parent = this.root;
    boolean foundIt = false;
    int progeny = 0;
    
    if (parent.data.equals(item)) {
      // this will happen if the root contains the item
      remove = this.root;
      foundIt = true;
    }
    else {
      while (parent != null) {
        if (parent.left.data.equals(item) && parent.left != null) {
          remove = parent.left;
          foundIt = true;
          break;
        }
        if (parent.right.data.equals(item) && parent.right != null) {
          remove = parent.right;
          foundIt = true;
          break;
        }
        int LorR = item.compareTo(parent.data);
        if (LorR < 0) {
          parent = parent.left;
        }
        else {
          parent = parent.right;
        }
      }
    }
    if (foundIt) {
      // count the node's children
      if (remove.left != null) {
        progeny++;
      }
      if (remove.right != null) {
        progeny++;
      }
      switch (progeny) {
        case 0:
        // this is the case if the node we remove is a leaf node
          if (parent == remove && parent == this.root) {
            parent = null;
            this.root = null;
          }
          else if (remove == parent.left) {
            parent.left = null;
          }
          else {
            parent.right = null;
          }
          remove = null;
          break;
        case 1:
        // this is the case if the node we remove has one child
          BSTNode grandchild;
          if (remove.left != null) {
            grandchild = remove.left;
            remove.left = null;
          }
          else {
            grandchild = remove.right;
            remove.right = null;
          }
          if (parent == remove && parent == this.root) {
            this.root = grandchild;
            parent  = null;
          }
          else if (parent.left == remove) {
            parent.left = grandchild;
          }
          else {
            parent.right = grandchild;
          }
          remove = null;
          break;
        case 2:
        // this is the case if the node we remove has two children
          BSTNode leftmost = remove.right;
          BSTNode leftmostDad = remove;
          if (leftmost.left != null) {
            while (leftmost.left != null) {
              leftmostDad = leftmost;
              leftmost = leftmost.left;
            }
          leftmostDad.left = leftmost.right;
          leftmost.right = remove.right;
          }
          leftmost.left = remove.left;
          if (parent == remove && parent == this.root) {
            this.root = leftmost;
            parent = null;
          }
          else if (parent.left == remove) {
            parent.left = leftmost;
          }
          else {
            parent.right = leftmost;
          }
          remove.right = null;
          remove.left = null;
          remove = null;
          break;
        default: break;  
      }
    }
  }

  public void printPreorder() { if (root != null) root.printPreorder(""); }
  public void verifySearchOrder() { if (root != null) root.verifySearchOrder(); }

}

/* An EncryptionTree is a special type of BST which knows how to encrypt a
 * String object (e.g. word) into a string that represents the path to the 
 * object in the tree, and decrypt a path into the String object (e.g. word) 
 * it leads to.
 *
 * The constructor method is provided for you.
 *
 * The encrypt() method takes a String object and returns a string of the form 
 * "rX" where "r" is a literal letter r, and X is a sequence of 0 and 1 
 * characters (which may be empty). The r stands for "root", and each 0 and 1 
 * represent the left/right path from the root to the given object, with 0 
 * indicating a left-branch and 1 indicating a right-branch. If the object is 
 * not in the dictionary, an empty string (or the string "?") should be 
 * returned.
 *
 * The decrypt() method takes an encrypted string (or path through the tree) in
 * the form provided by encrypt(). It should return a pointer to the associated
 * string for the given path (or NULL if the path is invalid).
 */
class EncryptionTree extends BST {
  public EncryptionTree() {}
  
  public String encrypt(String item) {
    //- Encrypts the tree from the given input
    if (this.root == null) {
      return "?";
    }
    
    BSTNode node = this.root;
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
    // return item; // this line is here to make sure it compiles
  }
  public String decrypt(String path) { 
    //- Decrypts the tree
    if (this.root == null) {
      return "?";
    }
    String LeftRight[];
    BSTNode node = null;
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
