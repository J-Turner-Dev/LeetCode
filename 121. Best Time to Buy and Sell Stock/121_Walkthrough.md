# LeetCode - 121. Best Time to Buy and Sell Stock 

## Problem Description:

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

## Walkthrough and Solution

This problem requires you to find the lowest value in the array and then the highest value after that and subtract the highest value from the lowest. One exception is when the lowest value is at the end or when the lowest value is succeeded by only higher values then it returns 0. My initial thoughts say that I should check the very last element to see if it is the lowest, but that doesn't necessarily work becuase you can't know if it is the lowest without knowing the rest of the values. I will try to traverse backwards through the array and have to variables highest and lowest. My idea of traversing backwards was correct but I couldn't make it work.  I used a value to track both the min and max. I scrapped that idea and did the most simple solution working from the beginning to the end using a double for loop. This solution would start with one element then loop through the rest of the elements and subtract the further element from the current element and if the result was bigger than the saved biggest result, up date the save biggest result with that one.  By the time you went through all loops you'd return the biggest profit. If there was no biggest profit such as the amount went down each day it would return its starting value 0.  This exceeded the Leet Code time limit.  This is why it needs to be optimized.

I needed to reduce it to one loop, this loop chose a value to be the starting buy value and then updated the buy value when it found the lowest.  Automatically using the first element as the buy and as you loop through each element starting with the second element you would check to see if that value was lower that the buy value if it is it became the new buy value. Then take that element value and subtract the buy value. If that value is bigger than the profit value which initializes at 0.  As it traverses it will record the greatest profit for each sequence of buy values until you find the most profit. This solution has a big O value of O(n).

The previous solution ran at 2 ms, but there are faster solutions.  I looked into what makes the other faster.  Using the method Math.max(), it is able to find the maximum from the saved profit and the new value of calculated profit. Supposedly this is optimized to be branchless in the Java Virtual Machine or some of the JIT compilers for java bytcode. This solution still ran at 2 ms even though the branches were removed to make it easier to interpret efficiently. The final version that was able to run at 1 ms used the Math.min to find the minimum value for the buy value and also initialized the buy value as being Integer.MAX_VALUE.  This gives the buy variable the largest value it could possibly have to start with.  This is a standard as using 0 sometimes will affect the result.  