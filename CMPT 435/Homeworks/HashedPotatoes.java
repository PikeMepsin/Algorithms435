import java.util.*;

public class HashedPotatoes {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    int tableSize = 0;
    int[][] resultTable = new int[12][7];
    
    ArrayList<String> dictionary = new ArrayList<String>();
    String word = null;
    
    int[] sizes = {1753, 1786, 2039, 2048, 3067, 3072, 4093, 4096, 5119,
      5120, 8191, 8192};
    Hashtable<Integer, String> hash1 = null;
    int[][] hash2 = null;
    int hash = 0;
    
    int probes = 0;
    int probeCount = 0;
    
    while (keyboard.hasNext()) {
      word = keyboard.next();
      dictionary.add(word);
    }
    
    for (int w = 0; w < sizes.length; w++) {
      System.out.println();
      System.out.println(sizes[w]);
      
      resultTable[w][0] = sizes[w];
      hash2 = new int[sizes[w]][2];
      
      hash1 = new Hashtable<Integer, String>(sizes[w], (float).25);
      probeCount = 0;
      for (int i = 0; i < dictionary.size(); i++) {
        probes = 0;
        hash = hash_string_1(dictionary.get(i), sizes[w]);
        if (hash1.containsKey(hash) == false) {
          hash1.put(hash, dictionary.get(i));
          probes++;
        }
        else {
          while (probes < sizes[w]) {
            hash = (hash + 1) % sizes[w];
            probes++;
            if (hash1.containsKey(hash) == false) {
              hash1.put(hash, dictionary.get(i));
              probes++;
              break;
            }
          }
        }
        probeCount += probes;
      }
      
      System.out.println("Hash1 " + Integer.toString(probeCount));
      resultTable[w][1] = probeCount;
      hash1 = new Hashtable<Integer, String>(sizes[w], (float).5);
      probeCount = 0;
      for (int i = 0; i < dictionary.size(); i++) {
        probes = 0;
        hash = hash_string_1(dictionary.get(i), sizes[w]);
        if (hash1.containsKey(hash) == false) {
          hash1.put(hash, dictionary.get(i));
          probes++;
        }
        else {
          int hashTemp = hash;
          while (probes < sizes[w]) {
            probes++;
            hashTemp = (hash + (probes*probes)) % sizes[w];
            if (hash1.containsKey(hashTemp) == false) {
              hash1.put(hashTemp, dictionary.get(w));
              probes++;
              break;
            }
          }
        }
        probeCount += probes;
      }
      
      System.out.println("Hash1 Quadratic " + Integer.toString(probeCount));
      resultTable[w][2] = probeCount;
      hash1 = new Hashtable<Integer, String>(sizes[w], (float).25);
      probeCount = 0;
      for (int i = 0; i < dictionary.size(); i++) {
        probes = 0;
        hash = hash_string_2(dictionary.get(i), sizes[w]);
        if (hash1.containsKey(hash) == false) {
          hash1.put(hash, dictionary.get(i));
          probes++;
        }
        else {
          while (probes < sizes[w]) {
            hash = (hash + 1) % sizes[w];
            probes++;
            if (hash1.containsKey(hash) == false) {
              hash1.put(hash, dictionary.get(i));
              probes++;
              break;
            }
          }
        }
        probeCount += probes;
      }
      
      System.out.println(probeCount);
      resultTable[w][3] = probeCount;
      hash1 = new Hashtable<Integer, String>(sizes[w], (float).5);
      probeCount = 0;
      for (int i = 0; i < dictionary.size(); i++) {
        probes = 0;
        hash = hash_string_2(dictionary.get(i), sizes[w]);
        if (hash1.containsKey(hash) == false) {
          hash1.put(hash, dictionary.get(i));
          probes++;
        }
        else {
          int hashTemp = hash;
          while (probes < sizes[w]) {
            probes++;
            hashTemp = (hash + (probes*probes)) % sizes[w];
            if (hash1.containsKey(hashTemp)) {
              hash1.put(hashTemp, dictionary.get(i));
              probes++;
              break;
            }
          }
        }
        probeCount += probes;
      }
      System.out.println("Hash2 Quadratic " + Integer.toString(probeCount));
      resultTable[w][4] = probeCount;
      hash1 = new Hashtable<Integer, String>(sizes[w], (float)0.25);
      probeCount = 0;
      for (int i = 0; i < dictionary.size(); i++){
        probes = 0;
        hash = hash_string_3(dictionary.get(i), sizes[w]);
        if (hash1.containsKey(hash) == false) {
          hash1.put(hash, dictionary.get(i));
          probes++;
        }
        else{
          while (probes < sizes[w]){
            hash = (hash + 1) % sizes[w];
            probes++;
            if (hash1.containsKey(hash) == false) {
              hash1.put(hash, dictionary.get(i));
              probes++;
              break;
            }
          }
        }
        probeCount += probes;
      }
      
      System.out.println(probeCount);
      resultTable[w][5] = probeCount;
      hash1 = new Hashtable<Integer, String>(sizes[w], (float).5);
      probeCount = 0;
      for (int i = 0; i < dictionary.size(); i++){
        probes = 0;
        hash = hash_string_3(dictionary.get(i), sizes[w]);
        if (hash1.containsKey(hash) == false) {
          hash1.put(hash, dictionary.get(i));
          probes++;
        }
        else{
          while (probes < sizes[w]){
            hash = (hash + 1) % sizes[w];
            probes++;
            if (hash1.containsKey(hash) == false) {
              hash1.put(hash, dictionary.get(i));
              probes++;
              break;
            }
          }
        }
        probeCount += probes;
      }
      System.out.println("Hash3 Quadratic " + Integer.toString(probeCount));
      resultTable[w][6] = probeCount;
    }
    for (int[] results: resultTable) {
      System.out.println(Arrays.toString(results));
    }
    
  }
  
  public static int hash_string_1(String key, int tableSize) {
    return (key.length() * key.length() * 4) % tableSize;
  }

  public static int hash_string_2(String key, int tableSize) {
    return   (key.charAt(0) + 
        27 * key.charAt(1) + 
        729 * key.charAt(2)) % tableSize;
  }

  public static int hash_string_3(String key, int tableSize) {
    // come up with your own hash function and see if you 
    // can do better on any of the test cases
    return ((727*key.length()) + (13*key.charAt(0)) - (key.charAt(1)) % tableSize);
  }
}