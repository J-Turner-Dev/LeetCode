/*

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, 2 is written as II in Roman numeral, just two ones.puted together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9. 
    X can be placed before L (50) and C (100) to make 40 and 90. 
    C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer.


Example 1:

Input: s = 'III'
Output: 3
Explanation: III = 3.

Example 2:

Input: s = 'LVIII'
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 3:

Input: s = 'MCMXCIV'
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

 
Constraints:

    1 <= s.length <= 15
    s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
    It is guaranteed that s is a valid roman numeral in the range [1, 3999].

*/

// Attempt one - This was my basic idea written out straight forward
// I ran into several issues with this and it runs slow because of all the checks and logic

class Solution {
    public int romanToInt(String s) {
        String[] sArray = s.split('');
        int rnInt = 0;
        HashMap<String, Integer> rnDict = new HashMap<>();

        rnDict.put('I', 1);
        rnDict.put('V', 5);
        rnDict.put('X', 10);
        rnDict.put('L', 50);
        rnDict.put('C', 100);
        rnDict.put('D', 500);
        rnDict.put('M', 1000);
        rnDict.put('IV', 4);
        rnDict.put('IX', 9);
        rnDict.put('XL', 40);
        rnDict.put('XC', 90);
        rnDict.put('CD', 400);
        rnDict.put('CM', 900);

        for(int i = 0; i < sArray.length; i++) {
            int next = i+1;
            if (next < sArray.length && sArray[i].equals('I') && (sArray[next].equals('V') || sArray[next].equals('X'))) {
                rnInt += rnDict.get(sArray[i] + sArray[next]);
                i++;
            } else if (next < sArray.length && sArray[i].equals('X') && (sArray[next].equals('L') || sArray[next].equals('C'))) {
                rnInt += rnDict.get(sArray[i] + sArray[next]);
                i++;
            } else if (next < sArray.length && sArray[i].equals('C') && (sArray[next].equals('D') || sArray[next].equals('M'))) {
                rnInt += rnDict.get(sArray[i] + sArray[next]);
                i++;
            } else {
                rnInt += rnDict.get(sArray[i]);
            }

        }
        return rnInt;
    }
}

// I'm going to reiterate on the code above simplifying some of the syntax
// 

class Solution {
    public int romanToInt(String s) {
        int rnInt = 0;
        HashMap<Character, Integer> rnDict = new HashMap<>();

        rnDict.put('I', 1);
        rnDict.put('V', 5);
        rnDict.put('X', 10);
        rnDict.put('L', 50);
        rnDict.put('C', 100);
        rnDict.put('D', 500);
        rnDict.put('M', 1000);

        for(int i = 0; i < s.length(); i++) {
            int next = i+1;
            int value = rnDict.get(s.charAt(i));

            if (i < s.length() - 1 && value < rnDict.get(s.charAt(i+1))) {
                rnInt -= value;
            }
            else {
                rnInt += value;
            }
        }
        return rnInt;
    }
}

// Third attempt - This time doing the same as before but looping backwards 
// Going backwards does the same thing but makes it so you don't have to do lookups into the Dictionary
// Using the switch statement takes the place of using a HashMap
// This version also uses modern syntax with the arrow operator in the switch statement

class Solution {
    public int romanToInt(String s) {
        int rnInt = 0;
        int preValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int value = switch (s.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> 0;
            };

            if (value < preValue) {
                rnInt -= value;
            } else {
                rnInt += value;
            }

            preValue = value;
        }

        return rnInt;
    }
}