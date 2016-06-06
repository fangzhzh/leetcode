#include <unordered_map>
#include <string>
using namespace std;
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if(s.empty()) return 0;
        unordered_map<char, int> dict;
        int maxLen = 0;
        for(int i = 0, j=0; i < s.size(); ++i){
            if(dict.count(s[i])!=0){
                j = max(j, dict[s[i]]+1);
            }
            dict[s[i]]= i;
            maxLen = max(maxLen, i-j+1);
            
        }
        return maxLen;
    }
};
