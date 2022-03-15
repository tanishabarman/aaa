import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class  CoursesManagerGetCoursesCountTestcases
{
public static void main(String gg[])
{
try
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
System.out.println("Number of courses : "+coursesManager.getCoursesCount());
}catch(BLException blException)
{
List<String> properties=blException.getProperties();
properties.forEach((property)->{
System.out.println(blException.getException(property));
});
}
}
}