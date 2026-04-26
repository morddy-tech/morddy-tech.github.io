import java.util.ArrayList;

public class StockAnalysis {

    // Method to calculate average stock price from an array
    public static double calculateAveragePrice(int[] stockPrices) {
        int sum = 0;
        for (int price : stockPrices) {
            sum += price;
        }
        return (double) sum / stockPrices.length;
    }

    // Method to calculate average stock price from an ArrayList
    public static double calculateAveragePrice(ArrayList<Integer> stockPrices) {
        int sum = 0;
        for (int price : stockPrices) {
            sum += price;
        }
        return (double) sum / stockPrices.size();
    }

    // Method to find maximum stock price in an array
    public static int findMaximumPrice(int[] stockPrices) {
        int max = stockPrices[0];
        for (int price : stockPrices) {
            if (price > max) {
                max = price;
            }
        }
        return max;
    }

    // Method to find maximum stock price in an ArrayList
    public static int findMaximumPrice(ArrayList<Integer> stockPrices) {
        int max = stockPrices.get(0);
        for (int price : stockPrices) {
            if (price > max) {
                max = price;
            }
        }
        return max;
    }

    // Method to count occurrences of a specific price in an array
    public static int countOccurrences(int[] stockPrices, int targetPrice) {
        int count = 0;
        for (int price : stockPrices) {
            if (price == targetPrice) {
                count++;
            }
        }
        return count;
    }

    // Method to compute cumulative sum of stock prices in an ArrayList
    public static ArrayList<Integer> computeCumulativeSum(ArrayList<Integer> stockPrices) {
        ArrayList<Integer> cumulativeSum = new ArrayList<>();
        int sum = 0;
        for (int price : stockPrices) {
            sum += price;
            cumulativeSum.add(sum);
        }
        return cumulativeSum;
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        int[] stockArray = { 120, 130, 125, 140, 135, 145, 150, 155, 160, 165 };
        ArrayList<Integer> stockList = new ArrayList<>();
        for (int price : stockArray) {
            stockList.add(price);
        }

        // Calculate average price
        System.out.println("\nAverage Price (Array): " + calculateAveragePrice(stockArray));
        System.out.println("Average Price (ArrayList): " + calculateAveragePrice(stockList));

        // Find maximum price
        System.out.println("\nMaximum Price (Array): " + findMaximumPrice(stockArray));
        System.out.println("Maximum Price (ArrayList): " + findMaximumPrice(stockList));

        // Count occurrences
        int targetPrice = 140;
        System.out.println("\nOccurrences of " + targetPrice + ": " + countOccurrences(stockArray, targetPrice));

        // Compute cumulative sum
        ArrayList<Integer> cumulative = computeCumulativeSum(stockList);
        System.out.println("\nCumulative Sum (ArrayList): " + cumulative + "\n");
    }
}