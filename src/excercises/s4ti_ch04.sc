package excercises

object s4ti_ch04 {
  // 1. Set up a map of prices for a number of gizmos that you covet. Then produce a
  // second map with the same keys and the prices at a 10 percent discount.
  val gizmoMap = Map("one" -> 10, "two" -> 2, "three" -> 3)
                                                  //> gizmoMap  : scala.collection.immutable.Map[String,Int] = Map(one -> 10, two 
                                                  //| -> 2, three -> 3)
  val discountMap = for ((k,v) <- gizmoMap) yield (k,v*.9)
                                                  //> discountMap  : scala.collection.immutable.Map[String,Double] = Map(one -> 9.
                                                  //| 0, two -> 1.8, three -> 2.7)
  
  
  // 2. Write a program that reads words from a file. Use a mutable map to count how often each word appears.
  // To read the words, simply use a java.util.Scanner:
  //   val in = new java.util.Scanner(new java.io.File("myfile.txt"))
  //val fn = "/usr/share/doc/slang/v2/changes.txt"
  val fn = "/home/szabolcs/words.txt"             //> fn  : String = /home/szabolcs/words.txt
  val counts = scala.collection.mutable.Map[String,Int]()
                                                  //> counts  : scala.collection.mutable.Map[String,Int] = Map()
  val in = new java.util.Scanner(new java.io.File(fn))
                                                  //> in  : java.util.Scanner = java.util.Scanner[delimiters=\p{javaWhitespace}+][
                                                  //| position=0][match valid=false][need input=false][source closed=false][skippe
                                                  //| d=false][group separator=\ ][decimal separator=\,][positive prefix=][negati
                                                  //| ve prefix=\Q-\E][positive suffix=][negative suffix=][NaN string=\Q�\E][inf
                                                  //| inity string=\Q∞\E]
  //   while (in.hasNext()) process in.next()
  while (in.hasNext()) {
    val word = in.next();
    counts += (word -> (counts.getOrElse(word, 0)+1))
  }
  in.close()
  counts                                          //> res0: scala.collection.mutable.Map[String,Int] = Map(hár -> 3, egy -> 1, k�
                                                  //| �t -> 2, négy -> 4)
  //   Or look at Chapter 9 for a Scalaesque way.
  //http://stackoverflow.com/questions/15487413/scala-beginners-simplest-way-to-count-words-in-file
  //flatMap means the file doesn't have to be in memory
  //foldLeft is also more memory efficient than groupby
  import scala.io.Source
  val counts2 = Source.fromFile(fn, "UTF-8").getLines.flatMap(_.split("\\s+")).foldLeft(Map.empty[String,Int]) {
    (countMap, word) => countMap + (word -> (countMap.getOrElse(word, 0) +1))
  }                                               //> counts2  : scala.collection.immutable.Map[String,Int] = Map(egy -> 1, két 
                                                  //| -> 2, hár -> 3, négy -> 4)
  
  val counts3 = Source.fromFile(fn, "UTF-8").getLines.flatMap(_.split("\\s+")).toList.groupBy({ w=>w }) mapValues {_.size}
                                                  //> counts3  : scala.collection.immutable.Map[String,Int] = Map(hár -> 3, egy 
                                                  //| -> 1, két -> 2, négy -> 4)
  
  //   At the end, print out all words and their counts.
  for ((k,v) <- counts2) println (k + "->" + v)   //> egy->1
                                                  //| két->2
                                                  //| hár->3
                                                  //| négy->4
  
  // 3. Repeat the preceding exercise with an immutable map.
  
  //Mind the var(iable)!
  var immutableCounts = Map.empty[String,Int]     //> immutableCounts  : scala.collection.immutable.Map[String,Int] = Map()
  val in2 = new java.util.Scanner(new java.io.File(fn))
                                                  //> in2  : java.util.Scanner = java.util.Scanner[delimiters=\p{javaWhitespace}+
                                                  //| ][position=0][match valid=false][need input=false][source closed=false][ski
                                                  //| pped=false][group separator=\ ][decimal separator=\,][positive prefix=][ne
                                                  //| gative prefix=\Q-\E][positive suffix=][negative suffix=][NaN string=\Q�\E
                                                  //| ][infinity string=\Q∞\E]
  while (in2.hasNext()) {
    val word = in2.next();
    immutableCounts += (word -> (immutableCounts.getOrElse(word, 0)+1))
  }
  in2.close()
  
  immutableCounts                                 //> res1: scala.collection.immutable.Map[String,Int] = Map(egy -> 1, két -> 2,
                                                  //|  hár -> 3, négy -> 4)
  
   
  // 4. Repeat the preceding exercise with a sorted map, so that the words are printed in sorted order.
  
  //Let's do this with the functional approach
  Source.fromFile(fn, "UTF-8").getLines.flatMap(_.split("\\s+")).foldLeft(scala.collection.immutable.SortedMap[String,Int]()) {
    (countMap, word) => countMap + (word -> (countMap.getOrElse(word, 0) +1))
  }                                               //> res2: scala.collection.immutable.SortedMap[String,Int] = Map(egy -> 1, hár
                                                  //|  -> 3, két -> 2, négy -> 4)
  
  
  // 5. Repeat the preceding exercise with a java.util.TreeMap that you adapt to the Scala API.
  // 6. Define a linked hash map that maps "Monday" to java.util.Calendar.MONDAY, and similarly
  // for the other weekdays. Demonstrate that the elements are visited in insertion order.
  // 7. Print a table of all Java properties, like this:
  //   Click here to view code image
  //   java.runtime.name             | Java(TM) SE Runtime Environment
  //   sun.boot.library.path         | /home/apps/jdk1.6.0_21/jre/lib/i386
  //   java.vm.version               | 17.0-b16
  //   java.vm.vendor                | Sun Microsystems Inc.
  //   java.vendor.url               | http://java.sun.com/
  //   path.separator                | :
  //   java.vm.name                  | Java HotSpot(TM) Server VM
  //   You need to find the length of the longest key before you can print the table.
  // 8. Write a function minmax(values: Array[Int]) that returns a pair containing the
  // smallest and largest values in the array.
  // 9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple
  // containing the counts of values less than v, equal to v, and greater than v.
  // 10. What happens when you zip together two strings, such as "Hello".zip("World")?
  // Come up with a plausible use case.
  }