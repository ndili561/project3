package list;
import javax.swing.SwingUtilities;

import java.util.*;
import javax.swing.*;


public class Runner{

public static void main(String[] args) {
   SplashWindow s = new SplashWindow();
   s.setVisible(true);
           Thread t=Thread.currentThread();
           try{
           t.sleep(7000);
           s.dispose();
         }
         catch(Exception e){
          System.out.println(e);
         }
   SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Panel.createAndShowGui();  
      }
    });
  }
}