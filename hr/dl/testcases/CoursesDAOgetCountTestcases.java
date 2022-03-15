import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CoursesDAOgetCountTestcases
{
public static void main(String gg[])
{
try
{
CoursesDAOInterface courseDAO;
courseDAO =new CoursesDAO();
System.out.println("Designation count is : "+courseDAO.getCount());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}