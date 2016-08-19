package list;
import javax.swing.*;
import java.awt.*;
import javax.*;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import com.github.lgooddatepicker.components.*;
import javax.swing.ImageIcon;
import java.net.URL;



public class Panel extends JPanel {
   private static final String TITLE_TEXT = "Task List";
   private static final int TITLE_POINTS = 24;


   public Panel() {
      JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
      titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD,
            TITLE_POINTS));
      JPanel titlePanel = new JPanel();
      titlePanel.add(titleLabel); 
      DatePicker date = new DatePicker();
      URL dateImageURL = Panel.class.getResource("/images/datepickerbutton1.png");
      Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
      ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
      JButton datePickerButton = date.getComponentToggleCalendarButton();
      datePickerButton.setText("");
      datePickerButton.setIcon(dateExampleIcon);
      datePickerButton.setIcon(dateExampleIcon);

      JPanel accountBalancePanel = new JPanel();
      accountBalancePanel.add(new JLabel("Enter Task"));
      JTextField text = new JTextField(10);
      accountBalancePanel.add(text);
      accountBalancePanel.add(Box.createHorizontalStrut(20));
      accountBalancePanel.add(new JLabel("Task Details"));
      accountBalancePanel.add(new JTextField(10));
      accountBalancePanel.add(date);
      final JRadioButton java = new JRadioButton("Important");
      java.setActionCommand("Important");
      accountBalancePanel.add(java);
    

      JPanel northPanel = new JPanel();
      northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
      northPanel.add(titlePanel);
      northPanel.add(accountBalancePanel);
     

      JPanel southBtnPanel = new JPanel(new GridLayout(2, 4, 1, 1));
         JButton add = new JButton("Add to File");
         southBtnPanel.add(add);
         add.addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e) {
              String task = text.getText();
              String day = date.getText();
              if(java.isSelected()){
              System.out.println(java.getText());
           }else{
            System.out.println("not important");
           }
           
           }
         });
         JButton modify = new JButton("Modify");
         southBtnPanel.add(modify);
         modify.addMouseListener(new MouseAdapter(){
              public void mousePressed(MouseEvent e) {
                System.out.println(e.getClass());
              }
         });
         

      setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
      setLayout(new BorderLayout());
      add(northPanel, BorderLayout.NORTH);
      add(Box.createRigidArea(new Dimension(400, 400))); // just an empty placeholder
      add(southBtnPanel, BorderLayout.SOUTH);
   }

   public static void createAndShowGui() {
      Panel mainPanel = new Panel();
      JFrame frame = new JFrame("Nested Panels Example");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }


   // public static void mousePressed(MouseEvent e){
   //    for(String text : BTN_TEXTS){
   //       if(BTN_TEXTS.equals("Create a New Account")){
   //          System.out.println("hello");
   //       }else if (BTN_TEXTS.equals("Load a Trans from a File")){
   //          System.out.println("bye");
   //       }else{
   //          System.out.println("goodbye");
   //       }
   //    }
   // }

   
}





































































// class Runner{

// public static void main(String[] args){
//   JFrame frame = new JFrame();
//   frame.setVisible(true);
//   frame.setSize(500,400);
//   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//   frame.setLocation(500, 100);
//   try{
//     JPanel panel = new JPanel();

//       frame.add(panel);
     
//       // this.name = username_field.getText();

//       JButton loginBtn = new JButton("LOGIN");
//       loginBtn.setBounds(40, 150, 120, 25);
//       panel.add(loginBtn);
//   BufferedImage img = ImageIO.read(new File("/Users/user/desktop/rally.jpg"));
//   frame.setContentPane(new JLabel(new ImageIcon(img)));
//   frame.setLayout(new GridBagLayout());
//                       GridBagConstraints gbc = new GridBagConstraints();
//                       // gbc.gridwidth = GridBagConstraints.BASELINE;
//                       // Add stuff...
//                       frame.add(new JLabel("Hello world"), gbc);
//                       frame.add(new JLabel("I'm on top"),gbc);
//                       frame.add(new JButton("Clickity-clackity"),gbc);
//                       JTextField textField = new JTextField("This is a text",10);
//                       textField.setColumns(10);
//                       frame.add(textField);

//                       frame.pack();
//                       frame.setLocationRelativeTo(null);
//                       frame.setSize(600,700);
//                       frame.setVisible(true);

// }
// catch(Exception e){
//   System.out.println(e);
// }
 
  
//   //    frame.add(loginBtn);
//   //    // frame.pack();
//   //    frame.setVisible(true);

//   Entrant anEntrant = new Entrant();
// }

// }
