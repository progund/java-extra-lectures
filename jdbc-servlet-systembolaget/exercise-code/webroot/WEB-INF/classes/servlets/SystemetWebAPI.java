package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Predicate;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class SystemetWebAPI extends HttpServlet {

  @Override
  public void init() throws ServletException {
    // Put initialization to be run once at startup here
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
    request.setCharacterEncoding(UTF_8.name());
    response.setContentType("application/json;charset=" + UTF_8.name());
    response.setCharacterEncoding(UTF_8.name());

    PrintWriter out = response.getWriter();

    String productName = request.getParameter("product_name");
    if (productName == null) {
      // Överkurs: sätt rätt HTTP response code för att
      // visa att servern inte kunde hantera requestet.

      // Svara med JSON som innehåller error-meddelande
      out.println("{ error:\"Missing parameter product_name\"}");
      return;
    }

    // Här anropas en statisk metod i DB som skapar en List<Product>
    // med hjälp av JDBC
    List<Product> products = DB.getProductsByName(productName);

    // Uppgift: Implementera metoder för att filtrera listan
    // med hjälp av min_price, max_price, min_alcohol, max_alcohol
    // 1. Genom att skriva metoderna
    //  getProductsByNameAndMaxPrice(String name, double price)
    //  getProductsByNameAndMinPrice(String name, double price)
    
    // Här formateras listan till JSON
    Formatter formatter = FormatterFactory.getFormatter();
    String json = formatter.format(products);

    // Här skrivs JSON ut till response
    out.println(json);
    out.close();
  }
}
