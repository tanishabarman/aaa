import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class StudentsGetCountByCourseCodeTestcases
{
public static void main(String gg[])
{
int courseCode=Integer.parseInt(gg[0]);
try
{
System.out.println("Number of students : "+new StudentsDAO().getCountByCourseCode(courseCode));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}