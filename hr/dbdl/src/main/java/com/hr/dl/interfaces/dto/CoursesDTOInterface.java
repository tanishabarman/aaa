package com.thinking.machines.hr.dl.interfaces.dto;
public interface CoursesDTOInterface extends Comparable<CoursesDTOInterface>,java.io.Serializable
{
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}