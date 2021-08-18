/* CMPT 435
 * Project 0 -- Program trace verification
 * Filename: Driver_prj0.java
 * Student name: Michael Pepsin
 * 
 * Program is fed method invocations into a stack and verifies that the
 * trace is valid. If valid, indicates valididty and maximum stack depth.
 * Else, prints appropriate error message.
 */

import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    String line;
    boolean error = false;

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack <String> callStack = new Stack <String>();

    int lineNum = 0;
    int c_depth = 0;
    int max_depth = 0;
    
    while (keyboard.hasNext()) {
      line = keyboard.nextLine();
      lineNum++;
      
      if (line.startsWith("call ")) {
        callStack.push(line);
        c_depth = c_depth + 1;
        if (c_depth > max_depth) {
          max_depth = c_depth;
        }
      }
      
      else if (line.startsWith("return ")) {
        // Checks if the function is on top of the stack
        if (!callStack.empty() && callStack.peek().substring(5).equals(line.substring(7))) {
          callStack.pop();
          c_depth--;
        } 
        
        else {
          if(callStack.search(line.substring(7)) == -1 && callStack.empty()){
            // Searches the stack to see the fuction was called
            
            // returns an error when the input attempts to return from a
            // function that wasn't called
            if (callStack.search("call " + line) < 0) {
              System.out.println("Invalid trace at line " + lineNum);
              System.out.println("Returning from " + line.substring(7) +
                " which was not called");
              System.out.println("Stack trace:");
              //Print Stack Trace
              while (!callStack.empty()) {
                System.out.println(callStack.peek().substring(5));
                callStack.pop();
              }
              error = true;
              break;
            }
          }
          // returns an error if the input attempts to return a function
          // other than the one at the top of the stack
          else {
            System.out.println("Invalid trace at line " + lineNum);
            System.out.println("Returning from " + line.substring(7) + " instead of " +
              callStack.peek().substring(5));
            System.out.println("Stack trace:");
            //Print Stack Trace
            while (!callStack.empty()) {
              System.out.println(callStack.peek().substring(5));
              callStack.pop();
            }
            error = true;
            break;
          }
        }
      }
    }

    // returns an error if there are functions left unresolved
    if (!error && !callStack.empty()) {
      System.out.println("Invalid trace at line " + lineNum);
      System.out.println("Not all functions returned");
      System.out.println("Stack trace:");
      //Print Stack Trace
      while (!callStack.empty()) {
        System.out.println(callStack.peek().substring(5));
        callStack.pop();
      }
      error = true;
    }
    // the trace is valid
    else if (!error) {
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + max_depth);
    }
  }
}
