import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
public class StudentsAddTestcases
{
public static void main(String gg[])
{
String name=gg[0];
int studentCode=Integer.parseInt(gg[1]);
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
Date dateOfBirth=null;
try
{
dateOfBirth=simpleDateFormat.parse(gg[2]);
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
return;
}
char gender=gg[3].charAt(0);
boolean isIndian=Boolean.parseBoolean(gg[4]);
BigDecimal familyIncome=new BigDecimal(gg[5]);
String aadharCardNumber=gg[6];
String panNumber=gg[7];
try
{
StudentsDTOInterface studentDTO=new StudentsDTO();
studentDTO.setName(name);
studentDTO.setCourseCode(studentCode);
studentDTO.setDateOfBirth(dateOfBirth);
if(gender=='M') studentDTO.setGender(GENDER.MALE);
if(gender=='F') studentDTO.setGender(GENDER.FEMALE);
studentDTO.setIsIndian(isIndian);
studentDTO.setFamilyIncome(familyIncome);
studentDTO.setAadharCardNumber(aadharCardNumber);
studentDTO.setPANNumber(panNumber);
StudentsDAOInterface studentDAO=new StudentsDAO();
studentDAO.add(studentDTO);
System.out.println("Student is added with the roll number as : "+studentDTO.getRollNumber());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}
