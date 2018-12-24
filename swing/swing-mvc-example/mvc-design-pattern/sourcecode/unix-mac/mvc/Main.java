package mvc;

import mvc.model.*;
import mvc.view.*;
import mvc.control.*;
import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          Model model = new Model();
          View view = new View();
          Control control = new Control(view, model);
          control.controlIt();
        }
      });
  }
}
