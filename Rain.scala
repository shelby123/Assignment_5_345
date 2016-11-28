object Rain extends CommandRead {
  def main (args: Array[String]) {
    PANEL("P", 640, 480);
    NEWCIRCLE WITHNAME "BOTTOM" RADIUS 10 COLOR "LIGHT BLUE" AT (0, 20);
    NEWTRI WITHNAME "TOP" BASE 20 HEIGHT 30 COLOR "LIGHT BLUE" AT (0, 0);
    MAKE DUPLICATE "RAINDROP" FROM("BOTTOM", "TOP") SETROTATE -20;
    
    GET2 VAR "I";
    SETVAR ("I", 0)
    PATTERN("I", 10, 1) {
        GET2 VAR "J"
        SETVAR ("J", 0) 
        PATTERN("J", 15, 1) {
           GET COMPOSITE "RAINDROP" SETY (((GET2 VAR "I" ) * (480*1.0/10)).toInt)
           GET COMPOSITE "RAINDROP" SETX (((GET2 VAR "J" ) * (1000*1.0/15)).toInt)
           DRAWTOPANEL("RAINDROP", "P", 0, 0)
        }
    }
  }
}
