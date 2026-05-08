import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A professional stock analysis utility providing common metrics:
 * average, max/min, occurrence count, cumulative sum, standard deviation,
 * trend detection, and moving average.
 */
public class StockAnalysis {

    // ---------- Average ----------
    /**
     * Calculates the average stock price from an array.
     * @param stockPrices array of prices (non‑null, non‑empty)
     * @return average as double, or 0.0 if empty/null
     * @throws IllegalArgumentException if array is null or empty
     */
    public static double calculateAveragePrice(int[] stockPrices) {
        if (stockPrices == null || stockPrices.length == 0) {
            throw new IllegalArgumentException("Stock prices array cannot be null or empty");
        }
        long sum = 0;
        for (int price : stockPrices) {
            sum += price;
        }
        return (double) sum / stockPrices.length;
    }

    /**
     * Calculates the average stock price from an ArrayList.
     * @param stockPrices list of prices (non‑null, non‑empty)
     * @return average as double
     * @throws IllegalArgumentException if list is null or empty
     */
    public static double calculateAveragePrice(ArrayList<Integer> stockPrices) {
        if (stockPrices == null || stockPrices.isEmpty()) {
            throw new IllegalArgumentException("Stock prices list cannot be null or empty");
        }
        long sum = 0;
        for (int price : stockPrices) {
            sum += price;
        }
        return (double) sum / stockPrices.size();
    }

    // ---------- Maximum ----------
    public static int findMaximumPrice(int[] stockPrices) {
        if (stockPrices == null || stockPrices.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int max = stockPrices[0];
        for (int price : stockPrices) {
            if (price > max) max = price;
        }
        return max;
    }

    public static int findMaximumPrice(ArrayList<Integer> stockPrices) {
        if (stockPrices == null || stockPrices.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        int max = stockPrices.get(0);
        for (int price : stockPrices) {
            if (price > max) max = price;
        }
        return max;
    }

    // ---------- Minimum (new) ----------
    public static int findMinimumPrice(int[] stockPrices) {
        if (stockPrices == null || stockPrices.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int min = stockPrices[0];
        for (int price : stockPrices) {
            if (price < min) min = price;
        }
        return min;
    }

    public static int findMinimumPrice(ArrayList<Integer> stockPrices) {
        if (stockPrices == null || stockPrices.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }
        int min = stockPrices.get(0);
        for (int price : stockPrices) {
            if (price < min) min = price;
        }
        return min;
    }

    // ---------- Occurrence count ----------
    public static int countOccurrences(int[] stockPrices, int targetPrice) {
        if (stockPrices == null) return 0;
        int count = 0;
        for (int price : stockPrices) {
            if (price == targetPrice) count++;
        }
        return count;
    }

    // ---------- Cumulative sum ----------
    public static ArrayList<Integer> computeCumulativeSum(ArrayList<Integer> stockPrices) {
        if (stockPrices == null) return new ArrayList<>();
        ArrayList<Integer> cumulativeSum = new ArrayList<>();
        int sum = 0;
        for (int price : stockPrices) {
            sum += price;
            cumulativeSum.add(sum);
        }
        return cumulativeSum;
    }

    // ---------- Standard deviation (volatility) ----------
    /**
     * Calculates population standard deviation of stock prices.
     * @param stockPrices list of prices
     * @return standard deviation (0.0 if fewer than 2 elements)
     */
    public static double calculateStandardDeviation(ArrayList<Integer> stockPrices) {
        if (stockPrices == null || stockPrices.size() < 2) {
            return 0.0;
        }
        double mean = calculateAveragePrice(stockPrices);
        double sumSqDiff = 0.0;
        for (int price : stockPrices) {
            double diff = price - mean;
            sumSqDiff += diff * diff;
        }
        return Math.sqrt(sumSqDiff / stockPrices.size());
    }

    // ---------- Trend detection ----------
    /**
     * Checks if the price sequence is strictly increasing.
     * @return true if each day's price > previous day's price
     */
    public static boolean isTrendIncreasing(ArrayList<Integer> stockPrices) {
        if (stockPrices == null || stockPrices.size() < 2) return false;
        for (int i = 1; i < stockPrices.size(); i++) {
            if (stockPrices.get(i) <= stockPrices.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    // ---------- Simple Moving Average (window = 3) ----------
    /**
     * Computes a 3‑period simple moving average of the price list.
     * @return list of moving averages (size = original size - 2)
     */
    public static ArrayList<Double> calculateMovingAverage(ArrayList<Integer> stockPrices) {
        ArrayList<Double> movingAverages = new ArrayList<>();
        if (stockPrices == null || stockPrices.size() < 3) return movingAverages;
        for (int i = 2; i < stockPrices.size(); i++) {
            double avg = (stockPrices.get(i-2) + stockPrices.get(i-1) + stockPrices.get(i)) / 3.0;
            movingAverages.add(avg);
        }
        return movingAverages;
    }

    // ---------- Main with interactive input ----------
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Stock Analysis Tool ===");
            System.out.println("Enter daily stock prices (integers). Type 'done' when finished:");

            ArrayList<Integer> userPrices = new ArrayList<>();
            while (true) {
                System.out.print("Price: ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("done")) break;
                try {
                    int price = Integer.parseInt(input);
                    userPrices.add(price);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter an integer or 'done'.");
                }
            }

            // If no user input, use sample data
            if (userPrices.isEmpty()) {
                System.out.println("\nNo prices entered. Using sample data:");
                int[] sampleArray = {120, 130, 125, 140, 135, 145, 150, 155, 160, 165};
                for (int p : sampleArray) userPrices.add(p);
                System.out.println(userPrices);
            }

            // Perform analysis
            System.out.println("\n--- Analysis Results ---");
            try {
                System.out.printf("Average price: %.2f%n", calculateAveragePrice(userPrices));
                System.out.println("Maximum price: " + findMaximumPrice(userPrices));
                System.out.println("Minimum price: " + findMinimumPrice(userPrices));
                System.out.printf("Standard deviation (volatility): %.2f%n", calculateStandardDeviation(userPrices));
                System.out.println("Cumulative sums: " + computeCumulativeSum(userPrices));
                System.out.println("Strictly increasing trend? " + isTrendIncreasing(userPrices));
                System.out.println("3‑day moving averages: " + calculateMovingAverage(userPrices));
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}