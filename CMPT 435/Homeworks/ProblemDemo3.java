import java.lang.*;

public class ProblemDemo3 {

   public static void main(String[] args) {
    int sum = 0;       
    int n = 10; // changed with 10000 and 10000000
    long start = System.nanoTime();
    long end = start;
    
    for (int i=0; i<n; i++) { 
      for (int j=0; j<(n*n); j++) {
        sum++;
      }             
    }
    
    end = System.nanoTime();
    System.out.println(sum + " " + (end - start));
   }
}