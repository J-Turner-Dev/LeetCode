# LeetCode - 1249. Minimum Remove to Make Valid Parentheses

## Problem Description:

Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

    It is the empty string, contains only lowercase characters, or
    It can be written as AB (A concatenated with B), where A and B are valid strings, or
    It can be written as (A), where A is a valid string.

Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Constraints:

    1 <= s.length <= 105
    s[i] is either '(' , ')', or lowercase English letter.

## Walkthrough and Solution

This solution requires that you remove any parentheses that don't have a matching opposite parentheses to make it a valid string. Initially I wanted a solution that would mark the parenthesis that were going to be removed in a hash map with the key being the index of the parenthesis. To find the parenthesis that will be removed I will convert the string into an array of characters then check each character one by one until I find an opening parenthesis. I will then start another loop to search through the rest of the array to find a closing parenthesis. If none is found then we remove that parentheses. If a closing parenthesis is found then it is also removed. The last part would be to then loop through the character array again building the string out with all the characters that are not in the hash map to be removed.

This solution is clearly not the optimal solution, but it is a good starting point. This solution has a Big O time complexity of O(n^2). Even though the inner loop gets shorter every time the n^2 is still the dominant term that describes the slope as n grows bigger.
