object ShapeTester extends CommandRead {
  def main (args: Array[String]) {
    println("!!!!");
    NEWCIRCLE WITHNAME "C" AT(0,0) RADIUS 10 COLOR(255, 0, 0); 
    NEWSQUARE WITHNAME "S" AT(20, 20) SIDE 62 COLOR "TEAL";    
    NEWSQUIGGLE WITHNAME "SQ" HEIGHT 30 WIDTH 100 AT(100, 50) THICKNESS 10 PERIODS 3 IS "H" COLOR(0, 0, 0);
    
    MAKE DUPLICATE "comp" FROM("C", "S", "SQ", "S");
    
    UPDATE COMPOSITE "comp" GETSQUARE "S-1" AT(100, 100) COLOR "ORANGE GOLD"
    
    
    PANEL("P", 640, 480);
    
    
    DRAWTOPANEL("comp", "P", 0, 50);
  }
}
