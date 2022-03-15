import java.awt.*;
import javax.swing.*;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
class CoursesModelTestcases extends JFrame
{
private JTable tb;
private JScrollPane jsp;
private CoursesModel coursesModel;
private Container container;
CoursesModelTestcases()
{
coursesModel=new CoursesModel();
tb=new JTable(coursesModel);
jsp=new JScrollPane(tb,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.setLayout(new BorderLayout());
container.add(tb);
container add(jsp);
setLocation(100,200);
setSize(300,400);
setVisible(true);
}
public static void main(String gg[])
{
CoursesModelTestcases cmtc=new CoursesModelTestcases();
}
}