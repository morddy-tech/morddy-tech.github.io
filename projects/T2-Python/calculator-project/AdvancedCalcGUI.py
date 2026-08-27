"""
Advanced Scientific Calculator with GUI
Features: Basic arithmetic, scientific functions, memory, history, constants
No eval() used, all operations are safe function calls.
"""

import tkinter as tk
from tkinter import messagebox
import math
from typing import Optional, List, Tuple

# ========== Core Math Functions (Same as before, safe) ==========
def add(a: float, b: float) -> float:
    return a + b

def subtract(a: float, b: float) -> float:
    return a - b

def multiply(a: float, b: float) -> float:
    return a * b

def divide(a: float, b: float) -> float:
    if b == 0:
        raise ValueError("Division by zero")
    return a / b

def power(a: float, b: float) -> float:
    return a ** b

def modulo(a: float, b: float) -> float:
    if b == 0:
        raise ValueError("Modulo by zero")
    return a % b

def sqrt(x: float) -> float:
    if x < 0:
        raise ValueError("Square root of negative number")
    return math.sqrt(x)

def cbrt(x: float) -> float:
    # cube root works for negatives
    return math.copysign(abs(x) ** (1/3), x)

def factorial(x: float) -> int:
    if x < 0 or not x.is_integer():
        raise ValueError("Factorial requires non‑negative integer")
    return math.factorial(int(x))

def reciprocal(x: float) -> float:
    if x == 0:
        raise ValueError("Reciprocal of zero")
    return 1 / x

def sin_deg(x: float) -> float:
    return math.sin(math.radians(x))

def cos_deg(x: float) -> float:
    return math.cos(math.radians(x))

def tan_deg(x: float) -> float:
    return math.tan(math.radians(x))

def log10(x: float) -> float:
    if x <= 0:
        raise ValueError("log10 only for positive numbers")
    return math.log10(x)

def ln(x: float) -> float:
    if x <= 0:
        raise ValueError("ln only for positive numbers")
    return math.log(x)

def log2(x: float) -> float:
    if x <= 0:
        raise ValueError("log2 only for positive numbers")
    return math.log2(x)

def exp10(x: float) -> float:
    return 10 ** x

def exp_e(x: float) -> float:
    return math.exp(x)

def square(x: float) -> float:
    return x * x

def cube(x: float) -> float:
    return x ** 3

# ========== Calculator Backend (State Machine) ==========
class CalculatorEngine:
    """Manages operations, memory, history, and display logic."""
    
    def __init__(self):
        self.current = 0.0       # value currently displayed
        self.previous = 0.0      # stored value for binary ops
        self.operation = None     # function (binary) to apply
        self.waiting_for_operand = True  # start new number?
        self.last_result = 0.0
        self.memory: Optional[float] = None
        self.history: List[str] = []   # max 20 entries
    
    def _add_to_history(self, entry: str) -> None:
        self.history.append(entry)
        if len(self.history) > 20:
            self.history.pop(0)
    
    def set_display(self, value: float) -> str:
        """Format number for display (avoid scientific notation for common cases)."""
        if value == int(value):
            return str(int(value))
        # limit decimal places for readability
        return f"{value:.10g}".rstrip('0').rstrip('.')
    
    def number_pressed(self, num: str, current_display: str) -> str:
        """Handle digit/point input. Returns new display string."""
        if self.waiting_for_operand:
            current_display = "0"
            self.waiting_for_operand = False
        
        if num == '.':
            if '.' in current_display:
                return current_display
            return current_display + '.'
        else:
            if current_display == "0" and num != '.':
                return num
            return current_display + num
    
    def unary_operation(self, func, current_display: str) -> str:
        """Apply a unary function (sin, sqrt, etc.) to the current value."""
        try:
            x = float(current_display)
            result = func(x)
            # Save operation to history
            self._add_to_history(f"{func.__name__}({self.set_display(x)}) = {self.set_display(result)}")
            self.current = result
            self.last_result = result
            self.waiting_for_operand = True
            return self.set_display(result)
        except Exception as e:
            messagebox.showerror("Math Error", str(e))
            return current_display  # restore previous
    
    def binary_operation(self, op_func, current_display: str) -> None:
        """Store current value and operation for later evaluation."""
        try:
            self.previous = float(current_display)
            self.operation = op_func
            self.waiting_for_operand = True
        except ValueError:
            pass  # invalid number, ignore
    
    def compute(self, current_display: str) -> str:
        """Execute binary operation (e.g., add, multiply)."""
        if self.operation is None:
            return current_display
        
        try:
            second = float(current_display)
            first = self.previous
            result = self.operation(first, second)
            # Store in history
            op_name = self.operation.__name__
            history_entry = f"{self.set_display(first)} {op_name} {self.set_display(second)} = {self.set_display(result)}"
            self._add_to_history(history_entry)
            self.current = result
            self.last_result = result
            self.operation = None
            self.waiting_for_operand = True
            return self.set_display(result)
        except Exception as e:
            messagebox.showerror("Math Error", str(e))
            return current_display
    
    def clear_all(self) -> str:
        self.current = 0.0
        self.previous = 0.0
        self.operation = None
        self.waiting_for_operand = True
        return "0"
    
    def clear_entry(self, current_display: str) -> str:
        return "0"
    
    def backspace(self, current_display: str) -> str:
        if len(current_display) > 1:
            if current_display == "0":
                return "0"
            return current_display[:-1]
        return "0"
    
    def negate(self, current_display: str) -> str:
        try:
            val = float(current_display)
            val = -val
            return self.set_display(val)
        except:
            return current_display
    
    def percent(self, current_display: str) -> str:
        """Convert current display to percentage (divide by 100)."""
        try:
            val = float(current_display) / 100
            return self.set_display(val)
        except:
            return current_display
    
    def memory_add(self, current_display: str) -> None:
        try:
            val = float(current_display)
            if self.memory is None:
                self.memory = val
            else:
                self.memory += val
        except:
            pass
    
    def memory_sub(self, current_display: str) -> None:
        try:
            val = float(current_display)
            if self.memory is None:
                self.memory = -val
            else:
                self.memory -= val
        except:
            pass
    
    def memory_recall(self) -> Optional[str]:
        if self.memory is not None:
            self.waiting_for_operand = True
            return self.set_display(self.memory)
        return None
    
    def memory_clear(self) -> None:
        self.memory = None
    
    def set_answer(self) -> str:
        """Recall last result (ANS)."""
        self.waiting_for_operand = True
        return self.set_display(self.last_result)
    
    def set_constant(self, value: float) -> str:
        self.waiting_for_operand = True
        return self.set_display(value)

# ========== GUI Class ==========
class CalculatorGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Advanced Scientific Calculator")
        self.root.resizable(False, False)
        self.engine = CalculatorEngine()
        
        # Display StringVar
        self.display_var = tk.StringVar()
        self.display_var.set("0")
        
        # Create widgets
        self.create_display()
        self.create_buttons()
        self.create_history_panel()
        
        # Bind keyboard
        self.bind_keys()
    
    def create_display(self):
        """Entry widget for calculator display."""
        frame = tk.Frame(self.root)
        frame.pack(pady=10, padx=10)
        display = tk.Entry(frame, textvariable=self.display_var, font=("Arial", 24),
                           bd=10, relief=tk.RIDGE, justify="right", state="readonly")
        display.pack(fill=tk.BOTH, ipady=10)
    
    def create_buttons(self):
        """Create all calculator buttons."""
        button_frame = tk.Frame(self.root)
        button_frame.pack(pady=5, padx=10)
        
        # Button definitions: (text, row, column, columnspan, command (with args if needed))
        # We'll use a grid layout.
        buttons = [
            # Row 0: Memory & Clear
            ("MC", 0, 0, self.memory_clear), ("MR", 0, 1, self.memory_recall),
            ("M+", 0, 2, self.memory_add), ("M-", 0, 3, self.memory_sub),
            ("C", 0, 4, self.clear_all), ("CE", 0, 5, self.clear_entry),
            # Row 1: Scientific functions
            ("x²", 1, 0, self.x_squared), ("x³", 1, 1, self.x_cubed),
            ("√x", 1, 2, self.sqrt), ("∛x", 1, 3, self.cbrt),
            ("1/x", 1, 4, self.reciprocal), ("±", 1, 5, self.negate),
            # Row 2: More scientific
            ("sin", 2, 0, self.sin), ("cos", 2, 1, self.cos), ("tan", 2, 2, self.tan),
            ("log", 2, 3, self.log10), ("ln", 2, 4, self.ln), ("log₂", 2, 5, self.log2),
            # Row 3: Constants and powers
            ("π", 3, 0, self.pi), ("e", 3, 1, self.e_const),
            ("xʸ", 3, 2, self.power), ("10ˣ", 3, 3, self.exp10), ("eˣ", 3, 4, self.exp_e),
            ("x!", 3, 5, self.factorial),
            # Row 4: Digits and basic ops
            ("7", 4, 0, lambda: self.number("7")), ("8", 4, 1, lambda: self.number("8")),
            ("9", 4, 2, lambda: self.number("9")), ("/", 4, 3, self.divide),
            ("%", 4, 4, self.percent), ("Back", 4, 5, self.backspace),
            # Row 5
            ("4", 5, 0, lambda: self.number("4")), ("5", 5, 1, lambda: self.number("5")),
            ("6", 5, 2, lambda: self.number("6")), ("*", 5, 3, self.multiply),
            ("(", 5, 4, None), (")", 5, 5, None),   # placeholder, not implemented
            # Row 6
            ("1", 6, 0, lambda: self.number("1")), ("2", 6, 1, lambda: self.number("2")),
            ("3", 6, 2, lambda: self.number("3")), ("-", 6, 3, self.subtract),
            ("ANS", 6, 4, self.answer), ("=", 6, 5, self.equals),
            # Row 7
            ("0", 7, 0, lambda: self.number("0")), (".", 7, 1, lambda: self.number(".")),
            ("+", 7, 2, self.add), ("", 7, 3, None), ("", 7, 4, None), ("", 7, 5, None)
        ]
        
        # Create buttons with uniform styling
        for (text, row, col, command) in buttons:
            if text == "" or command is None:
                # placeholder, skip or make disabled
                btn = tk.Button(button_frame, text=text, state="disabled", width=6, height=2)
            else:
                btn = tk.Button(button_frame, text=text, width=6, height=2,
                                command=command, font=("Arial", 12))
            btn.grid(row=row, column=col, padx=2, pady=2, sticky="nsew")
        
        # Configure grid weights
        for i in range(8):
            button_frame.grid_rowconfigure(i, weight=1)
        for i in range(6):
            button_frame.grid_columnconfigure(i, weight=1)
    
    def create_history_panel(self):
        """Listbox to show calculation history."""
        history_frame = tk.Frame(self.root)
        history_frame.pack(pady=5, padx=10, fill=tk.BOTH, expand=True)
        
        tk.Label(history_frame, text="History (last 20)", font=("Arial", 10)).pack(anchor="w")
        self.history_listbox = tk.Listbox(history_frame, height=6, font=("Consolas", 10))
        self.history_listbox.pack(fill=tk.BOTH, expand=True)
        
        # Refresh history periodically
        self.refresh_history()
    
    def refresh_history(self):
        """Update history listbox from engine."""
        self.history_listbox.delete(0, tk.END)
        for entry in self.engine.history:
            self.history_listbox.insert(tk.END, entry)
        self.root.after(500, self.refresh_history)  # auto-refresh every 0.5s
    
    # ========== Button Command Methods ==========
    def number(self, digit):
        new_display = self.engine.number_pressed(digit, self.display_var.get())
        self.display_var.set(new_display)
    
    def clear_all(self):
        self.display_var.set(self.engine.clear_all())
    
    def clear_entry(self):
        self.display_var.set(self.engine.clear_entry(self.display_var.get()))
    
    def backspace(self):
        self.display_var.set(self.engine.backspace(self.display_var.get()))
    
    def negate(self):
        self.display_var.set(self.engine.negate(self.display_var.get()))
    
    def percent(self):
        self.display_var.set(self.engine.percent(self.display_var.get()))
    
    # Unary functions
    def sqrt(self):
        self.display_var.set(self.engine.unary_operation(sqrt, self.display_var.get()))
    
    def cbrt(self):
        self.display_var.set(self.engine.unary_operation(cbrt, self.display_var.get()))
    
    def x_squared(self):
        self.display_var.set(self.engine.unary_operation(square, self.display_var.get()))
    
    def x_cubed(self):
        self.display_var.set(self.engine.unary_operation(cube, self.display_var.get()))
    
    def reciprocal(self):
        self.display_var.set(self.engine.unary_operation(reciprocal, self.display_var.get()))
    
    def sin(self):
        self.display_var.set(self.engine.unary_operation(sin_deg, self.display_var.get()))
    
    def cos(self):
        self.display_var.set(self.engine.unary_operation(cos_deg, self.display_var.get()))
    
    def tan(self):
        self.display_var.set(self.engine.unary_operation(tan_deg, self.display_var.get()))
    
    def log10(self):
        self.display_var.set(self.engine.unary_operation(log10, self.display_var.get()))
    
    def ln(self):
        self.display_var.set(self.engine.unary_operation(ln, self.display_var.get()))
    
    def log2(self):
        self.display_var.set(self.engine.unary_operation(log2, self.display_var.get()))
    
    def exp10(self):
        self.display_var.set(self.engine.unary_operation(exp10, self.display_var.get()))
    
    def exp_e(self):
        self.display_var.set(self.engine.unary_operation(exp_e, self.display_var.get()))
    
    def factorial(self):
        self.display_var.set(self.engine.unary_operation(factorial, self.display_var.get()))
    
    # Binary operations
    def add(self):
        self.engine.binary_operation(add, self.display_var.get())
    
    def subtract(self):
        self.engine.binary_operation(subtract, self.display_var.get())
    
    def multiply(self):
        self.engine.binary_operation(multiply, self.display_var.get())
    
    def divide(self):
        self.engine.binary_operation(divide, self.display_var.get())
    
    def power(self):
        self.engine.binary_operation(power, self.display_var.get())
    
    def equals(self):
        self.display_var.set(self.engine.compute(self.display_var.get()))
    
    # Memory
    def memory_add(self):
        self.engine.memory_add(self.display_var.get())
    
    def memory_sub(self):
        self.engine.memory_sub(self.display_var.get())
    
    def memory_recall(self):
        val = self.engine.memory_recall()
        if val is not None:
            self.display_var.set(val)
    
    def memory_clear(self):
        self.engine.memory_clear()
    
    def answer(self):
        self.display_var.set(self.engine.set_answer())
    
    def pi(self):
        self.display_var.set(self.engine.set_constant(math.pi))
    
    def e_const(self):
        self.display_var.set(self.engine.set_constant(math.e))
    
    # Keyboard binding
    def bind_keys(self):
        self.root.bind('<Key>', self.key_pressed)
    
    def key_pressed(self, event):
        key = event.char
        if key.isdigit() or key == '.':
            self.number(key)
        elif key == '+':
            self.add()
        elif key == '-':
            self.subtract()
        elif key == '*':
            self.multiply()
        elif key == '/':
            self.divide()
        elif key == '=' or key == '\r':  # Enter key
            self.equals()
        elif key == 'c' or key == 'C':
            self.clear_all()
        elif key == '\b':  # Backspace
            self.backspace()
        elif key == '%':
            self.percent()


if __name__ == "__main__":
    root = tk.Tk()
    app = CalculatorGUI(root)
    root.mainloop()