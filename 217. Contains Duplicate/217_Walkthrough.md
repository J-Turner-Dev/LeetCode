# LeetCode - 217. Contains Duplicate

## Problem Description:

Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

 
Example 1:

Input: nums = [1,2,3,1]

Output: true

Explanation:

The element 1 occurs at the indices 0 and 3.

Example 2:

Input: nums = [1,2,3,4]

Output: false

Explanation:

All elements are distinct.

Example 3:

Input: nums = [1,1,1,3,3,4,3,2,4,2]

Output: true


Constraints:

    1 <= nums.length <= 105
    -109 <= nums[i] <= 109

## Walkthrough and Solution

This problem takes as input an array of numbers and returns a boolean value.  The easiest approach would be to traverse the array one element at a time and marking each value into another data structure.  This data structure such as a dictionary, would be used to check the current array value against what is in the dictionary. If it doesn't find a value then it inserts it and moves on to the next element of the array. As soon as you check the value and you find it already exists in the dictionary you will return a false value. This will have an O(n).

I could not think of a more optimized solution to this problem as for a solution to output false, meaning there are no duplicates, every element of the integer array must be checked resulting in O(n) at minimum.  

My solution consisted of using a Hashmap to build the dictionary structure in Java. It then checked to see if the HashMap contained the key which was the value of the array element itself. This proved to be effect as it was faster than 45% of the submissions. I could not figure out what could be a more optimal solution. It turns out the solutions which were faster used a HashSet. The difference between a HashMap and a HashSet is that the HashSet contains unique values and does not require a Key and Value pair. It just uses the value as both Key and Value in one element. This is essentially what I was doing with the Hashmap. Without the two values needing inserted a Hashset essentially cuts the operation in half.

Using a HashSet does improve the speed of the algorithm as it is now faster than 88% of submissions. How this happens I am unsure. Reading about the differences shows that a Hash Set uses a Hash Map as a subclass. Many claim the HashMap to be faster. Becuase the problem requires unique values and not a key and value pair that has a possiblity of the same values, a HashSet seems to be the better choice for this problem.