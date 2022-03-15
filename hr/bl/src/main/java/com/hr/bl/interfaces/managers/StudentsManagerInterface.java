package com.thinking.machines.hr.bl.interfaces.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;
public interface StudentsManagerInterface
{
public void addStudent(StudentsInterface student) throws BLException;
public void updateStudent(StudentsInterface student) throws BLException;
public void removeStudent(String rollNumber) throws BLException;
public StudentsInterface getStudentByRollNumber(String rollNumber) throws BLException;
public StudentsInterface getStudentByAadharCardNumber(String aadharCardNumber) throws BLException;
public StudentsInterface getStudentByPANNumber(String panNumber) throws BLException;
public int getStudentsCount();
public boolean studentRollNumberExists(String rollNumber);
public boolean studentPANNumberExists(String panNumber);
public boolean studentAadharCardNumberExists(String aadharCardNumber);
public Set<StudentsInterface> getStudents();
public Set<StudentsInterface> getStudentsByCourseCode(int courseCode) throws BLException;
public int getStudentsCountByCourseCode(int courseCode) throws BLException;
public boolean courseAlloted(int courseCode) throws BLException;

}