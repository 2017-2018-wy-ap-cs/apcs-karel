import kareltherobot.*;
import java.awt.Color;

public class Ch3Ex2PinSetter 
  extends UrRobot 
{
  public Ch3Ex2PinSetter(int row, int col, Direction dir, int beep)
  {
    super(row,col,dir,beep);
  }
  
  public void turnRight()
  {
    turnLeft();
    turnLeft();
    turnLeft();
  }
  
  public void putGo2()
  {
    putBeeper();putBeeper();putBeeper();
    move();
    move();
  }
  
  public void nextRowI()
  {
    turnRight();
    goToNextRow();
    turnRight();
    rowStart();
  }
  
  public void goToNextRow()
  {
    move();move();
  }
  
  public void rowStart()
  {
    move();
    move();
    move();
  }
    
  public void nextRowII()
  {
    turnLeft();
    goToNextRow();
    turnLeft();
    rowStart();
  }
  
  public void setPins()
  {
    putGo2();
    putGo2();
    putGo2();
    putGo2();
    nextRowI();
    
    putGo2();
    putGo2();
    putGo2();
    nextRowII();
    
    putGo2();
    putGo2();
    nextRowI();
    
    putGo2();
    nextRowII();
  }
  
  
  
  
  
  
  
  public static void main (String[] args)
  {
    //World.readWorld("worlds/fig1-2a.kwld");
    World.setVisible(true);
    World.setDelay(10);
    World.showSpeedControl(true);
    
    Ch3Ex2PinSetter ray = new Ch3Ex2PinSetter(10,2,East,-1);
    ray.setPins();
    ray.turnOff();
    
  }
  
}
