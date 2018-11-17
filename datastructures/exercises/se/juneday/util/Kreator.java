package se.juneday.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import se.juneday.domain.Contact;

public final class Kreator {

  private static Random rand;
  static {
    initialise();
  }
  
  private static void initialise() {
    givenNames = Arrays.asList(
                               "Maria" ,
                               "Anna" ,
                               "Margareta" ,
                               "Elisabeth" ,
                               "Eva" ,
                               "Birgitta" ,
                               "Kristina" ,
                               "Karin" ,
                               "Elisabet" ,
                               "Marie" ,
                               "Ingrid" ,
                               "Christina" ,
                               "Linnéa" ,
                               "Marianne" ,
                               "Sofia" ,
                               "Kerstin" ,
                               "Lena" ,
                               "Helena", 
                               "Erik" ,
                               "Lars" ,
                               "Karl" ,
                               "Anders" ,
                               "Johan" ,
                               "Per" ,
                               "Nils" ,
                               "Jan" ,
                               "Carl" ,
                               "Mikael" ,
                               "Lennart" );
    familyNames = Arrays.asList(
                                "Johansson" ,
                                "Andersson" ,
                                "Karlsson" ,
                                "Nilsson" ,
                                "Eriksson" ,
                                "Larsson" ,
                                "Olsson" ,
                                "Persson" ,
                                "Svensson" ,
                                "Gustafsson" ,
                                "Pettersson" ,
                                "Jonsson" ,
                                "Jansson" ,
                                "Hansson" );
    rand = new Random();
  }

  private static List<String> givenNames;
  private static List<String> familyNames;

  public static Contact createContact() {
    String givenName = givenNames.get(rand.nextInt(givenNames.size()));
    String familyName = familyNames.get(rand.nextInt(familyNames.size()));
    //    System.err.println(" names: " + givenName);
    
    return new Contact(givenName + " " + familyName,
                       givenName.toLowerCase() + "@" + familyName.toLowerCase() + ".com",
                      "" + rand.nextInt(1000000));
  }
  
}
