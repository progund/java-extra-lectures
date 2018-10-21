package servlets;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class DB {

  private static Connection connection;
  private static final String DB_URL =
    "jdbc:sqlite:webroot/WEB-INF/db/bolaget.db";

  static {
    try {
      connection = DriverManager.getConnection(DB_URL);
    } catch (SQLException sqle) {
      System.err.println("Couldn't get connection to " + DB_URL +
                         sqle.getMessage());
    }
  }

  /* Databasschema:

CREATE TABLE productGroup(id INT PRIMARY KEY NOT NULL,
                          name text);

CREATE TABLE product(nr INT primary key not null, name text,
                     price REAL, alcohol REAL, volume INT,
                     productGroupId INT, type text,
                     foreign key(productGroupId) references productGroup(id));

Tänk på att det finns "name" i båda tabellerna, så ni måste byta namn
med operatorn "AS" på name för att er JOIN ska fungera!

T ex:
                    /---------v----\
SELECT product.name AS product_name, price, alcohol, volume, type, nr,
       productGroup.name AS product_group FROM product
JOIN productGroup        \-----^-------/
ON product.productGroupId = productGroup.id
WHERE product_name like '%whisky%';
      \_____^____/
  */

  // Överkurs: byt till PreparedStatement
  public static List<Product> getProductsByName(String productName) {
    String SQL = "SELECT product.name as product_name, price, alcohol, volume, " +
      "type, nr, productGroup.name as product_group " +
      "FROM product JOIN productGroup ON " +
      "product.productGroupId = productGroup.id " +
      "WHERE product_name like '%" + productName + "%';";
    //System.out.println(SQL);
    List<Product> products = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(SQL);
      while (rs.next()) {
        String name;
        double alcohol;
        int volume;
        double price;
        int nr;
        String productGroup;
        String type;
        name = rs.getString("product_name");
        alcohol = rs.getDouble("alcohol");
        volume = rs.getInt("volume");
        price = rs.getDouble("price");
        nr = rs.getInt("nr");
        type = rs.getString("type");
        productGroup = rs.getString("product_group");
        products.add(new Product.Builder()
                     .name(name)
                     .price(price)
                     .alcohol(alcohol)
                     .volume(volume)
                     .nr(nr)
                     .productGroup(productGroup)
                     .type(type)
                     .build());
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return products;
  }
}
