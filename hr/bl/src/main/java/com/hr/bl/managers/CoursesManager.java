package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
public class CoursesManager implements CoursesManagerInterface
{
private Map<Integer,CoursesInterface> codeWiseCoursesMap;
private Map<String,CoursesInterface> titleWiseCoursesMap;
private Set<CoursesInterface> coursesSet;
private static CoursesManager coursesManager=null;
private CoursesManager() throws BLException
{
populateDataStructures();
}
private void populateDataStructures() throws BLException
{
this.codeWiseCoursesMap=new HashMap<>();
this.titleWiseCoursesMap=new HashMap<>();
this.coursesSet=new TreeSet<>();
try
{
Set<CoursesDTOInterface> dlCourses;
dlCourses=new CoursesDAO().getAll();
CoursesInterface course;
for(CoursesDTOInterface dlCourse:dlCourses)
{
course=new Courses();
course.setCode(dlCourse.getCode());
course.setTitle(dlCourse.getTitle());
this.codeWiseCoursesMap.put(new Integer(course.getCode()),course);
this.titleWiseCoursesMap.put(course.getTitle().toUpperCase(),course);
this.coursesSet.add(course);
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public static CoursesManagerInterface getCoursesManager() throws BLException
{
if(coursesManager==null) coursesManager=new CoursesManager();
return coursesManager;
}
public void addCourses(CoursesInterface course) throws BLException
{
BLException blException;
blException=new BLException();
if(course==null)
{
blException.setGenericException("Course required");
throw blException;
}

String title= course.getTitle();
int code = course.getCode();
if(code!=0)
{
blException.addException("code","code should be zero");
}
if(title==null)
{
blException.addException("title","Title required");
title="";
}
else
{
title=title.trim();
if(title.length()==0)
{
blException.addException("title","Title required");
}
}
if(title.length()>0)
{
if(this.titleWiseCoursesMap.containsKey(title.toUpperCase()))
{
blException.addException("title","Courses : "+title+" exists.");
}
}
if(blException.hasException())
{
throw blException;
}
try
{
CoursesDTOInterface courseDTO;
courseDTO=new CoursesDTO();
courseDTO.setTitle(title);
CoursesDAOInterface courseDAO;
courseDAO=new CoursesDAO();
courseDAO.add(courseDTO);
code=courseDTO.getCode();
course.setCode(code);
Courses dsCourses;
dsCourses=new Courses();
dsCourses.setCode(code);
dsCourses.setTitle(title);
codeWiseCoursesMap.put(new Integer(code),dsCourses);
titleWiseCoursesMap.put(title.toUpperCase(),dsCourses);
coursesSet.add(dsCourses);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void updateCourses(CoursesInterface course) throws BLException
{
BLException blException;
blException=new BLException();
if(course==null)
{
blException.setGenericException("Course required");
throw blException;
}
String title= course.getTitle();
int code = course.getCode();
if(code<=0)
{
blException.addException("code","Invalid code : "+code);
}
if(code>0)
{
if(this.codeWiseCoursesMap.containsKey(new Integer(code))==false)
{
blException.addException("code","Invalid code : "+code);
}
}
if(title==null)
{
blException.addException("title","Title required");
title="";
}
else
{
title=title.trim();
if(title.length()==0)
{
blException.addException("title","Title required");
}
}
if(title.length()>0)
{
CoursesInterface d;
d=titleWiseCoursesMap.get(title.toUpperCase());
if(d!=null && d.getCode()!=code)
{
blException.addException("title","Courses : "+title+" exists.");
}
}
if(blException.hasException())
{
throw blException;
}
try
{
CoursesInterface dsCourses=codeWiseCoursesMap.get(code);
CoursesDTOInterface dlCourses=new CoursesDTO();
dlCourses.setCode(code);
dlCourses.setTitle(title);
new CoursesDAO().update(dlCourses);
//remove old one from DS
codeWiseCoursesMap.remove(code);
titleWiseCoursesMap.remove(dsCourses.getTitle().toUpperCase());
coursesSet.remove(dsCourses);
//update DS object
dsCourses.setTitle(title);
//update DS
codeWiseCoursesMap.put(code,dsCourses);
titleWiseCoursesMap.put(title.toUpperCase(),dsCourses);
coursesSet.add(dsCourses);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void removeCourses(int code) throws BLException
{
BLException blException;
blException=new BLException();
if(code<=0)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
if(code>0)
{
if(this.codeWiseCoursesMap.containsKey(new Integer(code))==false)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
}
try
{
CoursesInterface dsCourses=codeWiseCoursesMap.get(code);
new CoursesDAO().delete(code);
//remove old one from DS
codeWiseCoursesMap.remove(code);
titleWiseCoursesMap.remove(dsCourses.getTitle().toUpperCase());
coursesSet.remove(dsCourses);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public CoursesInterface getCourseByCode(int code) throws BLException
{
CoursesInterface course;
course=codeWiseCoursesMap.get(code);
if(course==null)
{
BLException blException=new BLException();
blException.addException("code","Invalid code : "+code);
throw blException;
}
CoursesInterface d=new Courses();
d.setCode(course.getCode());
d.setTitle(course.getTitle());
return d;
}
public CoursesInterface getCourseByTitle(String title) throws BLException
{
CoursesInterface course;
course=titleWiseCoursesMap.get(title.toUpperCase());
if(course==null)
{
BLException blException=new BLException();
blException.addException("title","Invalid code : "+title);
throw blException;
}
CoursesInterface d=new Courses();
d.setCode(course.getCode());
d.setTitle(course.getTitle());
return d;
}
//here clone is not returned
CoursesInterface getDSCourseByCode(int code) throws BLException
{
CoursesInterface course;
course=codeWiseCoursesMap.get(code);
return course;
}
public int getCoursesCount() 
{
return coursesSet.size();
}
public boolean courseCodeExists(int code) 
{
return codeWiseCoursesMap.containsKey(code);
}
public boolean courseTitleExists(String title)
{
return titleWiseCoursesMap.containsKey(title.toUpperCase());
}
public Set<CoursesInterface> getCourses() 
{
Set<CoursesInterface> courses;
courses=new TreeSet<>();
coursesSet.forEach((course)->{
CoursesInterface d=new Courses();
d.setCode(course.getCode());
d.setTitle(course.getTitle());
courses.add(d);
});
return courses;
}
}