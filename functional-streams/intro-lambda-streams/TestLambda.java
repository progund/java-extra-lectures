import static java.util.Arrays.asList;

public class TestLambda {
  
  public static void main(String[] args) {
    // Print all arguments on a separate line in upper case
    asList(args).forEach(s -> System.out.println(s.toUpperCase()));
    // Create a List<Integer> and filter all elements divisible by 3 and print them
    asList(1,2,3,4,5,6,7,8,9,10,11,12).stream().filter(i -> i%3==0).forEach(System.out::println);
  }

}
