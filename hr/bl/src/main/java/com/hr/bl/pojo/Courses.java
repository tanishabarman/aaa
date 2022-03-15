package com.thinking.machines.hr.bl.pojo;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
public class Courses implements CoursesInterface
{
private int code;
private String title;
public Courses()
{
code=0;
title="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}
public int hashCode()
{
return code;
}
public boolean equals(Object other)
{
if(!(other instanceof CoursesInterface)) return false;
CoursesInterface course=(CoursesInterface)other;
return this.code==course.getCode();
}
public int compareTo(CoursesInterface course)
{
return this.code-course.getCode();
}
}