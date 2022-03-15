package com.thinking.machines.hr.bl.interfaces.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
public interface CoursesManagerInterface
{
public void addCourses(CoursesInterface course) throws BLException;
public void updateCourses(CoursesInterface course) throws BLException;
public void removeCourses(int code) throws BLException;
public CoursesInterface getCourseByCode(int code) throws BLException;
public CoursesInterface getCourseByTitle(String title) throws BLException;
public int getCoursesCount();
public boolean courseCodeExists(int code);
public boolean courseTitleExists(String title);
public Set<CoursesInterface> getCourses();
}