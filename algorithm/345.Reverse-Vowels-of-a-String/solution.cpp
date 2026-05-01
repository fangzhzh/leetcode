#include<iostream>
#include<string>
using namespace std;
class Solution {
public:
    string reverseVowels(string s) {
	    int len = s.length();	
        int pivot = len -1;
		int i = 0;
		int j = len-1;
		for(; i < len; ++i){
            if(!isVowel(s[i])){
                continue;
            }
            if(i >= pivot
				|| i >= j){
                return s;
            }
            for(; j > i; j--){
                if(!isVowel(s[j])){
                    continue;
                }
                if(j<pivot){
                    pivot = j;
                }
				if(s[i] != s[j]){
					char c = s[i];
					s[i]=s[j];
					s[j]=c;
				}
				j--;
				break;
            }
		}
		return s;
    }

	bool isVowel(char c){
		if(c=='a'
            ||c=='e'
            ||c=='i'
            ||c=='o'
            ||c=='u'
            ||c=='A'
            ||c=='E'
            ||c=='I'
            ||c=='O'
            ||c=='U') {
            return true;
        }
        return false;
	}
};

