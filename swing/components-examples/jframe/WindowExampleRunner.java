public class WindowExampleRunner {
  public static void main(String[] args) {
    fixLookAndFeel();
    try {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
            WindowExample winEx = new WindowExample();
            winEx.show();
          }
        });
    } catch(Exception e) {
      System.err.println("Exception in main: " + e.getMessage());
    }
  }

  static void fixLookAndFeel() {
    try {
      // Ignore the lines below - it's a fix for Rikard's computer. Hell Dell!
      javax.swing.UIManager
        .setLookAndFeel((javax.swing.LookAndFeel)Class
                        .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                        .newInstance());
    } catch (Exception ignore) {}
  }
}
