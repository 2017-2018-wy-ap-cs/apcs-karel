import kareltherobot.*;
import java.util.*;

public class TeacherBot extends SchoolBot
{
  public boolean isStudent() {
    return false;
  }
  
  public Strategy getHelp() {
    System.out.println("Teacher: I am happy to help you.");
    putBeeper();
    move();
    return new GetBeeperStrat();
  }
  
  public TeacherBot(int r, int c, Direction d, int b)
  {
    super(r,c,d,b);
  }
  
  public static void main(String[] args)
  {
    World.setVisible(true);
    World.setDelay(50);
    StudentBot helpless = new StudentBot(4,1,East,0);
    TeacherBot goodTeacher = new TeacherBot(4,5,North,3);
    helpless.lookForTeacher();
    goodTeacher.bow();
  }
}