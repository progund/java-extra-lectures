public class Delivery {

  public static class Address {
    private String recipient;
    private String street;
    private String country;
    public Address(String recipient, String street, String country) {
      this.recipient = recipient;
      this.street = street;
      this.country = country;
    }

    public String recipient() {
      return recipient;
    }

    public String street() {
      return street;
    }

    public String country() {
      return country;
    }

    public String toString() {
      return recipient + ", " + street + ", " + country;
    }
  }
  
  public enum Type {
    EXPRESS(100),
    PRIORITY_MAIL(50),
    SNAIL_MAIL(10);

    Type(int price) {
      this.price = price;
    }

    private int price;

    public int price() {
      return price;
    }

    public String toString() {
      return name() + "(" + price + " SEK)";
    }
  }

  private Address address;
  private String goods;
  private Type type;

  public Delivery(Address address, String goods, Type type) {
    this.address = address;
    this.goods = goods;
    this.type = type;
  }

  public double cost() {
    double cost = 0.0;
    switch (address.country()) {
      case "Sweden":
        cost += 100;
        break;
      default:
        cost += 200;
    }
    cost += type.price();
    return cost;
  }

  public String toString() {
    return address + ", " + goods + ", " + type;
  }
}

class DeliveryDemo {
  public static void main(String[] args) {
    // Create a delivery, calculate price
    Delivery.Address addr =
      new Delivery.Address("Mr Holmes", "10 Baker Street, London", "UK");
    Delivery deliv = new Delivery(addr,
                                  "Violin 2000",
                                  Delivery.Type.PRIORITY_MAIL);
    double cost = deliv.cost();
    System.out.println("Cost for " + deliv + " is " + cost);
    addr =
      new Delivery.Address("Herr Holm", "Storgatan 20, Sala", "Sweden");
    deliv = new Delivery(addr,
                         "Violin 2000",
                         Delivery.Type.PRIORITY_MAIL);
    cost = deliv.cost();
    System.out.println("Cost for " + deliv + " is " + cost);
  }
}
