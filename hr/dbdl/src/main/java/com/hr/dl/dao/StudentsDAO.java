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
import java.sql.*;
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
Connection connection=null;
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select code from courses where code=?");
preparedStatement.setInt(1,courseCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("course code "+courseCode+"is not alloted");
}
resultSet.close();
preparedStatement.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
java.util.Date dateOfBirth=studentDTO.getDateOfBirth();
if(dateOfBirth==null)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
 throw new DAOException("date of birth is null");
}
char gender=studentDTO.getGender();
if(gender==' ')
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
 throw new DAOException("gender is not mentioned");
}
boolean isIndian=studentDTO.getIsIndian();
BigDecimal familyIncome=studentDTO.getFamilyIncome();
if(familyIncome==null) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("family income is null");
}
if(familyIncome.signum()==-1) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("family income is negative");
}
String aadharCardNumber=studentDTO.getAadharCardNumber();
if(aadharCardNumber==null) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("aadhar card number is null");
}
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("length of aadhar card number is zeroo");
}
String panNumber=studentDTO.getPANNumber();
if(panNumber==null) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("PAN card number is null");
}
panNumber=panNumber.trim();
if(panNumber.length()==0) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("length of PAN card number is zeroo");
}
try
{
boolean panNumberExists;
boolean aadharCardNumberExists;
preparedStatement=connection.prepareStatement("select gender from students where pan_number=? ");
preparedStatement.setString(1,panNumber);
resultSet=preparedStatement.executeQuery();
panNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select gender from students where aadhar_card_number=? ");
preparedStatement.setString(1,aadharCardNumber);
resultSet=preparedStatement.executeQuery();
aadharCardNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(aadharCardNumberExists && panNumberExists)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("aadhar card ("+aadharCardNumber+") and pan number ("+panNumber+") exists");
}
if(panNumberExists)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("pan number ("+panNumber+") exists");
}
if(aadharCardNumberExists)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("aadhar card number ("+aadharCardNumber+") exists");
}
preparedStatement=connection.prepareStatement("insert into students (name,course_code,date_of_birth,family_income,gender,is_indian,pan_number,aadhar_card_number) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.setInt(2,courseCode);
java.sql.Date sqlDateOfBirth=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(3,sqlDateOfBirth);
preparedStatement.setBigDecimal(4,familyIncome);
preparedStatement.setString(5,String.valueOf(gender));
preparedStatement.setBoolean(6,isIndian);
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8,aadharCardNumber);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int generatedRollNumber=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
studentDTO.setRollNumber("A"+(1000000+generatedRollNumber));
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void update(StudentsDTOInterface studentDTO) throws DAOException
{
if(studentDTO==null) throw new DAOException("Student data is null");
String rollNumber=studentDTO.getRollNumber();
if(rollNumber==null) throw new DAOException("Roll Number is null");
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) throw new DAOException("length of roll number is zero");
int actualRollNumber;
try
{
actualRollNumber=Integer.parseInt(rollNumber.substring(1))-1000000;
}catch(Exception exception)
{
throw new DAOException("Invalid roll number");
}
String name=studentDTO.getName();
if(name==null) throw new DAOException("name is null");
name=name.trim();
if(name.length()==0) throw new DAOException("length of name is zero");
int courseCode=studentDTO.getCourseCode();
if(courseCode<=0) throw new DAOException("code is negative or zero");
Connection connection=null;
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select code from courses where code=?");
preparedStatement.setInt(1,courseCode);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("course code "+courseCode+"is not alloted");
}
resultSet.close();
preparedStatement.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
java.util.Date dateOfBirth=studentDTO.getDateOfBirth();
if(dateOfBirth==null)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
 throw new DAOException("date of birth is null");
}
char gender=studentDTO.getGender();
if(gender==' ')
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
 throw new DAOException("gender is not mentioned");
}
boolean isIndian=studentDTO.getIsIndian();
BigDecimal familyIncome=studentDTO.getFamilyIncome();
if(familyIncome==null) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("family income is null");
}
if(familyIncome.signum()==-1) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("family income is negative");
}
String aadharCardNumber=studentDTO.getAadharCardNumber();
if(aadharCardNumber==null) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("aadhar card number is null");
}
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("length of aadhar card number is zeroo");
}
String panNumber=studentDTO.getPANNumber();
if(panNumber==null) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("PAN card number is null");
}
panNumber=panNumber.trim();
if(panNumber.length()==0) 
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("length of PAN card number is zeroo");
}
try
{
boolean panNumberExists;
boolean aadharCardNumberExists;
preparedStatement=connection.prepareStatement("select gender from students where pan_number=? and roll_number<>?");
preparedStatement.setString(1,panNumber);
preparedStatement.setInt(2,actualRollNumber);
resultSet=preparedStatement.executeQuery();
panNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select gender from students where aadhar_card_number=? and roll_number<>?");
preparedStatement.setString(1,aadharCardNumber);
preparedStatement.setInt(2,actualRollNumber);
resultSet=preparedStatement.executeQuery();
aadharCardNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
if(aadharCardNumberExists && panNumberExists)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("aadhar card ("+aadharCardNumber+") and pan number ("+panNumber+") exists");
}
if(panNumberExists)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("pan number ("+panNumber+") exists");
}
if(aadharCardNumberExists)
{
try
{
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
throw new DAOException("aadhar card number ("+aadharCardNumber+") exists");
}
preparedStatement=connection.prepareStatement("update students set name=?,course_code=?,date_of_birth=?,family_income=?,gender=?,is_indian=?,pan_number=?,aadhar_card_number=? where roll_number=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,courseCode);
java.sql.Date sqlDateOfBirth=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(3,sqlDateOfBirth);
preparedStatement.setBigDecimal(4,familyIncome);
preparedStatement.setString(5,String.valueOf(gender));
preparedStatement.setBoolean(6,isIndian);
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8,aadharCardNumber);
preparedStatement.setInt(9,actualRollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void delete(String rollNumber) throws DAOException
{
if(rollNumber==null) throw new DAOException("Roll Number is null");
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) throw new DAOException("length of roll number is zero");
int actualRollNumber;
try
{
actualRollNumber=Integer.parseInt(rollNumber.substring(1))-1000000;
}catch(Exception exception)
{
throw new DAOException("Invalid roll number");
}
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
preparedStatement=connection.prepareStatement("select gender from students where roll_number=?");
preparedStatement.setInt(1,actualRollNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid roll number : "+rollNumber);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from students where roll_number=?");
preparedStatement.setInt(1,actualRollNumber);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public Set<StudentsDTOInterface> getAll() throws DAOException
{
Set<StudentsDTOInterface> students=new TreeSet<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select * from students");
StudentsDTOInterface studentDTO; 
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
while(resultSet.next())
{
studentDTO=new StudentsDTO();
studentDTO.setRollNumber("A"+(1000000+resultSet.getInt("roll_number")));
studentDTO.setName(resultSet.getString("name").trim());
studentDTO.setCourseCode(resultSet.getInt("course_code"));
sqlDateOfBirth=resultSet.getDate("date_of_birth");
utilDateOfBirth=new java.util.Date(sqlDateOfBirth.getYear(),sqlDateOfBirth.getMonth(),sqlDateOfBirth.getDate());
studentDTO.setDateOfBirth(sqlDateOfBirth);
studentDTO.setFamilyIncome(resultSet.getBigDecimal("family_income"));
gender=resultSet.getString("gender");
if(gender.equals("M"))
{
studentDTO.setGender(GENDER.MALE);
}
if(gender.equals("F"))
{
studentDTO.setGender(GENDER.FEMALE);
}
studentDTO.setIsIndian(resultSet.getBoolean("is_indian"));
studentDTO.setPANNumber(resultSet.getString("pan_number").trim());
studentDTO.setPANNumber(resultSet.getString("aadhar_card_number").trim());
students.add(studentDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return students;
}
public Set<StudentsDTOInterface> getByCourseCode(int courseCode) throws DAOException
{
Set<StudentsDTOInterface> students=new TreeSet<>();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select code from courses where code=?");
preparedStatement.setInt(1,courseCode);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid course code : "+courseCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from students where course_code=?");
preparedStatement.setInt(1,courseCode);
resultSet=preparedStatement.executeQuery();
StudentsDTOInterface studentDTO; 
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
while(resultSet.next())
{
studentDTO=new StudentsDTO();
studentDTO.setRollNumber("A"+(1000000+resultSet.getInt("roll_number")));
studentDTO.setName(resultSet.getString("name").trim());
studentDTO.setCourseCode(resultSet.getInt("course_code"));
sqlDateOfBirth=resultSet.getDate("date_of_birth");
utilDateOfBirth=new java.util.Date(sqlDateOfBirth.getYear(),sqlDateOfBirth.getMonth(),sqlDateOfBirth.getDate());
studentDTO.setDateOfBirth(sqlDateOfBirth);
studentDTO.setFamilyIncome(resultSet.getBigDecimal("family_income"));
gender=resultSet.getString("gender");
if(gender.equals("M"))
{
studentDTO.setGender(GENDER.MALE);
}
if(gender.equals("F"))
{
studentDTO.setGender(GENDER.FEMALE);
}
studentDTO.setIsIndian(resultSet.getBoolean("is_indian"));
studentDTO.setPANNumber(resultSet.getString("pan_number").trim());
studentDTO.setPANNumber(resultSet.getString("aadhar_card_number").trim());
students.add(studentDTO);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return students;
}
public boolean isCourseAlloted(int courseCode) throws DAOException
{
boolean courseAlloted=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select code from courses where code=?");
preparedStatement.setInt(1,courseCode);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid course code : "+courseCode);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select gender from students where course_code=?");
preparedStatement.setInt(1,courseCode);
resultSet=preparedStatement.executeQuery();
courseAlloted=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return courseAlloted;
}
public StudentsDTOInterface getByRollNumber(String rollNumber) throws DAOException
{
if(rollNumber==null) throw new DAOException("roll number is null");
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) throw new DAOException("length of roll number is 0");
int actualRollNumber=0;
try
{
actualRollNumber=Integer.parseInt(rollNumber.substring(1))-1000000;
}catch(Exception exception)
{
throw new DAOException("Invalid roll number : "+rollNumber);
}
StudentsDTOInterface studentDTO; 
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from students where roll_number=?");
preparedStatement.setInt(1,actualRollNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid roll number : "+rollNumber);
}
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
studentDTO=new StudentsDTO();
studentDTO.setRollNumber("A"+(1000000+resultSet.getInt("roll_number")));
studentDTO.setName(resultSet.getString("name").trim());
studentDTO.setCourseCode(resultSet.getInt("course_code"));
sqlDateOfBirth=resultSet.getDate("date_of_birth");
utilDateOfBirth=new java.util.Date(sqlDateOfBirth.getYear(),sqlDateOfBirth.getMonth(),sqlDateOfBirth.getDate());
studentDTO.setDateOfBirth(sqlDateOfBirth);
studentDTO.setFamilyIncome(resultSet.getBigDecimal("family_income"));
gender=resultSet.getString("gender");
if(gender.equals("M"))
{
studentDTO.setGender(GENDER.MALE);
}
if(gender.equals("F"))
{
studentDTO.setGender(GENDER.FEMALE);
}
studentDTO.setIsIndian(resultSet.getBoolean("is_indian"));
studentDTO.setPANNumber(resultSet.getString("pan_number").trim());
studentDTO.setPANNumber(resultSet.getString("aadhar_card_number").trim());
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return studentDTO;
}
public StudentsDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) throw new DAOException("Aadharcard number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("length od aadhar number is zero");
StudentsDTOInterface studentDTO; 
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from students where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid aadhar card number : "+aadharCardNumber);
}
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
studentDTO=new StudentsDTO();
studentDTO.setRollNumber("A"+(1000000+resultSet.getInt("roll_number")));
studentDTO.setName(resultSet.getString("name").trim());
studentDTO.setCourseCode(resultSet.getInt("course_code"));
sqlDateOfBirth=resultSet.getDate("date_of_birth");
utilDateOfBirth=new java.util.Date(sqlDateOfBirth.getYear(),sqlDateOfBirth.getMonth(),sqlDateOfBirth.getDate());
studentDTO.setDateOfBirth(sqlDateOfBirth);
studentDTO.setFamilyIncome(resultSet.getBigDecimal("family_income"));
gender=resultSet.getString("gender");
if(gender.equals("M"))
{
studentDTO.setGender(GENDER.MALE);
}
if(gender.equals("F"))
{
studentDTO.setGender(GENDER.FEMALE);
}
studentDTO.setIsIndian(resultSet.getBoolean("is_indian"));
studentDTO.setPANNumber(resultSet.getString("pan_number").trim());
studentDTO.setPANNumber(resultSet.getString("aadhar_card_number").trim());
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return studentDTO;
}
 public StudentsDTOInterface getByPANNumber(String panNumber) throws DAOException
{

if(panNumber==null) throw new DAOException("PAN card number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("length of pan number is zero");
StudentsDTOInterface studentDTO; 
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from students where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid pan number : "+panNumber);
}
java.util.Date utilDateOfBirth;
java.sql.Date sqlDateOfBirth;
String gender;
studentDTO=new StudentsDTO();
studentDTO.setRollNumber("A"+(1000000+resultSet.getInt("roll_number")));
studentDTO.setName(resultSet.getString("name").trim());
studentDTO.setCourseCode(resultSet.getInt("course_code"));
sqlDateOfBirth=resultSet.getDate("date_of_birth");
utilDateOfBirth=new java.util.Date(sqlDateOfBirth.getYear(),sqlDateOfBirth.getMonth(),sqlDateOfBirth.getDate());
studentDTO.setDateOfBirth(sqlDateOfBirth);
studentDTO.setFamilyIncome(resultSet.getBigDecimal("family_income"));
gender=resultSet.getString("gender");
if(gender.equals("M"))
{
studentDTO.setGender(GENDER.MALE);
}
if(gender.equals("F"))
{
studentDTO.setGender(GENDER.FEMALE);
}
studentDTO.setIsIndian(resultSet.getBoolean("is_indian"));
studentDTO.setPANNumber(resultSet.getString("pan_number").trim());
studentDTO.setPANNumber(resultSet.getString("aadhar_card_number").trim());
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return studentDTO;
}
public boolean rollNumberExists(String rollNumber) throws DAOException
{
boolean rollNumberExists=false;
if(rollNumber==null) return false;
rollNumber=rollNumber.trim();
if(rollNumber.length()==0) return false;
int actualRollNumber=0;
try
{
actualRollNumber=Integer.parseInt(rollNumber.substring(1))-1000000;
}catch(Exception exception)
{
throw new DAOException("Invalid roll number : "+rollNumber);
}
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select gender from students where roll_number=?");
preparedStatement.setInt(1,actualRollNumber);
ResultSet resultSet=preparedStatement.executeQuery();
rollNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return rollNumberExists;
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null) return false;
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) return false;
boolean aadharCardNumberExists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select gender from students where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
aadharCardNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return aadharCardNumberExists;
}
public boolean panNumberExists(String panNumber) throws DAOException
{
if(panNumber==null) return false;
panNumber=panNumber.trim();
if(panNumber.length()==0) return false;
boolean panNumberExists=false;
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select gender from students where pan_number=?");
preparedStatement.setString(1,panNumber);
ResultSet resultSet=preparedStatement.executeQuery();
panNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return panNumberExists;
}
public int getCount() throws DAOException
{
int count=0;
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select count(*) as cnt from students");
resultSet.next();
count=resultSet.getInt("cnt");
resultSet.close();
statement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return count;
}
public int getCountByCourseCode(int courseCode) throws DAOException
{
int count=0;
try
{
if(new CoursesDAO().codeExists(courseCode)==false) throw new DAOException("Invalid course code : "+courseCode);
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select count(*) as cnt from students where course_code=?");
preparedStatement.setInt(1,courseCode);
ResultSet resultSet=preparedStatement.executeQuery();
resultSet.next();
count=resultSet.getInt("cnt");
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return count;
}
}