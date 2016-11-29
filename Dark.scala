

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

    
    MAKE A "samLeftEye" FROM ("iris", "pupil", "pupilWhite");
    MAKE A "samRightEye" FROM("iris2", "pupil2", "pupilWhite2");
    
    DRAWTOPANEL("background", "P");
    
    
    (MAKE A "samEyes" FROM("samLeftEye", "samRightEye")) * 0.65;
    
    DRAWTOPANEL("samEyes", "P", 0, 130);
    
    (GET COMPOSITE "samLeftEye" GETOVAL "pupil") << 6 vv 8; 
    (GET COMPOSITE "samLeftEye" GETOVAL "pupilWhite") << 6 vv 8;
    
    (GET COMPOSITE "samRightEye" GETOVAL "pupil2") << 6 vv 8; 
    (GET COMPOSITE "samRightEye" GETOVAL "pupilWhite2") << 6 vv 8;
    
    MAKE A "otherGuysEyes" FROM ("samLeftEye", "samRightEye");
    
    DRAWTOPANEL("otherGuysEyes", "P", 330, 60);
    
    (NEWSQUARE WITHNAME "otherGuyEyelid" SIDE 25 COLOR "black" AT(480, 235)) % 15;
    
    DRAWTOPANEL("otherGuyEyelid", "P");
    
    ((GET SQUARE "otherGuyEyelid") >> 25)% -30;
    
    DRAWTOPANEL("otherGuyEyelid", "P");
    
    
    (((MAKE DUPLICATE "leftEyelid2" FROM("iris") GETOVAL "iris" COLOR "black") vv 222) << 11) * 2;
    (((MAKE DUPLICATE "rightEyelid2" FROM("iris2") GETOVAL "iris2" COLOR "black") vv 222) << 11) * 2;
    (MAKE A "samBottomLids" FROM("leftEyelid2", "rightEyelid2"));
    
    DRAWTOPANEL("samBottomLids", "P", 0, 0);
    
  }
  
}