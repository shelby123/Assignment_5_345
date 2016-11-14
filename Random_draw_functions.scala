 
 // not inside of a class :) 
 
 def fillTriangle(g:Graphics,  x:Int,  y:Int,  height:Int,  base:Int) =  {
		g.fillPolygon(getTriangleX(x, y, height, base), getTriangleX(x, y, height, base), 3);
	}
  def drawTriangle(g:Graphics,  x:Int,  y:Int,  height:Int,  base:Int) =  {
		g.drawPolygon(getTriangleX(x, y, height, base), getTriangleX(x, y, height, base), 3);
	}
  private def getTriangleX(x:Int, y:Int, height:Int, base:Int): Array[Int] =  {
    return  Array(x, x+base, x + (base*1.0/2).toInt);
  }
  private def getTriangleY(x:Int, y:Int, height:Int, base:Int): Array[Int] =  {
    return  Array(y+height, y+height, y);
  }
  
  def fillRegularPoly(g:Graphics, x:Int, y:Int, side:Int, numPoints:Int) {
    g.fillPolygon(generatePolyX(x, side, numPoints), generatePolyY(y, side, numPoints), numPoints)
  }
  def drawRegularPoly(g:Graphics, x:Int, y:Int, side:Int, numPoints:Int) {
    g.drawPolygon(generatePolyX(x, side, numPoints), generatePolyY(y, side, numPoints), numPoints)
  }
  private def generatePolyX(x:Int, side:Int, numPoints:Int):Array[Int] = {
    val xVals:Array[Int] = new Array(numPoints);
    for(i <- 0 to (numPoints - 1) ) {
      xVals(i) = (x + side*Math.cos(2*Math.PI * ((i+1.0)/numPoints) + Math.PI/2) + side).toInt; 
    }
    return xVals;
  }
  private def generatePolyY(y:Int, side:Int, numPoints:Int):Array[Int] = {
    val yVals:Array[Int] = new Array(numPoints);
    for(i <- 0 to (numPoints - 1) ) {
      yVals(i) = (y+ side*Math.sin(2*Math.PI * ((i+1.0)/numPoints) - Math.PI/2) + side).toInt; 
    }
    return yVals;
  }
  def fillVertSquiggle(g:Graphics, x:Int, y:Int, height:Int, width:Int, numPeriods:Double, thickness:Int) {
      //where precision represents the number of points in a period to generate
      val precision:Int = 50;
      g.fillPolygon(findSquiggleXVert(x, width, numPeriods, precision, thickness), findSquiggleYVert(y, height, numPeriods, precision),
          (numPeriods*precision).toInt*2);
  }
   def fillHorSquiggle(g:Graphics, x:Int, y:Int, height:Int, width:Int, numPeriods:Double, thickness:Int) {
      //where precision represents the number of points in a period to generate
      val precision:Int = 10;
      g.fillPolygon(findSquiggleXHor(x, width, numPeriods, precision), findSquiggleYHor(y, height, numPeriods, precision, thickness),
          (numPeriods*precision).toInt*2);
  }
    private def findSquiggleXHor(x:Int, width:Int, numPeriods:Double, precision:Int):Array[Int] =  {
      val length:Int = (numPeriods*precision).toInt*2;
      val halfLength:Int = (numPeriods*precision).toInt;
      val xVals:Array[Int] = new Array(length);
      var currentX = x;
      for(i<-0 to halfLength) {
        currentX = currentX + (width*1.0/halfLength).toInt;
        xVals(i) = currentX;
        println("xVal at " + i + " is " + xVals(i));
      }
      for(i<-halfLength to length-1) {
        xVals(i) = currentX;
        currentX = currentX - (width*1.0/halfLength).toInt;
        println("xVal at " + i + " is " + xVals(i));
      }
      return xVals;
  }
  private def findSquiggleYHor(y:Int, height:Int, numPeriods:Double, precision:Int, thickness:Int):Array[Int] =  {
    val length:Int = (numPeriods*precision).toInt*2;
      val yVals:Array[Int] = new Array(length);
      val offset = (thickness*1.0/2).toInt;
      for(i<-0 to (numPeriods*precision).toInt) {
        yVals(i) = ((y + y*1.0/2 + height*Math.cos(2*Math.PI*i/precision))/2 + height/2 + offset).toInt;
        println("yVal at " + i + " is " + yVals(i));
      }
      for(i<- (numPeriods*precision).toInt+1 to length-1) {
        yVals(i) = yVals(length-i)-offset;
         println("yVal at " + i + " is " + yVals(i));
      }
      return yVals;
  }
  
  private def findSquiggleXVert(x:Int, width:Int, numPeriods:Double, precision:Int, thickness:Int):Array[Int] =  {
      val length:Int = (numPeriods*precision).toInt*2;
      println("Length" + length);
      val xVals:Array[Int] = new Array(length);
      val offset = (thickness*1.0/2).toInt;
      for(i<-0 to (numPeriods*precision).toInt) {
        xVals(i) = ((x + x*1.0/2 + width*Math.cos(2*Math.PI*i/precision))/2 + width/2 - offset).toInt;
      }
      for(i<- (numPeriods*precision).toInt+1 to length-1) {
        xVals(i) = xVals(length-i) + offset;
      }
      return xVals;
  }
  private def findSquiggleYVert(y:Int, height:Int, numPeriods:Double, precision:Int):Array[Int] =  {
      val length:Int = (numPeriods*precision).toInt*2;
      val halfLength:Int = (numPeriods*precision).toInt;
      val yVals:Array[Int] = new Array(length);
      var currentY = y;
      for(i<-0 to halfLength) {
        currentY = currentY + (height*1.0/halfLength).toInt;
        yVals(i) = currentY;
      }
      for(i<-halfLength to length-1) {
        yVals(i) = currentY;
        currentY = currentY - (height*1.0/halfLength).toInt;
      }
      return yVals;
  }  
