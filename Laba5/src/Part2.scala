import scala.collection.immutable.Range
import java.util.List


object Part2 {
  //Range between 2 numbers. Find numbers whose squares fall into that range
  
  //With tail recusrion
	def numSqrA(start:Int,end:Int):Range = {
	  def findStart(i:Int):Int = {
	    if(Long2long(i)*i >= Long2long(start)) i
	    else findStart(i+1)
	  }
	  def findEnd(i:Int):Int = {
	    if(Long2long(i)*i > Long2long(end)) i
	    else findEnd(i+1)
	  }
	  Range(findStart(0), findEnd(0))
	}
	
	//Without tail recusrion
	def numSqrB(start:Int,end:Int):Range = {
	  def findStart(i:Int):Int = {
	    if(Long2long(i)*i >= Long2long(start)) 0
	    else 1 + findStart(i+1)
	  }
	  def findEnd(i:Int):Int = {
	    if(Long2long(i)*i > Long2long(end)) 0
	    else 1 + findEnd(i+1)
	  }
	  Range(findStart(0), findEnd(0))
	}
	
	//Helper function to output numbers in console
	def printRange(range:Range){
	  println("Number of elements: " + range.size);
	  for(i <- range)
	  {
	    print(i + " ")
	    if(i%25 == 0) println()
	  }
	  println()
	}
	
	//Wrappers. Otherwise Java can't deal with Range object
	def printResultA(start:Int, end:Int){
	  printRange(numSqrA(start, end))
	}
	def printResultB(start:Int, end:Int){
	  printRange(numSqrB(start, end))
	}
	
	
}