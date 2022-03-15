import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class  CoursesManagerRemoveTestcases
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
coursesManager.removeCourses(code);
System.out.println("Courses deleted/removed \n");
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
List<String> properties=blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}
}
}