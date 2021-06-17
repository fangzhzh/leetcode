#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
	int acY, acM, acD;
	int exY, exM, exD;
	cin >> acD >> acM >> acY;
	cin >> exD >> exM >> exY;

	if(acY < exY 
			|| (acY == exY && acM < exM )
			|| (acY == exY && acM == exM && acD < exD )){
		cout << 0;
		return 0;
	}
	cout << ((acY > exY) ? 10000
		: (acM > exM) ? (acM-exM)*500 
		: (acD > exD)? (acD-exD)*15
		: 0);

    return 0;
}

