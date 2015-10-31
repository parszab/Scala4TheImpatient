object s4ti_ch02 {
  //1. The signum of a number is 1 if the number is positive, –1 if itECLIPSE_HOME/plugins/org.eclipse.ui.themes_*/css/e4_default_gtk.css
  
  // is negative, and 0 if it is zero. Write a function that computes this value.
  def sn(x:Int) = {if (x<0) -1 else if (x>0) 1 else 0}
                                                  //> sn: (x: Int)Int
  sn(10)                                          //> res0: Int = 1
  sn(-10)                                         //> res1: Int = -1
  sn(0)                                           //> res2: Int = 0
  
  //2. What is the value of an empty block expression {}? What is its type?
  //() / Unit
  val a = {}                                      //> a  : Unit = ()
  a.getClass                                      //> res3: Class[Unit] = void
  
  //3. Come up with one situation where the assignment x = y = 1 is valid in Scala.
  // (Hint: Pick a suitable type for x.)
  var y = 1                                       //> y  : Int = 1
  var x:Unit = ()                                 //> x  : Unit = ()
  x = y = 1
  y == 1                                          //> res4: Boolean = true
  x == y                                          //> res5: Boolean = false
  
  //4. Write a Scala equivalent for the Java loop
  //Click here to view code image
  //  for (int i = 10; i >= 0; i--) System.out.println(i);
  (10 to 0 by -1) foreach {println(_)}            //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
  //for (i <- 10 to 0 by -1)  println(i)
  //for (i <- 10 to (0, -1)) println(i)
  
  //5. Write a procedure countdown(n: Int) that prints the numbers from n to 0.
  def countdown(x: Int) = (x to 0 by -1) foreach {println(_)}
                                                  //> countdown: (x: Int)Unit
  countdown(5)                                    //> 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
  
  //6. Write a for loop for computing the product of the Unicode codes of all
  // letters in a string. For example, the product of the characters in "Hello" is 9415087488L.
  def unicodeSumLoop(s:String) = {
    var prod:Long = 1
    for (c <- s)
      prod *= c.toInt
    prod
  }                                               //> unicodeSumLoop: (s: String)Long
  //def unicodeSum(s:String ) = {s.foldLeft(BigInt(1)) (_*_.toInt)}
  unicodeSumLoop("Hello")                         //> res6: Long = 9415087488
  
  //7. Solve the preceding exercise without writing a loop. (Hint: Look at the StringOps Scaladoc.)
  //calling 'toInt' isn't really necessary 'h'*'e'
  def unicodeSum(s:String ) = {s.foldLeft(1l) (_*_)}
                                                  //> unicodeSum: (s: String)Long
  //def unicodeSum(s:String ) = {s.foldLeft(BigInt(1)) (_*_.toInt)}
  unicodeSum("Hello")                             //> res7: Long = 9415087488
  
  
  //8. Write a function product(s : String) that computes the product, as described in the preceding exercises.
  //see above
  
  //9. Make the function of the preceding exercise a recursive function.
  def unicodeSumRec(s:String):Long = {
    if (s.length == 1)
      s.head //or s(0) - to convert to char
    else
      s.head * unicodeSumRec(s.tail)
  }                                               //> unicodeSumRec: (s: String)Long
  
  unicodeSumRec("Hello")                          //> res8: Long = 9415087488
  
  //10. Write a function that computes xn, where n is an integer. Use the following recursive definition:
  //  • xn = y2 if n is even and positive, where y = xn / 2.
  //  • xn = x·xn – 1 if n is odd and positive.
  //  • x0 = 1.
  //  • xn = 1 / x–n if n is negative.
  // Don’t use a return statement.
  
}