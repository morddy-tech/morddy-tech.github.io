"""Basic Calculator in Python Language"""
# Addition
def add(num1, num2):
    return num1 + num2

# Subtraction
def subtract(num1, num2):
    return num1 - num2

# Multiplication
def multiply(num1, num2):
    return num1 * num2

# Division
def divide(num1, num2):
    return num1 / num2

"""Section One Test

print(add(1, 1))
print(subtract(1, 1))
print(multiply(1, 1))
print(divide(1, 1))
    """

"""Input/Operation Section"""
num1 = input("Enter your first value: ")
num2 = input("Enter your last value: ")

operation = input("Do you want to Add, Subtract, Multiply, or Divide your values? ")

"""Operation Mode"""
if (operation == 'add'):
      print(add(num1 + num2))
else:
    print("Error generated Response")
