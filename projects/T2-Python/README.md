# 🧮 Advanced Python Calculator Suite

A professional, secure, and feature‑rich calculator suite built with Python.  
Includes a **basic command‑line calculator** for learning and a **fully‑featured scientific GUI calculator** for daily use.

## 📦 Project Overview

| Version | Interface | Features |
|---------|-----------|----------|
| **Basic Calculator** | Command Line | Addition, subtraction, multiplication, division |
| **Advanced Calculator** | GUI (Tkinter) | Arithmetic, trigonometry, logarithms, exponents, roots, factorial, memory, history, constants (π, e), answer recall, keyboard support |

## ✨ Advanced Calculator Features (GUI)
- **Basic ops**: `+`, `-`, `*`, `/`, `%`
- **Scientific**: `sin`, `cos`, `tan` (degrees), `log₁₀`, `ln`, `log₂`
- **Exponents & roots**: `x²`, `x³`, `xʸ`, `√x`, `∛x`, `eˣ`, `10ˣ`
- **Utilities**: `1/x`, `x!`, `±`, backspace, clear, clear entry
- **Memory**: `M+`, `M-`, `MR`, `MC`
- **History**: Last 20 calculations displayed in a listbox
- **Constants**: `π`, `e`
- **Answer recall**: `ANS` (last result)
- **Keyboard support**: Numeric keys, operators, Enter for equals, Backspace, etc.
- **Security**: No `eval()` – all operations use safe function calls with input validation

## 🛠️ Tech Stack
- Python 3.8+
- Tkinter (built‑in GUI library)
- Math module
- PyInstaller (for creating standalone executables)

## 📁 Project Structure
calculator-project/
├── BasicCalc.py                # Basic CLI version
├── AdvancedCalcCLI.py          # Advanced CLI version
├── AdvancedCalcGUI.py          # Advanced GUI version (main)
└── requirements.txt      
README.md

> **Note**: The `advanced_calculator_cli.py` is the terminal‑based advanced calculator shown earlier. The main focus is the GUI version.


## 🚀 Installation & Running

### 1. Clone or download the project

bash
git clone https://github.com/morddy-tech/projects/T2-Python/calculator-project.git
cd calculator-project

### 2. Ensure you have Python 3.8+ installed

bash
python --version

### 3. Run the desired version

**Basic CLI calculator:**
bash
python basic_calculator.py

**Advanced GUI calculator:**
bash
python advanced_calculator_gui.py

> Tkinter is included with standard Python installations on Windows, macOS, and most Linux distributions. If missing on Linux, install with `sudo apt-get install python3-tk`.

## 📦 Building a Standalone Executable (Windows `.exe`)

You can share the advanced calculator as a single file that runs without Python.

1. **Install PyInstaller** in a virtual environment (recommended):
   ```bash
   python -m venv venv
   source venv/bin/activate   # or .\venv\Scripts\activate on Windows
   pip install pyinstaller
   ```

2. **Build the executable**:
   ```bash
   pyinstaller --onefile --windowed --name "AdvancedCalculator" advanced_calculator_gui.py
   ```

3. **Find the `.exe`** in the `dist/` folder. You can distribute this file directly.

> ⚠️ The executable is platform‑specific. Build it on Windows to share a `.exe`, on macOS for `.app`, etc.

## 🔒 Security & Professional Practices

| Concern | Implementation |
|---------|----------------|
| No arbitrary code execution | Every operation is a direct function call – `eval()` is never used |
| Input validation | All user inputs are parsed with `float()` inside try/except blocks |
| Domain error handling | Square root of negative, log ≤0, division by zero – all caught and shown as error dialogs |
| Clear error messages | Users are told exactly what went wrong |
| Maintainable code | Object‑oriented backend, separation of UI and business logic |
| History limit | Only last 20 operations stored to prevent memory bloat |

## 📸 Screenshots


## 🧪 Future Enhancements (Ideas)

- [ ] Plotting functions (graph of `y = f(x)`)
- [ ] Complex number support
- [ ] RPN (Reverse Polish Notation) mode
- [ ] Save/load history to a file
- [ ] Dark/Light theme toggle

## 📄 License

This project is open‑source and available under the [MIT License](LICENSE). Feel free to use, modify, and distribute.

## 👨‍💻 Author
**Ifedayo Matthew**  
[GitHub] https://github.com/morddy-tech/ • [Portfolio] Loading...

## 🙏 Acknowledgements

- Built with Python’s `tkinter` and `math` modules.
- Inspired by classic scientific calculators.