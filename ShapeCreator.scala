import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ShapeCreator {
  
}

abstract class BaseShape {
  var name: String = "";
  var x: Int = 0;
  var y: Int = 0;

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
  
  def duplicate(newName: String): BaseShape
  
  def draw(graphics: Graphics, xAdj: Int, yAdj: Int)
}

abstract class Shape extends BaseShape {
  var color: Color = new Color(0, 0, 0);
  var fill: Boolean = true;
  
  def COLOR(r: Int, g: Int, b: Int): this.type = {
    this.color = new Color(r, g, b);
    return this;
  }
  
  def COLOR(color: String): this.type = {
    // TODO: string pattern matching?
    return this;
  }
  
  def FILLSTATE(state: String): this.type = {
    this.fill = "outline".equals(state);
    return this;
  }
}

class Circle extends Shape {
  var radius: Int = 0;
  
  def RADIUS(radius: Int): this.type = {
    this.radius = radius;
    return this;
  }
  
  override def draw(graphics: Graphics, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    if (this.fill) {
      graphics.fillOval(this.x + xAdj, this.y + yAdj, radius * 2, radius * 2);
    } else {
      graphics.drawOval(this.x + xAdj, this.y + yAdj, radius * 2, radius * 2);
    }
  }
  
  override def duplicate(newName: String): Circle = {
    var newCircle: Circle = new Circle();
    
    newCircle.name = newName;
    newCircle.x = this.x;
    newCircle.y = this.y;
    newCircle.color = this.color;
    newCircle.fill = this.fill;
    
    newCircle.radius = this.radius;
    return newCircle;
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
  
  override def draw(graphics: Graphics, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    if (this.fill) {
      graphics.fillOval(this.x + xAdj, this.y + yAdj, radiusX * 2, radiusY * 2);
    } else {
      graphics.drawOval(this.x + xAdj, this.y + yAdj, radiusX * 2, radiusY * 2);
    }
  }
  
  override def duplicate(newName: String): Oval = {
    var newOval: Oval = new Oval();
    
    newOval.name = newName;
    newOval.x = this.x;
    newOval.y = this.y;
    newOval.color = this.color;
    newOval.fill = this.fill;
    
    newOval.radiusX = this.radiusX;
    newOval.radiusY = this.radiusY;
    return newOval;
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
  
  override def draw(graphics: Graphics, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    if (this.fill) {
      graphics.fillRect(this.x + xAdj, this.y + yAdj, this.width, this.height);
    } else {
      graphics.drawRect(this.x + xAdj, this.y + yAdj, this.width, this.height);
    }
  }
  
  override def duplicate(newName: String): Rectangle = {
    var newRect: Rectangle = new Rectangle();
    
    newRect.name = newName;
    newRect.x = this.x;
    newRect.y = this.y;
    newRect.color = this.color;
    newRect.fill = this.fill;
    
    newRect.width = this.width;
    newRect.height = this.height;
    return newRect;
  }
}

class Square extends Shape {
  var side: Int = 0;
  
  def SIDE(side: Int): this.type = {
    this.side = side;
    return this;
  }
  
  override def draw(graphics: Graphics, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    if (this.fill) {
      graphics.fillRect(this.x + xAdj, this.y + yAdj, this.side, this.side);
    } else {
      graphics.drawRect(this.x + xAdj, this.y + yAdj, this.side, this.side);
    }
  }
  
  override def duplicate(newName: String): Square = {
    var newSquare: Square = new Square();
    
    newSquare.name = newName;
    newSquare.x = this.x;
    newSquare.y = this.y;
    newSquare.color = this.color;
    newSquare.fill = this.fill;
    
    newSquare.side = this.side;
    return newSquare;
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
  
  override def draw(graphics: Graphics, xAdj: Int, yAdj: Int) {
    graphics.setColor(this.color);
    if (this.fill) {
		  graphics.fillPolygon(getTriangleX(x, y, height, base, xAdj), getTriangleY(x, y, height, base, yAdj), 3);
    } else {
		  graphics.drawPolygon(getTriangleX(x, y, height, base, xAdj), getTriangleY(x, y, height, base, yAdj), 3);
    }
  }
  
  override def duplicate(newName: String): Triangle = {
    var newTri: Triangle = new Triangle();
    
    newTri.name = newName;
    newTri.x = this.x;
    newTri.y = this.y;
    newTri.color = this.color;
    newTri.fill = this.fill;
    
    newTri.base = this.base;
    newTri.height = this.height;
    return newTri;
  }
  
  private def getTriangleX(x: Int, y: Int, height: Int, base: Int, xAdj: Int): Array[Int] = {
    return Array(x + xAdj, x + base + xAdj, x + (base * 1.0 / 2).toInt + xAdj);
  }
  private def getTriangleY(x: Int, y: Int, height: Int, base: Int, yAdj: Int): Array[Int] = {
    return Array(y + height + yAdj, y + height + yAdj, y + yAdj);
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
  
  def addShape(shape: BaseShape, newName: String, duplicate: Boolean) {
    if (duplicate) {
      this.shapes.add(0, shape.duplicate(newName));
    } else {
      this.shapes.add(0, shape);
    }
  }
  
  def removeShape(newName: String) {
    for (i <- 0 to shapes.size() - 1) {
      if (shapes.get(i).name.equals(newName)) {
        shapes.remove(i);
        return;
      }
    }
  }
  
  def draw(graphics: Graphics, xAdj: Int, yAdj: Int) {
    for (i <- 0 to shapes.size() - 1) {
      shapes.get(i).draw(graphics, xAdj, yAdj);
    }
  }
  
  def duplicate(newName: String): BaseShape = {
    var newComp: Composite = new Composite();
    
    newComp.name = newName;
    newComp.x = this.x;
    newComp.y = this.y;
    
    for (i <- 1 to shapes.size()) {
      var currShape: BaseShape = shapes.get(shapes.size - i); 
      newComp.addShape(currShape, currShape.name, true);
    }
    
    return newComp;
  }
}

class CommandRead {
  var panelMap: Map[String, DrawingPanel] = new HashMap[String, DrawingPanel]();
  var shapeMap: Map[String, BaseShape] = new HashMap[String, BaseShape]();
  
  object UPDATE {
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
  
  object DRAWTOPANEL {
    def apply(shapeName: String, panelName: String, x: Int, y: Int) {
      if (panelMap.containsKey(panelName) && shapeMap.containsKey(shapeName)) {
        var shape: BaseShape = shapeMap.get(shapeName);
        var graphics: Graphics = panelMap.get(panelName).getGraphics();
        shape.draw(graphics, x, y);
      }
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
}