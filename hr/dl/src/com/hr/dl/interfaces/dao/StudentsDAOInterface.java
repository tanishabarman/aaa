package com.thinking.machines.hr.dl.interfaces.dao;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
public interface StudentsDAOInterface
{
public void add(StudentsDTOInterface studentDTO) throws DAOException;
public void update(StudentsDTOInterface studentDTO) throws DAOException;
public void delete(String rollNumber) throws DAOException;
public Set<StudentsDTOInterface> getAll() throws DAOException;
public Set<StudentsDTOInterface> getByCourseCode(int courseCode) throws DAOException;
public StudentsDTOInterface getByRollNumber(String rollNumber) throws DAOException;
public StudentsDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException;
public StudentsDTOInterface getByPANNumber(String panNumber) throws DAOException;
public boolean rollNumberExists(String rollNumber) throws DAOException;
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException;
public boolean panNumberExists(String panNumber) throws DAOException;
public boolean isCourseAlloted(int courseCode) throws DAOException;
public int getCount() throws DAOException;
public int getCountByCourseCode(int courseCode) throws DAOException;
} 