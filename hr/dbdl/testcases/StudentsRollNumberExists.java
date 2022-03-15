import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class StudentsRollNumberExists
{
public static void main(String gg[])
{
String rollNumber=gg[0];
try
{
System.out.println("Roll number : "+rollNumber+" exists : "+new StudentsDAO().rollNumberExists(rollNumber));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}