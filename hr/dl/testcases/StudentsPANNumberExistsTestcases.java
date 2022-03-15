import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class StudentsPANNumberExistsTestcases
{
public static void main(String gg[])
{
String panNumber=gg[0];
try
{
System.out.println("PANNumber : "+panNumber+" exists : "+new StudentsDAO().panNumberExists(panNumber));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}