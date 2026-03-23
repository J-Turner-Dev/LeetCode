# LeetCode - 13. Roman to Integer

## Problem Description:

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9. 
    X can be placed before L (50) and C (100) to make 40 and 90. 
    C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer.


Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.

Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

 
Constraints:

    1 <= s.length <= 15
    s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
    It is guaranteed that s is a valid roman numeral in the range [1, 3999].


## Walkthrough and Solution

This problem looks as though it should be simple. I can store the roman numeral values in a dictionary data structure. Traverse through the Roman Numeral sequence and translate the letters to the numbers and then add all the numbers. I would first start by building the dictionary. Then I would create an integer value starting at zero, and with each Roman numeral translation add it to that integer. Whatever number is left at the end is what will be the value. There is only on caveat. That is you will need to look at the next element every time you reach one of these three letters, I, X, and C.  If the next letter is one the creates a non standard Roman numeral, then use the two letters put together as the key and skip an extra element during traversal.

My first attempt at this solution was one where I coded exactly as I had written above. The first thing that I stumbled on was complex syntax for the If statements.  I was trying to look at the element ahead. This runs into an issue at the last element as there is no element to look at after that. I needed to add a bound to avoid an out of bound error. The next problem was using the string and converting it to a charcter array, then trying to convert it back to a string for the combined roman numerals. Strings can not be used with the == compare operator as it does not compare the string literals. Instead using a String.equals("I") was needed. Also using an array[i++] was not a good idea in the if statement becuase the next time your use array[i++] it once again incremented it one more from the previous increment resulting in a two element look foward instead of just one element both times. This solution did have a time complexity of O(n) but it ran slower than it should with unnecessary HashMap look ups and if statment compares.

If you look at the way the two letter Roman numerals work.  The first Roman numeral is subtracted form the second. This means using the HashMap to look up double Roman Numerals isn't needed. Instead when you look at the element if the value of the next element is greater than you know you must subtract the value of the current element. My second iteration of the solution uses this instead using the HashMap to find the value of the current element and compare it to the value of the next. Subtracting when necessary and adding the rest. This sped up the solution some but there is another way to do this without even needing the Hashmap.

The third solutions started at the end of the roman Numeral string and worked backwards. This mean you could store the value of the current element and compare it to the element that was passed previously. Then all you need to do is translate the current roman Numeral to a value and compare to the previous if it is less than the previous subtract that value. The same as before but without the lookups into the Hashmap instead using a switch statement to find the current value and using the saved value from previous element to compare to. This solution is still O(n) but reduces the number of operations in each loop freeing up memory and time. This is the optimal solution.