import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class  CoursesManagerGetCoursesTestcases
{
public static void main(String gg[])
{
try
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
Set<CoursesInterface> courses;
courses=coursesManager.getCourses();
courses.forEach((course)->{
System.out.printf("Code : %d , Title : %s \n",course.getCode(),course.getTitle());
});
}catch(BLException blException)
{
List<String> properties=blException.getProperties();
properties.forEach((property)->{
System.out.println(blException.getException(property));
});
}
}
}