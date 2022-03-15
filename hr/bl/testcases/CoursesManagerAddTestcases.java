import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class CoursesManagerAddTestcases
{
public static void main(String gg[])
{
CoursesInterface course=new Courses();
course.setTitle("J2EE");
try
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
coursesManager.addCourses(course);
System.out.println("Courses added with code as : "+course.getCode());
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