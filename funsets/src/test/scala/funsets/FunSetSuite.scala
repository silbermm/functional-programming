package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */
  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 200))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    

  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(num) contains correct number") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton") 
      assert(contains(s2, 2), "Singleton 2")
      assert(contains(s3, 3), "Singleton 3")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)

      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains combined elements"){
    new TestSets {
      val s = intersect(x => x > 0, s1)
      val t = intersect(x => x > 0, s2)
      val u = intersect(x => x > 0, s3)
      val v = intersect(x => x < 0, s1)
      assert(contains(s, 1), "Union 1")
      assert(contains(t, 2), "Union 2")
      assert(contains(u, 3), "Union 3")
      assert(!contains(v, 1), "No Union 1")      
    }
  }

  test("diff function"){
    new TestSets {
      val s = diff(x => x > 0, s1)
      assert(contains(s, 2))
      assert(!contains(s,1))
    }
  }

  test("filter function"){
    new TestSets {
      val s = filter(s1, x => x > 0)
      assert(contains(s, 1))
      assert(!contains(s, 2))
    }
  }

  test("foreach function"){
    new TestSets {
      assert(forall(s1, x => x > 0))      
      assert(forall(s3, x => x > -2 && x < 4))
      assert(!forall( x => x > 0 && x < 10, x=>  x > 1 && x < 9))
    }
  }

  test("exists function"){
    new TestSets {
      assert(exists(s3, x => x > 2 && x < 4))
    }
  }

  test("map test"){
    new TestSets {
      val s : Set = x => x < 0
      val ma = map(s, x => x+2)
      assert(contains(ma, 1))
      assert(!contains(ma, 2))
    }
  }

}
