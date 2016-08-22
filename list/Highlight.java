package list;
import com.github.lgooddatepicker.components.*;
import com.github.lgooddatepicker.optionalusertools.*;
import java.time.*;
import com.github.lgooddatepicker.zinternaltools.HighlightInformation;
import com.github.lgooddatepicker.tableeditors.*;
import com.github.lgooddatepicker.ysandbox.*;
import com.github.lgooddatepicker.zinternaltools.*;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.*;
import java.util.Iterator;



public class Highlight implements DateHighlightPolicy  {

  public HighlightInformation getHighlightInformationOrNull(LocalDate date) {
     
    if (date.getDayOfMonth() == 22) {
      return new HighlightInformation(Color.red, null, "It's the 25th!");
    }
    return null;
  }



  
}