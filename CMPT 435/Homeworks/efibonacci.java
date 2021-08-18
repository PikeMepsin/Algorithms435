
class efibonacci {
  static int efibonacci(int i) {
    
      if (i<1) {
        return i;
      }
      else if (i>0 && i<=3) {
        return 1;
      }
      else {
        return efibonacci(i-1) + efibonacci(i-2) + efibonacci(i-3);
      }
  }
  public static void main(String[] args) {
    System.out.println(efibonacci(30));
  }
}