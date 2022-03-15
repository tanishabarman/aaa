package com.thinking.machines.hr.dl.dto;
import com.thinking.machines.hr.dl.interfaces.dto.*;
public class CoursesDTO implements CoursesDTOInterface
{
private int code;
private String title;
public CoursesDTO()
{
this.code=0;
this.title="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setTitle(java.lang.String title)
{
this.title=title;
}
public java.lang.String getTitle()
{
return this.title;
}
public boolean equals(Object other)
{
if(!(other instanceof CoursesDTOInterface)) return false;
CoursesDTOInterface courseDTO;
courseDTO=(CoursesDTO) other;
return this.code==courseDTO.getCode();
}
public int compareTo(CoursesDTOInterface courseDTO)
{
return this.code-courseDTO.getCode();
}
public int hashCode()
{
return code;
}
}