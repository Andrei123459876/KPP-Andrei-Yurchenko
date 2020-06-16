

object Part1 {
  //Find power of a number
  
	//With tail recursion
	def powerA(base:Float,pow:Int):Float = {
    //Function to keep base value
	  def powBSub(num:Float,ctr:Int):Float = {
	    if(ctr == 0) num
	    else powBSub(num*base,ctr-1)
	  }
	  if(pow<0) 1/powBSub(1,-pow)
	  else powBSub(1,pow)
	}
	
  //Without tail recursion
	def powerB(base:Float,pow:Int):Float = {
    if(pow == 0) 1
    else if(pow < 0) powerB(1/base, -pow)
	  else base * powerB(base, pow-1)
	}
}