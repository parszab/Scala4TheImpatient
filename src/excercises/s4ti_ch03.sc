package excercises

import scala.collection.mutable.ArrayBuffer
import com.sun.org.apache.xalan.internal.xsltc.compiler.Import

object s4ti_ch03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //1. Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n (exclusive).
  
  def randomArr(n: Int) = {
    println(n)
    val a = new Array[Int](n)
    for (i <- 0 until n)
      a(i) = util.Random.nextInt(n)
    a
  }                                               //> randomArr: (n: Int)Array[Int]
  
  val randArr=randomArr(10)                       //> 10
                                                  //| randArr  : Array[Int] = Array(1, 4, 8, 9, 1, 3, 8, 2, 9, 2)
  
  //2. Write a loop that swaps adjacent elements of an array of integers. For example, Array(1, 2, 3, 4, 5)
  //  becomes Array(2, 1, 4, 3, 5).
  val a=Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)      //> a  : Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  for (i <- 0 to (a.length-2, 2)) {
    val temp = a(i)
    a(i) = a(i+1)
    a(i+1) = temp
  }
  a                                               //> res0: Array[Int] = Array(2, 1, 4, 3, 6, 5, 8, 7, 10, 9)
  
  //3. Repeat the preceding assignment, but produce a new array with the swapped values. Use for/yield.
  val b=Array(1, 2, 3, 4, 5, 6, 7, 8, 9)          //> b  : Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
  for (i <- 0 until b.length) yield { if (i%2 == 1) b(i-1) else if (i < b.length-1) b(i+1) else b(i)}
                                                  //> res1: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 1, 4, 3, 6, 5,
                                                  //|  8, 7, 9)
    
  
  
  //4. Given an array of integers, produce a new array that contains all positive values of the original array,
  //  in their original order, followed by all values that are zero or negative, in their original order.
  val tuple = Array(1, -2, 3, -4, 0, 5, -6).partition(_>0)
                                                  //> tuple  : (Array[Int], Array[Int]) = (Array(1, 3, 5),Array(-2, -4, 0, -6))
  val res = tuple._1 ++ tuple._2                  //> res  : Array[Int] = Array(1, 3, 5, -2, -4, 0, -6)
  
    
  //5. How do you compute the average of an Array[Double]?
  val da = Array(1d, 2d, 3d)                      //> da  : Array[Double] = Array(1.0, 2.0, 3.0)
  val avg = da.sum / da.length                    //> avg  : Double = 2.0
  
  //6. How do you rearrange the elements of an Array[Int] so that they appear in reverse sorted order?
  val sa = Array(1, 3, 2, 0, 4)                   //> sa  : Array[Int] = Array(1, 3, 2, 0, 4)
  // returnns a new array
  sa.sorted                                       //> res2: Array[Int] = Array(0, 1, 2, 3, 4)
  sa.sortWith(_>_)                                //> res3: Array[Int] = Array(4, 3, 2, 1, 0)
  sa.sorted.reverse                               //> res4: Array[Int] = Array(4, 3, 2, 1, 0)
  //original intact
  sa                                              //> res5: Array[Int] = Array(1, 3, 2, 0, 4)
  //to have it sorted in place
  util.Sorting.quickSort(sa)
  sa                                              //> res6: Array[Int] = Array(0, 1, 2, 3, 4)
  
  //  How do you do the same with an ArrayBuffer[Int]?
  val sab = scala.collection.mutable.ArrayBuffer(1, 3, 2, 0, 4)
                                                  //> sab  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 3, 2, 0, 
                                                  //| 4)
  sab.sortWith(_>_)                               //> res7: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(4, 3, 2, 1, 0
                                                  //| )
  //original still intact
  sab                                             //> res8: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 3, 2, 0, 4
                                                  //| )
  //won't compile
  //util.Sorting.quickSort(sab)
  
  //7. Write a code snippet that produces all values from an array with duplicates removed. (Hint: Look at Scaladoc.)
  Array(1, 1, 2, 3, 3, 4, 1, 4, 0).distinct       //> res9: Array[Int] = Array(1, 2, 3, 4, 0)
  
  //8. Rewrite the example at the end of Section 3.4, “Transforming Arrays,” on page 32.
  //  Collect indexes of the negative elements, reverse the sequence, drop the last index,
  //  and call a.remove(i) for each index. Compare the efficiency of this approach with the two approaches in Section 3.4.
  val aneg = ArrayBuffer(-1, 1, -2, 2, -3, 3, 0)  //> aneg  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(-1, 1, -2, 
                                                  //| 2, -3, 3, 0)
  val negIdx = for (i <- 0 until aneg.length if aneg(i) <0) yield i
                                                  //> negIdx  : scala.collection.immutable.IndexedSeq[Int] = Vector(0, 2, 4)
  negIdx.reverse.dropRight(1).foreach { x => aneg.remove(x) }
  aneg                                            //> res10: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(-1, 1, 2, 3,
                                                  //|  0)
  
  //9. Make a collection of all time zones returned by java.util.
  //  TimeZone.getAvailableIDs that are in America. Strip off the "America/" prefix and sort the result.
  java.util.TimeZone.getAvailableIDs filter (_.startsWith("America")) map (_.stripPrefix("America/")) sorted
                                                  //> res11: Array[String] = Array(Adak, Anchorage, Anguilla, Antigua, Araguaina,
                                                  //|  Argentina/Buenos_Aires, Argentina/Catamarca, Argentina/ComodRivadavia, Arg
                                                  //| entina/Cordoba, Argentina/Jujuy, Argentina/La_Rioja, Argentina/Mendoza, Arg
                                                  //| entina/Rio_Gallegos, Argentina/Salta, Argentina/San_Juan, Argentina/San_Lui
                                                  //| s, Argentina/Tucuman, Argentina/Ushuaia, Aruba, Asuncion, Atikokan, Atka, B
                                                  //| ahia, Bahia_Banderas, Barbados, Belem, Belize, Blanc-Sablon, Boa_Vista, Bog
                                                  //| ota, Boise, Buenos_Aires, Cambridge_Bay, Campo_Grande, Cancun, Caracas, Cat
                                                  //| amarca, Cayenne, Cayman, Chicago, Chihuahua, Coral_Harbour, Cordoba, Costa_
                                                  //| Rica, Creston, Cuiaba, Curacao, Danmarkshavn, Dawson, Dawson_Creek, Denver,
                                                  //|  Detroit, Dominica, Edmonton, Eirunepe, El_Salvador, Ensenada, Fort_Wayne, 
                                                  //| Fortaleza, Glace_Bay, Godthab, Goose_Bay, Grand_Turk, Grenada, Guadeloupe, 
                                                  //| Guatemala, Guayaquil, Guyana, Halifax, Havana, Hermosillo, Indiana/Indianap
                                                  //| olis, Indiana/Knox, Ind
                                                  //| Output exceeds cutoff limit.
  
  //10. Import java.awt.datatransfer._ and make an object of type SystemFlavorMap with the call
  //  val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
  //  Then call the getNativesForFlavor method with parameter DataFlavor.imageFlavor and get the
  //    return value as a Scala buffer. (Why this obscure class?
  //    It’s hard to find uses of java.util.List in the standard Java library.)
  import java.awt.datatransfer._
  val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap];
                                                  //> flavors  : java.awt.datatransfer.SystemFlavorMap = java.awt.datatransfer.Sy
                                                  //| stemFlavorMap@1efed156
  import scala.collection.JavaConversions.asScalaBuffer
  import scala.collection.mutable.Buffer
  val natives: Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
                                                  //> natives  : scala.collection.mutable.Buffer[String] = Buffer(image/png, imag
                                                  //| e/x-png, image/jpeg, image/gif, PNG, JFIF)
  
  
}