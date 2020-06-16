import scala.io.StdIn
import scala.collection.immutable.List



object ScalaApp extends App {
  println("Lab 6, var. 24");
  println("Part 1 - delete recurring elements from collection.");
  println("Input array of strings. Empty string to finish");
  
  var part1List = List[String]();
  var p1flag = true
  while(p1flag)
  {
    val newString = StdIn.readLine();
    if(newString.isEmpty()){
      p1flag = false
    }
    else
    {
    part1List = part1List :+ newString  //Add new line
    }
  }
  part1List = Parts.part1(part1List)
  println("Answer: " + part1List.toString())
  
  
  
  println("Part 2 - average of List[Double]")
  println("Input values. Non-Double to finish")
  
  var part2List = List[Double]()
  var p2flag = true
  while(p2flag)
  {
    val newStr = StdIn.readLine()
    if(newStr.isEmpty()){
      p2flag = false
    }
    else{
      try{
        val newDouble = newStr.toDouble
        part2List = part2List :+ newDouble
      }
      catch{
        case e: java.lang.NumberFormatException => p2flag = false
      }
    }
  }
  println("Answer: " + Parts.part2(part2List))
  
  
  
  println("Part 3 - take list of strings, put ones that begin with uppercase letter into string")
  println("Input strings. Empty string to finish")
  
  var part3List = List[String]();
  var p3flag = true
  while(p3flag)
  {
    val newString = StdIn.readLine();
    if(newString.isEmpty()){
      p3flag = false
    }
    else
    {
    part3List = part3List :+ newString  //Add new line
    }
  }
 
  println("Answer: " + Parts.part3(part3List))
}




