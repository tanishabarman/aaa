package com.thinking.machines.hr.dl.dto;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.enums.*;
import java.math.*;
import java.util.*;
public class StudentsDTO implements StudentsDTOInterface
{
private String rollNumber;
private String name;
private int courseCode;
private Date dateOfBirth;
private char gender;
private boolean isIndian;
private BigDecimal familyIncome;
private String aadharCardNumber;
private String panNumber;
public StudentsDTO()
{
this.rollNumber="";
this.name="";
this.courseCode=0;
this.dateOfBirth=null;
this.gender=' ';
this.isIndian=false;
this.familyIncome=null;
this.aadharCardNumber="";
this.panNumber="";
}
public void setRollNumber(java.lang.String rollNumber)
{
this.rollNumber=rollNumber;
}
public java.lang.String getRollNumber()
{
return this.rollNumber;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setCourseCode(int courseCode)
{
this.courseCode=courseCode;
}
public int getCourseCode()
{
return this.courseCode;
}
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setGender(GENDER gender)
{
if(gender==GENDER.MALE) this.gender='M';
else this.gender='F';
}
public char getGender()
{
return this.gender;
}
public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setFamilyIncome(java.math.BigDecimal familyIncome)
{
this.familyIncome=familyIncome;
}
public java.math.BigDecimal getFamilyIncome()
{
return this.familyIncome;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}

public void setPANNumber(java.lang.String panNumber)
{
this.panNumber=panNumber;
}
public java.lang.String getPANNumber()
{
return this.panNumber;
}
public boolean equals(Object other)
{
if(!(other instanceof StudentsDTOInterface)) return false;
StudentsDTOInterface studentDTO=(StudentsDTOInterface)other;
return this.rollNumber.equalsIgnoreCase(studentDTO.getRollNumber());
}
public int compareTo(StudentsDTOInterface other)
{
return this.rollNumber.compareToIgnoreCase(other.getRollNumber());
}
public int hashCode()
{
return this.rollNumber.toUpperCase().hashCode();
}
}