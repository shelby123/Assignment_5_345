### Welcome to PolyGO.

A user experience like no other.  Create structured, beautiful, vibrant art at the touch of your fingertips.

## Visual. Graphic. Library.

Easy to use, yet complex in design.  PolyGO takes away all the difficulty of programming and makes it fun and easy to use.  Simply use some of our pre-defined shapes or create your own.  The choice is yours.  In no time at all you can be the next Picasso with PolyGO.

## Just take a look at PolyGO:

PANEL panel 640 480
NEWCIRCLE WITHNAME redCircle AT x y DIMENSIONS height width COLOR #ff0000
UPDATECIRCLE redCircle FILLSTATE outline
MAKE stickFigure FROM (redCircle, square, ...)
MAKE superStickFigure FROM (stickFigure, etc…)
MAKE exampleShapes BIND circles [1, 2, 3]? (let 1, 2, 3 define parameters of circles)
SCALE stickFigure?
DRAWTOPANEL redCircle

## Dive into the details:
# Operations for defining shapes:
Rotate
Scale
	Stretch horizontally 
	Stretch vertically
	Scale proportionally (i.e. make it 2 times bigger)
Recolor
	Change hue
	Color completely (Monochrome)
	Black and white? Sepia?
Change base shapes 
	I.e. if you have a figure containing circles, you could say something like circle -> square
and any circles in the figure would become squares


# Operations for screen display:
Move
Create
Delete
Rotate 
Pattern (Example: functionality for redefining loops)
Recoloring the entire screen (i.e. making the entire panel black and white)
Stretching/ scaling the entire screen 
(These operations are giving me a vector-graphics kind of feel, lol)
