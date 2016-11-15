object ShapeTester extends CommandRead {
  def main (args: Array[String]) {
    println("!!!!");
    NEWCIRCLE WITHNAME "C" AT(0,0) RADIUS 10 COLOR(255, 0, 0); 
    NEWSQUARE WITHNAME "S" SIDE 62 AT(20, 20) COLOR(0, 255, 0);
    
    MAKE A "comp" FROM("C", "S");
    PANEL("P", 640, 480);
    
    DRAWTOPANEL("comp", "P", 0, 0);
  }
}
