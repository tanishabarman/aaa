import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CoursesTitleExistsTestcases
{
public static void main(String gg[])
{
String title=gg[0];
try
{
CoursesDAOInterface courseDAO;
courseDAO =new CoursesDAO();
System.out.println(title+" exists : "+courseDAO.titleExists(title));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}