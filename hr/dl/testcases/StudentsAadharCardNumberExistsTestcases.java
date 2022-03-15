import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class StudentsAadharCardNumberExistsTestcases
{
public static void main(String gg[])
{
String aadharCardNumber=gg[0];
try
{
System.out.println("AadharCardNumber : "+aadharCardNumber+" exists : "+new StudentsDAO().aadharCardNumberExists(aadharCardNumber));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}