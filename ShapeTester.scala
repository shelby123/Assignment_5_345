import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ShapeCreator {
  
}

class Circle {
  var name: String = "";
  var width: Int = 0;
  var height: Int = 0;
  var x: Int = 0;
  var y: Int = 0;
  var color: Color = new Color(0, 0, 0);
  var fill: Boolean = true;
  
  def AT(x: Int, y: Int): Circle = {
    this.x = x;
    this.y = y;
    return this;
  }
  
  def SETX(x: Int): Circle = {
    this.x = x;
    return this;
  }
  
  def SETY(y: Int): Circle = {
    this.y = y;
    return this;
  }
  
  def DIMENSIONS(height: Int, width: Int): Circle = {
    this.width = width;
    this.height = height;
    return this;
  }
  
  def WIDTH(width: Int): Circle = {
    this.width = width;
    return this;
  }
  
  def HEIGHT(height: Int): Circle = {
    this.height = height;
    return this;
  }
  
  def COLOR(r: Int, g: Int, b: Int): Circle = {
    this.color = new Color(r, g, b);
    return this;
  }
  
  def COLOR(color: String): Circle = {
    // TODO: string pattern matching?
    return this;
  }
  
  def FILLSTATE(state: String): Circle = {
    this.fill = "outline".equals(state);
    return this;
  }
}

class CommandRead {
  var panelMap: Map[String, DrawingPanel] = new HashMap[String, DrawingPanel]();
  var shapeMap: Map[String, Circle] = new HashMap[String, Circle]();
  
  object NEWCIRCLE {
    def WITHNAME(parameterName: String): Circle = {
      var newCircle: Circle = new Circle();
      newCircle.name = parameterName;
      shapeMap.put(parameterName, newCircle);
      return newCircle;
    }
  }
  
  object UPDATECIRCLE {
    def apply(circleName: String): Circle = {
      return shapeMap.get(circleName);
    }
  }
  
  object DRAWTOPANEL {
    def apply(shapeName: String, panelName: String) {
      if (panelMap.containsKey(panelName) && shapeMap.containsKey(shapeName)) {
        var circle: Circle = shapeMap.get(shapeName);
        panelMap.get(panelName).getGraphics.fillOval(circle.x, circle.y, circle.width, circle.height);
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