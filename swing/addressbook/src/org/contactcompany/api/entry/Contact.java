package org.contactcompany.api.entry;
import java.io.Serializable;

/**
 * This class describes a Contact (for instance an entry in an
 * address book). The class implements Serializable (so that
 * Contact instances can be saved) and Comparable (so that
 * Contact instances in a list can be sorted).
 *
 * Contact instances are immutable, which means that you cannot change
 * the state of an instance of Contact. Every call to methods for
 * changing an attribute of a Contact, such as changeName(), will
 * result in a new Contact with a state representing the old Contact
 * but with a new value for (in this case) name.
 */

public final class Contact implements Serializable, Comparable<Contact>{
  private final String name;
  private final String email;
  private final String phone;
  
  /**
   * Constructs a new Contact, using the arguments for the state.
   * If the name parameter is null, a NullPointerException is thrown. 
   * If the name parameter is the empty string, an IllegalArgumentException
   * is thrown. 
   *
   * If any of the other parameters (email or phone) is null, they are converted to the
   * empty String (it is not possible to create a contact without a name, but
   * both email and phone may be empty).
   * @param name The name of this Contact (a name is a simple String with both
   * first name and last name)
   * @param email The email address of this Contact
   * @param phone The phone number of this Contact
   * @throws java.lang.NullPointerException if name is null
   */
  public Contact(String name, String email, String phone){
    if(name==null){
      throw new NullPointerException("Name cannot be null");
    }
    if(name.equals("")){
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.name  = name;
    this.email = email==null?"":email;
    this.phone = phone==null?"":phone;
  }

  /**
   * Returns the name of this contact, as a reference to a String
   * @return the name of this contact
   */
  public String name() { return name; }

  /**
   * Returns the email of this contact, as a reference to a String
   * @return the email of this contact
   */
  public String email(){ return email; }

  /**
   * Returns the phone number of this contact, as a reference to a String
   * @return the phone number of this contact
   */
  public String phone(){ return phone; }
  
  /**
   * Changes the name of this Contac by returning a new Contact reflecting this change
   */
  public Contact changeName(String newName){
    return new Contact(newName, email, phone);
  }

  /**
   * Changes the email of this Contac by returning a new Contact reflecting this change
   */
  public Contact changeEmail(String newEmail){
    return new Contact(name, newEmail, phone);
  }
  
  /**
   * Changes the phone number of this Contac by returning a new Contact reflecting this change
   */
  public Contact changePhone(String newPhone){
    return new Contact(name, email, newPhone);
  }
  
  /**
   * Returns a String representation of this Contact on the form "Name Email Phone"
   * @return A reference to a String representing this Contact
   */
  @Override
  public String toString(){
    return name + " " + email + " " + phone; 
  }
  
  /**
   * Compares this Contact with another Contact lexicograhpically, using only the name for comparison.
   * See java.lang.String for an explanation on how Strings are compared.
   */
  @Override
  public int compareTo(Contact other){
    if(other == null){
      throw new NullPointerException("Can't compare to null");
    }
    return name.compareTo(other.name);
  }
  
  /**
   * Compares this Contact to the Specified object. The result is true if and only if the argument is not null
   * and is a Contact object that represents the same name as this Contact.
   * @return true if the given object represents a Contact with exactly the same name as this object, false otherwise
   */
  @Override
  public boolean equals(Object other){
    if(other == null || !(other instanceof Contact)){
      return false;
    }
    Contact otherContact = (Contact)other;
    return otherContact.name.equals(name); 
  }

  /**
   * Returns a hashcode for this Contact, using only the name of this contact.
   * @return a hashcode for this Contact.
   */
  @Override
  public int hashCode(){
    return name.hashCode();
  }
}
