package com.thinking.machines.hr.pl.model;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import javax.swing.table.*;
import java.io.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.io.image.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.io.font.constants.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;
import com.itextpdf.layout.borders.*;
public class CoursesModel extends AbstractTableModel
{
private java.util.List<CoursesInterface> courses;
private String[] columnTitle;
private CoursesManagerInterface coursesManager;
public CoursesModel()
{
this.populateDataStructure();
}
private void populateDataStructure()
{
this.columnTitle=new String[2];
this.columnTitle[0]="S.no.";
this.columnTitle[1]="Course";
try
{
coursesManager=CoursesManager.getCoursesManager();
}catch(BLException blException)
{
// ???????????????

}
Set<CoursesInterface> blCourses=coursesManager.getCourses();
this.courses=new LinkedList<>();
for(CoursesInterface course:blCourses)
{
this.courses.add(course);
}
Collections.sort(this.courses,new Comparator<CoursesInterface>(){
public int compare(CoursesInterface left,CoursesInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
}
public int getRowCount()
{
return courses.size();
}
public int getColumnCount()
{
return this.columnTitle.length;
}
public String getColumnName(int columnIndex)
{
return columnTitle[columnIndex];
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
return this.courses.get(rowIndex).getTitle();
}
public Class getColumnClass(int columnIndex) 
{
//Class.forName("java.lang.Integer") special//
if(columnIndex==0) return Integer.class;
return String.class;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
//Application specific methods
public void add(CoursesInterface course) throws BLException
{
coursesManager.addCourses(course);
this.courses.add(course);
Collections.sort(this.courses,new Comparator<CoursesInterface>(){
public int compare(CoursesInterface left,CoursesInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}
public int indexOfCourse(CoursesInterface course) throws BLException
{
Iterator<CoursesInterface> iterator=this.courses.iterator();
CoursesInterface c;
int index=0;
while(iterator.hasNext())
{
c=iterator.next();
if(c.equals(course))
{
return index;
}
index++;
}
BLException blException=new BLException();
blException.setGenericException("Invalid course : "+course.getTitle());
throw blException;
}
public int indexOfTitle(String title,boolean partialLeftSearch) throws BLException
{
Iterator<CoursesInterface> iterator=this.courses.iterator();
CoursesInterface c;
int index=0;
while(iterator.hasNext())
{
c=iterator.next();
if(partialLeftSearch)
{
if(c.getTitle().toUpperCase().startsWith(title.toUpperCase()))
{
return index;
}
}
else
{
if(c.getTitle().equalsIgnoreCase(title))
{
return index;
}
}
index++;
}
BLException blException=new BLException();
blException.setGenericException("Invalid title : "+title);
throw blException;
}
public void update(CoursesInterface course) throws BLException
{
coursesManager.updateCourses(course);
this.courses.remove(indexOfCourse(course));
this.courses.add(course);
Collections.sort(this.courses,new Comparator<CoursesInterface>(){
public int compare(CoursesInterface left,CoursesInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}

public void remove(int code) throws BLException
{
coursesManager.removeCourses(code);
Iterator<CoursesInterface> iterator=this.courses.iterator();
int index=0;
while(iterator.hasNext())
{
if(iterator.next().getCode()==code) break;
index++;
}
if(index==this.courses.size())
{
BLException blException=new BLException();
blException.setGenericException("Invalid course code : "+code);
throw blException;
}
this.courses.remove(index);
fireTableDataChanged();	
}
public CoursesInterface getCourseAt(int index) throws BLException
{
if(index<0 || index>=this.courses.size())
{
BLException blException=new BLException();
blException.setGenericException("Invalid index : "+index);
throw blException;
}
return this.courses.get(index);
}
public void exportToPDF(File file) throws BLException
{
try
{
if(file.exists()) file.delete();
PdfWriter pdfWriter=new PdfWriter(file);
PdfDocument pdfDocument=new PdfDocument(pdfWriter);
Document doc=new Document(pdfDocument);
Image logo=new Image(ImageDataFactory.create(this.getClass().getResource("/icons/logo_icon.png")));
Paragraph logoPara=new Paragraph();
logoPara.add(logo);
Paragraph companyNamePara=new Paragraph();
companyNamePara.add("ABCD Corporation");
PdfFont companyNameFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
companyNamePara.setFont(companyNameFont);
companyNamePara.setFontSize(18);
Paragraph reportTitlePara=new Paragraph("List of courses :");
PdfFont reportTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
reportTitlePara.setFont(reportTitleFont);
reportTitlePara.setFontSize(15);
PdfFont pageNumberFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont columnTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont dataFont=PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
Paragraph columnTitle1=new Paragraph("S.No.");
columnTitle1.setFont(columnTitleFont);
columnTitle1.setFontSize(14);
Paragraph columnTitle2=new Paragraph("Courses");
columnTitle2.setFont(columnTitleFont);
columnTitle2.setFontSize(14);
Paragraph pageNumberPara;
Paragraph dataPara;
float topTableColumnWidths[]={1,5};
float dataTableColumnWidths[]={1,5};
int sno,x,pageSize;
pageSize=5;
boolean newPage=true;
Table pageNumberTable;
Table topTable;
Table dataTable=null;
Cell cell;
int numberOfPages=this.courses.size()/pageSize;
if((this.courses.size()%pageSize)!=0) numberOfPages++;
int pageNumber=0;
CoursesInterface course;
sno=0;
x=0;
while(x<this.courses.size())
{
if(newPage==true)
{
//create page header
pageNumber++;
topTable=new Table(UnitValue.createPercentArray(topTableColumnWidths));
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(logoPara);
topTable.addCell(cell);
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(companyNamePara);
cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
topTable.addCell(cell);
doc.add(topTable);
pageNumberPara=new Paragraph("Page : "+pageNumber+"/"+numberOfPages);
pageNumberPara.setFont(pageNumberFont);
pageNumberPara.setFontSize(13);
pageNumberTable=new Table(1);
pageNumberTable.setWidth(UnitValue.createPercentValue(100));
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(pageNumberPara);
cell.setTextAlignment(TextAlignment.RIGHT);
pageNumberTable.addCell(cell);
doc.add(pageNumberTable);
dataTable=new Table(UnitValue.createPercentArray(dataTableColumnWidths));
dataTable.setWidth(UnitValue.createPercentValue(100));
cell=new Cell(1,2);
cell.add(reportTitlePara);
cell.setTextAlignment(TextAlignment.CENTER);
dataTable.addHeaderCell(cell);
dataTable.addHeaderCell(columnTitle1);
dataTable.addHeaderCell(columnTitle2);
newPage=false;
}
course=this.courses.get(x);
//add row to table
sno++;
cell=new Cell();
dataPara=new Paragraph(String.valueOf(sno));
dataPara.setFont(dataFont);
dataPara.setFontSize(14);
cell.add(dataPara);
cell.setTextAlignment(TextAlignment.RIGHT);
dataTable.addCell(cell);
cell=new Cell();
dataPara=new Paragraph(course.getTitle());
dataPara.setFont(dataFont);
dataPara.setFontSize(14);
cell.add(dataPara);
dataTable.addCell(cell);
x++;
if(sno%pageSize==0 || x==this.courses.size())
{
//create footer
doc.add(dataTable);
doc.add(new Paragraph("Software by : Tanisha Barman"));
if(x<this.courses.size())
{
//add new page to document
doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
newPage=true;
}
}
}
doc.close();
}catch(Exception exception)
{
BLException blException;
blException=new BLException();
blException.setGenericException(exception.getMessage());
throw blException;
}
}
}