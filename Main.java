import java.io.*;
import java.util.*;

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

        // loop through each month file
        for (int m = 0; m < MONTHS; m++) {
            String fileName = folderPath + months[m] + ".txt";

            try {
                Scanner reader = new Scanner(new File(fileName));
                reader.nextLine();

                // read all lines from file
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

                    // find commodity index
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

    // ========== METHOD 1: Most Profitable Commodity In Month ==========
    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month >= MONTHS)
            return "INVALID_MONTH";

        int[] totals = new int[COMMS];

        // sum profit for each commodity
        for (int d = 0; d < DAYS; d++) {
            for (int c = 0; c < COMMS; c++) {
                totals[c] += profitData[month][d][c];
            }
        }

        // find max
        int maxIndex = 0;
        for (int c = 1; c < COMMS; c++) {
            if (totals[c] > totals[maxIndex])
                maxIndex = c;
        }

        return commodities[maxIndex] + " " + totals[maxIndex];
    }

    // ========== METHOD 2: Total Profit On Day ==========
    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS)
            return -99999;

        int total = 0;

        // sum all commodities
        for (int c = 0; c < COMMS; c++) {
            total += profitData[month][day - 1][c];
        }
        return total;
    }

    // ========== METHOD 3: Commodity Profit In Range ==========
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

        // sum across all months
        for (int m = 0; m < MONTHS; m++) {
            for (int d = from - 1; d < to; d++) {
                total += profitData[m][d][commIndex];
            }
        }
        return total;
    }

    // ========== METHOD 4: Best Day Of Month ==========
    public static int bestDayOfMonth(int month) {
        if (month < 0 || month >= MONTHS)
            return -1;

        int maxDay = 0;
        int maxProfit = Integer.MIN_VALUE;

        // check each day
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

    // ========== METHOD 5: Best Month For Commodity ==========
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

        // check each month
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

    // ========== METHOD 6: Consecutive Loss Days ==========
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

        // count consecutive loss days
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

    // ========== METHOD 7: Days Above Threshold ==========
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

        // count days above threshold
        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS; d++) {
                if (profitData[m][d][commIndex] > threshold)
                    count++;
            }
        }
        return count;
    }

    // ========== METHOD 8: Biggest Daily Swing ==========
    public static int biggestDailySwing(int month) {
        if (month < 0 || month >= MONTHS)
            return -99999;

        int maxSwing = 0;

        // compare consecutive days
        for (int d = 0; d < DAYS - 1; d++) {
            int day1Total = 0;
            int day2Total = 0;

            for (int c = 0; c < COMMS; c++) {
                day1Total += profitData[month][d][c];
                day2Total += profitData[month][d + 1][c];
            }

            int swing = Math.abs(day2Total - day1Total);
            if (swing > maxSwing)
                maxSwing = swing;
        }
        return maxSwing;
    }

    // ========== METHOD 9: Compare Two Commodities ==========
    public static String compareTwoCommodities(String c1, String c2) {
        int comm1Index = -1;
        int comm2Index = -1;

        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(c1))
                comm1Index = c;
            if (commodities[c].equals(c2))
                comm2Index = c;
        }

        if (comm1Index == -1 || comm2Index == -1)
            return "INVALID_COMMODITY";

        int total1 = 0;
        int total2 = 0;

        // calculate yearly totals
        for (int m = 0; m < MONTHS; m++) {
            for (int d = 0; d < DAYS; d++) {
                total1 += profitData[m][d][comm1Index];
                total2 += profitData[m][d][comm2Index];
            }
        }

        if (total1 > total2)
            return c1 + " is better by " + (total1 - total2);
        else if (total2 > total1)
            return c2 + " is better by " + (total2 - total1);
        else
            return "Equal";
    }

    // ========== METHOD 10: Best Week Of Month ==========
    public static String bestWeekOfMonth(int month) {
        if (month < 0 || month >= MONTHS)
            return "INVALID_MONTH";

        int[] weekTotals = new int[4];

        // calculate profit for each week
        for (int week = 0; week < 4; week++) {
            int startDay = week * 7;
            int endDay = startDay + 7;

            for (int d = startDay; d < endDay && d < DAYS; d++) {
                for (int c = 0; c < COMMS; c++) {
                    weekTotals[week] += profitData[month][d][c];
                }
            }
        }

        // find best week
        int maxWeek = 0;
        for (int w = 1; w < 4; w++) {
            if (weekTotals[w] > weekTotals[maxWeek])
                maxWeek = w;
        }

        return "Week " + (maxWeek + 1);
    }

    // ========== MAIN ==========
    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded");
    }
}
