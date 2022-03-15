import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
public class CoursesGetAllTestcases
{
public static void main(String gg[])
{
try
{
CoursesDAOInterface courseDAO;
courseDAO =new CoursesDAO();
Set<CoursesDTOInterface> courses;
courses=courseDAO.getAll();
courses.forEach((courseDTO)->{
System.out.printf("code : %d , Title : %s \n",courseDTO.getCode(),courseDTO.getTitle());
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}