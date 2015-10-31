object s4ti_ch01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //1. In the Scala REPL, type 3. followed by the Tab key. What methods can be applied?
  
	//2. In the Scala REPL, compute the square root of 3, and then square that value. By how much does the result differ from 3? (Hint: The res variables are your friend.)
	val res1= scala.math.sqrt(3)              //> res1  : Double = 1.7320508075688772
	scala.math.pow(res1,3)                    //> res0: Double = 5.196152422706631
	
	//3. Are the res variables val or var?
	//val
	
	//4. Scala lets you multiply a string with a number—try out "crazy" * 3 in the REPL. What does this operation do? Where can you find it in Scaladoc?
	"crazy"*3                                 //> res1: String = crazycrazycrazy
	
	//5. What does 10 max 2 mean? In which class is the max method defined?
	10 max 2                                  //> res2: Int = 10
	//RichInt
	
	//6. Using BigInt, compute 21024.
	//BigInt(2) pow 1024
	BigInt(2).pow(1024)                       //> res3: scala.math.BigInt = 17976931348623159077293051907890247336179769789423
                                                  //| 0657273430081157732675805500963132708477322407536021120113879871393357658789
                                                  //| 7688144166224928474306394741243777678934248654852763022196012460941194530829
                                                  //| 5208500576883815068234246288147391311054082723716335051068458629823994724593
                                                  //| 8479716304835356329624224137216
	
	//7. What do you need to import so that you can get a random prime as probablePrime(100, Random),
	//without any qualifiers before probablePrime and Random?
	import util.Random
	import BigInt.probablePrime
	probablePrime(100, Random)                //> res4: scala.math.BigInt = 1052547497091781395903728074309
	
	//8. One way to create random file or directory names is to produce a random
	//BigInt and convert it to base 36, yielding a string such as
	//"qsnvbevtomcj38o06kul". Poke around Scaladoc to find a way of doing this in Scala.
	BigInt(Random.nextInt()).toString(32)     //> res5: String = 1sn04nm
	
  //9. How do you get the first character of a string in Scala? The last character?
  val s="Scala"                                   //> s  : String = Scala
  s.head                                          //> res6: Char = S
  s(0)                                            //> res7: Char = S
  s.last                                          //> res8: Char = a
  s(s.length-1)                                   //> res9: Char = a
  
  //10. What do the take, drop, takeRight, and dropRight string functions do?
  //What advantage or disadvantage do they have over using substring?
  s take 2                                        //> res10: String = Sc
  s.drop(2)                                       //> res11: String = ala
  s takeRight 2                                   //> res12: String = la
  s.dropRight(2)                                  //> res13: String = Sca

}