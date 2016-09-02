package list;
import javax.swing.SwingUtilities;
import java.util.*;
import javax.swing.*;


public class Runner{

public static void main(String[] args) {
   SplashWindow s = new SplashWindow();
   s.setVisible(true);
           Thread th1=Thread.currentThread();
           try{
           th1.sleep(7000);
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