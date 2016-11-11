object ShapeTester extends CommandRead {
  def main (args: Array[String]) {
    println("!!!!");
    NEWCIRCLE WITHNAME "C" AT(0,0) DIMENSIONS (20, 20) COLOR(255, 0,0); 
    PANEL("P", 640, 488);
    DRAWTOPANEL("C", "P");
  }
}
