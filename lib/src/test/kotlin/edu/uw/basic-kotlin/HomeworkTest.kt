/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package edu.uw.basickotlin

import kotlin.test.Test
import kotlin.test.*

class HomeworkTest {
    // =================
    // whenFn tests
    @Test fun when_Hello() {
        assertEquals(whenFn("Hello"), "world")
    }
    @Test fun when_Howdy() {
        assertEquals(whenFn("Howdy"), "Say what?")
    }
    @Test fun when_Bonjour() {
        assertEquals(whenFn("Bonjour"), "Say what?")
    }
    @Test fun when_ints() {
        val when_tests = listOf(
            0 to "zero",
            1 to "one",
            5 to "low number",
            9 to "low number",
            17.0 to "I don't understand"
        )
        for ((k,v) in when_tests) {
            assertEquals(whenFn(k), v)
        }
    }

    // =================
    // add/sub/mathOp tests
    @Test fun addition_tests() {
        val add_tests = listOf(
            Pair(0, 0) to 0,
            Pair(1, 2) to 3,
            Pair(-2, 2) to 0,
            Pair(123, 456) to 579
        )
        for ( (k,v) in add_tests) {
            assertEquals(add(k.first, k.second), v)
        }
    }

    @Test fun subtraction_tests() {
        val sub_tests = listOf(
            Pair(0, 0) to 0,
            Pair(2, 1) to 1,
            Pair(-2, 2) to -4,
            Pair(456, 123) to 333
        )
        for ( (k,v) in sub_tests) {
            assertEquals(sub(k.first, k.second), v)
        }
    }

    @Test fun mathOp_tests() {
        assertEquals(4, mathOp(2, 2, { l,r -> l+r} ))
        assertEquals(4, mathOp(2, 2, ::add ))
        assertEquals(0, mathOp(2, 2, ::sub ))
        assertEquals(16, mathOp(4, 4, { l,r -> l*r} ))

        assertEquals(1, mathOp(3, -2, ::add )) //new test 1
        assertEquals(5, mathOp(3, -2, ::sub )) //new test 2


    }

    // =================
    // Person tests
    @Test fun Person_tests() {
        val p1 = Person("Ted", "Neward", 48)
        assertEquals("Ted", p1.firstName )
        assertEquals(48, p1.age)
        assertEquals("[Person firstName:Ted lastName:Neward age:48]", p1.debugString)
        
        //new test 3
        val p2 = Person("Olivia", "Sapp", 21)
        assertEquals("Olivia", p2.firstName )
        assertEquals(21, p2.age)
        assertEquals("[Person firstName:Olivia lastName:Sapp age:21]", p2.debugString)
    }
    

    // =================
    // Money tests
    @Test fun Money_sanityTests() {
        val negEx = assertFailsWith(IllegalArgumentException::class) {
            Money(-5, "USD")
        }

        val currEx = assertFailsWith(IllegalArgumentException::class) {
            Money(10, "YEN")
        }
    }
    val tenUSD = Money(10, "USD")
    val twelveUSD = Money(12, "USD")
    val fiveGBP = Money(5, "GBP")
    val fifteenEUR = Money(15, "EUR")
    val fifteenCAN = Money(15, "CAN")
    @Test fun Money_convertTests() {
        val tests = listOf(
            Pair(tenUSD, tenUSD),
            Pair(tenUSD, fiveGBP),
            Pair(tenUSD, fifteenEUR),
            Pair(twelveUSD, fifteenCAN),
            Pair(fiveGBP, tenUSD),
            Pair(fiveGBP, fifteenEUR),
            Pair(fifteenEUR, fiveGBP)
        )
        for ( (from,to) in tests) {
            assertEquals(to.amount, from.convert(to.currency).amount)
        }
    }
   @Test fun Money_addTests() {
        val tests = listOf(
            Pair(tenUSD, tenUSD) to Money(20, "USD"),
            Pair(tenUSD, fiveGBP) to Money(20, "USD"),
            Pair(fiveGBP, tenUSD) to Money(10, "GBP"),
            Pair(fiveGBP, fiveGBP) to Money(10, "GBP"), //new test 4
            Pair(fifteenEUR, fiveGBP) to Money(30, "EUR"), //new test 5 
            Pair(fiveGBP, fifteenEUR) to Money(10, "GBP"), //new test 6
        )
        for ( (pair, result) in tests) {
            assertEquals(result.amount, (pair.first + pair.second).amount)
            assertEquals(result.currency, (pair.first + pair.second).currency)
       }
    }
}
