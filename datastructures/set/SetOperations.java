import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class SetOperations {
  public static void main(String[] args) {
    Set<Integer> s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
    Set<Integer> s2 = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
    Set<Integer> union = new HashSet<>(s1);
    union.addAll(s2);

    Set<Integer> intersection = new HashSet<>(s1);
    intersection.retainAll(s2);

    Set<Integer> difference = new HashSet<>(s1);
    difference.removeAll(s2);

    System.out.println("s1: " + s1);
    System.out.println("s2: " + s2);
    System.out.println("union: " + union);
    System.out.println("intersection: " + intersection);
    System.out.println("difference: " + difference);
  }
}
