import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class StudentsGetByPANNumberTestcases
{
public static void main(String gg[])
{
String panNumber=gg[0];
try
{
StudentsDAOInterface studentDAO;
studentDAO =new StudentsDAO();
StudentsDTOInterface studentDTO;
studentDTO=studentDAO.getByPANNumber(panNumber);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

System.out.println("Roll number : "+studentDTO.getRollNumber());
System.out.println("Name : "+studentDTO.getName());
System.out.println("Course code : "+studentDTO.getCourseCode());
System.out.println("Date of birth : "+sdf.format(studentDTO.getDateOfBirth()));
System.out.println("Gender : "+studentDTO.getGender());
System.out.println("is indian : "+studentDTO.getIsIndian());
System.out.println("Family income : "+studentDTO.getFamilyIncome().toPlainString());
System.out.println("Aadhar card number : "+studentDTO.getAadharCardNumber());
System.out.println("PAN card number : "+studentDTO.getPANNumber());
System.out.println("*******************************");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}