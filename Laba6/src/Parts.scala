import scala.collection.immutable.List
import scala.collection.immutable.Set


object Parts {
  
  //Theory - set only contains unique variables, 
  //so should exclude all duplicates 
  def part1(arr:List[String]):List[String] = {
    val arrSet:Set[String] = arr.toSet
    arrSet.toList                              
  }
  
  
  def part2(arr:List[Double]):Double = {
    arr.sum / arr.length    //Produces NaN if empty list
  }
  
  
  def part3(arr:List[String]):String = {
    //Assembles a string without use of var
    def concRecrs(str:String, pos:Int):String = {
      if(pos >= arr.length){
        str
      }
      else
      {    
        if(arr.apply(pos).charAt(0).isUpper){
          concRecrs(str + arr.apply(pos) + " ", pos + 1)
        }
        else
        {
          concRecrs(str, pos + 1)
        }
      }
    }
    
    concRecrs(new String, 0);
  }
  
}