/*

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

*/

// Attempt one, Initial walkthrough with brute force 

class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> count = new HashMap<>();

        if (s.length() != t.length()) {
            return false;
        } else {
            for(int i = 0; i < s.length(); i++) {
                if (count.containsKey(s.charAt(i))) {
                    count.put(s.charAt(i), count.get(s.charAt(i)) + 1);
                } else {
                    count.put(s.charAt(i), 1);
                }                
            }
            for(int j = 0; j < t.length(); j++) {
                if (count.containsKey(t.charAt(j))) {
                    if (count.get(t.charAt(j)) - 1 == 0) {
                        count.remove(t.charAt(j));
                    } else {
                        count.put(t.charAt(j), count.get(t.charAt(j)) - 1);
                    }
                } else {
                    return false;
                }       
            }           
        }
        if (count.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}

// Second attempt making the previous syntax more concise and using HashMap methods instead of logic
// This only made it slightly faster as I think the methods only do what the logic above does
// combine the two passes with one instead made it slightly faster

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            count.merge(s.charAt(i), 1, Integer::sum);
            count.merge(t.charAt(i), -1, Integer::sum);
        }

        return count.values().stream().allMatch(v -> v == 0);
    }
}

// Third attempt using a method I read on the internet
// This method used an array instead of a HashMap 
// This also uses a trick where you take the character and subtract an a from it
// This results in an integer between 0 to 25, 'b'-'a' = 98 - 97 = 1 etc.

class Solution {
public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) return false;
        }

        return true;
    }
}

// This one is answering what if there are unicode questions
// This ends up being slower and using my HashMap idea as the array depends on the letter trick
// codePoints() method retains the unicode value then uses that value in the HashMap

public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;

    Map<Integer, Integer> count = new HashMap<>();

    s.codePoints().forEach(cp -> count.merge(cp, 1, Integer::sum));
    t.codePoints().forEach(cp -> count.merge(cp, -1, Integer::sum));

    for (int value : count.values()) {
        if (value != 0) return false;
    }

    return true;
}

// This is the fastest solution I could come up with
// this uses a character array for each string
// this ends up being slightly faster than String.charAt(i) method

class Solution {
public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26];
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            count[sa[i] - 'a']++;
            count[ta[i] - 'a']--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) return false;
        }

        return true;
    }
}