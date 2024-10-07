package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    if (arg is String){
        if (arg == "Hello") {
            return "world"
        } else{
             return "Say what?"
        }
    } 
    else if (arg is Int){
        if (arg == 0) {
            return "zero"
        } else if (arg == 1){
            return "one"
        } else if (arg >= 2 && arg <= 10){
            return "low number"
        } //else {
            //return "a number"
       // }
    } 
    return "I don't understand"
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int){
    val debugString get()= "[Person firstName:$firstName lastName:$lastName age:$age]"

}

// write a class "Money"
class Money(val amount: Int, val currency: String){
    init{
        require(currency === "USD" || currency === "CAN" || currency === "EUR" || currency === "GBP") {
            "Currency Must be of type USD, CAN, EUR, GBP"}
        require(amount >= 0) {
            "Amount can not be negative"}
    }
    operator fun plus(other: Money): Money {
        if (this.currency != other.currency){
            var newAmount = convert(this.currency)
            return Money(this.amount + newAmount.amount, this.currency)
        }
        return Money(this.amount + other.amount, this.currency)
    }

    fun convert(newCurrency: String): Money{
        var newAmount = amount
        if(currency !== newCurrency){
            //convert to USD
            if (currency === "GBP"){
                newAmount = amount * 2
            }
            if (currency === "CAN"){
                newAmount = (amount * 4) /5
            }
            if (currency === "EUR"){
                newAmount = (amount * 2) /3
            }

            //convert to newCurrency
            if (newCurrency === "GBP"){
                newAmount = newAmount / 2
            }
            if (newCurrency === "CAN"){
                newAmount = (newAmount * 5) /4
            }
            if (newCurrency === "EUR"){
                newAmount = (newAmount * 3) /2
            }
        }
        return Money(newAmount, newCurrency)
    }
}