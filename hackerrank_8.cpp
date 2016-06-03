#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_map>
using namespace std;


int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    unordered_map<string, int> map;
    int n;
    cin >> n ;
    string name;
    int value;
    while(n-- > 0){
        cin >> name >> value;
        map.insert(make_pair(name, value));
    }
    string query;
    while(cin >> query ){
        std::unordered_map<string,int >::const_iterator got = map.find(query);
        if(got != map.end()){
            cout << query << "=" << got->second << endl;
        } else {
            cout << "Not found" << endl;
        }
    }
    
    return 0;
}

