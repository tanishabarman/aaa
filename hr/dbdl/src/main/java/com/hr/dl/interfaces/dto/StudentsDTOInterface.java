package com.thinking.machines.hr.dl.interfaces.dto;
import java.math.*;
import java.util.*;
import com.thinking.machines.enums.*;
public interface StudentsDTOInterface extends Comparable<StudentsDTOInterface>,java.io.Serializable
{
public void setRollNumber(String rollNumber);
public String getRollNumber();
public void setName(String name);
public String getName();
public void setCourseCode(int courseCode);
public int getCourseCode();
public void setDateOfBirth(Date dateOfBirth);
public Date getDateOfBirth();
public void setGender(GENDER gender);
public char getGender();
public void setIsIndian(boolean isIndian);
public boolean getIsIndian();
public void setFamilyIncome(BigDecimal familyIncome);
public BigDecimal getFamilyIncome();
public void setAadharCardNumber(String aadharCardNumber);
public String getAadharCardNumber();
public void setPANNumber(String panNumber);
public String getPANNumber();
}