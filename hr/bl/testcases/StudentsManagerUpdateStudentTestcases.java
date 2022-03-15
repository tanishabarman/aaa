import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;
class StudentsManagerUpdateStudentTestcases
{
public static void main(String gg[])
{
try
{
String rollNumber="A10000001";
String name="Tanisha";
CoursesInterface course=new Courses();
course.setCode(3);
Date dateOfBirth=new Date();
boolean isIndian=false;
BigDecimal familyIncome=new BigDecimal("10212023");
String aadharCardNumber="ACN1246";
String panNumber="PAN644";
StudentsInterface student;
student= new Students();
student.setRollNumber(rollNumber);
student.setName(name);
student.setCourse(course);
student.setDateOfBirth(dateOfBirth);
student.setGender(GENDER.FEMALE);
student.setIsIndian(isIndian);
student.setFamilyIncome(familyIncome);
student.setAadharCardNumber(aadharCardNumber);
student.setPANNumber(panNumber);
StudentsManagerInterface studentsManager=StudentsManager.getStudentsManager();
studentsManager.updateStudent(student);
System.out.printf("Student updated");
}catch(BLException blException)
{
if(blException.hasGenericException()) System.out.println(blException.getGenericException());
List<String> properties=blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}
}
}

