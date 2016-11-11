object ShapeTester extends CommandRead {
  def main (args: Array[String]) {
    println("!!!!");
    NEWCIRCLE WITHNAME "C" AT(0,0) RADIUS 10 COLOR(255, 0, 0); 
    NEWRECT WITHNAME "R" WIDTH 10 HEIGHT 15 AT(40, 10) COLOR(0, 255, 0);
    NEWSQUARE WITHNAME "S" SIDE 62 AT(62, 20) COLOR(0, 255, 0);
    UPDATE SQUARE "S" COLOR(0, 0, 255);
    PANEL("P", 640, 480);
    DRAWTOPANEL("C", "P");
    DRAWTOPANEL("R", "P");
    DRAWTOPANEL("S", "P");
  }
}
