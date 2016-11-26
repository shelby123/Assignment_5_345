/*

Example on setting the x y points. Note that # of x points must equal # of y points: 

NEWORGANIC WITHNAME "X" SET_X(Array(1, 2, 3)) SET_Y(Array(1, 2, 3))

*/


class Organic extends Shape {
  var xPoints:Array[Int] = null;
  var yPoints:Array[Int] = null;
  var size = 0;
  
  def SET_X(x:Array[Int]):this.type =  {
    xPoints = x;
    size = x.length;
    return this;
  }
  
   def SET_Y(y:Array[Int]):this.type = {
    yPoints = y;
    size = y.length;
    return this;
  }
    
  def duplicate(newName: String): BaseShape ={
    var newOrganic: Organic = new Organic();
    
    newOrganic.name = newName;
    newOrganic.x = this.x;
    newOrganic.y = this.y;
    newOrganic.rotation = this.rotation;
    newOrganic.scaleX = this.scaleX;
    newOrganic.scaleY = this.scaleY;
    newOrganic.color = this.color;
    newOrganic.fill = this.fill;
    newOrganic.sparkle = this.sparkle;
    
    newOrganic.xPoints = this.xPoints;
    newOrganic.yPoints = this.yPoints;
    newOrganic.size = this.size;
    return newOrganic;
  }
  
  def draw(graphics: Graphics2D, xAdj: Int, yAdj: Int) {
     graphics.setColor(this.color);
    var transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    transform.rotate(Math.toRadians(rotation), this.x + xAdj, this.y + yAdj);
    shift(((this.x + xAdj) / scaleX).toInt, ((this.y + yAdj) / scaleY).toInt);
   // transform.translate((this.x/scaleX).toInt+xAdj, (this.y/scaleY).toInt+yAdj);
    graphics.transform(transform);
    if (this.fill) {
      graphics.fillPolygon(xPoints, yPoints, size);
    } else {
      graphics.drawPolygon(xPoints, yPoints, size);
    }
    shift(-((this.x + xAdj) / scaleX).toInt, -((this.y + yAdj) / scaleY).toInt);
    var transform2 = new AffineTransform();
    transform2.scale(1 / scaleX, 1 / scaleY);
    transform2.rotate(-Math.toRadians(rotation), this.x + xAdj, this.y + yAdj );
  //  transform2.translate(-(this.x/scaleX).toInt+xAdj, (this.y/scaleY).toInt+yAdj));
    graphics.transform(transform2);
  }
  
  private def shift(x:Int, y:Int) {
    for(i <- 0 to xPoints.length-1) {
      xPoints(i) = xPoints(i) + x;
      yPoints(i) = yPoints(i) + y;
    }
  }
  
  def getBoundingWidth(): Int = {
    var minX:Int = Integer.MAX_VALUE;
    var maxX:Int = Integer.MIN_VALUE;
    for(i <- 0 to xPoints.length-1) {
      var temp = xPoints(i);
      if(temp < minX) minX = temp;
      if(temp > maxX) maxX = temp;
    }
    return ((maxX-minX)*scaleX).toInt;
  }
  
  def getBoundingHeight(): Int = {
    var minY:Int = Integer.MAX_VALUE;
    var maxY:Int = Integer.MIN_VALUE;
    for(i <- 0 to yPoints.length-1) {
      var temp = yPoints(i);
      if(temp < minY) minY = temp;
      if(temp > maxY) maxY = temp;
    }
    return ((maxY-minY)*scaleY).toInt;
  }
}
