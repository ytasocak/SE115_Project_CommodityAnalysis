import java.io.*;
import java.util.*;

// Main.java — Implemented methods 5-7

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = { "Gold", "Oil", "Silver", "Wheat", "Copper" };
    static String[] months = { "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" };

    static int[][][] profitData = new int[MONTHS][DAYS][COMMS];

    // ========== LOAD DATA ==========
    public static void loadData() {
        String folderPath = "Data_Files/";

        for (int m = 0; m < MONTHS; m++) {
            String fileName = folderPath + months[m] + ".txt";

            try {
                Scanner reader = new Scanner(new File(fileName));
                reader.nextLine();

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();

                    if (line.trim().isEmpty())
                        continue;

                    String[] parts = line.split(",");
                    if (parts.length != 3)
                        continue;

                    int day = Integer.parseInt(parts[0].trim()) - 1;
                    String commodity = parts[1].trim();
                    int profit = Integer.parseInt(parts[2].trim());

                    int commIndex = -1;
                    for (int c = 0; c < COMMS; c++) {
                        if (commodities[c].equals(commodity)) {
                            commIndex = c;
                            break;
                        }
                    }

                    if (commIndex != -1 && day >= 0 && day < DAYS) {
                        profitData[m][day][commIndex] = profit;
                    }
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error reading file: " + fileName);
            }
        }
    }

    // ========== METHOD 1 ==========
    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month >= MONTHS)
            return "INVALID_MONTH";

        int[] totals = new int[COMMS];

        for (int d = 0; d < DAYS; d++) {
            for (int c = 0; c < COMMS; c++) {
                totals[c] += profitData[month][d][c];
            }
        }

        int maxIndex = 0;
        for (int c = 1; c < COMMS; c++) {
            if (totals[c] > totals[maxIndex])
                maxIndex = c;
        }

        return commodities[maxIndex] + " " + totals[maxIndex];
    }

    // ========== METHOD 2 ==========
    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS)
            return -99999;

        int total = 0;

        for (int c = 0; c < COMMS; c++) {
            total += profitData[month][day - 1][c];
        }
        return total;
    }

    // ========== METHOD 3 ==========
    public static int commodityProfitInRange(String commodity, int from, int to) {
        int commIndex = -1;
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(commodity)) {
                commIndex = c;
                break;
            }
        }

        if (commIndex == -1 || from < 1 || from > DAYS || to < 1 || to > DAYS || from > to) {
            return -99999;
        }

        int total = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = from - 1; d < to; d++) {
                total += profitData[m][d][commIndex];
            }
        }
        return total;
    }

    // ========== METHOD 4 ==========
    public static int bestDayOfMonth(int month) {
        if (month < 0 || month >= MONTHS)
            return -1;

        int maxDay = 0;
        int maxProfit = Integer.MIN_VALUE;

        for (int d = 0; d < DAYS; d++) {
            int dayTotal = 0;
            for (int c = 0; c < COMMS; c++) {
                dayTotal += profitData[month][d][c];
            }
            if (dayTotal > maxProfit) {
                maxProfit = dayTotal;
                maxDay = d + 1;
            }
        }
        return maxDay;
    }

    // ========== METHOD 5 ==========
    public static String bestMonthForCommodity(String comm) {
        int commIndex = -1;
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(comm)) {
                commIndex = c;
                break;
            }
        }

        if (commIndex == -1)
            return "INVALID_COMMODITY";

        int maxMonth = 0;
        int maxProfit = Integer.MIN_VALUE;

        for (int m = 0; m < MONTHS; m++) {
            int monthTotal = 0;
            for (int d = 0; d < DAYS; d++) {
                monthTotal += profitData[m][d][commIndex];
            }
            if (monthTotal > maxProfit) {
                maxProfit = monthTotal;
                maxMonth = m;
            }
        }
        return months[maxMonth];
    }

    // ========== METHOD 6 ==========
    public static int consecutiveLossDays(String comm) {
        int commIndex = -1;
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(comm)) {
                commIndex = c;
                break;
            }
        }

        if (commIndex == -1)
            return -1;

        int maxStreak = 0;
        int currentStreak = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS; d++) {
                if (profitData[m][d][commIndex] < 0) {
                    currentStreak++;
                    if (currentStreak > maxStreak)
                        maxStreak = currentStreak;
                } else {
                    currentStreak = 0;
                }
            }
        }
        return maxStreak;
    }

    // ========== METHOD 7 ==========
    public static int daysAboveThreshold(String comm, int threshold) {
        int commIndex = -1;
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(comm)) {
                commIndex = c;
                break;
            }
        }

        if (commIndex == -1)
            return -1;

        int count = 0;

        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS; d++) {
                if (profitData[m][d][commIndex] > threshold)
                    count++;
            }
        }
        return count;
    }

    // ========== METHODS 8-10 (not implemented yet) ==========

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded");
    }
}
