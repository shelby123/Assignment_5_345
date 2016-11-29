object PresentationInfoSlide extends CommandRead {
  def main (args: Array[String]) {
    PANEL("P", 840, 480);
    SETPANEL( "P")
    
    NEWCIRCLE WITHNAME "TEALTOPLEFT" RADIUS 100 COLOR "presentation aqua" AT (50, -50) 
    NEWCIRCLE WITHNAME "TEALTOPLEFTINSIDE" RADIUS 40 COLOR "WHITE" AT (110, 10)
    
    NEWCIRCLE WITHNAME "TOP LEFT BLUE OUTLINE" RADIUS 35 COLOR "PRESENTATION BLUE" AT (225, 25) FILLSTATE "OUTLINE"
    NEWCIRCLE WITHNAME "TOP LEFT BLUE" RADIUS 30 COLOR "PRESENTATION BLUE" AT (230, 30)

    NEWCIRCLE WITHNAME "ORANGELEFT" RADIUS 50 COLOR "PRESENTATION ORANGE" AT (-5, 90)
    
    NEWCIRCLE WITHNAME "LEFTYELLOW" RADIUS 30 COLOR "PRESENTATION YELLOW" AT (90, 210)
    NEWCIRCLE WITHNAME "LEFTYELLOWCENTER" RADIUS 20 COLOR "WHITE" AT (100, 220)
    
    NEWCIRCLE WITHNAME "LEFTGREEN OUTLINE" RADIUS 35 COLOR "PRESENTATION LIME" AT (130, 240) FILLSTATE "OUTLINE"
    
    NEWCIRCLE WITHNAME "LEFT FUSCIA" RADIUS 15 COLOR "PRESENTATION FUSCIA" AT ( 50, 230)
    
    NEWCIRCLE WITHNAME "LEFT BOTTOM LIME" RADIUS 60 COLOR "PRESENTATION LIME" AT (10, 380);

    NEWCIRCLE WITHNAME "LEFT BOTTOM GREEN" RADIUS 25 COLOR "PRESENTATION GREEN" AT (150, 350);

    NEWCIRCLE WITHNAME "LEFT BOTTOM GREEN CENTER" RADIUS 5 COLOR "WHITE" AT (170, 370);

    NEWCIRCLE WITHNAME "TOP RIGHT BLUE" RADIUS 30 COLOR "PRESENTATION BLUE" AT (770, -25)
    NEWCIRCLE WITHNAME "TOP RIGHT BLUE CENTER" RADIUS 15 COLOR "WHITE" AT (785, -10)
    
    NEWCIRCLE WITHNAME "TOP RIGHT YELLOW" RADIUS 20 COLOR "PRESENTATION YELLOW" AT (720, 40)

    NEWCIRCLE WITHNAME "TOP RIGHT ORANGE OUTLINE" RADIUS 17 COLOR "PRESENTATION ORANGE" AT (740, 65) FILLSTATE "OUTLINE"

    NEWCIRCLE WITHNAME "RIGHT TEAL" RADIUS 15 COLOR "PRESENTATION AQUA" AT (820, 80)
    
    NEWCIRCLE WITHNAME "LEFT LIME" RADIUS 7 COLOR "PRESENTATION LIME" AT ( 800, 120)

    
    DRAW ("TEALTOPLEFT")
    DRAW ("TEALTOPLEFTINSIDE")
    DRAW ("TOP LEFT BLUE")
    DRAW ("TOP LEFT BLUE OUTLINE")
    DRAW ("ORANGELEFT")
    DRAW ("LEFTYELLOW")
    DRAW ("LEFTYELLOWCENTER")
    DRAW ("LEFTGREEN OUTLINE")
    DRAW ("LEFT FUSCIA")
    DRAW ("LEFT BOTTOM LIME")
    DRAW ("LEFT BOTTOM GREEN")
    DRAW ("LEFT BOTTOM GREEN CENTER")
    
    
    
    DRAW ("TOP RIGHT BLUE")
    DRAW ("TOP RIGHT BLUE CENTER")
    DRAW ("TOP RIGHT YELLOW" )
    DRAW ("TOP RIGHT ORANGE OUTLINE")
    DRAW ("RIGHT TEAL")
    DRAW ("LEFT LIME")
  }
  
}
