import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;
import list.*;


public class TaskTest{

Task task;
@Before
public void before(){
  task = new Task("shop", "food", "August 11, 2016", "Important");
}

@Test
public void hasBeenCompleted(){
  assertEquals(false, task.isCompleted());
}

@Test
public void taskNumber(){
  Task mockedList = mock(Task.class);
  assertEquals(8, verify(mockedList).getTaskNumber());
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
  String date = "23/12/2016";
  assertEquals(23, task.returnDay(date));
}

@Test
public void getIntMonth(){
 assertEquals("December",  task.returnMonth("23/12/2016"));
}








}