import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class  CoursesManagerCourseTitleExistsTestcases
{
public static void main(String gg[])
{
String title=gg[0];
try
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
System.out.println(title+" exists : "+coursesManager.courseTitleExists(title));
}catch(BLException blException)
{
List<String> properties=blException.getProperties();
properties.forEach((property)->{
System.out.println(blException.getException(property));
});
}
}
}