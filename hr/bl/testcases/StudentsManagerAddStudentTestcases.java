import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.math.*;
class StudentsManagerAddStudentTestcases
{
public static void main(String gg[])
{
try
{
String name="Nitin";
CoursesInterface course=new Courses();
course.setCode(1);
Date dateOfBirth=new Date();
boolean isIndian=true;
BigDecimal familyIncome=new BigDecimal("1042123");
String aadharCardNumber="ACN123";
String panNumber="PAN321";
StudentsInterface student;
student= new Students();
student.setName(name);
student.setCourse(course);
student.setDateOfBirth(dateOfBirth);
student.setGender(GENDER.FEMALE);
student.setIsIndian(isIndian);
student.setFamilyIncome(familyIncome);
student.setAadharCardNumber(aadharCardNumber);
student.setPANNumber(panNumber);
StudentsManagerInterface studentsManager=StudentsManager.getStudentsManager();
studentsManager.addStudent(student);
System.out.printf("Student added with roll number : %s \n",student.getRollNumber());
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

