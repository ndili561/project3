package list;
import javax.swing.*;
import java.awt.*;
// import javax.*;
import java.awt.event.*;
// import java.awt.GridBagConstraints;
// import java.awt.GridBagLayout;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;
// import javax.swing.ImageIcon;
// import javax.swing.UIManager;
// import javax.swing.UnsupportedLookAndFeelException;
// import java.awt.BorderLayout;
// import java.awt.Dimension;
// import java.awt.Font;
// import java.awt.GridLayout;
import com.github.lgooddatepicker.components.*;
// import javax.swing.ImageIcon;
import java.net.URL;



public class Panel extends JPanel {
   private static final String TITLE_TEXT = "Task List";
   private static final int TITLE_POINTS = 24;
   private static DatePicker date;
   private static JTextField task;
   private static JTextField description;
   private static JRadioButton radioButton;
   private static JTextField createFile;
   private static JFrame frame;


   public Panel() {
      JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
      titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD,
            TITLE_POINTS));
      JPanel titlePanel = new JPanel();
      titlePanel.add(titleLabel); 
      date = new DatePicker();
      URL dateImageURL = Panel.class.getResource("/images/datepickerbutton1.png");
      Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
      ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
      JButton datePickerButton = date.getComponentToggleCalendarButton();
      datePickerButton.setText("Task Date");
      datePickerButton.setIcon(dateExampleIcon);
      datePickerButton.setIcon(dateExampleIcon);

      JPanel northAreaPanel = new JPanel();
      northAreaPanel.add(new JLabel("Enter Task"));
      task = new JTextField(10);
      northAreaPanel.add(task);
      northAreaPanel.add(new JLabel("Task Details"));
      description = new JTextField(10);
      northAreaPanel.add(description);
      northAreaPanel.add(Box.createHorizontalStrut(20));
      northAreaPanel.add(date);
      radioButton = new JRadioButton("Important");
      radioButton.setActionCommand("Important");
      northAreaPanel.add(radioButton);
    

      JPanel northPanel = new JPanel();
      northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
      northPanel.add(titlePanel);
      northPanel.add(northAreaPanel);
     

      JPanel southBtnPanel = new JPanel(new GridLayout(2, 4, 1, 1));
         JButton add = new JButton("Add to File");
         southBtnPanel.add(add);
         add.addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e) {
                Panel.addTask();
           }
         });
         JButton createFile = new JButton("Read File");
         southBtnPanel.add(createFile);
         createFile.addMouseListener(new MouseAdapter(){
              public void mousePressed(MouseEvent e) {
                Panel.readFile();
              }
         });
         

      setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
      setLayout(new BorderLayout());
      add(northPanel, BorderLayout.NORTH);
      add(Box.createRigidArea(new Dimension(400, 400))); 
      add(southBtnPanel, BorderLayout.SOUTH);
   }

   private static void addTask(){
        String importance=null;
          if(radioButton.isSelected()){
          importance = radioButton.getText();
       }else{
         importance = "not important";
       }
       Task task1 = new Task(task.getText(),description.getText(),date.getText(),importance);
       Task.writeToFile(task1);
       JOptionPane.showMessageDialog(frame.getComponent(0), "Task Added");
   }

   private static void readFile(){
        Task.loadTask();
   }

  

   public static void createAndShowGui() {
      Panel mainPanel = new Panel();
      frame = new JFrame("To-Do List");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
}