import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
public class StudentsUpdateTestcases
{
public static void main(String gg[])
{
String rollNumber=gg[0];
String name=gg[1];
int courseCode=Integer.parseInt(gg[2]);
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
Date dateOfBirth=null;
try
{
dateOfBirth=simpleDateFormat.parse(gg[3]);
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
return;
}
char gender=gg[4].charAt(0);
boolean isIndian=Boolean.parseBoolean(gg[5]);
BigDecimal familyIncome=new BigDecimal(gg[6]);
String aadharCardNumber=gg[7];
String panNumber=gg[8];
try
{
StudentsDTOInterface studentDTO=new StudentsDTO();
studentDTO.setRollNumber(rollNumber);
studentDTO.setName(name);
studentDTO.setCourseCode(courseCode);
studentDTO.setDateOfBirth(dateOfBirth);
if(gender=='M') studentDTO.setGender(GENDER.MALE);
if(gender=='F') studentDTO.setGender(GENDER.FEMALE);
studentDTO.setIsIndian(isIndian);
studentDTO.setFamilyIncome(familyIncome);
studentDTO.setAadharCardNumber(aadharCardNumber);
studentDTO.setPANNumber(panNumber);
StudentsDAOInterface studentDAO=new StudentsDAO();
studentDAO.update(studentDTO);
System.out.println("Student is updated");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}
