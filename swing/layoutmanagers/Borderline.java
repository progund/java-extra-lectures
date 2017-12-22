import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class Borderline {
  private static void fixLookAndFeel() {
    try {
      // Ignore the lines below - it's a fix for Rikard's computer. Hell Dell!
      javax.swing.UIManager
        .setLookAndFeel((javax.swing.LookAndFeel)Class
                        .forName("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
                        .newInstance());
    } catch (Exception ignore) {}
  }
  
  private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("BorderLayout");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(480,200));

    JButton north = new JButton("NORTH");
    JButton west  = new JButton("WEST");
    JButton east  = new JButton("EAST");
    JButton center= new JButton("CENTER");
    JButton south = new JButton("SOUTH");
    frame.add(north, BorderLayout.NORTH);
    frame.add(west, BorderLayout.WEST);
    frame.add(center, BorderLayout.CENTER);
    frame.add(east, BorderLayout.EAST);
    frame.add(south, BorderLayout.SOUTH);
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }
 
  public static void main(String[] args) {
    fixLookAndFeel();
    // Threading is tricky. We'll leave that to Java for now!

    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          createAndShowGUI();
        }
      });
  }
}
