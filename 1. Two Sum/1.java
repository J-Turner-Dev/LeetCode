/*

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

*/

// Attempt one is a simple brute force method that passes through all the elements for each element
// This means it loops at the most extreme case n * n times

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] output = new int[2];
        for (int i = 0; i <= nums.length -1; i++) {
            for (int j = 0; j <= nums.length -1; j++) {
                if (i != j) {
                    if (nums[j] + nums[i] == target) {
                        output[0] = j;
                        output[1] = i;
                    }
                }
            }            
        }
        return output;
    }
}

// Optimized attempt now passes through the initial array one time
// To do this, it stores values of each element in a Hashmap until the solution is found
// Big O of O(n)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mappedNums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int valueNeeded = target - nums[i];
            if (mappedNums.containsKey(valueNeeded)) {
                return new int[] {mappedNums.get(valueNeeded), i};
            } else {
                mappedNums.put(nums[i], i);
            }
        } return new int[] {};
    }
}

