# Commodity Profit Analysis System

A robust Java application designed to analyze annual profit and loss data for various commodities. This system processes raw data from text files and provides comprehensive financial insights through multi-dimensional data analysis.

## 📊 Overview
This project simulates a financial analysis tool that tracks 5 key commodities:
- **Gold**, **Oil**, **Silver**, **Wheat**, and **Copper**.

It processes 12 months of data, assuming a standard 28-day financial month, to calculate performance metrics, risk factors, and market trends.

## 🚀 Key Features
The system includes 10 specialized analysis methods:
1.  **Monthly Leader**: Find the most profitable commodity in any given month.
2.  **Daily Totals**: Calculate the combined profit of all commodities for a specific day.
3.  **Range Analysis**: Sum profits for a commodity over a specific day range across the whole year.
4.  **Peak Performance**: Identify the "Best Day" of any month based on total profit.
5.  **Historical Best**: Determine which month was most successful for a specific commodity.
6.  **Loss Streak Tracking**: Calculate the longest consecutive run of days with negative profit.
7.  **Performance Thresholds**: Count how many days a commodity stayed above a target profit.
8.  **Volatility Analysis**: Measure the "Biggest Daily Swing" (change between consecutive days).
9.  **Direct Comparison**: Compare two commodities to see which performed better annually.
10. **Weekly Breakdown**: Identify the most profitable week (7-day block) within a month.

## 🛠 Technical Details
- **Architecture**: Built using a 3D Array structure `profitData[12][28][5]` for $O(1)$ access time to specific data points.
- **Data Loading**: Implements a robust `loadData()` method that reads from the `Data_Files/` directory.
- **File Parsing**: Uses `java.util.Scanner` to parse CSV-style data from `.txt` files.
- **Error Handling**: Includes validation for months, days, and commodity names to prevent runtime crashes.

## 📂 Project Structure
```text
project/
├── Main.java           # Core logic and analysis methods
└── Data_Files/         # Folder containing January.txt ... December.txt
```

## 💻 How to Run
1. Clone the repository.
2. Ensure you have a `Data_Files` folder with the necessary `.txt` files.
3. Compile the project:
   ```bash
   javac Main.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## 📝 Data Format
The input files should follow this format:
`Day, Commodity, Profit`
Example: `1, Gold, 500`
