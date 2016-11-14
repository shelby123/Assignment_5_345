
// use this to be able to accept strings and conver to a "hex" rgb 
// create in this way:
/* val c:Colors = new Colors;
      c.generateColorMap;
      // c.getNumber(*colorname*) returns a hex int
      g.setColor(new Color(c.getNumber("olive")));
*/
class Colors { 
      var colors: Map[String, Int] = new HashMap[String, Int]();
      val location: String = "colors.txt";
      def generateColorMap =  {
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
  }
      
  def getNumber(name:String):Int =  {
        return colors.get(name.toLowerCase());
  }
  
}
