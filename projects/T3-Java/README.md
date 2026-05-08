# 📁 T3-Java – Java Projects Collection

This folder contains four distinct Java applications developed as part of coursework or personal practice, demonstrating object-oriented programming, data structures, GUI development, and software design principles.


## 📦 Projects Overview

| # | Project Name | Description |
|---|--------------|-------------|
| 1 | **Library System** | Manages books, members, and borrowing operations (console‑based). |
| 2 | **Stock Analysis** | Fetches and analyzes stock market data (e.g., moving averages, volatility). |
| 3 | **Advanced Student Management System GUI** | Full graphical interface to manage student records, courses, and grades. |
| 4 | **Vehicle Information System** | OOP demo with interfaces for cars, motorcycles, and trucks; input validation and enums. |


## 🗂️ Folder Structure (Expected)

T3-Java/
├── LibrarySystem/
│   ├── LibrarySystem.java
│   └── README.md
├── StockAnalysis/
│   ├── StockAnalysis
│   └── README.md
├── studentManagementSystemGUI/
│   ├── src/
│   └── README.md
├── vehicleInfoSystem/
│   ├── com/vehicleInfoSystem/...
│   └── README.md
└── README.md

> **Note:** Each sub‑project may have its own detailed `README.md`. The instructions below are generic – please refer to sub‑folder READMEs for exact commands.


## 🚀 How to Run Each Project

### Prerequisites
- **Java JDK 11 or higher** (download from [Adoptium](https://adoptium.net/) or Oracle)
- **Git** (optional, for cloning)
- An IDE (IntelliJ IDEA, Eclipse, VS Code) or terminal with `javac` and `java`

### 1. Library System
```bash
cd LibrarySystem/
javac LibrarySystem.java
java LibrarySystem
```
*Typical features:* Add/remove books, search by title/author, track borrowed books.


### 2. Stock Analysis
```bash
cd stockAnalysis/src
javac StockAnalysis.java
java StockAnalysis
```
*May require an API key* (e.g., Alpha Vantage, Yahoo Finance). Set it via environment variable or config file.


### 3. Advanced Student Management System GUI
```bash
cd studentManagementSystemGUI/src
javac StudentManagementSystemGUI.java
java StudentManagementSystemGUI
```
*Requires Java Swing/JavaFX.* The GUI allows adding, editing, deleting students, and generating reports.


### 4. Vehicle Information System (Detailed)
This project is packaged as `com.vehicleInfoSystem`.

#### Compile (from the root folder `T3-Java/`)
```bash
javac vehicle-info-system/com/vehicleInfoSystem/models/*.java \
      vehicle-info-system/com/vehicleInfoSystem/models/enums/*.java \
      vehicle-info-system/com/vehicleInfoSystem/VehicleInformationSystem.java
```

#### Run
```bash
java -cp vehicle-info-system com.vehicleInfoSystem.VehicleInformationSystem
```

#### Sample Interaction
```
=== Vehicle Information System ===

--- Car Details ---
Make: Toyota
Model: Camry
Year: 2020
Number of Doors (2-6): 4
Fuel Type (Petrol/Diesel/Electric): Petrol
...
```

---

## 🔧 Build Automation (Optional)

For a more professional setup, each project can include a `build.xml` (Ant) or `pom.xml` (Maven). A simple `Makefile` could also be created to compile all projects at once.

---

## 🛡️ Security & Code Quality Notes

- **Input validation** is implemented in the Vehicle Information System (year bounds, enum validation, etc.).
- The GUI project avoids SQL injection by using `PreparedStatement` if a database is connected.
- Stock analysis handles API keys via environment variables (never hard‑coded).
- All projects follow Java naming conventions and include exception handling.

---

## 📝 Contribution & Customisation

Feel free to extend any project:
- Add a persistent database (H2, SQLite) to the Library or Student system.
- Connect the Stock Analysis to a live data stream.
- Enhance the Vehicle system with rental cost calculation.

---

## ❓ Troubleshooting

| Problem | Likely Solution |
|---------|----------------|
| `class not found` | Ensure you are running `java` from the correct directory and that the classpath includes the compiled `.class` files. |
| GUI doesn’t start | For JavaFX projects, add VM arguments: `--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls` |
| Stock API fails | Check your internet connection and API key validity. |

---

## 📜 License

This collection is provided for educational purposes. You may use and modify the code freely.

---

*For each project’s individual details (e.g., UML diagrams, test cases), see the sub‑folder README.*