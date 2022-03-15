import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class  CoursesManagerGetCourseByCodeTestcases
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
CoursesInterface course;
course=coursesManager.getCourseByCode(code);
System.out.printf("Code : %d ...... Title : %s\n",course.getCode(),course.getTitle());
}catch(BLException blException)
{
List<String> properties=blException.getProperties();
properties.forEach((property)->{
System.out.println(blException.getException(property));
});
}
}
}