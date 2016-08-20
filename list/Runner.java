package list;

import javax.swing.SwingUtilities;

public class Runner{

public static void main(String[] args) {
   SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Panel.createAndShowGui();  
      }
    });
  }
}