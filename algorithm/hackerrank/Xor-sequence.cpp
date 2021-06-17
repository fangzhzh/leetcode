#include <map>
#include <set>
#include <list>
#include <cmath>
#include <ctime>
#include <deque>
#include <queue>
#include <stack>
#include <string>
#include <bitset>
#include <cstdio>
#include <limits>
#include <vector>
#include <climits>
#include <cstring>
#include <cstdlib>
#include <fstream>
#include <numeric>
#include <sstream>
#include <iostream>
#include <algorithm>
#include <unordered_map>

using namespace std;


int main(){
   int Q;
    cin >> Q;
    for(int a0 = 0; a0 < Q; a0++){
        long L;
        long R;
        cin >> L >> R;
		cout << a0 << endl;
        int n = (L+1)/4;
        int min = n*4-1;
        int A[R-min+1];
		A[0]=0;
        for(int i=1; i<=R-min+1; ++i){
            A[i] = A[i-1]^(i+min);
        }
        int result = A[L-min];
        if(L==R){
		  cout << result << endl;
          continue;          
        }
		for(int i = L-min+1; i <= R-min; ++i) {
			result ^= A[i];
		}
		cout << result << endl;
    }
    return 0;
}

