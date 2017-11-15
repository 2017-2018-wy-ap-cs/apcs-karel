import kareltherobot.*;

public abstract class SchoolBot extends Robot
{
  public abstract boolean isStudent();
  
  public boolean isTeacher() { 
    return ! isStudent();
  }
  
  public abstract Strategy getHelp();
  
  public boolean isHelpful()
  {
    // return getHelp() != null;
    return isTeacher();
  }
  
  public void bow()
  {
    turnLeft();
    turnLeft();
    turnLeft();
    turnLeft();
  }
  
  public SchoolBot(int r, int c, Direction d, int b)
  {
    super(r,c,d,b);
  }
}
