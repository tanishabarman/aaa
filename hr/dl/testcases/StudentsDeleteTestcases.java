import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
public class StudentsDeleteTestcases
{
public static void main(String gg[])
{
String rollNumber=gg[0];
try
{
StudentsDAOInterface studentDAO;
studentDAO=new StudentsDAO();
studentDAO.delete(rollNumber);
System.out.println("Student with "+rollNumber+" is deleted");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}
