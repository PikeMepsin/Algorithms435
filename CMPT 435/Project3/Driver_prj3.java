/*  Michael Pepsin
    CMPT 435
    Project 3
    3/6/2019
    
    Run this program to use the Encryption Tree
*/

import java.util.Scanner;
public class Driver_prj3 {
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    EncryptionTree encrypTree = new EncryptionTree();
    
    while (reader.hasNext()) {
      String next = reader.nextLine();
      String nextInput[] = next.split(" ");
      
      switch (nextInput[0].charAt(0)) {
        case 'e':
          for (int t=1; t<nextInput.length-1; t++) {
            System.out.print(encrypTree.encrypt(nextInput[t] + " "));
          }
          System.out.print(encrypTree.encrypt(nextInput[nextInput.length-1]));
          System.out.println();
          break;
        case 'd':
          for (int s=1; s<nextInput.length-1; s++) {
            System.out.print(encrypTree.decrypt(nextInput[s]) + " ");
          }
          System.out.print(encrypTree.decrypt(nextInput[nextInput.length-1]));
          System.out.println("");
          break;
        case 'p':
          if (encrypTree.root != null) {
            encrypTree.root.printPreorder("");
          }
          break;
        case 'i':
          encrypTree.insert(nextInput[1]);
          break;
        case 'r':
          encrypTree.remove(nextInput[1]);
          break;
        default: break;
      }
    }
  }
}