// 1789 + 987
public class Solution {
    public String addStrings(String nums1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int sum = 0;
        int i=nums1.length()-1, j=num2.length()-1;
        for(;i>=0&&j>=0;--i,--j) {
            sum = nums1.charAt(i)-'0'+num2.charAt(j)-'0'+carry;
            result.append(sum%10);
            carry = sum / 10;
        }
        while(i>=0){
            sum = nums1.charAt(i)-'0'+carry;
            result.append(sum%10);
            carry = sum / 10;
            i--;
        }
        while(j>=0){
            sum = num2.charAt(j)-'0'+carry;
            result.append(sum%10);
            carry = sum / 10;
            j--;
        }
        if(carry>0){
            result.append(carry);
        }
        return result.reverse().toString();
    }
}

