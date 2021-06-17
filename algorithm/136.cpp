class Solution {
public:
    int singleNumber(int A[], int n) {
                
            int c = 0;
            for(int i = 0; i < n; ++i){
                c = c ^ A[i];
            }
            return c;
        

    }
};
