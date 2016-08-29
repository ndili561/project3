package list;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.lang.Exception;


 
public class SplashWindow extends JFrame {
 
    private JLabel imglabel;
    private ImageIcon img;
    private Image image;
    private static JProgressBar pbar;
    Thread t = null;
 
    public SplashWindow() {
        setSize(404, 310);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        image = null;
        URL imgSmartURL = this.getClass().getResource("/java5.png");
                try{
                     image = ImageIO.read(imgSmartURL);
                }
            catch(Exception e){
       System.out.println("Exception" +e);
        }
        img = new ImageIcon(image);
        imglabel = new JLabel(img,JLabel.CENTER);
        add(imglabel);
        setLayout(null);
        pbar = new JProgressBar();
        pbar.setMinimum(0);
        pbar.setMaximum(100);
        pbar.setStringPainted(true);
        pbar.setForeground(Color.LIGHT_GRAY);
        imglabel.setBounds(0, 0, 404, 310);
        add(pbar);
        pbar.setPreferredSize(new Dimension(310, 30));
        pbar.setBounds(0, 290, 404, 20);
 
        Thread t = new Thread() {
            public void run() {
                int i = 0;
                while (i <= 100) {
                    pbar.setValue(i);
                    try {
                        sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SplashWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    i++;
                }
            }
        };
        t.start();
    }
}
   

