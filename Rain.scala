object Rain extends CommandRead {
  def main (args: Array[String]) {
    PANEL("P", 640, 480);

    NEWTRI WITHNAME "TOP" BASE 20 HEIGHT 30 COLOR "LIGHT BLUE" AT (0, 0);
    NEWCIRCLE WITHNAME "C" RADIUS 10 COLOR "LIGHT BLUE" AT (0, 22);
    
    MAKE A "RAINDROP" FROM("TOP", "C") SCALE .5 

    GETVAR ("I");
    SETVAR ("I", 0)
    PATTERN("I", 10, 1) {
        GET COMPOSITE "RAINDROP" SETX 0
        GETVAR ("J")
        SETVAR ("J", 0) 
        PATTERN("J", 15, 1) {
          DRAWTOPANEL("RAINDROP", "P", 0, 0)
          ((GET COMPOSITE "RAINDROP") * 1.5 ) >> (640/30)
          DRAWTOPANEL("RAINDROP", "P", 0, 0)
          (GET COMPOSITE "RAINDROP") / 1.5
          (GET COMPOSITE "RAINDROP") >> (640/30)
        }
    }
      (GET COMPOSITE "RAINDROP") vv (480/10)

  }
}
