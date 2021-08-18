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

public class Driver_prj01 {

  public static void main(String[] args) {
    // Here we initialize the scaner variable to read lines of input
    Scanner input = new Scanner(System.in);
    String line;
    

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();
    //Stack<String> dumpStack = new Stack<String>();
    
    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while 
    // condition. If System.in has reached the end of the file, it will return 
    // false and the loop will exit.  Otherwise, it will return true and the 
    // loop will continue.
    int c_depth = 0;
    // current depth for comparing to max depth
    int lineNumber = 0;
    int maximum_depth = 0;
    String substr1, substr2 = null;
    // saves whether the statement is call or return
    String c_function = null;
    // the last function to be placed on top of the stack
    String top, resolve = null;
    // top is the function on top, resolve is the function that the stack
    // is attempting to return
    
    while (input.hasNext()) {
      // pushes calls to the stack, records new max depth
      line = input.nextLine();
      lineNumber++;
      substr1 = line.substring(0,4); // call
      substr2 = line.substring(0,6); // return
      if (substr1.equals("call")) {
        // System.out.println(top);
        // for testing purposes
        c_depth++;
        c_function = line.substring(4);
        // System.out.println(c_function);
        // for testing purposes
        callStack.push(c_function);
        if (c_depth > maximum_depth) {
          maximum_depth = c_depth;
        }
        // System.out.println(line);
        // for testing purposes
      
      }
      if (substr2.equals("return")) {
        resolve = line.substring(6);
        // this value is assigned because it is where the name of the function
        // begins after the 'return'
        try {
          top = callStack.peek();
        // read what's on top of callStack
        }
        catch (Exception i) {
          top = null;
          // catch the EmptyStackException in case a return statement
          // is read when callStack is empty
        }
        
        if (resolve.equals(top)) {
          // pop the top item if it equals the resolve query, decrement c_depth
          // System.out.println("return " + top);
          // for testing purposes
          callStack.pop();
          c_depth--;
        }
        else if (callStack.search(resolve) >= 0) {
          // error: the resolve query exists on the callStack but is not on top
          // System.out.println(callStack);
          System.out.println("Invalid trace at line " + lineNumber);
          System.out.println("Returning from " + resolve + " instead of " + top);
          while (!callStack.empty()) {
            /*dumpStack.push(callStack.pop());
          }
          System.out.println("Stack trace: ");
          while (!dumpStack.empty()) { */
            System.out.println(callStack.pop());
          }
          //System.out.println(dumpStack);
          break;
        }
        else if (callStack.search(resolve) == -1) {
          // error: the resolve query does NOT exist on the stack
          // System.out.println(resolve);
          // for testing purposes
          // System.out.println(callStack);
          System.out.println("Invalid trace at line " + lineNumber);
          System.out.println("Returning from " + resolve + " which was not called");
          while (!callStack.empty()) {
            /*dumpStack.push(callStack.pop() + " \n");
          } */
          System.out.println("Stack trace: ");
          //while (!dumpStack.empty()) {*/
            System.out.println(callStack.pop());
          }
          break;
        }   
      }
      if (callStack.empty() && !input.hasNext()) {
        // valid trace
        System.out.println("Valid trace");
        System.out.println("Maximum call depth was " + maximum_depth);
      }
      if (!callStack.empty() && !input.hasNext()) {
      // error: while-loop terminates with calls still on the stack
        while (!callStack.empty()) {
          /*dumpStack.push(callStack.pop() + " \n");
        } 
      System.out.println("Stack trace: ");
      while (!dumpStack.empty()) { */
          System.out.println(callStack.pop());
        }
      System.out.println("Invalid trace at line " + lineNumber);
      System.out.println("Not all functions returned");
      } 
    }
  }
}