import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CoursesDeleteTestcases
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
CoursesDAOInterface courseDAO;
courseDAO =new CoursesDAO();
courseDAO.delete(code);
System.out.println("Courses deleted");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}