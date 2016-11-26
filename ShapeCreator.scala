import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.TexturePaint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;

class ShapeCreator {
  
}




class Colors { 
      // construct the colors hashmap when a new colors object is declared
      var colors: HashMap[String, Int] = new HashMap[String, Int]();
      val location: String = "colors.txt";
      val f:File = new File(location);
      val sc:Scanner = new Scanner(f);
      while(sc.hasNextLine()) {
        val lSc: Scanner = new Scanner(sc.nextLine());
        var name:String = lSc.next();
        while(!lSc.hasNextInt()) {
          name = name + " " +  lSc.next();
        }
        name = name.toLowerCase();
        val hex:Int = lSc.nextInt();
        colors.put(name, hex);
      }
      
  def getNumber(name:String):Int =  {
        return colors.get(name.toLowerCase());
  }
  
}






abstract class BaseShape {
  var name: String = "";
  var x: Int = 0;
  var y: Int = 0;
  var rotation: Int = 0;
  var scaleX: Double = 1.0d;
  var scaleY: Double = 1.0d;

  def AT(x: Int, y: Int): this.type = {
    this.x = x;
    this.y = y;
    return this;
  }
  
  def SETX(x: Int): this.type = {
    this.x = x;
    return this;
  }
  
  def SETY(y: Int): this.type = {
    this.y = y;
    return this;
  }
  
  def SCALEX(factor: Double): this.type = {
    this.scaleX = factor;
    return this;
  }
  
  def SCALEY(factor: Double): this.type = {
    this.scaleY = factor;
    return this;
  }
  
  def SCALE(factorX: Double, factorY: Double): this.type = {
    this.scaleX = factorX;
    this.scaleY = factorY;
    return this;
  }
  
  def SCALE(factor: Double): this.type = {
    this.scaleX = factor;
    this.scaleY = factor;
    return this;
  }
  
  def SETROTATE(rotation: Int): this.type = {
    this.rotation = rotation;
    return this;
  }
  
  def duplicate(newName: String): BaseShape
  
  def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int)
  
  def getBoundingWidth(): Int;
  
  def getBoundingHeight(): Int;
  
  def >>(shift: Int) : this.type = {
    this.x += shift;
    return this;
  }
  
  def <<(shift: Int) : this.type = {
    this.x -= shift;
    return this;
  }
  
  def ^^(shift: Int) : this.type = {
    this.y -= shift;
    return this;
  }
  
  def vv(shift: Int) : this.type = {
    this.y += shift;
    return this;
  }
  
  def *(factor: Double): this.type = {
    this.scaleX *= factor;
    this.scaleY *= factor;
    return this;
  }
  
  def /(factor: Double): this.type = {
    this.scaleX /= factor;
    this.scaleY /= factor;
    return this;
  }
  
  def %(angle: Int): this.type = {
    this.rotation += angle;
    return this
  }
}





abstract class Shape extends BaseShape {
  var color: Color = new Color(0, 0, 0);
  var fill: Boolean = true;
  var colorMap:Colors = new Colors();
  var sparkle:Boolean = false;
  
  def COLOR(r: Int, g: Int, b: Int): this.type = {
    this.color = new Color(r, g, b);
    return this;
  }
  
  def COLOR(color: String): this.type = {
    this.color = new Color(colorMap.getNumber(color));
    return this;
  }
  
  def FILLSTATE(state: String): this.type = {
    this.fill = "outline".equals(state);
    return this;
  }
  
  def !! : this.type = {
    sparkle = !sparkle;
    
    return this;
  }
}




class Line extends BaseShape {
  var length: Int = 0;
  var destX: Int = 0;
  var destY: Int = 0;
  var color: Color = new Color(0, 0, 0);
  var colorMap:Colors = new Colors();
  
  def DESTAT(x: Int, y: Int): this.type = {
    this.destX = x;
    this.destY = y;
    return this;
  }
  
  def SETDESTX(x: Int): this.type = {
    this.destX = x;
    return this;
  }
  
  def SETDESTY(y: Int): this.type = {
    this.destY = y;
    return this;
  }
  
  def SETLINE(originX: Int, originY: Int, destX: Int, destY: Int): this.type = {
    this.x = originX;
    this.y = originY;
    this.destX = destX;
    this.destY = destY;
    return this;
  }
  
  def SETLINEANGULAR(originX: Int, originY: Int, theta: Int, magnitude: Int): this.type = {
    this.x = originX;
    this.y = originY;
    this.destX = (x + (magnitude * Math.cos(Math.toRadians(theta)))).toInt;
    this.destY = (y + (magnitude * Math.sin(Math.toRadians(theta)))).toInt;
    return this;
  }
  
  def COLOR(r: Int, g: Int, b: Int): this.type = {
    this.color = new Color(r, g, b);
    return this;
  }
  
  def COLOR(color: String): this.type = {
    this.color = new Color(colorMap.getNumber(color));
    return this;
  }
  
  def duplicate(newName: String): Line = {
    var newLine: Line = new Line();
    
    newLine.name = newName;
    newLine.x = this.x;
    newLine.y = this.y;
    newLine.rotation = this.rotation;
    newLine.scaleX = this.scaleX;
    newLine.scaleY = this.scaleY;
    
    newLine.destX = this.destX;
    newLine.destY = this.destY;
    newLine.color = this.color;
    return newLine;
  }
  
  def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + getBoundingWidth() / 2, this.y + yAdj + getBoundingHeight() / 2);
    graphics.transform(transform);
    graphics.drawLine(x, y, destX, destY);
    var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + getBoundingWidth() / 2, this.y + yAdj + getBoundingHeight() / 2);
    graphics.transform(transform2);
  }
  
  def getBoundingWidth(): Int = {
    return Math.abs(destX - x);
  }
  
  def getBoundingHeight(): Int = {
    return Math.abs(destY - y);
  }
}

class Circle extends Shape {
  var radius: Int = 0;
  
  def RADIUS(radius: Int): this.type = {
    this.radius = radius;
    return this;
  }
  
  override def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + radius, this.y + yAdj + radius);
    graphics.transform(transform);
    if (this.fill) {
      graphics.fillOval(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, radius * 2, radius * 2);
    } else {
      graphics.drawOval(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, radius * 2, radius * 2);
    }
     var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + radius, this.y + yAdj + radius);
    graphics.transform(transform2);
  }
  
  override def duplicate(newName: String): Circle = {
    var newCircle: Circle = new Circle();
    
    newCircle.name = newName;
    newCircle.x = this.x;
    newCircle.y = this.y;
    newCircle.rotation = this.rotation;
    newCircle.scaleX = this.scaleX;
    newCircle.scaleY = this.scaleY;
    newCircle.color = this.color;
    newCircle.fill = this.fill;
    newCircle.sparkle = this.sparkle;
    
    newCircle.radius = this.radius;
    return newCircle;
  }
  
  override def getBoundingWidth(): Int = {
    return (radius * 2 * scaleX).toInt;
  }
  
  override def getBoundingHeight(): Int = {
    return (radius * 2 * scaleY).toInt;
  }
}






class Oval extends Shape {
  var radiusX: Int = 0;
  var radiusY: Int = 0;
  
  def RADIUSX(radiusX: Int): this.type = {
    this.radiusX = radiusX;
    return this;
  }
  
  def RADIUSY(radiusY: Int): this.type = {
    this.radiusY = radiusY;
    return this;
  }
  
  override def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + radiusX, this.y + yAdj + radiusY);
    graphics.transform(transform);
    if (this.fill) {
      graphics.fillOval(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, radiusX * 2, radiusY * 2);
    } else {
      graphics.drawOval(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, radiusX * 2, radiusY * 2);
    }
    var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + radiusX, this.y + yAdj + radiusY);
    graphics.transform(transform2);
  }
  
  override def duplicate(newName: String): Oval = {
    var newOval: Oval = new Oval();
    
    newOval.name = newName;
    newOval.x = this.x;
    newOval.y = this.y;
    newOval.rotation = this.rotation;
    newOval.scaleX = this.scaleX;
    newOval.scaleY = this.scaleY;
    newOval.color = this.color;
    newOval.fill = this.fill;
    newOval.sparkle = this.sparkle;
    
    newOval.radiusX = this.radiusX;
    newOval.radiusY = this.radiusY;
    return newOval;
  }
  
  override def getBoundingWidth(): Int = {
    return (radiusX * 2 * scaleX).toInt;
  }
  
  override def getBoundingHeight(): Int = {
    return (radiusY * 2 * scaleY).toInt;
  }
}





class Rectangle extends Shape {
  var width: Int = 0;
  var height: Int = 0;
  
  def WIDTH(width: Int): this.type = {
    this.width = width;
    return this;
  }
  
  def HEIGHT(height: Int): this.type = {
    this.height = height;
    return this;
  }
  
  override def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + (width / 2), this.y + yAdj + (height / 2));
    graphics.transform(transform);
    if (this.fill) {
      graphics.fillRect(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, this.width, this.height);
    } else {
      graphics.drawRect(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, this.width, this.height);
    }
    var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + (width / 2), this.y + yAdj + (height / 2));
    graphics.transform(transform2);
    
  }
  
  override def duplicate(newName: String): Rectangle = {
    var newRect: Rectangle = new Rectangle();
    
    newRect.name = newName;
    newRect.x = this.x;
    newRect.y = this.y;
    newRect.rotation = this.rotation;
    newRect.scaleX = this.scaleX;
    newRect.scaleY = this.scaleY;
    newRect.color = this.color;
    newRect.fill = this.fill;
    newRect.sparkle = this.sparkle;
    
    newRect.width = this.width;
    newRect.height = this.height;
    return newRect;
  }
  
  override def getBoundingWidth(): Int = {
    return (width * scaleX).toInt;
  }
  
  override def getBoundingHeight(): Int = {
    return (height * scaleY).toInt;
  }
}

class Square extends Shape {
  var side: Int = 0;
  
  def SIDE(side: Int): this.type = {
    this.side = side;
    return this;
  }
  
  override def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    if (!sparkle) {
      graphics.setColor(this.color);
    }
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + (side / 2), this.y + yAdj + (side / 2));
    graphics.transform(transform);
    if (this.fill) {
      graphics.fillRect(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, this.side, this.side);
    } else {
      graphics.drawRect(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt, this.side, this.side);
    }
    var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + (side / 2), this.y + yAdj + (side / 2));
    
    graphics.transform(transform2);
    //graphics.rotate(-Math.toRadians(rotation), -this.x - xAdj, -this.y - yAdj);
    
  }
  
  override def duplicate(newName: String): Square = {
    var newSquare: Square = new Square();
    
    newSquare.name = newName;
    newSquare.x = this.x;
    newSquare.y = this.y;
    newSquare.rotation = this.rotation;
    newSquare.scaleX = this.scaleX;
    newSquare.scaleY = this.scaleY;
    newSquare.color = this.color;
    newSquare.fill = this.fill;
    newSquare.sparkle = this.sparkle;
    
    newSquare.side = this.side;
    return newSquare;
  }
  
  override def getBoundingWidth(): Int = {
    return (side * scaleX).toInt;
  }
  
  override def getBoundingHeight(): Int = {
    return (side * scaleY).toInt;
  }
}






class Triangle extends Shape {
  var base: Int = 0;
  var height: Int = 0;
  
  def BASE(base: Int): this.type = {
    this.base = base;
    return this;
  }
  
  def HEIGHT(height: Int): this.type = {
    this.height = height;
    return this;
  }
  
  override def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    if (!sparkle) {
      graphics.setColor(this.color);
    }
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + (base / 2), this.y + yAdj + (height / 2));
    graphics.transform(transform);
    if (this.fill) {
		  graphics.fillPolygon(getTriangleX(x, y, height, base, xAdj), getTriangleY(x, y, height, base, yAdj), 3);
    } else {
		  graphics.drawPolygon(getTriangleX(x, y, height, base, xAdj), getTriangleY(x, y, height, base, yAdj), 3);
    }
     var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 /scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + (base / 2), this.y + yAdj + (height / 2));
    graphics.transform(transform2);
  }
  
  override def duplicate(newName: String): Triangle = {
    var newTri: Triangle = new Triangle();
    
    newTri.name = newName;
    newTri.x = this.x;
    newTri.y = this.y;
    newTri.rotation = this.rotation;
    newTri.scaleX = this.scaleX;
    newTri.scaleY = this.scaleY;
    newTri.color = this.color;
    newTri.fill = this.fill;
    newTri.sparkle = this.sparkle;
    
    newTri.base = this.base;
    newTri.height = this.height;
    return newTri;
  }
  
  private def getTriangleX(x: Int, y: Int, height: Int, base: Int, xAdj: Int): Array[Int] = {
    return Array(((x + xAdj) / scaleX).toInt, ((x + base + xAdj) / scaleX).toInt, ((x + (base * 1.0 / 2).toInt + xAdj) / scaleX).toInt);
  }
  private def getTriangleY(x: Int, y: Int, height: Int, base: Int, yAdj: Int): Array[Int] = {
    return Array(((y + height + yAdj) / scaleY).toInt, ((y + height + yAdj) / scaleY).toInt, ((y + yAdj) / scaleY).toInt);
  }
  
  override def getBoundingWidth(): Int = {
    return (base * scaleX).toInt;
  }
  
  override def getBoundingHeight(): Int = {
    return (height * scaleY).toInt;
  }
}






class Polygon extends Shape {
  var side: Int = 0;
  var numPoints: Int = 0;
  
  def SIDE(side: Int): this.type = {
    this.side = side;
    return this;
  }
  
  def POINTS(numPoints: Int): this.type = {
    this.numPoints = numPoints;
    return this;
  }
  
  override def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + (side / 2), this.y + yAdj + (side / 2));
    graphics.transform(transform);
    if(this.fill) {
      graphics.fillPolygon(generatePolyX(x, side, numPoints), generatePolyY(y, side, numPoints), numPoints)
    } else {
      graphics.drawPolygon(generatePolyX(x, side, numPoints), generatePolyY(y, side, numPoints), numPoints)
    }
    var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + (side / 2), this.y + yAdj + (side / 2));
    graphics.transform(transform2);
  }
  private def generatePolyX(x:Int, side:Int, numPoints:Int):Array[Int] = {
    val xVals:Array[Int] = new Array(numPoints);
    for(i <- 0 to (numPoints - 1) ) {
      xVals(i) = ((x + side*Math.cos(2*Math.PI * ((i+1.0)/numPoints) + Math.PI/2) + side) / scaleX).toInt; 
    }
    return xVals;
  }
  private def generatePolyY(y:Int, side:Int, numPoints:Int):Array[Int] = {
    val yVals:Array[Int] = new Array(numPoints);
    for(i <- 0 to (numPoints - 1) ) {
      yVals(i) = ((y+ side*Math.sin(2*Math.PI * ((i+1.0)/numPoints) - Math.PI/2) + side) / scaleY).toInt; 
    }
    return yVals;
  }
  
  override def duplicate(newName: String): Polygon = {
    var newPoly: Polygon = new Polygon();
    
    newPoly.name = newName;
    newPoly.x = this.x;
    newPoly.y = this.y;
    newPoly.rotation = this.rotation;
    newPoly.scaleX = this.scaleX;
    newPoly.scaleY = this.scaleY;
    newPoly.color = this.color;
    newPoly.fill = this.fill;
    newPoly.sparkle = this.sparkle;
    
    newPoly.numPoints = this.numPoints;
    newPoly.side = this.side;
    return newPoly;
  }
  
  override def getBoundingWidth(): Int = {
    return (side * scaleX).toInt;
  }
  
  override def getBoundingHeight(): Int = {
    return (side * scaleY).toInt;
  }
}





class Squiggle extends Shape {
  val VERT = "VERTICAL"
  
  var height: Int = 0;
  var width: Int = 0;
  var numPeriods: Int = 0;
  var thickness: Int = 0;
  var vert:Boolean = false;
  def HEIGHT(height: Int): this.type = {
    this.height = height;
    return this;
  }
  
  def WIDTH(width: Int): this.type = {
    this.width = width;
    return this;
  }
  def THICKNESS(thickness: Int): this.type = {
    this.thickness = thickness;
    return this;
  }
  def PERIODS(periods: Int): this.type = {
    this.numPeriods = periods;
    return this;
  }
  def IS(choice: String): this.type = {
    this.vert = VERT.equals(choice.toUpperCase());
    return this;
  }
  
  override def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + (width / 2), this.y + yAdj + (height / 2));
    graphics.transform(transform);
    if(this.vert)
      VertSquiggle(graphics, xAdj+this.x, yAdj+this.y, this.height, this.width, this.numPeriods, this.thickness);
    else
      HorSquiggle(graphics, yAdj+this.y, xAdj+this.x, this.height, this.width, this.numPeriods, this.thickness);
    
    var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj, this.y + yAdj);
    graphics.transform(transform2);
  }
   def VertSquiggle(g:Graphics2D, x:Int, y:Int, height:Int, width:Int, numPeriods:Double, thickness:Int) {
      //where precision represents the number of points in a period to generate
      var precision:Int = height;
      if(width < height)
        precision = width;
      g.fillPolygon(findSquiggleBounce(x, width, numPeriods, precision, thickness), findSquiggleConstant(y, height, numPeriods, precision),
          precision*2);
  }
   def HorSquiggle(g:Graphics2D, x:Int, y:Int, height:Int, width:Int, numPeriods:Double, thickness:Int) {
      //where precision represents the number of points in a period to generate
      var precision:Int = height;
      if(width < height)
        precision = width;
      g.fillPolygon (findSquiggleConstant(y, width, numPeriods, precision),findSquiggleBounce(x, height, numPeriods, precision, thickness),
          precision*2);
  }
  
  private def findSquiggleBounce(x:Int, width:Int, numPeriods:Double, precision:Int, thickness:Int):Array[Int] =  {
      val length2:Int = precision*2;
      val halfLength:Int = precision;
      val numPointsInAPeriod = halfLength/numPeriods;
      println("Length" + length2);
      val xVals:Array[Int] = new Array(length2);
      val offset = (thickness*1.0/2).toInt;
      for(i<-0 to halfLength) {
        xVals(i) = ((x + x*1.0/2 + width*Math.cos(2*Math.PI*i/numPointsInAPeriod))/2 + width/2 - offset).toInt;

      }
      for(i<- halfLength+1 to length2-1) {
        xVals(i) = xVals(length2-i) + offset;
      }
      
      for (i <- 0 to length2 - 1) {
        xVals(i) = (xVals(i) / scaleX).toInt;
      }
      return xVals;
  }
  private def findSquiggleConstant(y:Int, height:Int, numPeriods:Double, precision:Int):Array[Int] =  {
      val length:Int = precision*2;
      val halfLength:Int = precision;
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
      
      for (i <- 0 to length - 1) {
        yVals(i) = (yVals(i) / scaleY).toInt;
      }
      return yVals;
  }   
  
  override def duplicate(newName: String): Squiggle = {
    var newSquiggle: Squiggle = new Squiggle();
    
    newSquiggle.name = newName;
    newSquiggle.x = this.x;
    newSquiggle.y = this.y;
    newSquiggle.rotation = this.rotation;
    newSquiggle.scaleX = this.scaleX;
    newSquiggle.scaleY = this.scaleY;
    newSquiggle.color = this.color;
    newSquiggle.fill = this.fill;
    newSquiggle.sparkle = this.sparkle;
    
    newSquiggle height= this.height;
    newSquiggle width = this.width;
    newSquiggle numPeriods = this.numPeriods;
    newSquiggle thickness = this.thickness;
    newSquiggle vert = this.vert;
    return newSquiggle;
  }
  
  override def getBoundingWidth(): Int = {
    return (width * scaleX).toInt;
  }
  
  override def getBoundingHeight(): Int = {
    return (height * scaleY).toInt;
  }
}





class Composite extends BaseShape {
  var shapes: ArrayList[BaseShape] = new ArrayList[BaseShape]();
  var duplicate: Boolean = false;
  var shapeMap: Map[String, BaseShape] = new HashMap[String, BaseShape]();

  
  def FROM(shapes: String*): Composite = {
    var shapesArray: Array[String] = shapes.toArray;
    var length: Int = shapesArray.length - 1;
    
    for (i <- 0 to length) {
      this.addShape(shapeMap.get(shapesArray(i)), shapesArray(i), this.duplicate);
    }
    
    return this;
  }
  
  private def get(shapeName: String): BaseShape = {
    for (i <- 0 to shapes.size() - 1) {
      var currShape: BaseShape = shapes.get(i);
      if (shapeName.equals(currShape.name)) {
        return currShape;
      }
    }
    return null;
  }
   
  def GETSQUARE(squareName: String): Square = {
    return get(squareName).asInstanceOf[Square];
  }
  
  def GETRECT(rectName: String): Rectangle = {
    return get(rectName).asInstanceOf[Rectangle];
  }
  
  def GETCIRCLE(circleName: String): Circle = {
    return get(circleName).asInstanceOf[Circle];
  }
  
  def GETOVAL(ovalName: String): Oval = {
    return get(ovalName).asInstanceOf[Oval];
  }
  
  def GETTRI(triName: String): Triangle = {
    return get(triName).asInstanceOf[Triangle];
  }
  
  def GETPOLY(polyName: String): Polygon = {
    return get(polyName).asInstanceOf[Polygon];
  }
  
  def GETSQUIGGLE(squiggleName: String): Squiggle = {
    return get(squiggleName).asInstanceOf[Squiggle];
  }
  
  def GETCOMPOSITE(compName: String): Composite = {
    return get(compName).asInstanceOf[Composite];
  }
	 def GETORGANIC(organicName: String): Organic = {
    return get(organicName).asInstanceOf[Organic];
  }
  
  def ADD(shapeName: String, duplicate: String) {
    addShape(shapeMap.get(shapeName), shapeName, "DUPLICATE".equals(duplicate.toUpperCase()));
  }
  
  def ADD(shapeName: String) {
    addShape(shapeMap.get(shapeName), shapeName, duplicate);
  }
  
  def addShape(shape: BaseShape, newName: String, duplicate: Boolean) {
    // Duplicate finding
    var maxTag: Int = 0;
    for (i <- 0 to shapes.size() - 1) {
      var shapeString: String = shapes.get(i).name;
      //println(shapeString);
      
      if (shapeString.length() >= newName.length() && shapeString.substring(0, newName.length).equals(newName)) {
        if (shapeString.length() != newName.length() && shapeString.charAt(newName.length()) == '-') {
          var currTag: Int = Integer.parseInt(shapeString.substring(newName.length() + 1, shapeString.length()));
          if (currTag > maxTag) {
            maxTag = currTag + 1;
          }
        } else if (maxTag == 0) {
          maxTag = 1;
        }
      }
    }
    // Add tag if duplicate
    var useMe: String = newName;
    if (maxTag > 0) {
      useMe = newName + '-' + maxTag;
    }
    
    if (duplicate) {
      this.shapes.add(shape.duplicate(useMe));
    } else {
      this.shapes.add(shape);
    }
  }
  
  def REMOVE(shapeName: String) {
    removeShape(shapeName);
  }
  
  def removeShape(newName: String) {
    for (i <- 0 to shapes.size() - 1) {
      if (shapes.get(i).name.equals(newName)) {
        shapes.remove(i);
        return;
      }
    }
  }
  
  def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
    var centerX: Int = getBoundingWidth() / 2;
    var centerY: Int = getBoundingHeight() / 2;
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj + centerX, this.y + yAdj + centerY);
    graphics.transform(transform);
    for (i <- 0 to shapes.size() - 1) {
      shapes.get(i).draw(graphics, (xAdj / scaleX).toInt, (yAdj / scaleY).toInt);
    }
    var transform2 = new AffineTransform();
    transform.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj + centerX, this.y + yAdj + centerX);
    graphics.transform(transform2);
  }
  
  def duplicate(newName: String): BaseShape = {
    var newComp: Composite = new Composite();
    
    newComp.name = newName;
    newComp.x = this.x;
    newComp.y = this.y;
    newComp.rotation = this.rotation;
    newComp.scaleX = this.scaleX;
    newComp.scaleY = this.scaleY;
    
    for (i <- 1 to shapes.size()) {
      var currShape: BaseShape = shapes.get(shapes.size - i); 
      newComp.addShape(currShape, currShape.name, true);
    }
    
    return newComp;
  }
  
  override def getBoundingWidth(): Int = {
    var minX: Int = Int.MaxValue;
    var maxX: Int = Int.MinValue;
    for (i <- 0 to shapes.size - 1) {
      var currShape = shapes.get(i);
      var currX: Int = currShape.x;
      var currWidth: Int = currShape.getBoundingWidth();
      //min case
      if (currX < minX) {
        minX = currX;
      }
      //max case
      if (currX + currWidth > maxX) {
        maxX = currX + currWidth;
      }
    }
    return maxX - minX;
  }
  
  override def getBoundingHeight(): Int = {
    var minY: Int = Int.MaxValue;
    var maxY: Int = Int.MinValue;
    for (i <- 0 to shapes.size - 1) {
      var currShape = shapes.get(i);
      var currY: Int = currShape.y;
      var currHeight: Int = currShape.getBoundingHeight();
      //min case
      if (currY < minY) {
        minY = currY;
      }
      //max case
      if (currY + currHeight > maxY) {
        maxY = currY + currHeight;
      }
    }
    return maxY - minY;
  }
}



class CommandRead {
  var panelMap: Map[String, DrawingPanel] = new HashMap[String, DrawingPanel]();
  var shapeMap: Map[String, BaseShape] = new HashMap[String, BaseShape]();
  var varMap: Map[String, Int] = new HashMap[String, Int]();
  var bang: TexturePaint = new TexturePaint(ImageIO.read(new File("Sparkle.jpg")), new java.awt.Rectangle(0, 0, 400, 400));
  
  
  object GET {
    def CIRCLE(circleName: String): Circle = {
      return shapeMap.get(circleName).asInstanceOf[Circle];
    }
    
    def RECT(circleName: String): Rectangle = {
      return shapeMap.get(circleName).asInstanceOf[Rectangle];
    }
    
    def SQUARE(circleName: String): Square = {
      return shapeMap.get(circleName).asInstanceOf[Square];
    }
    
    def OVAL(ovalName: String): Oval = {
      return shapeMap.get(ovalName).asInstanceOf[Oval];
    }
    
    def TRI(triName: String): Triangle = {
      return shapeMap.get(triName).asInstanceOf[Triangle];
    }
    
    def POLY(polyName: String): Polygon = {
      return shapeMap.get(polyName).asInstanceOf[Polygon];
    }
    
    def SQUIGGLE(squiggleName: String): Squiggle = {
      return shapeMap.get(squiggleName).asInstanceOf[Squiggle];
    }
    
    def COMPOSITE(compositeName: String): Composite = {
      return shapeMap.get(compositeName).asInstanceOf[Composite];
    }

    def LINE(compositeName: String): Line = {
      return shapeMap.get(compositeName).asInstanceOf[Line];
    }
		def ORGANIC(organicName: String): Organic = {
     	return shapeMap.get(organicName).asInstanceOf[Organic];
    }
  }
  
  object NEWCIRCLE {
    def WITHNAME(parameterName: String): Circle = {
      var newCircle: Circle = new Circle();
      newCircle.name = parameterName;
      shapeMap.put(parameterName, newCircle);
      return newCircle;
    }
  }
  object NEWOVAL {
    def WITHNAME(parameterName: String): Oval = {
      var newOval: Oval = new Oval();
      newOval.name = parameterName;
      shapeMap.put(parameterName, newOval);
      return newOval;
    }
  }
  
  object NEWRECT {
    def WITHNAME(parameterName: String): Rectangle = {
      var newRect: Rectangle = new Rectangle();
      newRect.name = parameterName;
      shapeMap.put(parameterName, newRect);
      return newRect;
    }
  }
  
  object NEWSQUARE {
    def WITHNAME(parameterName: String): Square = {
      var newSquare: Square = new Square();
      newSquare.name = parameterName;
      shapeMap.put(parameterName, newSquare);
      return newSquare;
    }
  }
  
  object NEWTRI {
    def WITHNAME(parameterName: String): Triangle = {
      var newTri: Triangle = new Triangle();
      newTri.name = parameterName;
      shapeMap.put(parameterName, newTri);
      return newTri;
    }
  }
  object NEWPOLY {
    def WITHNAME(parameterName: String): Polygon = {
      var newPoly: Polygon = new Polygon();
      newPoly.name = parameterName;
      shapeMap.put(parameterName, newPoly);
      return newPoly;
    }
  }
  object NEWSQUIGGLE {
    def WITHNAME(parameterName: String): Squiggle = {
      var newSquiggle: Squiggle = new Squiggle();
      newSquiggle.name = parameterName;
      shapeMap.put(parameterName, newSquiggle);
      return newSquiggle;
    }
  }
  
  object NEWLINE {
    def WITHNAME(parameterName: String): Line = {
      var newLine: Line = new Line();
      newLine.name = parameterName;
      shapeMap.put(parameterName, newLine);
      return newLine;
    }
  }
  object NEWORGANIC {
    def WITHNAME(parameterName: String): Organic = {
      var newOrganic: Organic = new Organic();
      newOrganic.name = parameterName;
      shapeMap.put(parameterName, newOrganic);
      return newOrganic;
    }
  }
  
  object DRAWTOPANEL {
    def apply(shapeName: String, panelName: String, x: Int, y: Int) {
      if (panelMap.containsKey(panelName) && shapeMap.containsKey(shapeName)) {
        var shape: BaseShape = shapeMap.get(shapeName);
        var graphics: Graphics2D = panelMap.get(panelName).getGraphics();
        var temp = graphics.getPaint();
        
        graphics.setPaint(bang);
        shape.draw(graphics, x, y);
        graphics.setPaint(temp);
      }
    }
    def apply(shapeName: String, panelName: String) {
      apply(shapeName, panelName, 0, 0);
    }
  }
  
  object PANEL {
    def apply(panelName: String, width: Int, height: Int) {
      var newPanel: DrawingPanel = new DrawingPanel(width, height);
      panelMap.put(panelName, newPanel);
    }
  }
  
  object MAKE {
    def A(parameterName: String): Composite = {
      return createComp(parameterName, false);
    }
    
    def DUPLICATE(parameterName: String) : Composite = {
      return createComp(parameterName, true);
    }
    
    def createComp(parameterName: String, duplicate: Boolean): Composite = {
      var newComp: Composite = new Composite();
      newComp.name = parameterName;
      shapeMap.put(parameterName, newComp);
      newComp.duplicate = duplicate;
      newComp.shapeMap = shapeMap;
      return newComp;
    }
  }
  
  object GETVAR {
    def apply(varName: String): Int = {
      return varMap.get(varName);
    }
  }
  
  object SETVAR {
    def apply(varName: String, value: Int) = {
      varMap.put(varName, value);
    }
  }
  
  object PATTERN {
    def apply(varName: String, limit: Int, step: Int)(body: => Unit) {
      var currIndex: Int = GETVAR(varName);
      if (currIndex <= limit) {
        body
        SETVAR(varName, currIndex + step)
        apply(varName, limit, step)(body);
      }
    }
    def apply(varName: String, limit: Int)(body: => Unit) {
      apply(varName, limit, 1)(body);
    }
  }
}
