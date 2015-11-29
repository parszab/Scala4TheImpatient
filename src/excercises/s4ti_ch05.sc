package excercises

import scala.beans.BeanProperty

object s4ti_ch05 {
  //1. Improve the Counter class in Section 5.1, “Simple Classes and Parameterless Methods,”
  // on page 49 so that it doesn’t turn negative at Int.MaxValue.
  class Counter {
    private var value:BigInt = Int.MaxValue // You must initialize the field
    def increment() { value += 1 } // Methods are public by default
    def current() = value
  }
  
  val myCounter = new Counter // Or new Counter() //> myCounter  : excercises.s4ti_ch05.Counter = excercises.s4ti_ch05$Counter@1a9
                                                  //| 3a7ca
  println(myCounter.current)                      //> 2147483647
  myCounter.increment()
  println(myCounter.current)                      //> 2147483648
    
  
  //2. Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.
  class BankAccount {
    private var _balance = 0d
    
    def this(b: Double) {this(); _balance = b}
  
    def deposit(d: Double) {_balance += d}
    def whitdraw(w: Double) {_balance -= w} //allow negative
    def balance = _balance
        
    override def toString():String = _balance.toString
    
  }
  
  val ba = new BankAccount(100.10)                //> ba  : excercises.s4ti_ch05.BankAccount = 100.1
  ba.deposit(20.20)
  ba                                              //> res0: excercises.s4ti_ch05.BankAccount = 120.3
  ba.balance                                      //> res1: Double = 120.3
  ba.whitdraw(20.30)
  ba.balance                                      //> res2: Double = 100.0
  
  
  //3. Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean
  // that checks whether this time comes before the other. A Time object should be constructed
  // as new Time(hrs, min), where hrs is in military time format (between 0 and 23).
  class Time(val hrs: Int, val min: Int) {
    require(hrs>=0 && hrs<=24)
    require(min>=0 && min<=60)
    
    def before(other: Time) = (hrs<other.hrs) || (min<other.min)
    
    override def toString() = s"$hrs:$min"
  }
  
  val t1 = new Time(3, 59)                        //> t1  : excercises.s4ti_ch05.Time = 3:59
  t1.hrs                                          //> res3: Int = 3
  t1.min                                          //> res4: Int = 59
  val t2 = new Time(13, 59)                       //> t2  : excercises.s4ti_ch05.Time = 13:59
  t1.before(t2)                                   //> res5: Boolean = true
   
  //4. Reimplement the Time class from the preceding exercise so that the internal representation
  // is the number of minutes since midnight (between 0 and 24 × 60 – 1). Do not change the public interface.
  // That is, client code should be unaffected by your change.
  24*60-1                                         //> res6: Int = 1439
  
  class Time2(_hrs: Int, _min: Int) {
    require(_hrs>=0 && _hrs<=24)
    require(_min>=0 && _min<=60)
    private val minstotal = _hrs*60+_min
    
    def hrs:Int = minstotal/60
    def min = minstotal%60
    def before(other: Time2) = minstotal<other.minstotal
    
    override def toString() = s"${minstotal/60}:${minstotal%60}"
  }
  
  val t2_1 = new Time2(3, 59)                     //> t2_1  : excercises.s4ti_ch05.Time2 = 3:59
  t2_1.hrs                                        //> res7: Int = 3
  t2_1.min                                        //> res8: Int = 59
  val t2_2 = new Time2(13, 59)                    //> t2_2  : excercises.s4ti_ch05.Time2 = 13:59
  t2_1.before(t2_2)                               //> res9: Boolean = true
  
  //5. Make a class Student with read-write JavaBeans properties name (of type String) and id (of type Long).
  // What methods are generated? (Use javap to check.) Can you call the JavaBeans getters and setters in Scala Should you?
  
  class Student(@BeanProperty var name: String, @BeanProperty var id: Int) {
    override def toString = s"$name ($id)"
  }
  
  val s = new Student("John Doe", 12)             //> s  : excercises.s4ti_ch05.Student = John Doe (12)
  // you can - probably shouldn't
  s.name == s.getName                             //> res10: Boolean = true
  s.id == s.getId                                 //> res11: Boolean = true
  
  //6. In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,” on page 49,
  // provide a primary constructor that turns negative ages to 0.
  
  class Person(var age: Int = 0) {
    if (age < 0) age = 0
  
    override def toString = s"age: $age"
  }
  
  val p = new Person(10)                          //> p  : excercises.s4ti_ch05.Person = age: 10
  
  //7. Write a class Person with a primary constructor that accepts a string containing a first name,
  // a space, and a last name, such as new Person("Fred Smith"). Supply read-only properties firstName and lastName.
  // Should the primary constructor parameter be a var, a val, or a plain parameter? Why?
  
  // the parameter will be a plain parameter, as we don't need either a getter or setter for it
  class Person2(name: String) {
    val Name = """([^ ]+) ([^ ]+)""".r
    val Name(firstName, lastName) = name
    
    override def toString = s"$firstName $lastName"
  }
  
  val p2 = new Person2("John Doe")                //> p2  : excercises.s4ti_ch05.Person2 = John Doe
  p2.firstName                                    //> res12: String = John
  p2.lastName                                     //> res13: String = Doe
  
  
  //8. Make a class Car with read-only properties for manufacturer, model name, and model year,
  // and a read-write property for the license plate. Supply four constructors. All require the manufacturer
  // and model name. Optionally, model year and license plate can also be specified in the constructor.
  // If not, the model year is set to -1 and the license plate to the empty string.
  // Which constructor are you choosing as the primary constructor? Why?
  
  class Car(val manufacturer: String, val modelName: String, val modelYear: Int, var licensePlate: String) {
    def this(manufacturer: String, modelName: String, modelYear: Int) = {this(manufacturer, modelName, modelYear, "")}
    def this(manufacturer: String, modelName: String, licensePlate: String) = {this(manufacturer, modelName, -1, licensePlate)}
    def this(manufacturer: String, modelName: String) = {this(manufacturer, modelName, -1, "")}

    override def toString = s"$manufacturer $modelName, $modelYear. $licensePlate"
    
  }
  
  new Car("Honda", "Civic", 2011, "ABC123")       //> res14: excercises.s4ti_ch05.Car = Honda Civic, 2011. ABC123
  new Car("Honda", "Civic", 2011)                 //> res15: excercises.s4ti_ch05.Car = Honda Civic, 2011. 
  new Car("Honda", "Civic", "ABC123")             //> res16: excercises.s4ti_ch05.Car = Honda Civic, -1. ABC123
  new Car("Honda", "Civic")                       //> res17: excercises.s4ti_ch05.Car = Honda Civic, -1. 
  
  //can achieve this with default parameters as well
  class Car2(val manufacturer: String, val modelName: String, val modelYear: Int = -1, var licensePlate: String = "") {
    override def toString = s"$manufacturer $modelName, $modelYear. $licensePlate"
  }
  
  new Car2("Honda", "Civic", 2011, "ABC123")      //> res18: excercises.s4ti_ch05.Car2 = Honda Civic, 2011. ABC123
  new Car2("Honda", "Civic", 2011)                //> res19: excercises.s4ti_ch05.Car2 = Honda Civic, 2011. 
  new Car2("Honda", "Civic", licensePlate = "ABC123")
                                                  //> res20: excercises.s4ti_ch05.Car2 = Honda Civic, -1. ABC123
  new Car2("Honda", "Civic")                      //> res21: excercises.s4ti_ch05.Car2 = Honda Civic, -1. 
  
  
  //9. Reimplement the class of the preceding exercise in Java, C#, or C++ (your choice).
  // How much shorter is the Scala class?
  
  // it's much more verbose in other languages, yadi yadi yada...
  
  //10. Consider the class
  //  class Employee(val name: String, var salary: Double) {
  //    def this() { this("John Q. Public", 0.0) }
  //  }
  // Rewrite it to use explicit fields and a default primary constructor. Which form do you prefer? Why?
  
  //yeah, I don't like this one, it's cleaner to define the widest possible field list in the main constructor
  class Employee() {
      private var _name = "John Q. Public"
      var salary = 0d
      def this(name: String, salary: Double) {
        this()
        _name = name
        this.salary = salary
      }
      
      def name = _name
  }
  
}