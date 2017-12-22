package org.contactcompany.api.util;

/**
 * A utility class with (so far) one static method for getting
 * values from the user, using standard out and standard in for
 * communication.
 *
 * This class cannot be instantiated, and serves only as a
 * utility class, to help reading text from standard in.
 */
public class TextUtils{

  // Prevent people from creating instances of this class
  private TextUtils(){}
  /**
   * Asks the user a question and returns the result
   * <p>
   * The String returned cannot be null (if the user creates a null,
   * the empty String will be returned). The String returned will be trimmed
   * so no white space remains before or after the text in the String.
   * </p>
   * Asks the user a question and returns the result
   * @param prompt The question to ask the user, e.g. "Name: "
   * @return The user's reply as a reference to a String
   */
  public static String askFor(String prompt){
    String result=null;
    System.out.print(prompt + ": ");
    if(System.console() == null){
      result = new java.util.Scanner(System.in).nextLine();
    }else{
      result = System.console().readLine();
    }
    if(result==null){
      result = "";
    }
    return result.trim();
  }
}
