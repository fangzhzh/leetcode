public clas KMP {
	int[] b;
	String str, pattern;
	void kmpPreprocess() { // call this before calling kmpSearch
		int i = 0; j = -1; 
		b[0] = -1;
		while(i < pattern.length()) { // pre-process the pattern
			while( j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = b[j]; // different, reset j using b
			}
			i++; // if same, advance both pointers
			j++;
			b[i] = j; // observe i = 8,9,10,11,12,3 with j = 0, 1, 2, 3, 4, 5
					  // in the example of P="SEVENTY SEVEN above"
		}
	}

	void mkpSearch() { // this is similar as kmpPreprocess(), but on str
		int i = 0, j = 0;
		while(i < n){ // search throught String str
			while(j>=0 && str[i] != pattern[j]) j = b[j]; // different, reset j using b
			i++; // if same, advance both pointers
			j++;
			if(j == pattern.length()) { // a match found whtn j == m
				// found patter at index i-j
				j = b[j]; // prepare j for the next possible match
			}

		}
	}

}
