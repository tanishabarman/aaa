package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
import java.io.*;
public class StudentsDAO implements StudentsDAOInterface
{
private static final String FILE_NAME="student.data";
public void add(StudentsDTOInterface studentDTO) throws DAOException
{
if(studentDTO==null) throw new DAOException("Student data is null");
String rollNumber;
String name=studentDTO.getName();
if(name==null) throw new DAOException("name is null");
name=name.trim();
if(name.length()==0) throw new DAOException("length of name is zero");
int courseCode=studentDTO.getCourseCode();
if(courseCode<=0) throw new DAOException("code is negative or zero");
CoursesDAOInterface courseDAO=new CoursesDAO();
boolean isCourseCodeValid=courseDAO.codeExists(courseCode);
if(!isCourseCodeValid) throw new DAOException("course code "+courseCode+"is not alloted");
Date dateOfBirth=studentDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("date of birth is null");
char gender=studentDTO.getGender();
if(gender==' ') throw new DAOException("gender is not mentioned");
boolean isIndian=studentDTO.getIsIndian();
BigDecimal familyIncome=studentDTO.getFamilyIncome();
if(familyIncome==null) throw new DAOException("family income is null");
if(familyIncome.signum()==-1) throw new DAOException("family income is negative");
String aadharCardNumber=studentDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("aadhar card number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("length of aadhar card number is zeroo");
String panNumber=studentDTO.getPANNumber();
if(panNumber==null) throw new DAOException("PAN card number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("length of PAN card number is zeroo");
try
{
int lastGeneratedRollNumber=10000000;
String lastGeneratedRollNumberString=" ";
int recordCount=0;
String recordCountString=" ";
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
lastGeneratedRollNumberString=String.format("%-10s","10000000");
randomAccessFile.writeBytes(lastGeneratedRollNumberString+"\n");
recordCountString=String.format("%-10s","0");
randomAccessFile.writeBytes(recordCountString+"\n");
}
else
{
lastGeneratedRollNumber=Integer.parseInt(randomAccessFile.readLine().trim());
recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
}
boolean aadharCardNumberExists,panNumberExists;
panNumberExists=false;
aadharCardNumberExists=false;
String fAadharCardNumber;
String fPANNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=7;x++) randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
if(aadharCardNumberExists==false && aadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
aadharCardNumberExists=true;
}
if(panNumberExists==false && panNumber.equalsIgnoreCase(fPANNumber))
{
panNumberExists=true;
}
if(panNumberExists && aadharCardNumberExists) break;
}
if(aadharCardNumberExists && panNumberExists)
{
throw new DAOException("aadhar card ("+aadharCardNumber+") and pan number ("+panNumber+") exists");
}
if(panNumberExists)
{
throw new DAOException("pan number ("+panNumber+") exists");
}
if(aadharCardNumberExists)
{
throw new DAOException("aadhar card number ("+aadharCardNumber+") exists");
}
lastGeneratedRollNumber++;
rollNumber="A"+lastGeneratedRollNumber;
recordCount++;
randomAccessFile.writeBytes(rollNumber+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(courseCode+"\n");
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(familyIncome.toPlainString()+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.seek(0);
lastGeneratedRollNumberString=String.format("%-10d",lastGeneratedRollNumber);
randomAccessFile.writeBytes(lastGeneratedRollNumberString+"\n");
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
studentDTO.setRollNumber(rollNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void update(StudentsDTOInterface studentDTO) throws DAOException
{

if(studentDTO==null) throw new DAOException("Student data is null");
String rollNumber=studentDTO.getRollNumber();
if(rollNumber==null) throw new DAOException("roll number is null");
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) throw new DAOException("length of roll number is zero");
String name=studentDTO.getName();
if(name==null) throw new DAOException("name is null");
name=name.trim();
if(name.length()==0) throw new DAOException("length of name is zero");
int courseCode=studentDTO.getCourseCode();
if(courseCode<=0) throw new DAOException("code is negative or zero");
CoursesDAOInterface courseDAO=new CoursesDAO();
boolean isCourseCodeValid=courseDAO.codeExists(courseCode);
if(!isCourseCodeValid) throw new DAOException("course code "+courseCode+"is not alloted");
Date dateOfBirth=studentDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("date of birth is null");
char gender=studentDTO.getGender();
if(gender==' ') throw new DAOException("gender is not mentioned");
boolean isIndian=studentDTO.getIsIndian();
BigDecimal familyIncome=studentDTO.getFamilyIncome();
if(familyIncome==null) throw new DAOException("family income is null");
if(familyIncome.signum()==-1) throw new DAOException("family income is negative");
String aadharCardNumber=studentDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("aadhar card number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("length of aadhar card number is zeroo");
String panNumber=studentDTO.getPANNumber();
if(panNumber==null) throw new DAOException("PAN card number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("length of pan card number is zeroo");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Inavlid roll number : "+rollNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
throw new DAOException("Invalid roll number"+rollNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String fRollNumber;
String fName;
int fCourseCode;
Date fDateOfBirth;
char fGender;
boolean fIsIndian;
BigDecimal fBasicSalary;
String fAadharCardNumber;
String fPANNumber; 
int x;
boolean rollNumberFound=false;
boolean aadharCardNumberFound=false;
boolean panNumberFound=false;
String panNumberFoundAgainstRollNumber="";
String aadharCardNumberFoundAgainstRollNumber="";
long foundAt=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
if(rollNumberFound==false) foundAt=randomAccessFile.getFilePointer();
fRollNumber=randomAccessFile.readLine();
for(x=1;x<=6;x++) randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
if(rollNumberFound==false && fRollNumber.equalsIgnoreCase(rollNumber))
{
rollNumberFound=true;
}
if(aadharCardNumberFound==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
aadharCardNumberFound=true;
aadharCardNumberFoundAgainstRollNumber=fRollNumber;
}
if(panNumberFound==false && fPANNumber.equalsIgnoreCase(panNumber))
{
panNumberFound=true;
panNumberFoundAgainstRollNumber=fRollNumber;
}
if(rollNumberFound && panNumberFound && aadharCardNumberFound) break;
}
if(rollNumberFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid roll number : "+rollNumber);
}
boolean panNumberExists=false;
if(panNumberFound && panNumberFoundAgainstRollNumber.equalsIgnoreCase(rollNumber)==false)
{
panNumberExists=true;
}
boolean aadharCardNumberExists=false;
if(aadharCardNumberFound && aadharCardNumberFoundAgainstRollNumber.equalsIgnoreCase(rollNumber)==false)
{
aadharCardNumberExists=true;
}
if(panNumberExists && aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN number ("+panNumber+") and Aadhar card number ("+aadharCardNumber+") exists.");
}
if(panNumberExists)
{
randomAccessFile.close();
throw new DAOException("PAN number ("+panNumber+") exists.");
}
if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number ("+aadharCardNumber+") exists.");
}
randomAccessFile.seek(foundAt);
for(x=1;x<=9;x++) randomAccessFile.readLine();
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
}
randomAccessFile.seek(foundAt);
randomAccessFile.writeBytes(rollNumber+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(courseCode+"\n");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(familyIncome.toPlainString()+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void delete(String rollNumber) throws DAOException
{
if(rollNumber==null) throw new DAOException("roll number is null");
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) throw new DAOException("length of roll number is zero");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Inavlid roll number : "+rollNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
throw new DAOException("Invalid roll number"+rollNumber);
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
String fRollNumber;
int x;
boolean rollNumberFound=false;
long foundAt=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
foundAt=randomAccessFile.getFilePointer();
fRollNumber=randomAccessFile.readLine();
for(x=1;x<=8;x++) randomAccessFile.readLine();
if(fRollNumber.equalsIgnoreCase(rollNumber))
{
rollNumberFound=true;
break;
}
}
if(rollNumberFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid roll number : "+rollNumber);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
}
randomAccessFile.seek(foundAt);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
recordCount--;
String recordCountString=String.format("%-10d",recordCount);
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();

}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}
public Set<StudentsDTOInterface> getAll() throws DAOException
{
Set<StudentsDTOInterface> students=new TreeSet<>();
try
{
File file=new File(FILE_NAME);
if(!file.exists()) return students;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
return students;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentsDTOInterface studentDTO;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
char fGender;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
studentDTO=new StudentsDTO();
studentDTO.setRollNumber(randomAccessFile.readLine());
studentDTO.setName(randomAccessFile.readLine());
studentDTO.setCourseCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
studentDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
}
fGender=randomAccessFile.readLine().charAt(0);
if(fGender=='M') studentDTO.setGender(GENDER.MALE);
if(fGender=='F') studentDTO.setGender(GENDER.FEMALE);
studentDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
studentDTO.setFamilyIncome(new BigDecimal(randomAccessFile.readLine()));
studentDTO.setAadharCardNumber(randomAccessFile.readLine());
studentDTO.setPANNumber(randomAccessFile.readLine());
students.add(studentDTO);
}
randomAccessFile.close();
return students;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public Set<StudentsDTOInterface> getByCourseCode(int courseCode) throws DAOException
{
if(new CoursesDAO().codeExists(courseCode)==false)
{
throw new DAOException("Invalid course code : "+courseCode);
}
Set<StudentsDTOInterface> students=new TreeSet<>();
try
{
File file=new File(FILE_NAME);
if(!file.exists()) return students;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
return students;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentsDTOInterface studentDTO;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String fRollNumber,fName;
int fCourseCode;
int x;
char fGender;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
if(fCourseCode!=courseCode)
{
for(x=1;x<=6;x++) randomAccessFile.readLine();
continue;
}
studentDTO=new StudentsDTO();
studentDTO.setRollNumber(fRollNumber);
studentDTO.setName(fName);
studentDTO.setCourseCode(fCourseCode);
try
{
studentDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
}
fGender=randomAccessFile.readLine().charAt(0);
if(fGender=='M') studentDTO.setGender(GENDER.MALE);
if(fGender=='F') studentDTO.setGender(GENDER.FEMALE);
studentDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
studentDTO.setFamilyIncome(new BigDecimal(randomAccessFile.readLine()));
studentDTO.setAadharCardNumber(randomAccessFile.readLine());
students.add(studentDTO);
studentDTO.setPANNumber(randomAccessFile.readLine());
students.add(studentDTO);
}
randomAccessFile.close();
return students;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public StudentsDTOInterface getByRollNumber(String rollNumber) throws DAOException
{
if(rollNumber==null) throw new DAOException("roll number is null");
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) throw new DAOException("length of roll number is 0");

try
{
File file=new File(FILE_NAME);
if(!file.exists()) throw new DAOException("file does not exists");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("file is empty");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentsDTOInterface studentDTO;
String fRollNumber;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=randomAccessFile.readLine();
if(fRollNumber.equalsIgnoreCase(rollNumber))
{
studentDTO=new StudentsDTO();
studentDTO.setRollNumber(fRollNumber);
studentDTO.setName(randomAccessFile.readLine());
studentDTO.setCourseCode(Integer.parseInt(randomAccessFile.readLine()));
try
{
studentDTO.setDateOfBirth(simpleDateFormat.parse(randomAccessFile.readLine()));
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
}
studentDTO.setGender((randomAccessFile.readLine().charAt(0)=='M')?GENDER.MALE:GENDER.FEMALE);
studentDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
studentDTO.setFamilyIncome(new BigDecimal(randomAccessFile.readLine()));
studentDTO.setAadharCardNumber(randomAccessFile.readLine());
studentDTO.setPANNumber(randomAccessFile.readLine());
randomAccessFile.close();
return studentDTO;
}
for(int i=1;i<=8;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
throw new DAOException("not found");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public StudentsDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) throw new DAOException("Aadharcard number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("length od aadhar number is zero");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid aadhar card number"+aadharCardNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid aadhar card number"+aadharCardNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentsDTOInterface studentDTO;
String fRollNumber;
String fName;
int fCourseCode;
Date fDateOfBirth=null;
char fGender;
boolean fIsIndian;
BigDecimal fFamilyIncome;
String fAadharCardNumber;
String fPANNumber;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
fGender=randomAccessFile.readLine().charAt(0);
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fFamilyIncome=new BigDecimal(randomAccessFile.readLine());
fAadharCardNumber=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
studentDTO=new StudentsDTO();
studentDTO.setRollNumber(fRollNumber);
studentDTO.setName(fName);
studentDTO.setCourseCode(fCourseCode);
studentDTO.setDateOfBirth(fDateOfBirth);
studentDTO.setGender((fGender=='M')?GENDER.MALE:GENDER.FEMALE);
studentDTO.setIsIndian(fIsIndian);
studentDTO.setFamilyIncome(fFamilyIncome);
studentDTO.setAadharCardNumber(fAadharCardNumber);
studentDTO.setPANNumber(fPANNumber);
randomAccessFile.close();
return studentDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid aadhar card number "+aadharCardNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
 public StudentsDTOInterface getByPANNumber(String panNumber) throws DAOException
{

if(panNumber==null) throw new DAOException("PAN card number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("length of pan number is zero");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid aadhar card number"+panNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid pan card number"+panNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentsDTOInterface studentDTO;
String fRollNumber;
String fName;
int fCourseCode;
Date fDateOfBirth=null;
char fGender;
boolean fIsIndian;
BigDecimal fFamilyIncome;
String fAadharCardNumber;
String fPANNumber;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
try
{
fDateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
fGender=randomAccessFile.readLine().charAt(0);
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fFamilyIncome=new BigDecimal(randomAccessFile.readLine());
fAadharCardNumber=randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
studentDTO=new StudentsDTO();
studentDTO.setRollNumber(fRollNumber);
studentDTO.setName(fName);
studentDTO.setCourseCode(fCourseCode);
studentDTO.setDateOfBirth(fDateOfBirth);
studentDTO.setGender((fGender=='M')?GENDER.MALE:GENDER.FEMALE);
studentDTO.setIsIndian(fIsIndian);
studentDTO.setFamilyIncome(fFamilyIncome);
studentDTO.setAadharCardNumber(fAadharCardNumber);
studentDTO.setPANNumber(fPANNumber);
randomAccessFile.close();
return studentDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid pan card number "+panNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean rollNumberExists(String rollNumber) throws DAOException
{
if(rollNumber==null) return false;
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) return false;
try
{
File file=new File(FILE_NAME);
if(!file.exists()) throw new DAOException("file does not exists");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("file is empty");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fRollNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=randomAccessFile.readLine();
if(fRollNumber.equalsIgnoreCase(rollNumber))
{
randomAccessFile.close();
return true;
}
for(int i=1;i<=8;i++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) return false;
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) return false;
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fAadharCardNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=7;x++) randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean panNumberExists(String panNumber) throws DAOException
{

if(panNumber==null) return false;
panNumber=panNumber.trim();
if(panNumber.length()==0) return false;
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fPANNumber;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(x=1;x<=8;x++) randomAccessFile.readLine();
fPANNumber=randomAccessFile.readLine();
if(fPANNumber.equalsIgnoreCase(panNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean isCourseAlloted(int courseCode) throws DAOException
{
try
{
File file=new File(FILE_NAME);
if(!file.exists()) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentsDTOInterface studentDTO;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String fRollNumber,fName;
int fCourseCode;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fRollNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
if(fCourseCode==courseCode) return true;
for(x=1;x<=6;x++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public int getCount() throws DAOException
{
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) return 0;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public int getCountByCourseCode(int courseCode) throws DAOException
{
try
{
if(new CoursesDAO().codeExists(courseCode)==false) throw new DAOException("Invalid course code : "+courseCode);
File file=new File(FILE_NAME);
if(file.exists()==false) return 0;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCourseCode;
int count=0;
int x;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
if(fCourseCode==courseCode) count++;
for(x=1;x<=6;x++) randomAccessFile.readLine();
}
randomAccessFile.close();
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}