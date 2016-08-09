/**
379. Design Phone Directory  Please login first.  My Submissions 											
Total Accepted: 625
Total Submissions: 2429
Difficulty: Medium
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
*/
#include <iostream>
#include <stdio.h>
#include <vector>
using namespace std;

class PhoneDirectory {
	public:
		int maxNumbers;
		vector<bool> flags;
	public:
		PhoneDirectory(int maxNumbers):flags(maxNumbers, 1) {
			this->maxNumbers = maxNumbers;	
		}

		int get() {
			for(int i = 0; i < maxNumbers; ++i){
				if(check(i)){
					switchOff(i);
					return i;
				}
			}
			return -1;
		}

		bool check(int number) {
			if(number >= maxNumbers){
				return false;
			}
			return flags[number]==1;
		}


		void release(int number) {
			turnOn(number);
		}
	private:
		void turnOn(int index){
			if(index >= maxNumbers){
				return;
			}
			flags[index] = 1;
		}
		void switchOff(int index){
			if(index >= maxNumbers){
				return;
			}
			flags[index] = 0;
		}
};

