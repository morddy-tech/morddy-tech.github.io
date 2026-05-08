"""Basic Calculator in Python Language - Professional & Secure Version"""

def add(num1: float, num2: float) -> float:
    """Return the sum of two numbers."""
    return num1 + num2

def subtract(num1: float, num2: float) -> float:
    """Return the difference of two numbers."""
    return num1 - num2

def multiply(num1: float, num2: float) -> float:
    """Return the product of two numbers."""
    return num1 * num2

def divide(num1: float, num2: float) -> float:
    """Return the quotient of two numbers. Raises ValueError if num2 is zero."""
    if num2 == 0:
        raise ValueError("Division by zero is not allowed.")
    return num1 / num2

def get_number(prompt: str) -> float:
    """Prompt the user for a number, repeating until a valid float is entered."""
    while True:
        try:
            return float(input(prompt))
        except ValueError:
            print("Invalid input. Please enter a numeric value.")

def main() -> None:
    """Main calculator loop."""
    print("Welcome to the Basic Calculator")
    
    # Get validated numeric inputs
    num1 = get_number("Enter your first value: ")
    num2 = get_number("Enter your second value: ")
    
    # Operation choice with case‑insensitive matching
    operation = input("Do you want to Add, Subtract, Multiply, or Divide your values? ").strip().lower()
    
    # Map operation names to functions and error messages
    operations = {
        'add': (add, "Sum: {}"),
        'subtract': (subtract, "Difference: {}"),
        'multiply': (multiply, "Product: {}"),
        'divide': (divide, "Quotient: {}")
    }
    
    if operation in operations:
        func, output_template = operations[operation]
        try:
            result = func(num1, num2)
            print(output_template.format(result))
        except ValueError as e:
            print(f"Error: {e}")
    else:
        print("Error: Unsupported operation. Please choose 'add', 'subtract', 'multiply', or 'divide'.")

if __name__ == "__main__":
    main()