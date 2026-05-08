# 📈 Stock Analysis Tool – README

This project provides a **Java‑based stock analysis utility** that calculates key metrics from daily stock prices. It demonstrates method overloading, collection handling, error handling, and basic statistical computations.

## ✨ Features

- **Average price** (supports `int[]` and `ArrayList<Integer>`)
- **Maximum & minimum price**
- **Occurrence count** of a specific price
- **Cumulative sum** over time
- **Standard deviation** (volatility measure)
- **Trend detection** – checks if prices are strictly increasing
- **3‑day simple moving average** (SMA)
- **Interactive console input** – user enters prices manually, with a fallback sample dataset

## 🛠️ Requirements

- Java JDK 11 or higher
- Terminal / command prompt (or any Java IDE)

## 📥 Installation & Setup

1. **Clone or download** this `stockAnalysis/` folder.
2. Ensure the file `StockAnalysis.java` is present.

## 🚀 How to Run

Open a terminal in the `stockAnalysis/` directory and run:

```bash
javac StockAnalysis.java
java StockAnalysis
```

## 📝 Usage

- When prompted, enter daily stock prices one by one (integers only).
- Type `done` when finished.
- If you enter no prices, the program will use a sample dataset:  
  `{120, 130, 125, 140, 135, 145, 150, 155, 160, 165}`.

### Sample interaction

```
=== Stock Analysis Tool ===
Enter daily stock prices (integers). Type 'done' when finished:
Price: 100
Price: 105
Price: 102
Price: 110
Price: done

--- Analysis Results ---
Average price: 104.25
Maximum price: 110
Minimum price: 100
Standard deviation (volatility): 3.64
Cumulative sums: [100, 205, 307, 417]
Strictly increasing trend? false
3‑day moving averages: [102.33333333333333, 105.66666666666667]
```

## 🔧 Method Reference

| Method | Description |
|--------|-------------|
| `calculateAveragePrice(int[] / ArrayList<Integer>)` | Arithmetic mean |
| `findMaximumPrice(...)` | Highest price |
| `findMinimumPrice(...)` | Lowest price |
| `countOccurrences(int[], int)` | Frequency of a target price |
| `computeCumulativeSum(ArrayList<Integer>)` | Running total |
| `calculateStandardDeviation(ArrayList<Integer>)` | Population standard deviation |
| `isTrendIncreasing(ArrayList<Integer>)` | Strictly rising? |
| `calculateMovingAverage(ArrayList<Integer>)` | 3‑period SMA |

All methods include **null/empty checks** and throw `IllegalArgumentException` when appropriate.

## ⚠️ Error Handling

- Invalid inputs (non‑integers) are caught and reprompted.
- Empty or `null` collections are handled gracefully (exceptions thrown with clear messages).
- The main method uses try‑catch to prevent unexpected crashes.

## 📂 Project Structure

```
stockAnalysis/
├── StockAnalysis.java      # main class with all methods
└── README.md               
```

## 🤝 Extending the Tool

You can easily add more indicators, e.g.:

- Exponential moving average (EMA)
- Relative Strength Index (RSI)
- Read prices from a CSV file
- Plot charts using a library like JFreeChart

## 📄 License

This code is provided for educational purposes. Use and modify freely.

---

*For the parent folder `T3-Java` containing other projects, see the main README.*