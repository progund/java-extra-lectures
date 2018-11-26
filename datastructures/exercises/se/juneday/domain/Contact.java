package se.juneday.domain;

import java.io.Serializable;

public final class Contact implements Serializable, Comparable<Contact>{

  private final String name;
  private final String email;
  private final String phone;
  
  public Contact(String name, String email, String phone){
    this.name = name;
    this.email = email;
    this.phone = phone;
  }

  public String name() { return name; }

  public String email(){ return email; }

  public String phone(){ return phone; }
  
  @Override
  public String toString(){
    return name + " " + email + " " + phone; 
  }
  
  @Override
  public int compareTo(Contact other){
    return this.name.compareTo(other.name);
  }
  
  @Override
  public boolean equals(Object other){
    if (other==null) {
      throw new NullPointerException("can't do equal on null");
    }
    if ( ! (other instanceof Contact) ) {
      throw new IllegalArgumentException("Can't only invoke equal on Contact instances");
    }
    Contact otherContact = (Contact) other;
    return name.equals(otherContact.name);
  }

  @Override
  public int hashCode(){
    return name.hashCode();
  }
}
