# LeetCode - 1. Two Sum 

## Problem Description:

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.
 

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.

 
Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?

## Walkthrough and Solution

Given the array of integers the brute force method would be to loop through the array and one the current element, open another loop that checks the rest of the elements. Keeping in mind that the value of one element can not be counted twice. This means skip checking the current element. This solution is the easiest way to to get to a solution but this has a Big O of O(n^2). This solution proved to be very slow as it was close to last place in the ranking.

The optimal solution, I could see is to be able to only pass through the array only one time. If it does that, then at most the Big O would be O(n). As you go through the array one element at the time you could take current value in the element subtract that from the target value and then check if one the previous elements equals that value that is needed. If it doesn't contain that value then add the current element to a key value mapping, HashMap. At first I thought there may be a problem of having multiple elements the same number not being a unique value for the key, but after walking throught the problem you wouldn't ever store more than one of the same value. This is because you are only looking for two values and once you find the second value, then you can return the answer and no longer need to store the value. I'll use the value of the element as the key, and the element number as the value of the HashMap. This solution only passes through the array once until it finds an answer. To avoid adding duplicates key values into the HashMap, once the answer is found then it returns it and exits the loop. This solution is faster than 98% of most of the solutions.
