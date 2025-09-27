fun main() {
    println("Calculator")
    val num1 = getNumber("Enter first number: ")
    val operator = getOperator("Enter operator (+, -, *, /): ")
    val num2 = getNumber("Enter second number: ")

    if (num1 == null || num2 == null || operator == null) {
        println("Invalid input.")
        return
    }

    val result = calculate(num1, num2, operator)
    println("Result: $result")
}

fun getNumber(prompt: String): Double? {
    print(prompt)
    return readLine()?.toDoubleOrNull()
}

fun getOperator(prompt: String): String? {
    print(prompt)
    return readLine()
}

fun calculate(num1: Double, num2: Double, operator: String): Any {
    return when (operator) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "*" -> num1 * num2
        "/" -> if (num2 != 0.0) num1 / num2 else "Cannot divide by zero"
        else -> "Unknown operator"
    }
}