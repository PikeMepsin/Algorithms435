import java.lang.*;

public class ProblemDemo6 {

   public static void main(String[] args) {
    int sum = 0;       
    int n = 1000; // changed with 10000 and 10000000
    long start = System.nanoTime();
    long end = start;
    
    for (int i=1; i<n; i++) { 
      for (int j=1; j<(i*i); j++) {
        if (j%i == 0) {
          for (int k=0; k<j; k++) {
            sum++;
          }
        }
      }
    }             
        
    end = System.nanoTime();
    System.out.println(sum + " " + (end - start));
   }
}