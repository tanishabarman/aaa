import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CoursesCodeExistsTestcases
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
CoursesDAOInterface courseDAO;
courseDAO =new CoursesDAO();
System.out.println(code+" exists : "+courseDAO.codeExists(code));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}