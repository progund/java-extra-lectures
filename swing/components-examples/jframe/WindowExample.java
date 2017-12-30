import javax.swing.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;

public class WindowExample {
  private JFrame window;

  public WindowExample() {
    initComponents();
    fixShape();
    fixTranslucence();
  }

  private void initComponents() {
    window = new JFrame("Window example");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(800, 600);
  }

  private void fixShape() {
    window.addComponentListener(new ComponentAdapter() {
        // Give the window an elliptical shape.
        // If the window is resized, the shape is recalculated here.
        @Override
        public void componentResized(ComponentEvent e) {
          window.setShape(new Ellipse2D.Double(0,0,window.getWidth(),window.getHeight()));
        }
      });
  }
  
  private void fixTranslucence() {
    // Determine what the GraphicsDevice can support.
    GraphicsEnvironment ge =
      GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    final boolean isTranslucencySupported =
      gd.isWindowTranslucencySupported(TRANSLUCENT);
    
    //If shaped windows aren't supported, exit.
    if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT)) {
      System.err.println("Shaped windows are not supported");
      System.exit(0);
    }
    
    //If translucent windows aren't supported,
    //create an opaque window.
    if (!isTranslucencySupported) {
      System.out.println(
                         "Translucency is not supported, creating an opaque window");
    } else {
      window.setUndecorated(true);
      window.setOpacity(0.7f);
    }
    
  }
  
  public void show() {
    window.setVisible(true);
  }
}
