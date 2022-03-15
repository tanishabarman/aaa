import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class CoursesManagerUpdateTestcases
{
public static void main(String gg[])
{
CoursesInterface course=new Courses();
course.setCode(1);
course.setTitle("Python");
try
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
coursesManager.updateCourses(course);
System.out.println("Courses updated \n");
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