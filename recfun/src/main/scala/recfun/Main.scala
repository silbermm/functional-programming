  package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = (c, r) match { 
    case (c,r) if c>r || c < 0 => 0
    case (_,0) => 1
    case (_,_) => pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {    
        
    def innerBalance(chList: List[Char], index : Int) : Boolean = {
      
      def checkList(m: List[Char]) : Boolean = m match {
        case Nil => index == 0
        case _ :: tail => checkChar(m.head, m.tail)      
      }
      
      def checkChar(ch : Char, ls: List[Char]) : Boolean = (ch, ls) match {
        case ('(', _) => innerBalance(ls, index + 1)
        case (')', _) => if(index > 0) innerBalance(ls, index -1) else false
        case (_,_) => innerBalance(ls, index)
      }      
      checkList(chList)           
    }
    if(chars.indexOf('(') == -1) false else innerBalance(chars, 0);
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
    def change(m: Int, index: Int) : Int = (m, index) match {
      case(0, _) => 1
      case(m, index) if m < 0 => 0
      case(m, index) if index <= 0 && m >= 1 => 0
      case (_,_) => change(m, index-1) + change(m-coins(index-1),index)
    }
    change(money, coins.length)    
  }
}
