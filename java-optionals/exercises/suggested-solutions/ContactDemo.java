public class ContactDemo {

  public static void main(String[] args) {
    Contact unemployed = new Contact("Hank", "hank@gais.se", "123456");
    Contact desputado = new Contact("Ozzy", "ozzy@666.com", "13-666-666666", "031-905109");
    System.out.println("Does unemployed have a work phone? " +
                       (unemployed.workPhone().isPresent() ? " Yes: " + unemployed.workPhone().get()
                        : " nope."));
    System.out.println("Does desputado have a work phone? " +
                       (desputado.workPhone().isPresent() ? " Yes: " + desputado.workPhone().get()
                        : " nope."));
    System.out.println(unemployed);
    System.out.println(desputado);
  }
}
