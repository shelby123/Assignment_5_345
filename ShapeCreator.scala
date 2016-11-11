import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ShapeCreator {
  
}

abstract class Shape {
  var name: String = "";
  var x: Int = 0;
  var y: Int = 0;
  var color: Color = new Color(0, 0, 0);
  var fill: Boolean = true;

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
  
  def draw(graphics: Graphics)
  
}

class Circle extends Shape {
  var radius: Int = 0;
  
  def RADIUS(radius: Int): this.type = {
    this.radius = radius;
    return this;
  }
  
  override def draw(graphics: Graphics) {
    graphics.setColor(this.color);
    if (this.fill) {
      graphics.fillOval(this.x, this.y, radius * 2, radius * 2);
    } else {
      graphics.drawOval(this.x, this.y, radius * 2, radius * 2);
    }
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
  
  override def draw(graphics: Graphics) {
    graphics.setColor(this.color);
    if (this.fill) {
      graphics.fillRect(this.x, this.y, this.width, this.height);
    } else {
      graphics.drawRect(this.x, this.y, this.width, this.height);
    }
  }
}

class Square extends Shape {
  var side: Int = 0;
  
  def SIDE(side: Int): this.type = {
    this.side = side;
    return this;
  }
  
  override def draw(graphics: Graphics) {
    graphics.setColor(this.color);
    if (this.fill) {
      graphics.fillRect(this.x, this.y, this.side, this.side);
    } else {
      graphics.drawRect(this.x, this.y, this.side, this.side);
    }
  }
}

class CommandRead {
  var panelMap: Map[String, DrawingPanel] = new HashMap[String, DrawingPanel]();
  var shapeMap: Map[String, Shape] = new HashMap[String, Shape]();
  
  object NEWCIRCLE {
    def WITHNAME(parameterName: String): Circle = {
      var newCircle: Circle = new Circle();
      newCircle.name = parameterName;
      shapeMap.put(parameterName, newCircle);
      return newCircle;
    }
  }
  
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
  
  object DRAWTOPANEL {
    def apply(shapeName: String, panelName: String) {
      if (panelMap.containsKey(panelName) && shapeMap.containsKey(shapeName)) {
        var shape: Shape = shapeMap.get(shapeName);
        var graphics: Graphics = panelMap.get(panelName).getGraphics();
        shape.draw(graphics);
      }
    }
  }
  
  object PANEL {
    def apply(panelName: String, width: Int, height: Int) {
      var newPanel: DrawingPanel = new DrawingPanel(width, height);
      panelMap.put(panelName, newPanel);
    }
  }
}