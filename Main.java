// Main.java — Added profitData array and loadData implementation
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
            }
        }
    }

    // ======== 10 REQUIRED METHODS ========

    public static String mostProfitableCommodityInMonth(int month) {
        return "DUMMY";
    }

    public static int totalProfitOnDay(int month, int day) {
        return 1234;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        return 1234;
    }

    public static int bestDayOfMonth(int month) {
        return 1234;
    }

    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

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
