/*     */ package kareltherobot;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PipedInputStream;
/*     */ import java.io.PipedOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Observable;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class UrRobot extends Observable
/*     */   implements Directions, Runnable, UrInterface
/*     */ {
/* 263 */   private Vector senders = new Vector();
/* 264 */   private int nextSender = 0;
/*     */ 
/* 353 */   private boolean pausing = false;
/* 354 */   private boolean userLevelPausing = false;
/*     */ 
/* 507 */   BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
/*     */   private static final int on = 2;
/*     */   private static final int off = 1;
/*     */   private static final int crashed = 0;
/* 512 */   private int[] loc = new int[4];
/*     */   private int beepers;
/* 514 */   private Color badgeColor = null;
/*     */   private Directions.Direction direction;
/* 516 */   private int moves = 0;
/* 517 */   private int state = 2;
/* 518 */   private boolean isVisible = true;
/*     */   private int idNumber;
/*     */   private static final int threshhold = 10;
/* 523 */   private static int numberOfRobots = 0;
/*     */   private StateObject initialState;
/*     */ 
/*     */   public void turnLeft()
/*     */   {
/*  33 */     if (this.state == 2) {
/*  34 */       pause("turnLeft");
/*  35 */       this.direction = this.direction.rotate(-1);
/*  36 */       StateObject s = new StateObject(1);
/*  37 */       setChanged();
/*  38 */       notifyObservers(s);
/*  39 */       sleep();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void move() {
/*  44 */     if (this.state == 2) {
/*  45 */       pause("move");
/*  46 */       boolean crashed = false;
/*  47 */       normalize();
/*  48 */       switch (this.direction.points()) {
/*     */       case 3:
/*  50 */         if (World.checkEWWall(this.loc[3], this.loc[0]))
/*  51 */           crashed = crash("Tried to walk through an East West wall");
/*  52 */         break;
/*     */       case 0:
/*  54 */         if (World.checkNSWall(this.loc[3], this.loc[0]))
/*  55 */           crashed = crash("Tried to walk through a North South wall");
/*  56 */         break;
/*     */       case 1:
/*  58 */         if (World.checkEWWall(this.loc[3] - 1, this.loc[0]))
/*  59 */           crashed = crash("Tried to walk through an East West wall");
/*  60 */         break;
/*     */       case 2:
/*  62 */         if (World.checkNSWall(this.loc[3], this.loc[0] - 1))
/*  63 */           crashed = crash("Tried to walk through a North South wall");
/*     */         break;
/*     */       }
/*  66 */       if (!crashed) {
/*  67 */         this.loc[this.direction.points()] += 1;
/*  68 */         this.moves += 1;
/*  69 */         if (this.moves > 10) {
/*  70 */           normalize();
/*     */         }
/*     */       }
/*  73 */       validate();
/*  74 */       StateObject s = new StateObject(0);
/*  75 */       setChanged();
/*  76 */       notifyObservers(s);
/*  77 */       sleep();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void pickBeeper() {
/*  82 */     if (this.state == 2) {
/*  83 */       pause("pickBeeper");
/*  84 */       normalize();
/*  85 */       boolean crashed = false;
/*  86 */       if (!World.checkBeeper(this.loc[3], this.loc[0]))
/*  87 */         crashed = crash("No beepers to pick");
/*  88 */       if (!crashed) {
/*  89 */         if (this.beepers != -1) this.beepers += 1;
/*  90 */         World.placeBeepers(this.loc[3], this.loc[0], -1);
/*     */       }
/*  92 */       StateObject s = new StateObject(2);
/*  93 */       setChanged();
/*  94 */       notifyObservers(s);
/*  95 */       sleep();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void putBeeper() {
/* 100 */     if (this.state == 2) {
/* 101 */       pause("putBeeper");
/* 102 */       normalize();
/* 103 */       if (this.beepers == 0) {
/* 104 */         crash("No beepers to put.");
/* 105 */         StateObject s = new StateObject(3);
/* 106 */         setChanged();
/* 107 */         notifyObservers(s);
/* 108 */         return;
/*     */       }
/* 110 */       if (this.beepers != -1) this.beepers -= 1;
/* 111 */       if (!validate()) {
/* 112 */         if (this.beepers != -1) this.beepers += 1; 
/*     */       }
/*     */       else
/*     */       {
/* 115 */         World.placeBeepers(this.loc[3], this.loc[0], 1);
/* 116 */         StateObject s = new StateObject(3);
/* 117 */         setChanged();
/* 118 */         notifyObservers(s);
/*     */       }
/* 120 */       sleep();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void turnOff() {
/* 125 */     pause("turnOff");
/* 126 */     if (this.state == 2) {
/* 127 */       System.out.println("Robot " + this.idNumber + ": Turning off");
/* 128 */       this.state = 1;
/* 129 */       StateObject s = new StateObject(4);
/* 130 */       setChanged();
/* 131 */       notifyObservers(s);
/* 132 */       sleep();
/*     */     }
/*     */   }
/*     */ 
/*     */   public World world()
/*     */   {
/* 145 */     return World.asObject();
/*     */   }
/*     */ 
/*     */   public Enumeration neighbors()
/*     */   {
/* 150 */     Vector v = new Vector();
/* 151 */     Enumeration all = World.robots();
/* 152 */     while (all.hasMoreElements()) {
/* 153 */       UrRobot r = (UrRobot)all.nextElement();
/* 154 */       if ((r != this) && (r.areYouHere(street(), avenue()))) {
/* 155 */         v.addElement(r);
/*     */       }
/*     */     }
/* 158 */     return v.elements();
/*     */   }
/*     */ 
/*     */   public String getNextCommunication()
/*     */   {
/* 183 */     if (this.senders.size() == 0) return null;
/* 184 */     int count = 0;
/*     */     while (true) {
/* 186 */       if (count >= this.senders.size()) return null;
/* 187 */       if (this.nextSender >= this.senders.size()) this.nextSender = 0;
/* 188 */       BufferedReader in = (BufferedReader)this.senders.elementAt(this.nextSender);
/*     */       try {
/* 190 */         this.nextSender += 1;
/* 191 */         if (in.ready()) return in.readLine(); 
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/* 195 */       count++;
/* 196 */       sleep();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String waitForCommunication()
/*     */   {
/* 202 */     if (this.senders.size() == 0) return null; while (true)
/*     */     {
/* 204 */       if (this.nextSender >= this.senders.size()) this.nextSender = 0;
/* 205 */       BufferedReader in = (BufferedReader)this.senders.elementAt(this.nextSender);
/*     */       try {
/* 207 */         this.nextSender += 1;
/* 208 */         if (in.ready()) return in.readLine(); 
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/* 212 */       sleep();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String waitForNextCommunication() {
/* 217 */     if (this.senders.size() == 0) return null; while (true)
/*     */     {
/* 219 */       if (this.nextSender >= this.senders.size()) this.nextSender = 0;
/* 220 */       BufferedReader in = (BufferedReader)this.senders.elementAt(this.nextSender);
/*     */       try {
/* 222 */         this.nextSender += 1;
/* 223 */         return in.readLine();
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public BufferedWriter connectTo(UrRobot other, ConnectStrategy strat)
/*     */     throws IOException
/*     */   {
/* 234 */     PipedOutputStream out = new PipedOutputStream();
/* 235 */     BufferedWriter result = new BufferedWriter(new OutputStreamWriter(out));
/* 236 */     other.acceptConnectionFrom(this, out, strat);
/* 237 */     return result;
/*     */   }
/*     */ 
/*     */   public synchronized void acceptConnectionFrom(UrRobot sender, PipedOutputStream s, ConnectStrategy strat) throws IOException
/*     */   {
/* 242 */     BufferedReader manager = new BufferedReader(new InputStreamReader(new PipedInputStream(s)));
/* 243 */     if (strat != null)
/* 244 */       strat.action(sender, this, manager);
/*     */     else
/* 246 */       this.senders.addElement(manager);
/*     */   }
/*     */ 
/*     */   public synchronized void acceptConnection(PipedOutputStream s, ConnectStrategy strat)
/*     */     throws IOException
/*     */   {
/* 256 */     BufferedReader manager = new BufferedReader(new InputStreamReader(new PipedInputStream(s)));
/* 257 */     if (strat != null)
/* 258 */       strat.action(null, this, manager);
/*     */     else
/* 260 */       this.senders.addElement(manager);
/*     */   }
/*     */ 
/*     */   public void send(BufferedWriter other, String s)
/*     */     throws IOException
/*     */   {
/* 284 */     other.write(s + '\n');
/* 285 */     other.flush();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */   }
/*     */ 
/*     */   final void pause(String message)
/*     */   {
/* 307 */     if (this.pausing) {
/* 308 */       System.out.println("RobotID " + this.idNumber + " is about to " + message + ".");
/*     */       try
/*     */       {
/* 311 */         this.sysin.readLine();
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void userPause(String message)
/*     */   {
/* 324 */     if (this.userLevelPausing) {
/* 325 */       System.out.println("RobotID " + this.idNumber + " is about to " + message + ".");
/*     */       try
/*     */       {
/* 328 */         this.sysin.readLine();
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void setPause(boolean pausing)
/*     */   {
/* 343 */     this.pausing = pausing;
/*     */   }
/*     */ 
/*     */   public final void setUserPause(boolean pausing)
/*     */   {
/* 351 */     this.userLevelPausing = pausing;
/*     */   }
/*     */ 
/*     */   public UrRobot(int street, int avenue, Directions.Direction direction, int beepers, Color badgeColor)
/*     */   {
/* 357 */     this.loc[3] = street;
/* 358 */     this.loc[1] = 0;
/* 359 */     this.loc[0] = avenue;
/* 360 */     this.loc[2] = 0;
/* 361 */     this.direction = direction;
/* 362 */     this.beepers = beepers;
/* 363 */     validate();
/* 364 */     this.idNumber = incrementRobots();
/* 365 */     this.state = 2;
/* 366 */     this.initialState = new StateObject(-1);
/* 367 */     this.badgeColor = badgeColor;
/* 368 */     World.addRobot(this);
/* 369 */     sleep();
/* 370 */     sleep();
/*     */   }
/*     */ 
/*     */   public UrRobot(int street, int avenue, Directions.Direction direction, int beepers)
/*     */   {
/* 379 */     this(street, avenue, direction, beepers, null);
/*     */   }
/*     */ 
/*     */   final Color badgeColor()
/*     */   {
/* 385 */     return this.badgeColor;
/*     */   }
/*     */   final void restoreInitialState() {
/* 388 */     this.loc[3] = this.initialState.street;
/* 389 */     this.loc[1] = 0;
/* 390 */     this.loc[0] = this.initialState.avenue;
/* 391 */     this.loc[2] = 0;
/* 392 */     this.direction = this.initialState.direction;
/* 393 */     this.beepers = this.initialState.beepers;
/* 394 */     this.state = 2;
/* 395 */     showState("Restoring ");
/* 396 */     setChanged();
/* 397 */     notifyObservers(this.initialState);
/* 398 */     sleep();
/*     */   }
/*     */ 
/*     */   public final String toString() {
/* 402 */     normalize();
/* 403 */     return "RobotID " + this.idNumber + " at (street: " + this.loc[3] + ") (avenue: " + this.loc[0] + 
/* 404 */       ") (beepers: " + (this.beepers >= 0 ? this.beepers : "infinite") + ") ( direction: " + this.direction.toString() + (
/* 405 */       this.state == 2 ? ") on" : ") off");
/*     */   }
/*     */ 
/*     */   private String direction(int d)
/*     */   {
/* 410 */     switch (d) { case 0:
/* 411 */       return "East";
/*     */     case 1:
/* 412 */       return "South";
/*     */     case 2:
/* 413 */       return "West";
/*     */     case 3:
/* 414 */       return "North"; }
/* 415 */     return "ERROR";
/*     */   }
/*     */ 
/*     */   protected void sleep()
/*     */   {
/*     */     try {
/* 421 */       Thread.sleep(10 * World.delay());
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {
/*     */     }
/*     */   }
/*     */ 
/*     */   public final void showState(String s) {
/* 428 */     normalize();
/* 429 */     System.out.println(s + this);
/*     */   }
/*     */ 
/*     */   final boolean areYouHere(int street, int avenue) {
/* 433 */     normalize();
/* 434 */     return (this.loc[3] == street) && (this.loc[0] == avenue);
/*     */   }
/*     */ 
/*     */   private boolean validate() {
/* 438 */     normalize();
/* 439 */     if (this.beepers < -1) return !crash("Robot has negative beepers");
/* 440 */     if (this.loc[3] < 1) return !crash("Robot tried to move through South boundary wall");
/* 441 */     if (this.loc[0] < 1) return !crash("Robot tried to move through West boundary wall");
/* 442 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean crash(String s) {
/* 446 */     this.state = 0;
/* 447 */     showState("Error shutoff: ");
/*     */ 
/* 449 */     System.out.println(s);
/*     */ 
/* 451 */     return true;
/*     */   }
/*     */ 
/*     */   final boolean crashed() {
/* 455 */     return this.state == 0;
/*     */   }
/*     */ 
/*     */   private void pauseExit() {
/*     */     try {
/* 460 */       this.sysin.readLine(); } catch (IOException localIOException) {
/*     */     }
/* 462 */     System.exit(0);
/*     */   }
/*     */ 
/*     */   private void normalize() {
/* 466 */     this.moves = 0;
/* 467 */     this.loc[3] -= this.loc[1];
/* 468 */     this.loc[1] = 0;
/* 469 */     this.loc[0] -= this.loc[2];
/* 470 */     this.loc[2] = 0;
/*     */   }
/*     */ 
/*     */   final int beepers()
/*     */   {
/* 493 */     return this.beepers; 
/*     */   } 

/* 494 */   final Directions.Direction direction() { return this.direction; } 
/* 495 */   public final int street() { return this.loc[3] - this.loc[1]; } 
            public final void setStreet(int theStreet)
            {
                this.loc[3]=theStreet;
                this.loc[1]=0;
            }
            public final int getStreet()
            {
                return street();
            }
/* 496 */   public final int avenue() { return this.loc[0] - this.loc[2]; } 
            public final void setAvenue (int theAvenue)
            {
                this.loc[0]=theAvenue;
                this.loc[2]=0;
            }
            public final int getAvenue () 
            {
                return avenue();
            }
/* 497 */   final boolean running() { return this.state == 2; } 
/*     */   public final boolean isVisible() {
/* 499 */     return this.isVisible;
/*     */   }
/*     */   public final void setVisible(boolean visible) {
/* 502 */     if (visible != this.isVisible) {
/* 503 */       setChanged();
/* 504 */       this.isVisible = visible;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static synchronized int incrementRobots()
/*     */   {
/* 521 */     return numberOfRobots++;
/*     */   }
/*     */ 
/*     */   public static abstract interface ConnectStrategy
/*     */   {
/*     */     public abstract void action(UrRobot paramUrRobot1, UrRobot paramUrRobot2, BufferedReader paramBufferedReader);
/*     */   }
/*     */ 
/*     */   static abstract interface Action
/*     */   {
/*     */     public static final int move = 0;
/*     */     public static final int turnLeft = 1;
/*     */     public static final int pickBeeper = 2;
/*     */     public static final int putBeeper = 3;
/*     */     public static final int turnOff = 4;
/*     */     public static final int initial = -1;
/*     */   }
/*     */ 
/*     */   final class StateObject
/*     */     implements Serializable
/*     */   {
/*     */     private int street;
/*     */     private int avenue;
/*     */     private Directions.Direction direction;
/*     */     private int beepers;
/*     */     private int lastAction;
/*     */ 
/*     */     public StateObject(int lastAction)
/*     */     {
/* 475 */       this.street = (UrRobot.this.loc[3] - UrRobot.this.loc[1]);
/* 476 */       this.avenue = (UrRobot.this.loc[0] - UrRobot.this.loc[2]);
/* 477 */       this.direction = UrRobot.this.direction;
/* 478 */       this.beepers = UrRobot.this.beepers;
/* 479 */       this.lastAction = lastAction;
/*     */     }
/* 481 */     public int street() { return this.street; } 
/* 482 */     public int avenue() { return this.avenue; } 
/* 483 */     public Directions.Direction direction() { return this.direction; } 
/* 484 */     public int beepers() { return this.beepers; } 
/* 485 */     public int lastAction() { return this.lastAction; }
/*     */ 
/*     */   }
/*     */ }

/* Location:           C:\Users\School\Documents\2012-2013--HOME\02-AP-CompSci\karel-j-robot-code\kareluniverse\KarelJRobot.jar
 * Qualified Name:     kareltherobot.UrRobot
 * JD-Core Version:    0.6.2
 */