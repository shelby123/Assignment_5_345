# Welcome to PolyGO.
  
A user experience like no other.  Create structured, beautiful, vibrant art at the touch of your fingertips.
  
  
  
## Visual. Graphic. Library.
  
Easy to use, yet complex in design.  PolyGO takes away all the difficulty of programming and makes it fun and easy to use.  Simply use some of our pre-defined shapes or create your own.  The choice is yours.  In no time at all you can be the next Picasso with PolyGO.
  
  
  
## Just take a look at PolyGO:
```scala
NEWRECT WITHNAME "FORKBASE" AT (250,140) WIDTH 8 HEIGHT 70 COLOR "Gray"
NEWRECT WITHNAME "FORKSIDE" AT (240,140) WIDTH 28 HEIGHT 8 COLOR "Gray"
NEWRECT WITHNAME "FORKPRONG1" AT (240,120) WIDTH 8 HEIGHT 20 COLOR "Gray"
NEWRECT WITHNAME "FORKPRONG2" AT (250,120) WIDTH 8 HEIGHT 20 COLOR "Gray"
NEWRECT WITHNAME "FORKPRONG3" AT (260,120) WIDTH 8 HEIGHT 20 COLOR "Gray"
MAKE DUPLICATE "FORK" FROM ("FORKBASE", "FORKSIDE", "FORKPRONG1", "FORKPRONG2", "FORKPRONG3")
    
MAKE DUPLICATE "KNIFE"
NEWRECT WITHNAME "KNIFEBASE" AT (287, 121) WIDTH 8 HEIGHT 89 COLOR "Gray"
GET COMPOSITE "KNIFE" ADD "KNIFEBASE"
SETVAR("i", 1)
PATTERN("i", 5) {
  NEWTRI WITHNAME "T" AT (280, GETVAR("i") * 10 + 110) BASE 10 HEIGHT 10 SETROTATE 25 COLOR "Gray"
  GET COMPOSITE "KNIFE" ADD "T"
}    
```
WOW! That was easy!!  

## Dive into the details:
### Operations for defining shapes:
Rotate  
Scale  
	Stretch horizontally   
	Stretch vertically  
	Scale proportionally (i.e. make it 2 times bigger)  
Recolor  
	Change hue  
	Color completely (Monochrome)  
	Black and white, Sepia  
  
  
### Operations for screen display:  
Move  
Create  
Delete  
Rotate   
Pattern
