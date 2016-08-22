import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;
import list.*;
import java.util.*;


public class TaskTest{

Task task;
@Before
public void before(){
  task = new Task("shop", "food", "August 11, 2016", "Important", "Not Completed");
}

@Test
public void hasBeenCompleted(){
  assertEquals("Not Completed", task.isCompleted());
}



@Test
public void hasTask(){
  assertEquals("shop", task.getTask());
}

@Test
public void hashDescription(){
  assertEquals("food", task.getDescription());
}

@Test
public void hasDate(){
  assertEquals("August 11, 2016", task.getDate());
}

@Test
public void Importance(){
  assertEquals("Important", task.getImportance());
}

@Test
public void getIntDate(){
  String date = "August 11, 2016";
  assertEquals(11, task.returnDay(date));
}

@Test
public void geMonth(){
 assertEquals("August",  task.returnMonth("August 11, 2016"));
}

@Test
public void thedayToDate(){
  Date date = new Date("August 11, 2016");
  assertEquals(date,task.dateToDate("August 11, 2016"));
}

@Test
public void changeImportance(){
  task.setImportance("not Important");
  assertEquals("not Important",task.getImportance());
}

@Test 
public void changeToCompleted(){
  task.setCompleted("Completed");
  assertEquals("Completed", task.isCompleted());
}







}