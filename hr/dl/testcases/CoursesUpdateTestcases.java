import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CoursesUpdateTestcases
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
String title=gg[1];
try
{
CoursesDTOInterface courseDTO=new CoursesDTO();
courseDTO.setCode(code);
courseDTO.setTitle(title);
CoursesDAOInterface courseDAO;
courseDAO =new CoursesDAO();
courseDAO.update(courseDTO);
System.out.println("Courses updated");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}