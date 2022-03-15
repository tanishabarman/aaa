package com.thinking.machines.hr.bl.interfaces.pojo;
public interface CoursesInterface extends Comparable<CoursesInterface>,java.io.Serializable
{
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}