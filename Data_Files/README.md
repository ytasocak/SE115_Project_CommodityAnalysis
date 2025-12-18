# Data Files Directory

This directory contains the raw datasets required for the **Commodity Profit Analysis System**. The application processes these files to generate financial reports and performance metrics. All the data here is completely fabricated and not intended to describe the situation.

## 📂 Contents
The folder contains 12 individual `.txt` files, one for each month of the year:
- `January.txt`, `February.txt`, `March.txt`, ..., `December.txt`

## 📝 File Format
Each file follows a structured CSV-like format. The system is programmed to **skip the first line** (header) and read the subsequent data rows.

### **Data Columns:**
1. **Day**: Integer (1 to 28).
2. **Commodity**: String name of the asset. Supported values are:
   - `Gold`
   - `Oil`
   - `Silver`
   - `Wheat`
   - `Copper`
3. **Profit**: Integer value representing the daily profit (positive) or loss (negative).

### **Example Data Structure:**
```csv
Day, Commodity, Profit
1, Gold, 2500
1, Oil, -1200
1, Silver, 450
...
28, Copper, 800
```

## ⚠️ Requirements for Data Integrity
- **File Naming**: File names are case-sensitive and must match the month names exactly (e.g., `January.txt`).
- **Commodity Names**: Commodity names must be exactly as listed above (e.g., "Gold" is valid, "gold" or "GOLD" may not be recognized).
- **Encoding**: Files should be saved in standard UTF-8 format to prevent character parsing issues.
- **Missing Data**: If a day or commodity is missing from a file, the system will default its profit to `0` for that specific entry.
