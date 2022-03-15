import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CoursesGetByTitleTestcases
{
public static void main(String gg[])
{
String title=gg[0];
try
{
CoursesDTOInterface courseDTO;
CoursesDAOInterface courseDAO;
courseDAO =new CoursesDAO();
courseDTO=courseDAO.getByTitle(title);
System.out.printf("Code : %d , Title : %s \n",courseDTO.getCode(),courseDTO.getTitle());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}