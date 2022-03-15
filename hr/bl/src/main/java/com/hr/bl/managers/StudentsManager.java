package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import java.util.*;
import java.math.*;
import com.thinking.machines.enums.*;
import java.text.*;
public class StudentsManager implements StudentsManagerInterface
{
private Map<String,StudentsInterface> rollNumberWiseStudentsMap;
private Map<String,StudentsInterface> aadharCardNumberWiseStudentsMap;
private Map<String,StudentsInterface> panNumberWiseStudentsMap;
private Set<StudentsInterface> studentsSet;
private Map<Integer,Set<StudentsInterface>> courseCodeWiseStudentsMap;

private static StudentsManager studentsManager=null;
private StudentsManager() throws BLException
{
populateDataStructures();
}
private void populateDataStructures() throws BLException
{
this.rollNumberWiseStudentsMap=new HashMap<>();
this.aadharCardNumberWiseStudentsMap=new HashMap<>();
this.panNumberWiseStudentsMap=new HashMap<>();
this.studentsSet=new TreeSet<>();
this.courseCodeWiseStudentsMap=new HashMap<>();
try
{
Set<StudentsDTOInterface> dlStudents;
dlStudents=new StudentsDAO().getAll();
StudentsInterface student;
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
CoursesInterface course;
Set<StudentsInterface> sts;
for(StudentsDTOInterface dlStudent:dlStudents)
{
student=new Students();
student.setRollNumber(dlStudent.getRollNumber());
student .setName(dlStudent.getName());
course=coursesManager.getCourseByCode(dlStudent.getCourseCode());
student.setCourse(course);
student.setDateOfBirth(dlStudent.getDateOfBirth());
if(dlStudent.getGender()=='M')
{
student.setGender(GENDER.MALE);
}
else
{
student.setGender(GENDER.FEMALE);
}
student.setIsIndian(dlStudent.getIsIndian());
student.setFamilyIncome(dlStudent.getFamilyIncome());
student.setAadharCardNumber(dlStudent.getAadharCardNumber());
student.setPANNumber(dlStudent.getPANNumber());
this.rollNumberWiseStudentsMap.put(student.getRollNumber().toUpperCase(),student);
this.aadharCardNumberWiseStudentsMap.put(student.getAadharCardNumber().toUpperCase(),student);
this.panNumberWiseStudentsMap.put(student.getPANNumber().toUpperCase(),student);
this.studentsSet.add(student);
sts=this.courseCodeWiseStudentsMap.get(course.getCode());
if(sts==null)
{
sts=new TreeSet<>();
sts.add(student);
courseCodeWiseStudentsMap.put(new Integer(course.getCode()),sts);
}
else
{
sts.add(student);
}
}
}
catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
} 
}
public static StudentsManagerInterface getStudentsManager() throws BLException
{
if(studentsManager==null) studentsManager=new StudentsManager();
return studentsManager;
}
public void addStudent(StudentsInterface student) throws BLException
{
BLException blException=new BLException();
String rollNumber=student.getRollNumber();
String name=student.getName();
CoursesInterface course=student.getCourse();
int courseCode=0;
Date dateOfBirth=student.getDateOfBirth();
char gender=student.getGender();
boolean isIndian=student.getIsIndian();
BigDecimal familyIncome=student.getFamilyIncome();
String aadharCardNumber=student.getAadharCardNumber();
String panNumber=student.getPANNumber();
if(rollNumber!=null)
{
rollNumber=rollNumber.trim();
if(rollNumber.length()>0)
{
blException.addException("rollNumber","Roll number should be nil/empty");
}
}
if(name==null)
{
blException.addException("name","Name required");
}
else
{
name=name.trim();
if(name.length()==0) blException.addException("name","Name required");
}
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
if(course==null)
{
blException.addException("course","Course required");
}
else
{
courseCode=course.getCode();
if(coursesManager.courseCodeExists(course.getCode())==false)
{
blException.addException("course","Invaliid course code .");
}
}
if(dateOfBirth==null)
{
blException.addException("dateOfBirth","Date of birth required");
}
if(gender==' ')
{
blException.addException("gender","Gender required");
}
if(familyIncome==null)
{
blException.addException("familyIncome","Family income required");
}
else
{
if(familyIncome.signum()==-1)
{
blException.addException("familyIncome","Family income cannot be negative");
}
}
if(aadharCardNumber==null)
{
blException.addException("aadharCardNumber","Aadhar card number required");
}
else
{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addException("aadharCardNumber","AAdhar card number required");
}
}
if(panNumber==null)
{
blException.addException("panNumber","PAN card number required");
}
else
{
panNumber=panNumber.trim();
if(panNumber.length()==0)
{
blException.addException("panNumber","PAN card number required");
}
}
if(panNumber!=null && panNumber.length()>0)
{
if(panNumberWiseStudentsMap.containsKey(panNumber.toUpperCase()))
{
blException.addException("panNumber","PAN number "+panNumber+"exists.");
}
}
if(aadharCardNumber!=null && aadharCardNumber.length()>0)
{
if(aadharCardNumberWiseStudentsMap.containsKey(aadharCardNumber.toUpperCase()))
{
blException.addException("aadharCardNumber","Aadhar card number "+aadharCardNumber+"exists.");
}
}
if(blException.hasException())
{
throw blException;
}
try
{
StudentsDAOInterface studentsDAO;
studentsDAO=new StudentsDAO();
StudentsDTOInterface dlStudents;
dlStudents=new StudentsDTO();
dlStudents.setName(name);
dlStudents.setCourseCode(course.getCode());
 dlStudents.setDateOfBirth(dateOfBirth);
dlStudents.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dlStudents.setIsIndian(isIndian);
dlStudents.setFamilyIncome(familyIncome);
dlStudents.setAadharCardNumber(aadharCardNumber);
dlStudents.setPANNumber(panNumber);
studentsDAO.add(dlStudents);
student.setRollNumber(dlStudents.getRollNumber());
StudentsInterface dsStudents=new Students();

dsStudents.setRollNumber(student.getRollNumber());
dsStudents.setName(student.getName());
dsStudents.setCourse(((CoursesManager)coursesManager).getDSCourseByCode(course.getCode()));

dsStudents.setDateOfBirth((Date)dateOfBirth.clone());
dsStudents.setGender((gender==' ')?GENDER.MALE:GENDER.FEMALE);
dsStudents.setIsIndian(isIndian);
dsStudents.setFamilyIncome(familyIncome);
dsStudents.setAadharCardNumber(aadharCardNumber);
dsStudents.setPANNumber(panNumber);
//update data structure
studentsSet.add(dsStudents); 
rollNumberWiseStudentsMap.put(dsStudents.getRollNumber().toUpperCase(),dsStudents);
aadharCardNumberWiseStudentsMap.put(aadharCardNumber.toUpperCase(),dsStudents);
panNumberWiseStudentsMap.put(panNumber.toUpperCase(),dsStudents);
Set<StudentsInterface> sts;
sts=this.courseCodeWiseStudentsMap.get(dsStudents.getCourse().getCode());
if(sts==null)
{
sts=new TreeSet<>();
sts.add(dsStudents);
courseCodeWiseStudentsMap.put(dsStudents.getCourse().getCode(),sts);
}
else
{
sts.add(dsStudents);
}
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void updateStudent(StudentsInterface student) throws BLException
{
BLException blException=new BLException();
String rollNumber=student.getRollNumber();
String name=student.getName();
CoursesInterface course=student.getCourse();
int courseCode=0;
Date dateOfBirth=student.getDateOfBirth();
char gender=student.getGender();
boolean isIndian=student.getIsIndian();
BigDecimal familyIncome=student.getFamilyIncome();
String aadharCardNumber=student.getAadharCardNumber();
String panNumber=student.getPANNumber();
if(rollNumber==null)
{
blException.addException("rollNumber","Roll number required");
}
else
{
rollNumber=rollNumber.toUpperCase();
if(rollNumber.length()==0)
{
blException.addException("rollNumber","Roll number required.");
}
else
{
if(rollNumberWiseStudentsMap.containsKey(rollNumber.toUpperCase())==false)
{
blException.addException("rollNumber","Invalid roll number : "+rollNumber);
throw blException;
}
}
}
if(name==null)
{
blException.addException("name","Name required");
}
else
{
name=name.trim();
if(name.length()==0) blException.addException("name","Name required");
}
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
if(course==null)
{
blException.addException("course","Course required");
}
else
{
courseCode=course.getCode();
if(coursesManager.courseCodeExists(course.getCode())==false)
{
blException.addException("course","Invaliid course code .");
}
}
if(dateOfBirth==null)
{
blException.addException("dateOfBirth","Date of birth required");
}
if(gender==' ')
{
blException.addException("gender","Gender required");
}
if(familyIncome==null)
{
blException.addException("familyIncome","Family income required");
}
else
{
if(familyIncome.signum()==-1)
{
blException.addException("familyIncome","Family income cannot be negative");
}
}
if(aadharCardNumber==null)
{
blException.addException("aadharCardNumber","Aadhar card number required");
}
else
{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addException("aadharCardNumber","AAdhar card number required");
}
}
if(panNumber==null)
{
blException.addException("panNumber","PAN card number required");
}
else
{
panNumber=panNumber.trim();
if(panNumber.length()==0)
{
blException.addException("panNumber","PAN card number required");
}
}
if(aadharCardNumber!=null && aadharCardNumber.length()>0)
{
if(aadharCardNumberWiseStudentsMap.containsKey(aadharCardNumber.toUpperCase()))
{
blException.addException("aadharCardNumber","Aadhar card number "+aadharCardNumber+"exists.");
}
}
if(panNumber!=null && panNumber.length()>0)
{
if(panNumberWiseStudentsMap.containsKey(panNumber.toUpperCase()))
{
blException.addException("panNumber","PAN card number "+panNumber+"exists.");
}
}
if(blException.hasException())
{
throw blException;
}
try
{
StudentsInterface dsStudents;
dsStudents=rollNumberWiseStudentsMap.get(rollNumber.toUpperCase());
String oldPANNumber=dsStudents.getPANNumber();
String oldAadharCardNumber=dsStudents.getAadharCardNumber();
int oldCourseCode=dsStudents.getCourse().getCode();
StudentsDAOInterface studentsDAO;
studentsDAO=new StudentsDAO();
StudentsDTOInterface dlStudents;
dlStudents=new StudentsDTO();
dlStudents.setName(name);
dlStudents.setCourseCode(course.getCode());
dlStudents.setDateOfBirth(dateOfBirth);
dlStudents.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dlStudents.setIsIndian(isIndian);
dlStudents.setFamilyIncome(familyIncome);
dlStudents.setAadharCardNumber(aadharCardNumber);
dlStudents.setPANNumber(panNumber);
studentsDAO.update(dlStudents);										
dsStudents.setName(student.getName());
dsStudents.setCourse(((CoursesManager)coursesManager).getDSCourseByCode(course.getCode()));
dsStudents.setDateOfBirth((Date)dateOfBirth.clone());
dsStudents.setGender((gender==' ')?GENDER.MALE:GENDER.FEMALE);
dsStudents.setIsIndian(isIndian);
 dsStudents.setFamilyIncome(familyIncome);
dsStudents.setAadharCardNumber(aadharCardNumber);
dsStudents.setPANNumber(panNumber);

//update data structure
studentsSet.remove(dsStudents); 
rollNumberWiseStudentsMap.remove(rollNumber.toUpperCase());
aadharCardNumberWiseStudentsMap.remove(oldAadharCardNumber.toUpperCase());
panNumberWiseStudentsMap.remove(oldPANNumber.toUpperCase());
studentsSet.add(dsStudents);
rollNumberWiseStudentsMap.put(dsStudents.getRollNumber().toUpperCase(),dsStudents);
aadharCardNumberWiseStudentsMap.put(aadharCardNumber.toUpperCase(),dsStudents);
panNumberWiseStudentsMap.put(panNumber.toUpperCase(),dsStudents);
if(oldCourseCode!=dsStudents.getCourse().getCode())
{
Set<StudentsInterface> sts;
sts=this.courseCodeWiseStudentsMap.get(oldCourseCode);
sts.remove(dsStudents);
sts=this.courseCodeWiseStudentsMap.get(dsStudents.getCourse().getCode());
if(sts==null)
{
sts=new TreeSet<>();
sts.add(dsStudents);
courseCodeWiseStudentsMap.put(dsStudents.getCourse().getCode(),sts);
}
else
{
sts.add(dsStudents);
}
}
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void removeStudent(String rollNumber) throws BLException
{
if(rollNumber==null)
{
BLException blException=new BLException();
blException.addException("rollNumber","Roll number required");
}
else
{
rollNumber=rollNumber.toUpperCase();
if(rollNumber.length()==0)
{
BLException blException=new BLException();
blException.addException("rollNumber","Roll number required.");
}
else
{
if(rollNumberWiseStudentsMap.containsKey(rollNumber.toUpperCase())==false)
{
BLException blException=new BLException();
blException.addException("rollNumber","Invalid roll number : "+rollNumber);
throw blException;
}
}
}
try
{
StudentsInterface dsStudents;
dsStudents=rollNumberWiseStudentsMap.get(rollNumber.toUpperCase());

StudentsDAOInterface studentsDAO;
studentsDAO=new StudentsDAO();
studentsDAO.delete(dsStudents.getRollNumber());

//update data structure
studentsSet.remove(dsStudents); 
rollNumberWiseStudentsMap.remove(rollNumber.toUpperCase());
aadharCardNumberWiseStudentsMap.remove(dsStudents.getAadharCardNumber().toUpperCase());
panNumberWiseStudentsMap.remove(dsStudents.getPANNumber().toUpperCase());

Set<StudentsInterface> sts;
sts=this.courseCodeWiseStudentsMap.get(dsStudents.getCourse().getCode());
sts.remove(dsStudents);
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public StudentsInterface getStudentByRollNumber(String rollNumber) throws BLException
{
StudentsInterface dsStudents=rollNumberWiseStudentsMap.get(rollNumber.toUpperCase());
if(dsStudents==null)
{
BLException blException=new BLException();
blException.addException("rollNumber","Invalid Roll number: "+rollNumber);
throw blException;
}
StudentsInterface student=new Students();
student.setRollNumber(dsStudents.getRollNumber());
student.setName(dsStudents.getName());
CoursesInterface dsCourses=dsStudents.getCourse();
CoursesInterface course=new Courses();
course.setCode(dsCourses.getCode());
course.setTitle(dsCourses.getTitle());
student.setCourse(course);
student.setDateOfBirth((Date)dsStudents.getDateOfBirth().clone());
student.setGender((dsStudents.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
student.setIsIndian(dsStudents.getIsIndian());
student.setFamilyIncome(dsStudents.getFamilyIncome());
student.setAadharCardNumber(dsStudents.getAadharCardNumber());
student.setPANNumber(dsStudents.getPANNumber());
return student;
}
public StudentsInterface getStudentByAadharCardNumber(String aadharCardNumber) throws BLException
{
StudentsInterface dsStudents=aadharCardNumberWiseStudentsMap.get(aadharCardNumber.toUpperCase());
if(dsStudents==null)
{
BLException blException=new BLException();
blException.addException("aadharCardNumber","Invalid Aadhar card number: "+aadharCardNumber);
throw blException;
}
StudentsInterface student=new Students();
student.setRollNumber(dsStudents.getRollNumber());
student.setName(dsStudents.getName());
CoursesInterface dsCourses=dsStudents.getCourse();
CoursesInterface course=new Courses();
course.setCode(dsCourses.getCode());
course.setTitle(dsCourses.getTitle());
student.setCourse(course);
student.setDateOfBirth((Date)dsStudents.getDateOfBirth().clone());
student.setGender((dsStudents.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
student.setIsIndian(dsStudents.getIsIndian());
student.setFamilyIncome(dsStudents.getFamilyIncome());
student.setAadharCardNumber(dsStudents.getAadharCardNumber());
student.setPANNumber(dsStudents.getPANNumber());
return student;

}
public StudentsInterface getStudentByPANNumber(String panNumber) throws BLException
{

StudentsInterface dsStudents=panNumberWiseStudentsMap.get(panNumber.toUpperCase());
if(dsStudents==null)
{
BLException blException=new BLException();
blException.addException("panNumber","Invalid PAN card number: "+panNumber);
throw blException;
}
StudentsInterface student=new Students();
student.setRollNumber(dsStudents.getRollNumber());
student.setName(dsStudents.getName());
CoursesInterface dsCourses=dsStudents.getCourse();
CoursesInterface course=new Courses();
course.setCode(dsCourses.getCode());
course.setTitle(dsCourses.getTitle());
student.setCourse(course);
student.setDateOfBirth((Date)dsStudents.getDateOfBirth().clone());
student.setGender((dsStudents.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
student.setIsIndian(dsStudents.getIsIndian());
student.setFamilyIncome(dsStudents.getFamilyIncome());
student.setAadharCardNumber(dsStudents.getAadharCardNumber());
student.setPANNumber(dsStudents.getPANNumber());
return student;
}
public int getStudentsCount()
{
return studentsSet.size();
}
public boolean studentRollNumberExists(String rollNumber)
{
return rollNumberWiseStudentsMap.containsKey(rollNumber.toUpperCase());
}
public boolean studentPANNumberExists(String panNumber)
{
return panNumberWiseStudentsMap.containsKey(panNumber.toUpperCase());
}
public boolean studentAadharCardNumberExists(String aadharCardNumber)
{
return aadharCardNumberWiseStudentsMap.containsKey(aadharCardNumber.toUpperCase());
}
public Set<StudentsInterface> getStudents() 
{
Set<StudentsInterface> students=new TreeSet<>();
StudentsInterface student;
CoursesInterface dsCourses;
CoursesInterface course;
for(StudentsInterface dsStudents:studentsSet)
{
student=new Students();
student.setRollNumber(dsStudents.getRollNumber());
student.setName(dsStudents.getName());
dsCourses=dsStudents.getCourse();
course=new Courses();
course.setCode(dsCourses.getCode());
course.setTitle(dsCourses.getTitle());
student.setCourse(course);
student.setDateOfBirth((Date)dsStudents.getDateOfBirth().clone());
student.setGender((dsStudents.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
student.setIsIndian(dsStudents.getIsIndian());
student.setFamilyIncome(dsStudents.getFamilyIncome());
student.setAadharCardNumber(dsStudents.getAadharCardNumber());
student.setPANNumber(dsStudents.getPANNumber());
students.add(student);
}
return students;
}
public Set<StudentsInterface> getStudentsByCourseCode(int courseCode) throws BLException
{
CoursesManagerInterface coursesManager;
coursesManager=CoursesManager.getCoursesManager();
if(coursesManager.courseCodeExists(courseCode)==false)
{
BLException blException=new BLException();
blException.setGenericException("Invalid course code "+courseCode);
}
Set<StudentsInterface> students=new TreeSet<>();
Set<StudentsInterface> sts;
sts=courseCodeWiseStudentsMap.get(courseCode);
if(sts==null) return students;
StudentsInterface student;
CoursesInterface dsCourses;
CoursesInterface course;
for(StudentsInterface dsStudents:sts)
{
student=new Students();
student.setRollNumber(dsStudents.getRollNumber());
student.setName(dsStudents.getName());
dsCourses=dsStudents.getCourse();
course=new Courses();
course.setCode(dsCourses.getCode());
course.setTitle(dsCourses.getTitle());
student.setCourse(course);
student.setDateOfBirth((Date)dsStudents.getDateOfBirth().clone());
student.setGender((dsStudents.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
student.setIsIndian(dsStudents.getIsIndian());
student.setFamilyIncome(dsStudents.getFamilyIncome());
student.setAadharCardNumber(dsStudents.getAadharCardNumber());
student.setPANNumber(dsStudents.getPANNumber());
students.add(student);
}
return students;
}
public int getStudentsCountByCourseCode(int courseCode) throws BLException
{
Set<StudentsInterface> sts;
sts=this.courseCodeWiseStudentsMap.get(courseCode);
if(sts==null) return 0;
return sts.size();
}
public boolean courseAlloted(int courseCode) throws BLException
{
return this.courseCodeWiseStudentsMap.containsKey(courseCode);
}
}
