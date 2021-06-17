#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;
string add(string lvalue, string rvalue){
	int i = 0;
	int keep = 0;
	string result;
	size_t size = 62;
	while(true){
		string ltmp = lvalue.substr(i*size+0, min(size, lvalue.size()));
		string rtmp = rvalue.substr(i*size+0, min(size, rvalue.size()));
		int lv = atoi(ltmp.c_str());
		int rv = atoi(rtmp.c_str());
		int sum = lv+rv + keep;
		keep = sum / pow(10, 62);
		result.insert(0, to_string(sum%(int)pow(10,62)));
		i++;
		if(i*size > lvalue.size() 
				|| i*size > rvalue.size()){
			break;
		}
	}
	if(i*size < lvalue.size()){
		lvalue[i*size+1] += keep;
		result.insert(0, lvalue.substr(i*size+1));
	}
	if(i*size < rvalue.size()){
		rvalue[i*size+1] += keep;
		result.insert(0, rvalue.substr(i*size+1));
	}

	return result;
}

string multiple(string value, int k){
	string result;
	int multip = 0;
	while(true){
		int first = k % 10;
		for(int i = 0; i  < value.size(); ++i){
 			cout << "sum = " << value[value.size()-1-i] << " * " << ((int)pow(10,i)) << " * " << first << "*" << ((int)pow(10, multip)) << endl;
			int sum = (value[value.size()-1-i]-'0')*((int)pow(10,i))*first*((int)pow(10, multip));
//			cout << "result " << result << " +" << sum << "=";
			result = add(result, to_string(sum));
			cout << result << endl;
		}
		if(k >0){
			k /= 10;
			multip++;
		} else{
			break;
		}
	}
	return result;
}
string factorials(int n){
	string value = "1";
	for(int i = 2; i <=n; ++i){
		string result = multiple(value, i);
		add(value, result);	
	}
	return value;
}

int main(){
	/*
    int n;
    cin >> n;
    vector<int> sum;
    if(n <= 1){
        cout << 1 << endl;
		return 0;
    }
	*/
	cout << "100+200=" << add("100", "200") << endl;
	cout << "1+2=" << add("1", "2") << endl;
	cout << "1+200=" << add("1", "200") << endl;
	cout << "10+20100=" << add("100", "20100") << endl;

	string str="24";
	int rvalue = 5;
	string result = multiple(str, 2131212121);
	cout << "24 *5=" << result << endl;
	rvalue = 12121212;
	result = multiple(str, rvalue);
	cout << "24 *" << rvalue << "="  << result << endl;
	
    cout << endl;
    return 0;
}
