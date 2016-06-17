class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        vector<int> v = nums1;
        v.insert(v.begin(), nums2.begin(), nums2.end());
        sort(v.begin(), v.end());
        if(v.size() % 2 != 0){
            return v[(v.size()+1)/2-1];
        } else{
            int index = v.size()/2;
            return (v[index-1]+v[index])*1.0/2;
        }

        
    }
};
