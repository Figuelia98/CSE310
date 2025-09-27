//Author:Figuelia Ya'Sin 
//Project: Simple Kotlin Calculator
// Simple Calculator with history and more operations
fun main() {
    // List to store calculation history
    val history = mutableListOf<String>()
    // Main loop for menu and user interaction
    while (true) {
        printMenu() // Show menu options
        val choice = readLine() // Get user choice
        when (choice) {
            "1" -> {
                // Perform a calculation
                val num1 = getNumber("Enter first number: ")
                val operator = getOperator("Enter operator (+, -, *, /, %, ^, sqrt): ")
                val num2 = if (operator == "sqrt") null else getNumber("Enter second number: ")

                // Validate input and calculate result
                val result = if (num1 == null || (num2 == null && operator != "sqrt") || operator == null) {
                    "Invalid input."
                } else {
                    calculate(num1, num2, operator)
                }
                println("Result: $result")
                // Add calculation to history
                history.add("$num1 $operator ${num2 ?: ""} = $result")
            }
            "2" -> printHistory(history)
            "3" -> {
                println("Calculator reset.")
                history.clear() 
            }
            "4" -> printAbout()
            "0" -> {
                println("Goodbye!")
                break 
            }
            else -> println("Invalid choice.") 
        }
        println() 
    }
}

// Print the main menu
// Display the main menu to the user
fun printMenu() {
    println("==== Calculator Menu ====")
    println("1. Calculate")
    println("2. Show History")
    println("3. Reset History")
    println("4. About")
    println("0. Exit")
    print("Choose an option: ")
}

// Print About section with project details
fun printAbout() {
    println("--- About Calculator ---")
    println("Author: Ya'Sin Figuelia")
    println("Project: Simple Kotlin Calculator")
    println("Features: +, -, *, /, %, ^, sqrt, history, menu")
    println("Date: September 2025")
    println("This is a simple console calculator project for CSE310 coursework.")
}

// Print calculation history to the console
fun printHistory(history: List<String>) {
    println("--- Calculation History ---")
    if (history.isEmpty()) {
        println("No history yet.")
    } else {
        history.forEach { println(it) } 
    }
}

// Prompt the user for a number and return it as Double
fun getNumber(prompt: String): Double? {
    print(prompt)
    return readLine()?.toDoubleOrNull() 
}

// Prompt the user for an operator and return it as String
fun getOperator(prompt: String): String? {
    print(prompt)
    return readLine()
}

// Perform calculation based on operator and input values
fun calculate(num1: Double, num2: Double?, operator: String): Any {
    return when (operator) {
        "+" -> num1 + (num2 ?: 0.0)
        "-" -> num1 - (num2 ?: 0.0)
        "*" -> num1 * (num2 ?: 0.0)
        "/" -> if (num2 == 0.0) "Cannot divide by zero" else num1 / (num2 ?: 1.0)
        "%" -> if (num2 == 0.0) "Cannot modulo by zero" else num1 % (num2 ?: 1.0)
        "^" -> Math.pow(num1, num2 ?: 1.0)
        "sqrt" -> if (num1 < 0) "Cannot sqrt negative" else Math.sqrt(num1)
        else -> "Unknown operator" 
    }
}