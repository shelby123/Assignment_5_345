

object Dark extends CommandRead {
  def main(args : Array[String]) {
    
    PANEL("P", 640, 480);
    
    NEWRECT WITHNAME "background" COLOR "black" WIDTH 640 HEIGHT 480;
    
    NEWOVAL WITHNAME "iris" COLOR "white" AT(150, 190) RADIUSX 10 RADIUSY 15;
    NEWOVAL WITHNAME "pupil" COLOR "black" AT(158, 196) RADIUSX 5 RADIUSY 7;
    NEWOVAL WITHNAME "pupilWhite" COLOR "white" AT(161, 193) RADIUSX 2 RADIUSY 6;
    NEWOVAL WITHNAME "iris2" COLOR "white" AT(180, 190) RADIUSX 10 RADIUSY 15;
    NEWOVAL WITHNAME "pupil2" COLOR "black" AT(188, 196) RADIUSX 5 RADIUSY 7;
    NEWOVAL WITHNAME "pupilWhite2" COLOR "white" AT(191, 193) RADIUSX 2 RADIUSY 6;
    
    MAKE DUPLICATE "samLeftEye" FROM ("iris", "pupil", "pupilWhite");
    MAKE DUPLICATE "samRightEye" FROM("iris2", "pupil2", "pupilWhite2");
    (MAKE A "samEyeBall" FROM("samLeftEye", "samRightEye")) * 0.65;
    (((MAKE DUPLICATE "leftEyelid2" FROM("iris") GETOVAL "iris" COLOR "black") vv 92) << 11) * 2;
    (((MAKE DUPLICATE "rightEyelid2" FROM("iris2") GETOVAL "iris2" COLOR "black") vv 92) << 11) * 2;
    (MAKE DUPLICATE "samBottomLids" FROM("leftEyelid2", "rightEyelid2"));
    (MAKE A "samEyes" FROM("samEyeBall", "samBottomLids"));
    
    MAKE DUPLICATE "grouchyLeftEye" FROM ("iris", "pupil", "pupilWhite");
    MAKE DUPLICATE "grouchyRightEye" FROM("iris2", "pupil2", "pupilWhite2");
    (GET COMPOSITE "grouchyLeftEye" GETOVAL "pupil") << 6 vv 8; 
    (GET COMPOSITE "grouchyLeftEye" GETOVAL "pupilWhite") << 6 vv 8;
    (GET COMPOSITE "grouchyRightEye" GETOVAL "pupil2") << 6 vv 8; 
    (GET COMPOSITE "grouchyRightEye" GETOVAL "pupilWhite2") << 6 vv 8;
    MAKE A "grouchyEyeBall" FROM ("grouchyLeftEye", "grouchyRightEye");
    (NEWSQUARE WITHNAME "grouchyLeftEyelid" SIDE 25 COLOR "black" AT(150, 175)) % 15;
    ((MAKE DUPLICATE "grouchyRightEyelid" FROM("grouchyLeftEyelid") GETSQUARE "grouchyLeftEyelid" COLOR "black") >> 25) % -30;
    MAKE A "grouchyEyeLids" FROM("grouchyLeftEyelid", "grouchyRightEyelid");
    MAKE A "grouchyEyes" FROM("grouchyEyeBall", "grouchyEyeLids");

    
    DRAWTOPANEL("background", "P");
    DRAWTOPANEL("samEyes", "P", 0, 130);
    DRAWTOPANEL("grouchyEyes", "P", 330, 60);

  }
  
}