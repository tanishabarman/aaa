import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class StudentsIsCourseAllotedTestcases
{
public static void main(String gg[])
{
int courseCode=Integer.parseInt(gg[0]);
try
{
StudentsDAOInterface studentDAO=new StudentsDAO();
System.out.println("Students with course code : "+courseCode+" exists "+studentDAO.isCourseAlloted(courseCode));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}