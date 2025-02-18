# Sliding Window Made Simple

## What is a Sliding Window?
Think of it like a video camera that can zoom in and out:
```
String: "ADOBECODEBANC"
        [A]              Initial window size 1
        [ADO]            Expanded to size 3
        [ADOB]           Expanded to size 4
           [DOB]         Slid right
           [DOBE]        Expanded
              [BEC]      Slid right
```

## Three Types of Window Movement

### 1. EXPAND (Growing the Window)
```
"ADOBECODEBANC"
[A] → [AD] → [ADO] → [ADOB]
```
When to use:
- Need more characters
- Current window doesn't satisfy condition
- Looking for a larger pattern

### 2. CONTRACT (Shrinking the Window)
```
"ADOBECODEBANC"
[ADOB] → [DOB] → [OB]
```
When to use:
- Window has extra characters
- Looking for minimum size
- Current window is too big

### 3. SLIDE (Moving the Window)
```
"ADOBECODEBANC"
[ADO] → [DOB] → [OBE]
```
When to use:
- Fixed size window
- Need to check all positions
- Like finding anagrams

## Template for Sliding Window

### Basic Structure
```java
// Initialize window bounds
int start = 0, end = 0;

// Initialize window state
Map<Character, Integer> window = new HashMap<>();

// Process string
while (end < s.length()) {
    // 1. Expand: Add character at end
    char c = s.charAt(end++);
    window.put(c, window.getOrDefault(c, 0) + 1);
    
    // 2. Contract: Remove characters from start if needed
    while (needToShrink()) {
        char d = s.charAt(start++);
        window.put(d, window.get(d) - 1);
    }
    
    // 3. Update answer if needed
    updateAnswer();
}
```

## Real World Examples

### 1. Minimum Window Substring (LC 76)
Find smallest window containing all target characters

```
S: "ADOBECODEBANC"
T: "ABC"

Step-by-step visualization:
1. [A]DOBECODEBANC     Found: A    Need: B,C
2. [ADOB]ECODEBANC     Found: A,B  Need: C
3. [ADOBEC]ODEBANC     Found all!  Shrink...
4. DOBEC[BANC]         Better!     Answer="BANC"

Key States:
- Need to find: Map<Char, Count> of T
- Current window: Map<Char, Count> of window
- Valid when: All chars in T are found
```

### 2. Find All Anagrams (LC 438)
Find all anagrams of pattern in string

```
S: "cbaebabacd"
P: "abc"

Fixed-size window (size = P.length()):
[cba] → valid!      Add index 0
b[bae] → not valid
ba[aeb] → not valid
bae[eba] → not valid
baeb[bac] → valid!  Add index 5
```

## Common Patterns to Remember

1. Growing Window (Minimum Window Substring)
   - Expand until valid
   - Contract to minimize
   - Track minimum size

2. Fixed Window (Find Anagrams)
   - Window size = pattern size
   - Slide one by one
   - Compare window state

3. Variable Window (Longest Substring)
   - Expand while valid
   - Contract when invalid
   - Track maximum size

## Tips for Solving Sliding Window Problems

1. Ask yourself:
   - What makes a window valid?
   - When should I expand?
   - When should I contract?

2. Choose your window state:
   - HashMap for character frequencies
   - Counter for conditions
   - Set for unique elements

3. Optimize the solution:
   - Use array instead of HashMap for small character sets
   - Track validity with counter instead of checking map
   - Update state incrementally instead of recalculating

Remember: Sliding Window turns many O(n²) substring problems into O(n) solutions by avoiding repeated work!
