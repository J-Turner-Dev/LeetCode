# LeetCode - 242. Valid Anagram

## Problem Description:

Given two strings s and t, return true if t is an of s, and false otherwise.


Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false


Constraints:

    1 <= s.length, t.length <= 5 * 104
    s and t consist of lowercase English letters.


Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

## Walkthrough and Solution

The problem behind this is that you have two strings to traverse one letter at a time and find out if those two strings contain the same letters with the same frequencies. I would initially step through each letter of the first string and add an integer value associated with that character. Once you have the count of every letter you will then loop through the second string and subtract the for each letter found. Once you have finished that you should be able to look through the character counts and if any of those counts is not zero then you know you have an anagram.  There is one base condition to start, if the strings are not of equal length then it is automatically can be conclude to not be an anagram. I decided to use a HashMap as it allows you to use the character as a key and an integer as the count. It will need to retreive the value if it exists and add 1 to it for the first string. The second string it will retrieve the letter and subtract one. If the Hashmap does not contain that letter then it will set the value as negative one. While writing the solution I thought that I could just remove a key value mapping from the Hashmap when it reaches zero and then at the end if the size of the Hashmap is 0 then all the mappings have been removed and it is an Anagram.

This solution was very poor as it only beats 9.21% of the solution. After looking at it I realized that I used a lot of branching and two loops through the length of the strings. The loop could be reduced to just one loop of the length of the string and one loop through the length of the characters used which if standard letters are used then you will only need to traverse 26 elements. Also using a series of branches and operation to check if statements makes it more slow than necessary. There had to be a better way to add to the existing value rather than retrieving the value and then adding to it and replacing it. Looking into Hashmaps they had two methods.  The merge method will check the value, if it is not in the Hashmap it adds the value, if it is then it performs an operation on the old value with the new value. This approach turns out to be faster but it seems using a HashMap is still not the optimal solution.

On a side note to answer the follow up question about Unicode characters, using a Hashmap is the way to go. Using the string method .codePoints() allows the unicode character to retain its value when you enter it into the Hashmap and it seems to run faster than using the character as a key.

The optimal solution which only works for anagrams made of letters of the alphabet.  It uses an array and a trick to get the integer value of character between 0 and 26. The character 'a' has an integer value of 97.  Subtracting a from any other character gets you a number, 'a' - 'a' = 97 - 97 = 0, 'b' - 'a' = 98 - 97 = 1, etc. We do the same thing as before but just increment or decrement the value in each element, then loop through the array and check to see if any value is not 0. Side note Integer arrays initialize with all zeros.  I used the string method .charAt(i) to get the character.  It turns out if you convert the string to a character array and just access the element instead of using that method it makes it one second faster. The end result was that it was faster than 81.66% of submissions.

The java compiler seems to find certain syntax to be easier to optimize and the fastest results find those features.