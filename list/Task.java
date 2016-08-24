package list;
import java.text.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.github.lgooddatepicker.components.*;
import java.util.*;
import com.github.lgooddatepicker.optionalusertools.*;
import java.time.LocalDate;
import com.github.lgooddatepicker.zinternaltools.*;
import java.awt.Color;



public class Task implements DateHighlightPolicy {
  private String task;
  private String description;
  private String date;
  private String importance;
  private String completed;
  private String category;
  private static List<Task>tasks;


  public Task(String aTask,String aDescription,String aDate, String category,String importance,String isCompleted){
   this.task = aTask;
   this.description = aDescription;
   this.date = aDate;
   this.category = category;
   this.importance = importance;
   this.completed = isCompleted;
  }

 public Task(){
    super();
 }
 /**get category of the task**/

 public String getCategory(){
  return this.category;
 }

/** return if the task is completed**/
 public String isCompleted(){
  return this.completed;
 }

/** return the task**/
 public String getTask(){
  return this.task;
 }

/** return the task's description**/
 public String getDescription(){
  return this.description;
 }

/**return the date**/
 public String getDate(){
  return this.date;
 }

/** return the date to date object**/
 public Date getDateToDate(){
  return Task.dateToDate(this.getDate());
 }

/** return if the task is important**/
 public String getImportance(){
  return this.importance;
 }

/** set importance of the task**/
 public void setImportance(String importance){
   this.importance = importance;
  }
/** set the task completed**/
 public void setCompleted(String completed){
  this.completed = completed;
 }
/** set task category**/
 public void setCategory(String category){
  this.category = category;
 }
/** set the date of the task**/
 public void setDate(String date){
  this.date = date;
 }

/**return the day of ta date**/
 public int returnDay(String date){
 int day=0;
 try{
  SimpleDateFormat formatter = new SimpleDateFormat("MMM d, y");
  Date dat = formatter.parse(date);
  Calendar cal = Calendar.getInstance();
  cal.setTime(dat);
  day = cal.get(Calendar.DAY_OF_MONTH);
  }catch (ParseException e) 
  {
  System.out.println(e);
  }
  return day;
  }

 /**return the month of the date from a string**/

  public String returnMonth(String date){
   int month=0;
   try{
    SimpleDateFormat formatter = new SimpleDateFormat("MMM d, y");
    Date dat = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(dat);
    month = cal.get(Calendar.MONTH);
  }catch (ParseException e) 
  {
    System.out.println(e);
  }
  return Task.getMonthForInt(month);
  }


  private static String getMonthForInt(int num) {
  String month = "wrong";
  DateFormatSymbols dfs = new DateFormatSymbols();
  String[] months = dfs.getMonths();
  if (num >= 0 && num <= 11 ) {
    month = months[num];
  }
  return month;
  }

  /** write to File**/

  public static void writeToFile(Task task){
  // String path = Task.choose();
  File file = new File("/Users/user/Desktop/text.txt");
  FileWriter writer = null;
  try
  {
    writer = new FileWriter(file,true);
    writer.write(task.getTask() + "    " + task.getDescription() + "    " + task.getDate()+"    "+task.getCategory()+"    "+task.getImportance()+"    " +task.isCompleted());
    writer.write(System.getProperty( "line.separator" ));
  }
  catch(Exception exception)
  {
    System.out.println("Error" + exception);
  }
  finally 
  {
    try{
      writer.close();
    }
    catch (Exception exception){
      System.out.println("Error "+ exception);
    }
  }
    }

/**choose the file**/
  protected static String choose(){
 String file = null;
 JFileChooser chooser = new JFileChooser();
 Component y = new Checkbox();
 int returnVal = chooser.showOpenDialog(y);
 if(returnVal == JFileChooser.APPROVE_OPTION) {
  file = chooser.getSelectedFile().getAbsolutePath();
  }
  return file;
  }

/** read the file**/
  public static List<Task> loadTask(){
 // String path = Task.choose();
   File file = new File("/Users/user/Desktop/text.txt");
   Scanner bufferedScanner = null;
   tasks = new ArrayList<Task>();
   try{
    String task;
    String description;
    String date;
    String category;
    String importance;
    String completed;
    Scanner lineScanner;
    String currentLine=null;
    bufferedScanner = new Scanner(new BufferedReader(new FileReader(file)));
    while(bufferedScanner.hasNextLine()){
      currentLine = bufferedScanner.nextLine();
      lineScanner = new Scanner(currentLine);
      System.out.println(currentLine);
      lineScanner.useDelimiter("    ");
      task = lineScanner.next();
      description = lineScanner.next();
      date = lineScanner.next();
      category = lineScanner.next();
      importance = lineScanner.next();
      completed = lineScanner.next();
      System.out.println(category);
      tasks.add(new Task(task,description,date,category,importance,completed));
    }
  }
  catch(Exception e){
   System.out.println("Error "+ e);
   }
 finally{
  try{
   bufferedScanner.close();
   }
 catch(Exception e){
   System.out.println("Error" +e);
      }
    } 
   return tasks;
  }

  /**change the string to date object**/

  public static Date dateToDate(String adate){
    String trimmed = adate.trim();
    Date date = null;
    DateFormat format = new SimpleDateFormat("MMMM dd, yyyy",Locale.US);
    try{
      date = format.parse(trimmed);
    }
    catch(Exception e){
      System.out.println("Error" + e);
    }
    return date;
  }

  /**rewrite the file ofter tasks have been modified**/

  public static void convertObject(String aList){
    String[] array = aList.split(",");
    List<Task>task = Task.loadTask();

    for( Iterator< Task > it = task.iterator(); it.hasNext() ; )
    {
      Task str = it.next();
      if( str.getTask().equals(array[0] ) )
      {
       it.remove();
     }
   }
   Task.reWrite(task);
 }



 /**rewrite the file after amendements**/
 public static void reWrite(List<Task> list){
   PrintWriter writer = null;
   try{
    writer = new PrintWriter("/Users/user/Desktop/text.txt");
    writer.print("");
  }catch(Exception exec){
   System.out.println("Exception"+exec);
  }
  writer.close();
  File file = new File("/Users/user/Desktop/text.txt");
  FileWriter write = null;
  try{
  write = new FileWriter(file,true);
  for (Task task : list){
    write.write(task.getTask() + "    " + task.getDescription() + "    " + task.getDate()+"    "+task.getCategory()+"    "+task.getImportance()+"    " +task.isCompleted());
    write.write(System.getProperty( "line.separator" ));
  }
  }catch(Exception exect){
  System.out.println("Error"+ exect);
      }
      finally{
        try{
          write.close();
        }
        catch (Exception except){
         System.out.println("Error "+ except);
       }
     }
  } 


  /**create a new file to the desired path**/

  public String createAFile(){
    String file = null;
   JFileChooser chooser = new JFileChooser();
   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
   chooser.setAcceptAllFileFilterUsed(false);
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      file = chooser.getSelectedFile().getAbsolutePath();
    }
  return file;
  }

  /**set the task to compelte**/

  public void setToCompleted(String aList){
  String[] array = aList.split(",");
  String lastToken = array[array.length-1];
  lastToken="Completed";
  array[array.length-1]=lastToken;
  List<Task>task = Task.loadTask();
  for( Iterator< Task > it = task.iterator(); it.hasNext() ; )
  {
    Task str = it.next();
    if( str.getTask().equals(array[0] ) )
    {
      it.remove();
    }
  }
  Task result = new Task(array[0],array[1],array[2]+array[3],array[4],array[5],lastToken);
  System.out.println(result.isCompleted());
  task.add(result);
  Task.reWrite(task);
  }
  /**return a list of the events per day**/
  public static List<Integer> eventsDay(){
    Date d = null;
    Calendar calend = Calendar.getInstance();
    List<Task> events = Task.loadTask();
    List<Integer> day = new ArrayList<Integer>();
    for(Task e : events){
      d = Task.dateToDate(e.getDate());
      Calendar cal = Calendar.getInstance();
      cal.setTime(d);
      int days = cal.get(Calendar.DAY_OF_MONTH);
      day.add(days);
    }
    return day;
  }

    /**implements the interface for the calendar color**/

    public HighlightInformation getHighlightInformationOrNull(LocalDate date){
      List <Task> task = Task.loadTask();
      int dayInInt = 0;
      int monthInInt = 0;
      String month = null;
      for (Task tsk : task){
        if (tsk.isCompleted().equals("Not Completed")){
         Date day = Task.dateToDate(tsk.getDate());
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(day);
         dayInInt= calendar.get(Calendar.DAY_OF_MONTH);
         monthInInt = calendar.get(Calendar.MONTH);
         month = Task.getMonthForInt(monthInInt);
         String mon = month.toUpperCase();
         System.out.println(month);
         if ((date.getDayOfMonth() == dayInInt & date.getMonth().equals(mon)) & tsk.getCategory().equals("Business")) {
           return new HighlightInformation(Color.red, null, tsk.getDescription()+", Business category");
         }if ((date.getDayOfMonth() == dayInInt & date.getMonth().equals(mon)) & tsk.getCategory().equals("Pleasure")){
          return new HighlightInformation(Color.green, null, tsk.getDescription()+", Pleasure category");
        }
      } 
    }
    return null;
  }  
   /**return a list of the business tasks**/
   public static List<Task> categoriseBusiness(){
   List<Task>task = Task.loadTask();
   List<Task>result = new ArrayList<Task>();
   for (Task tsk : task){
     if (tsk.getCategory().equals("Business")){
      result.add(tsk);
     }
   }
   return result;
  }
   /**return a list of the pleasure tasks**/
   public static List<Task> categorisePleasure(){
   List<Task>task = Task.loadTask();
   List<Task>result = new ArrayList<Task>();
   for (Task tsk : task){
      if (tsk.getCategory().equals("Pleasure")){
      result.add(tsk);
      }
    }
   return result;
  }      
}  





