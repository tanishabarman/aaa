import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CoursesDAOAddTestcases
{
public static void main(String gg[])
{
String title=gg[0];
try
{
CoursesDTOInterface courseDTO=new CoursesDTO();
courseDTO.setTitle(title);
CoursesDAOInterface courseDAO;
courseDAO =new CourseDAO();
courseDAO.add(courseDTO);
System.out.println("Course with title "+title+" is added with code "+courseDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}