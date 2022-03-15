package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
public class CoursesUI extends JFrame implements DocumentListener,ListSelectionListener
{
private JLabel titleLabel;
private JLabel searchLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JLabel searchErrorLabel;
private JTable coursesTable;
private JScrollPane scrollPane;
private CoursesModel coursesModel;
private Container container;
private CoursesPanel coursesPanel;
private enum MODE{VIEW,ADD,EDIT,DELETE,EXPORT_TO_PDF};
private MODE mode;
private ImageIcon logoIcon;
private ImageIcon addIcon;
private ImageIcon editIcon;
private ImageIcon deleteIcon;
private ImageIcon cancelIcon;
private ImageIcon pdfIcon;
private ImageIcon saveIcon;
public CoursesUI()
{
initComponents();
setAppearance();
addListeners();
setViewMode();
coursesPanel.setViewMode();
}
private void initComponents()
{
logoIcon=new ImageIcon(this.getClass().getResource("/icons/logo_icon.png"));
addIcon=new ImageIcon(this.getClass().getResource("/icons/add_icon.png"));
editIcon=new ImageIcon(this.getClass().getResource("/icons/edit_icon.png"));
deleteIcon=new ImageIcon(this.getClass().getResource("/icons/delete_icon.png"));
cancelIcon=new ImageIcon(this.getClass().getResource("/icons/cancel_icon.png"));
pdfIcon=new ImageIcon(this.getClass().getResource("/icons/pdf_icon.png"));
saveIcon=new ImageIcon(this.getClass().getResource("/icons/save_icon.png"));
setIconImage(logoIcon.getImage());
coursesModel=new CoursesModel();
titleLabel=new JLabel("Courses");
searchLabel=new JLabel("Search");
searchTextField=new JTextField();
clearSearchTextFieldButton=new JButton("x");
searchErrorLabel=new JLabel("");
coursesTable=new JTable(coursesModel);
coursesPanel=new CoursesPanel();
scrollPane=new JScrollPane(coursesTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
}
private void setAppearance()
{
Font titleFont=new Font("Verdana",Font.BOLD,18);
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
Font columnHeaderFont=new Font("Verdana",Font.BOLD,16);
Font searchErrorFont=new Font("Veradna",Font.BOLD,12);
titleLabel.setFont(titleFont);
searchLabel.setFont(captionFont);
searchTextField.setFont(dataFont);
searchErrorLabel.setFont(searchErrorFont);
searchErrorLabel.setForeground(Color.red);
coursesTable.setFont(dataFont);

coursesTable.setRowHeight(40);
coursesTable.getColumnModel().getColumn(0).setPreferredWidth(20);   
coursesTable.getColumnModel().getColumn(1).setPreferredWidth(400);   
JTableHeader header=coursesTable.getTableHeader();
header.setFont(columnHeaderFont);
header.setReorderingAllowed(false);
header.setResizingAllowed(false);
coursesTable.setRowSelectionAllowed(true);
coursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

container.setLayout(null);
int lm,tm;
lm=0;
tm=0;
titleLabel.setBounds(lm+10,tm+10,200,40);
searchErrorLabel.setBounds(lm+10+100+400+10-75,tm+10+20+10,100,20);
searchLabel.setBounds(lm+10,tm+10+40+10,100,30);
searchTextField.setBounds(lm+10+100+5,tm+10+40+10,400,30);
clearSearchTextFieldButton.setBounds(lm+10+100+10+400,tm+10+40+10,30,30);
scrollPane.setBounds(lm+10,tm+10+40+10+30+10,565,300);
coursesPanel.setBounds(lm+10,tm+10+40+10+30+10+300+10,565,200);
container.add(titleLabel);
container.add(searchErrorLabel);
container.add(searchLabel);
container.add(searchTextField);
container.add(clearSearchTextFieldButton);
container.add(scrollPane);
container.add(coursesPanel);
int w,h;
w=600;
h=660;
setSize(w,h);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2),(d.height/2)-(h/2));
}
private void addListeners()
{
searchTextField.getDocument().addDocumentListener(this);
clearSearchTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
coursesTable.getSelectionModel().addListSelectionListener(this);
}
private void searchCourses()
{
searchErrorLabel.setText("");
String title=searchTextField.getText().trim();
if(title.length()==0) return;
int rowIndex;
try
{
rowIndex=coursesModel.indexOfTitle(title,true);
}catch(BLException blException)
{
searchErrorLabel.setText("Not found");
return;
}
coursesTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=coursesTable.getCellRect(rowIndex,0,true);
coursesTable.scrollRectToVisible(rectangle);
}
public void changedUpdate(DocumentEvent de)
{
searchCourses();
}
public void removeUpdate(DocumentEvent de)
{
searchCourses();
}
public void insertUpdate(DocumentEvent de)
{
searchCourses();
}
public void valueChanged(ListSelectionEvent ev)
{
int selectedRowIndex=coursesTable.getSelectedRow();
try
{
CoursesInterface course=coursesModel.getCourseAt(selectedRowIndex);
coursesPanel.setCourse(course);
}catch(BLException blException)
{
coursesPanel.clearCourse();
}
}
private void setViewMode()
{
this.mode=MODE.VIEW;
if(coursesModel.getRowCount()==0)
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
coursesTable.setEnabled(false);
}else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
coursesTable.setEnabled(true);
}
}
private void setAddMode()
{
this.mode=MODE.ADD;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
coursesTable.setEnabled(false);
}
private void setEditMode()
{
this.mode=MODE.EDIT;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
coursesTable.setEnabled(false);
}
private void setDeleteMode()
{
this.mode=MODE.DELETE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
coursesTable.setEnabled(false);
}
private void setExportToPDFMode()
{
this.mode=MODE.EXPORT_TO_PDF;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
coursesTable.setEnabled(false);
}
//inner class starts
class CoursesPanel extends JPanel
{
private JLabel titleCaptionLabel;
private JLabel titleLabel;
private JTextField titleTextField;
private JButton clearTitleTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private JPanel buttonsPanel;
private CoursesInterface course;

CoursesPanel()
{
setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
initComponents();
setAppearance();
addListeners();
}
public void setCourse(CoursesInterface course)
{
this.course=course;
titleLabel.setText(course.getTitle());
}
public void clearCourse()
{
this.course=null;
titleLabel.setText("");
}
private void initComponents()
{
course=null;
titleCaptionLabel=new JLabel("Course");
titleLabel=new JLabel("");
titleTextField=new JTextField();
clearTitleTextFieldButton=new JButton("x");
buttonsPanel=new JPanel();
addButton=new JButton(addIcon);
editButton=new JButton(editIcon);
cancelButton=new JButton(cancelIcon);
deleteButton=new JButton(deleteIcon);
exportToPDFButton=new JButton(pdfIcon);
}
private void setAppearance()
{
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
titleCaptionLabel.setFont(captionFont);
titleLabel.setFont(dataFont);
titleTextField.setFont(dataFont);
setLayout(null);
int lm=0;
int tm=0;
titleCaptionLabel.setBounds(lm+10,tm+20,110,30);
titleLabel.setBounds(lm+110+5,tm+20,400,30);
titleTextField.setBounds(lm+10+110+5,tm+20,350,30);
clearTitleTextFieldButton.setBounds(lm+10+110+5+350+5,tm+20,30,30);

buttonsPanel.setBounds(50,tm+20+30+30,465,75);
buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
addButton.setBounds(70,12,50,50);
editButton.setBounds(70+50+20,12,50,50);
cancelButton.setBounds(70+50+20+50+20,12,50,50);
deleteButton.setBounds(70+50+20+50+20+50+20,12,50,50);
exportToPDFButton.setBounds(70+50+20+50+20+50+20+50+20,12,50,50);
buttonsPanel.setLayout(null);
buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(deleteButton);
buttonsPanel.add(exportToPDFButton);
add(titleCaptionLabel);
add(titleLabel);
add(titleTextField);
add(clearTitleTextFieldButton);
add(buttonsPanel);
}
private boolean addCourse()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Course required");
titleTextField.requestFocus();
return false;
}
CoursesInterface d=new Courses();
d.setTitle(title);
try
{
coursesModel.add(d);
int rowIndex=0;
try
{
rowIndex=coursesModel.indexOfCourse(d);
}catch(BLException blException)
{
//do nothing
}
coursesTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=coursesTable.getCellRect(rowIndex,0,true);
coursesTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}
}
private boolean updateCourse()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Course required");
titleTextField.requestFocus();
return false;
}
CoursesInterface d=new Courses();
d.setCode(this.course.getCode());
d.setTitle(title);
try
{
coursesModel.update(d);
int rowIndex=0;
try
{
rowIndex=coursesModel.indexOfCourse(d);
}catch(BLException blException)
{
//do nothing
}
coursesTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=coursesTable.getCellRect(rowIndex,0,true);
coursesTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}
}
private void removeCourse()
{
try
{
String title=this.course.getTitle();
int selectedOption=JOptionPane.showConfirmDialog(this,"Delete "+title+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.NO_OPTION) return;

coursesModel.remove(this.course.getCode());
JOptionPane.showMessageDialog(this,title+" deleted");
//this.clearCourse();
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
}
}

private void addListeners()
{
this.exportToPDFButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
JFileChooser jfc=new JFileChooser();
jfc.setCurrentDirectory(new File("."));
int selectedOption=jfc.showSaveDialog(CoursesUI.this);
if(selectedOption==JFileChooser.APPROVE_OPTION)
{
try
{
File selectedFile=jfc.getSelectedFile();
String pdfFile=selectedFile.getAbsolutePath();
if(pdfFile.endsWith(".")) pdfFile+="pdf";
else if(pdfFile.endsWith(".pdf")==false) pdfFile+=".pdf";
File file=new File(pdfFile);
File parent=new File(file.getParent());
if(parent.exists()==false || parent.isDirectory()==false)
{
JOptionPane.showMessageDialog(CoursesUI.this,"Incorrect path"+file.getAbsolutePath());
return;
}
coursesModel.exportToPDF(file);
JOptionPane.showMessageDialog(CoursesUI.this,"Data exported to : "+file.getAbsolutePath());
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(CoursesUI.this,blException.getGenericException());
}
}
catch(Exception e)
{
System.out.println(e);
}
}
}
});
this.addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setAddMode();
}
else
{
//logic to save and get back to view mode
if(addCourse())
{
setViewMode();
}
}
}
});
this.editButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setEditMode();
}
else
{
//logic to save and get back to view mode
if(updateCourse())
{
setViewMode();
}
}
}
});
this.deleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setDeleteMode();
}
});
this.cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setViewMode();
}
});
}
void setViewMode()
{
CoursesUI.this.setViewMode();
this.addButton.setIcon(addIcon);
this.editButton.setIcon(editIcon);
this.titleTextField.setVisible(false);
this.titleLabel.setVisible(true);
this.addButton.setEnabled(true);
this.cancelButton.setEnabled(false);
this.clearTitleTextFieldButton.setVisible(false);
if(coursesModel.getRowCount()>0)
{
this.editButton.setEnabled(true);
this.deleteButton.setEnabled(true);
this.exportToPDFButton.setEnabled(true);
}
else
{
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.exportToPDFButton.setEnabled(false);
}
}
void setAddMode()
{
CoursesUI.this.setAddMode();
this.titleTextField.setText("");
this.titleLabel.setVisible(false);
this.clearTitleTextFieldButton.setVisible(true);
this.titleTextField.setVisible(true);
addButton.setIcon(saveIcon);
editButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
titleTextField.requestFocus();
}
void setEditMode()
{
if(coursesTable.getSelectedRow()<0 || coursesTable.getSelectedRow()>=coursesModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select course to edit");
return;
}
CoursesUI.this.setEditMode();
this.titleTextField.setText(this.course.getTitle());
this.titleLabel.setVisible(false);
this.clearTitleTextFieldButton.setVisible(true);
this.titleTextField.setVisible(true);
addButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
editButton.setIcon(saveIcon);
}
void setDeleteMode()
{
if(coursesTable.getSelectedRow()<0 || coursesTable.getSelectedRow()>=coursesModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select course to delete");
return;
}
CoursesUI.this.setDeleteMode();
addButton.setEnabled(false);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
removeCourse();
CoursesUI.this.setViewMode();
this.setViewMode();
}
void setExportToPDFMode()
{
CoursesUI.this.setExportToPDFMode();
addButton.setEnabled(false);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
}
//inner class ends
}