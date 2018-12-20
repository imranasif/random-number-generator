import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumberGenerator {
  
  static Set<Long> uniqueRandomNumbers = new HashSet<>();
  
  public static void main(String[] args) {

    System.out.println(randomNumberGenerator(10, 100, 0, false));
    System.out.println(uniqueRandomNumbers.toString());
    System.out.println(randomNumberGenerator(-10, 100, 5, false));
    System.out.println(uniqueRandomNumbers.toString());
    System.out.println(randomNumberGenerator(100, 10000, 4, false));
    System.out.println(uniqueRandomNumbers.toString());
    System.out.println(randomNumberGenerator(0, 0, 3, true));
    System.out.println(uniqueRandomNumbers.toString());

  } 
  
  /**
   * Creates a random number based on a variety of options, using 
   * supplied sources.
   *
   * @param min  the position to start at (inclusive)
   * @param max  the position to end before (exclusive)
   * @param count  the length of random number to create
   * @param reset  reset the previously generated number to start again
   * 
   * @return the random number
   */
  public static long randomNumberGenerator(long minimum, long maximum, int count, boolean reset) {
    
    if(reset) {
      uniqueRandomNumbers.clear();
    }

    if (minimum == 0 && maximum == 0) {
      if (count < 0) {
        throw new IllegalArgumentException("Digit count can not be negative");
      } else if (count == 0) {
        throw new IllegalArgumentException("Digit count and range both can not be null");
      } else {
        minimum = getMinLimit(count);
        maximum = getMaxLimit(count);
      }
    } else {
      if (maximum <= minimum) {
        throw new IllegalArgumentException(
            "Maximum must be greater than minimum");
      } else {
        if (count < 0) {
          throw new IllegalArgumentException("Digit count can not be negative");
        } else if (count > 0) {
          long minLimit = getMinLimit(count);
          long maxLimit = getMaxLimit(count);
          if (minLimit < minimum || minLimit > maximum || maxLimit < minimum
              || maxLimit > maximum) {
            throw new IllegalArgumentException("Digit count and max min limit does not match");
          }
        }
      }
    }
    
    return getUnique(minimum, maximum);
  }
  
  /**
   * Generate a unique random number between a given range.
   *
   * @param min the minimum range of the random number
   * @param max the maximum range of the random number
   * 
   * @return the unique number
   */
  public static long getUnique(long min, long max) {   
    long num = getRandom(min, max);  
    long range = max -min + 1;
    if(uniqueRandomNumbers.add(num)) {
      return num;
    } else {
      if(uniqueRandomNumbers.size() >= range) {
        throw new IllegalArgumentException("You are generating more number than number range");
      } else {
        getUnique(min, max);
      }
    }
    return num;
  }
  
  /**
   * Generate a random number between a given range.
   *
   * @param min the minimum range of the random number
   * @param max the maximum range of the random number
   * 
   * @return the random number
   */
  public static long getRandom(long min, long max) {
    Random random = new Random();
    return random.longs(min, (max + 1)).findFirst().getAsLong();
  }

  /**
   * Returns the maximum number range from a given digit count
   *
   * @param digitCount the number of digit
   * 
   * @return the maximum limit
   */
  public static long getMaxLimit(int digitCount) {
    return (long) (Math.pow(10, digitCount) - 1);
  }

  /**
   * Returns the minimum number range from a given digit count
   *
   * @param digitCount the number of digit
   * 
   * @return the minimum limit
   */
  public static long getMinLimit(int digitCount) {
    return (long) Math.pow(10, digitCount - 1);
  }
  

}
