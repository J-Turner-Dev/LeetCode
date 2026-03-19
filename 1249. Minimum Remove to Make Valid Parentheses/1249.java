/*
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

*/

// Attempt 1 Brute Force 
// This solution contains nests for loops and is not optimal
// O(n^2) time complexity

class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] charArray = s.toCharArray();
        String newString = "";
        Map<Integer, Integer> mappedNums = new HashMap<>();
        for (int i=0;i<=charArray.length-1;i++) {
            if (charArray[i] == '(') {
                for (int j=i+1;j<=charArray.length-1;j++) {
                    if(charArray[j] == ')' && !mappedNums.containsKey(j)) {
                        newString = newString + charArray[i];
                        mappedNums.put(j, i);
                        System.out.println(mappedNums.get(j));
                        break;
                    }
                }
            } else if (charArray[i] == ')' && mappedNums.containsKey(i)) {
                newString = newString + charArray[i];
            } else if (charArray[i] != ')' && charArray[i] != '(') {
                newString = newString + charArray[i];
            }
        }
        return newString;
    }
}

//Attempt 2 Using a stack to track parentheses
// This removes the nested for loops but contains two for loops
// O(2n) time complexity

class Solution {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> toRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();  // Matched with an opening parenthesis
                } else {
                    toRemove.add(i);  // Unmatched closing parenthesis
                }
            }
        }
        
        // Remaining items in stack are unmatched opening parentheses
        toRemove.addAll(stack);
        
        // Build result string excluding indices marked as to remove
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!toRemove.contains(i)) {
                result.append(s.charAt(i));
            }
        }       
        return result.toString();
    }
}