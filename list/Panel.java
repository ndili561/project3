package list;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.github.lgooddatepicker.components.*;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.io.*;



public class Panel extends JPanel {
 private static final String TITLE_TEXT = "Task List";
 private static final int TITLE_POINTS = 24;
 private static DatePicker date;
 private static JTextField task;
 private static JTextField description;
 private static JRadioButton radioButton;
 private static JFrame frame;
 protected static List<Task>tasks;




 public Panel() {
  JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
  titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD,
    TITLE_POINTS));
  JPanel titlePanel = new JPanel();
  titlePanel.add(titleLabel); 


  JPanel northAreaPanel = new JPanel();
  northAreaPanel.add(new JLabel("Enter Task"));
  task = new JTextField(10);
  northAreaPanel.add(task);
  northAreaPanel.add(new JLabel("Task Details"));
  description = new JTextField(10);
  northAreaPanel.add(description);
  northAreaPanel.add(Box.createHorizontalStrut(20));
  date = new DatePicker();
  URL dateImageURL = Panel.class.getResource("/images/datepickerbutton1.png");
  Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
  ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
  JButton datePickerButton = date.getComponentToggleCalendarButton();
  datePickerButton.setText("Task Date");
  datePickerButton.setIcon(dateExampleIcon);
  datePickerButton.setIcon(dateExampleIcon);
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
  JButton readFile = new JButton("Read File");
  southBtnPanel.add(readFile);
  readFile.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      Panel.readFile();
    }
  });
  JButton createFile = new JButton("Create File");
  southBtnPanel.add(createFile);
  createFile.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      Task task = new Task();
      boolean create = false;
     String file =  task.createAFile();
    try{
      File file2 = new File(file+"/created.txt");
     create = file2.createNewFile();
  }
  catch(Exception exet){
    System.out.println("Error");
    }
    System.out.println(create);
  }

  });
  JButton createJavadoc = new JButton("Create Javadoc");
  southBtnPanel.add(createJavadoc);
  createJavadoc.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      String url = "/Users/user/Desktop/javadoc/index.html";
      Runtime rt = Runtime.getRuntime();
      try{
        Process pr = rt.exec("javadoc ~/Desktop/project3/list/*.java -d ~/Desktop/javadoc");
      }catch(Exception ex){
        System.out.println("Exception");
      }
      try{
        File htmlFile = new File(url);
        Desktop.getDesktop().browse(htmlFile.toURI());
      }catch (Exception exec){
        System.out.println("Error" +exec);
      }

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
  String completed = "Not Completed";
  if(radioButton.isSelected()){
    importance = radioButton.getText();
  }else{
   importance = "not important";
 }
 Task task1 = new Task(task.getText(),description.getText(),date.getText(),importance, completed);
 Task.writeToFile(task1);
 JOptionPane.showMessageDialog(frame.getComponent(0), "Task Added");
}

private static void readFile(){
 tasks = Task.loadTask();
 JFrame frame = new JFrame("List Model Example");
 // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setContentPane(new ListItem());
 frame.setSize(400, 200);
 frame.setVisible(true);
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