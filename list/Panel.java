package list;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.github.lgooddatepicker.components.*;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.io.*;
import com.github.lgooddatepicker.optionalusertools.*;
import com.github.lgooddatepicker.zinternaltools.*;
import java.awt.Color;


public class Panel extends JPanel {
 private static DatePicker date;
 private static JTextField task;
 private static JTextField description;
 private static JRadioButton radioButton;
 private static JFrame frame;
 protected static List<Task>tasks;
 private JPanel titlePanel;
 private static Panel mainPanel;
 private static JComboBox menu;



 public Panel() {
  JLabel titleLabel = new JLabel("Task List", SwingConstants.CENTER);
  titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD,24));
  titlePanel = new JPanel();
  titlePanel.add(titleLabel); 

  /**North Area Panel**/
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

  northAreaPanel.add(date);
  String[] category={"Business","Pleasure"};
  menu = new JComboBox<String>(category);
  menu.setVisible(true);
  northAreaPanel.add(menu);
  radioButton = new JRadioButton("Important");
  radioButton.setActionCommand("Important");
  northAreaPanel.add(radioButton);


  JPanel northPanel = new JPanel();
  northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.PAGE_AXIS));
  northPanel.add(titlePanel);
  northPanel.add(northAreaPanel);

 
  /**South area Panel**/
  JPanel southBtnPanel = new JPanel(new GridLayout(2, 4, 1, 1));
  JButton add = new JButton("Add to File");
  southBtnPanel.add(add);
  add.addMouseListener(new MouseAdapter(){
   public void mousePressed(MouseEvent e) {
    Panel.addTask();
  }
  });
  JButton readFile = new JButton("Edit File");
  southBtnPanel.add(readFile);
  readFile.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      Panel.readFile();
    }
  });
  JButton categoriseTask = new JButton("Categorize Task");
  southBtnPanel.add(categoriseTask);
  categoriseTask.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      Panel.categorizeTask();   
    }
  });
  JButton calendar = new JButton("Calendar");
  southBtnPanel.add(calendar);
  calendar.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      Task atask = new Task();
      DatePickerSettings dateSettings = new DatePickerSettings();
      dateSettings.setVisibleClearButton(false);
      dateSettings.setHighlightPolicy(atask);
      DatePicker adate = new DatePicker(dateSettings);
      JFrame calendar = new JFrame();
      CalendarPanel date1 = new CalendarPanel(adate);
      calendar.setContentPane(date1);
      calendar.setVisible(true);
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension screenSize = tk.getScreenSize();
      int screenHeight = screenSize.height;
      int screenWidth = screenSize.width;
      calendar.setSize(screenWidth / 2, screenHeight / 2);
      calendar.setLocation(screenWidth / 4, screenHeight / 4);
      calendar.pack();
    }
  });
  JButton openFile = new JButton("Open File");
  southBtnPanel.add(openFile);
  openFile.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      Component y = new Checkbox();
      int result = fileChooser.showOpenDialog(y);
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        File f = fileChooser.getSelectedFile();
        try{
          Desktop.getDesktop().open(f);
        }
        catch(Exception exect){
          System.out.println(exect);
        }
      }
    }
  });
  JButton createJavadoc = new JButton("Create Javadoc");
  southBtnPanel.add(createJavadoc);
  createJavadoc.addMouseListener(new MouseAdapter(){
    public void mousePressed(MouseEvent e) {
      String url = "javadoc/index.html";
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

/**set the variable to write the file**/

  private static void addTask(){
   String importance=null;
   String completed = "Not Completed";
   if(radioButton.isSelected()){
     importance = radioButton.getText();
   }else{
     importance = "not Important";
   }
   String category = String.valueOf(menu.getSelectedItem());
   Task task1 = new Task(task.getText(),description.getText(),date.getText(),category,importance, completed);
   Task.writeToFile(task1);
   JOptionPane.showMessageDialog(frame.getComponent(0), "Task Added");
 }

   /**read the file**/

   private static void readFile(){
    tasks = Task.loadTask();
    JFrame frame = new JFrame("Tasks");
    frame.setContentPane(new ListItem());
    frame.setVisible(true);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    frame.setSize(screenWidth / 2, screenHeight / 2);
    frame.setLocation(screenWidth / 4, screenHeight / 4);
  }

   /**create and show the GUI**/

  public static void createAndShowGui() {
  mainPanel = new Panel();
  frame = new JFrame("To-Do List");
  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  frame.getContentPane().add(mainPanel);
  frame.pack();
  frame.setLocationByPlatform(true);
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   /**categorize tasks**/

   public static void categorizeTask(){
    Panel.readFile();
    JFrame frame2 = new JFrame("Tasks");
    frame2.setContentPane(new CategorizeTask());
    frame2.setVisible(true);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
    frame2.setSize(screenWidth / 2, screenHeight / 2);
    frame2.setLocation(screenWidth / 4, screenHeight / 4);
   }
}