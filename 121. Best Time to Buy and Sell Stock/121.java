/*

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 

Constraints:

    1 <= prices.length <= 105
    0 <= prices[i] <= 104

*/

// Attempt one after several failed attempts of trying to track the most profit
// with two variables keeping count and one pass in a loop I decided to do the brute force method
// This ultimately exceeded the LeetCode time limits because it wasn't fast enough

class Solution {
    public int maxProfit(int[] prices) {
        int lastElement = prices.length - 1;
        int profit = 0;

        for (int i = 0; i <= lastElement; i++) {
            for (int j = i + 1; j <= lastElement; j++) {
                int checkProfit = prices[j] - prices[i];
                if (checkProfit > profit) {
                    profit = checkProfit;
                }
            }
        }
        return profit;
    }
}

// Second attempt I'm going back through my original idea with one loop and using variables
// the variables keep track of the lowest element and keeps track of the profit from that element
// if it finds another lower element it tracks the profit for that but only updates if the profit
// becomes larger than the previous sequence

class Solution {
    public int maxProfit(int[] prices) {
        int buyElement = 0;
        int profit = 0;
        int limit = prices.length;

        for (int i = 1; i < limit; i++) {
            if (prices[i] < prices[buyElement]) {
                buyElement = i;
            }
            if (prices[i] - prices[buyElement] > profit) {
                profit = prices[i] - prices[buyElement];
            }
        }
        return profit;
    }
}

// I couldn't get this solution to run faster than 2ms but there were many that were 1ms
// I looked into why and the two if statments may be slowing it down
// Math.max() removes the need for the second if statement and is branchless after JIT optimization

class Solution {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];

            if (price < buy) buy = price;
            profit = Math.max(profit, price - buy);
        }
        return profit;
    }
}

// This one outperforms the previous solution by 1ms
// It starts with the max integer value possible instead of zero
// Also uses Math.min() instead of an if statement same reasoning as above

class Solution {
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            buy = Math.min(buy, price);
            profit = Math.max(profit, price - buy);
        }
        return profit;
    }
}

// Here is a bizarre branchless one that AI built
// it uses some bit tricks but seems to be slower not faster

public int maxProfit(int[] prices) {
    int min = prices[0];
    int profit = 0;

    for (int i = 1; i < prices.length; i++) {
        int price = prices[i];

        // Branchless min update
        int diffMin = price - min;
        min += (diffMin & (diffMin >> 31));

        // Branchless max update
        int diffProfit = price - min - profit;
        profit += (diffProfit & ~(diffProfit >> 31));
    }

    return profit;
}

